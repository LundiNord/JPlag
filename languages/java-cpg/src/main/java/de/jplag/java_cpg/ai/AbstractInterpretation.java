package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.graph.Name;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.*;
import de.fraunhofer.aisec.cpg.graph.scopes.TryScope;
import de.fraunhofer.aisec.cpg.graph.statements.*;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.*;
import de.fraunhofer.aisec.cpg.graph.types.HasType;
import de.fraunhofer.aisec.cpg.graph.types.PointerType;
import de.fraunhofer.aisec.cpg.graph.types.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.numbers.IntValue;
import de.jplag.java_cpg.transformation.operations.TransformationUtil;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.*;

/**
 * Abstract Interpretation engine for Java programs.
 * This class is the interface between the CPG Graph and the Abstract Interpretation Data Structures.
 *
 * @author ujiqk
 * @version 1.0
 */
public class AbstractInterpretation {

    /**
     * Helper: if we are recording changes for while loops.
     */
    private static boolean recordingChanges = false;
    /**
     * the methods available for the associated class.
     */
    @NotNull
    private final HashMap<String, MethodDeclaration> methods;
    /**
     * Stack for EOG traversal.
     */
    @NotNull
    private ArrayList<Node> nodeStack;
    /**
     * Stack for values during EOG traversal.
     */
    @NotNull
    private ArrayList<Value> valueStack;
    /**
     * The scoped variable store for the current scope.
     */
    @NotNull
    private VariableStore variables;
    /**
     * The object this AI engine is currently interpreting.
     */
    private JavaObject object;
    /**
     * Helper counter for nested if-else statements because cpg does not provide enough information.
     */
    private int ifElseCounter = 0;
    /**
     * Helper stack to work around cpg limitations.
     */
    private List<Node> lastVisitedLoopOrIf;

    public AbstractInterpretation() {
        variables = new VariableStore();
        nodeStack = new ArrayList<>();
        valueStack = new ArrayList<>();
        methods = new HashMap<>();
        lastVisitedLoopOrIf = new ArrayList<>();
    }

    /**
     * Starts the abstract interpretation by running the main method.
     *
     * @param tud TranslationUnitDeclaration graph node representing the whole program.
     */
    public void runMain(@NotNull TranslationUnitDeclaration tud) {
        RecordDeclaration mainClas;
        if (tud.getDeclarations().stream().map(Declaration::getClass).filter(x -> x.equals(NamespaceDeclaration.class)).count() == 1) {
            //Code in a package
            mainClas = tud.getDeclarations().stream()
                    .filter(NamespaceDeclaration.class::isInstance)
                    .map(NamespaceDeclaration.class::cast)
                    .findFirst()
                    .map(ns -> (RecordDeclaration) ns.getDeclarations().getFirst())
                    .orElseThrow(() -> new IllegalStateException("No NamespaceDeclaration found in translation unit"));
        } else if (tud.getDeclarations().stream().map(Declaration::getClass).filter(x -> x.equals(RecordDeclaration.class)).count() >= 1) {
            //Code without a package
            mainClas = tud.getDeclarations().stream()
                    .filter(RecordDeclaration.class::isInstance)
                    .map(RecordDeclaration.class::cast)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No RecordDeclaration found in translation unit"));
        } else {
            throw new IllegalStateException("Unexpected number of classes or namespaces in translation unit");
        }
        JavaObject mainClassVar = new JavaObject();
        setupClass(mainClas, mainClassVar);
        assert mainClas.getMethods().stream().map(MethodDeclaration::getName)
                .filter(x -> x.getLocalName().equals("main")).count() == 1;
        for (MethodDeclaration md : mainClas.getMethods()) {
            if (!md.getName().getLocalName().equals("main")) {
                methods.put(md.getName().getLocalName(), md);
            }
        }
        for (MethodDeclaration md : mainClas.getMethods()) {
            if (md.getName().getLocalName().equals("main")) {
                //Run main method
                List<Node> eog = md.getNextEOG();
                assert eog.size() == 1;
                variables.newScope();
                variables.addVariable(new Variable(new VariableName("args"), new JavaArray(de.jplag.java_cpg.ai.variables.Type.STRING)));
                graphWalker(eog.getFirst());
                variables.removeScope();
            }

        }
        //ignore include declaration for now

    }

    /**
     * Sets up the abstract interpretation for the given class and runs its constructor.
     *
     * @param rd              RecordDeclaration node representing the class.
     * @param objectInstance  the object instance that should represent the class.
     * @param constructorArgs the arguments for the constructor.
     */
    private void runClass(@NotNull RecordDeclaration rd, @NotNull JavaObject objectInstance, List<Value> constructorArgs) {
        setupClass(rd, objectInstance);
        //Run constructor method
        ConstructorDeclaration constr = rd.getConstructors().getFirst();    //ToDo what if multiple constructors?
        List<Node> eog = constr.getNextEOG();
        if (eog.size() == 1) {
            variables.newScope();
            assert constr.getParameters().size() == constructorArgs.size();
            for (int i = 0; i < constructorArgs.size(); i++) {
                variables.addVariable(new Variable(new VariableName(constr.getParameters().get(i).getName().toString()), constructorArgs.get(i)));
            }
            graphWalker(eog.getFirst());
            variables.removeScope();
        } else if (eog.isEmpty()) { //empty constructor
            return;
        } else {
            throw new IllegalStateException("Unexpected value: " + eog.size());
        }
    }

    /**
     * Sets up the abstract interpretation for the given class.
     * Adds fields and methods to the object instance.
     *
     * @param rd             RecordDeclaration node representing the class.
     * @param objectInstance the object instance that should represent the class.
     */
    @Impure
    private void setupClass(@NotNull RecordDeclaration rd, @NotNull JavaObject objectInstance) {
        assert !rd.getConstructors().isEmpty();
        objectInstance.setAbstractInterpretation(this);
        variables.addVariable(new Variable(new VariableName(rd.getName().toString()), objectInstance));
        variables.setThisName(new VariableName(rd.getName().toString()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.System.getName(), new de.jplag.java_cpg.ai.variables.objects.System()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Math.getName(), new de.jplag.java_cpg.ai.variables.objects.Math()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Integer.getName(), new de.jplag.java_cpg.ai.variables.objects.Integer()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Boolean.getName(), new de.jplag.java_cpg.ai.variables.objects.Boolean()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Double.getName(), new de.jplag.java_cpg.ai.variables.objects.Double()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.String.getName(), new de.jplag.java_cpg.ai.variables.objects.String()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Arrays.getName(), new de.jplag.java_cpg.ai.variables.objects.Arrays()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Pattern.getName(), new de.jplag.java_cpg.ai.variables.objects.Pattern()));
        this.object = objectInstance;
        for (MethodDeclaration md : rd.getMethods()) {
            methods.put(md.getName().getLocalName(), md);
        }
        for (FieldDeclaration fd : rd.getFields()) {
            Type type = fd.getType();
            Name name = fd.getName();
            if (fd.getInitializer() == null) {      //no initial value
                Variable newVar = new Variable(new VariableName(name.toString()), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type));
                newVar.setInitialValue();
                objectInstance.setField(newVar);
                //objectInstance.setField(new Variable(new VariableName(name.toString()), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type)));  //ToDo array inner type lost here
            } else if (!(fd.getInitializer() instanceof ProblemExpression)) {   //ToDo: simplify
                if (fd.getInitializer() instanceof Literal<?> literal) {
                    Value value = Value.valueFactory(literal.getValue());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), value));
                } else if (fd.getInitializer() instanceof NewExpression) {
                    JavaObject newObject = (JavaObject) graphWalker(fd.getNextEOG().getFirst());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), newObject));
                } else if (fd.getInitializer() instanceof InitializerListExpression || fd.getInitializer() instanceof NewArrayExpression) {
//                    nodeStack.add(fd);
                    JavaArray list = (JavaArray) graphWalker(fd.getNextEOG().getFirst());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), list));
                } else if (fd.getInitializer() instanceof MemberCallExpression) {
                    Value result = graphWalker(fd.getNextEOG().getFirst());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), result));
                } else if (fd.getInitializer() instanceof UnaryOperator unop) {
                    assert Objects.equals(unop.getOperatorCode(), "-");
                    Value value = graphWalker(fd.getNextEOG().getFirst());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), value));
                } else {
                    throw new IllegalStateException("Unexpected declaration: " + fd.getInitializer());
                }
            }
        }
    }

    /**
     * Graph walker for EOG traversal.
     * Walks the EOG starting from the given node until the current block ends.
     * If present, returns the value produced by the return statement.
     *
     * @param node the starting graph node.
     * @return the value resulting from the traversal, or null if no value is produced.
     */
    @Nullable
    private Value graphWalker(@NotNull Node node) {
        List<Node> nextEOG = node.getNextEOG();
        Node nextNode;
        //System.out.println(node);
        switch (node) {
            case FieldDeclaration fd -> {
                Value value = valueStack.getLast();
                valueStack.removeLast();
                nodeStack.removeLast();
                return value;   //return so that the class setup method can use the graph walker
            }
            case VariableDeclaration vd -> {
                nodeStack.add(vd);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case Literal<?> l -> {  //adds its value to the value stack
                nodeStack.add(l);
                valueStack.add(Value.valueFactory(l.getValue()));
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case MemberExpression me -> {
                if (me.getRefersTo() instanceof FieldDeclaration || me.getRefersTo() instanceof EnumConstantDeclaration) {
                    if (!(valueStack.getLast() instanceof JavaObject)) {
                        //not reached in normal execution
                        //assert false;
                        nodeStack.removeLast();
                        nodeStack.add(me);
                        valueStack.removeLast();    //remove object reference
                        valueStack.add(new VoidValue());
                    } else {
                        assert valueStack.getLast() instanceof JavaObject;
                        nodeStack.removeLast();
                        //like Reference
                        nodeStack.add(me);
                        assert me.getName().getParent() != null;
                        JavaObject javaObject = (JavaObject) valueStack.getLast();
                        valueStack.removeLast();    //remove object reference
                        Value result = javaObject.accessField(me.getName().getLocalName());
                        result.setParentObject(javaObject);
                        valueStack.add(result);
                    }
                } else if (me.getRefersTo() instanceof MethodDeclaration) {
                    nodeStack.removeLast();
                    nodeStack.add(me);
                } else {
                    //unknown
                    //look at last item on value stack
                    Value value = valueStack.getLast();
                    if (value instanceof VoidValue) {
                        valueStack.removeLast();    //remove object reference
                        valueStack.add(new JavaObject());
                    } else {
                        throw new IllegalStateException("Unexpected value: " + value);
                    }
                    nodeStack.removeLast();
                    nodeStack.add(me);
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case Reference ref -> {     //adds its value to the value stack
                if (ref.getName().getLocalName().equals("this")) {
                    valueStack.add(this.object);
                } else {
                    Variable variable = variables.getVariable(new VariableName(ref.getName().toString()));
                    if (variable != null) {
                        valueStack.add(variables.getVariable(new VariableName(ref.getName().toString())).getValue());
                    } else if (object.accessField(ref.getName().toString()) != null) { //sometimes cpg does not insert "this".
                        Value value = object.accessField(ref.getName().toString());
                        if (value.getType() == de.jplag.java_cpg.ai.variables.Type.VOID) {  //value isn't known
                            value = Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.fromCpgType(ref.getType()));
                        }
                        valueStack.add(value);
                    } else {    //unknown reference
                        assert false;
                    }
                }
                nodeStack.add(ref);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case SubscriptExpression se -> {    //adds its value to the value stack
                assert nodeStack.getLast() instanceof Literal || nodeStack.getLast() instanceof Reference
                        || nodeStack.getLast() instanceof BinaryOperator || nodeStack.getLast() instanceof MemberCallExpression;
                assert !valueStack.isEmpty();
                if ((valueStack.getLast() instanceof VoidValue)) {
                    valueStack.removeLast();
                    valueStack.add(Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.INT));
                }
                INumberValue indexLiteral = (INumberValue) valueStack.getLast();
                valueStack.removeLast();    //remove index value
                assert indexLiteral != null;
                Value ref = valueStack.getLast();
                valueStack.removeLast();    //remove array reference
                if (!(ref instanceof JavaArray)) {
                    //array might not be initialized yet
                    ref = new JavaArray();
                }
                Value result = ((JavaArray) ref).arrayAccess(indexLiteral);
                result.setArrayPosition((JavaArray) ref, indexLiteral);
                valueStack.add(result);
                nodeStack.removeLast();
                nodeStack.removeLast();
                nodeStack.add(se);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case MemberCallExpression mce -> {  //adds its value to the value stack
                Value result;
                if (mce.getArguments().isEmpty()) {     //no arguments
                    assert nodeStack.getLast() instanceof MemberExpression;
                    Name memberName = (nodeStack.getLast()).getName();
                    if (valueStack.getLast() instanceof VoidValue) {
                        valueStack.removeLast();
                        valueStack.add(new JavaObject());
                    }
                    JavaObject javaObject = (JavaObject) valueStack.getLast();
                    result = javaObject.callMethod(memberName.getLocalName(), null);
                } else {
                    List<Value> argumentList = new ArrayList<>();
                    for (int i = 0; i < mce.getArguments().size(); i++) {
                        if (mce.getArguments().get(i) instanceof ProblemExpression) {
                            continue;
                        }
                        argumentList.add(valueStack.getLast());
                        nodeStack.removeLast();
                        valueStack.removeLast();
                    }
                    Collections.reverse(argumentList);
                    if (!(nodeStack.getLast() instanceof MemberExpression)) {
                        System.out.println("Debug");
                    }
                    assert nodeStack.getLast() instanceof MemberExpression;
                    Name memberName = (nodeStack.getLast()).getName();
                    assert memberName.getParent() != null;
                    assert !valueStack.isEmpty();
                    if (!(valueStack.getLast() instanceof JavaObject)) {
                        System.out.println("Debug");
                    }
                    JavaObject javaObject = (JavaObject) valueStack.getLast();         //for now only one parameter
                    result = javaObject.callMethod(memberName.getLocalName(), argumentList);
                }
                valueStack.removeLast();    //remove object reference
                if (result == null) {       //if method reference isn't known
                    result = Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.fromCpgType(mce.getType()));
                }
                valueStack.add(result);
                nodeStack.removeLast();
                nodeStack.add(mce);
                if (nextEOG.isEmpty()) {    //when used as a field initializer
                    Value value = valueStack.getLast();
                    valueStack.removeLast();
                    return value;
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case DeclarationStatement ds -> {
                for (int i = ds.getDeclarations().size() - 1; i >= 0; i--) {
                    if (((VariableDeclaration) ds.getDeclarations().get(i)).getInitializer() == null) {
                        Variable newVar = new Variable(new VariableName((ds.getDeclarations().get(i)).getName().toString()),
                                de.jplag.java_cpg.ai.variables.Type.fromCpgType(((VariableDeclaration) ds.getDeclarations().get(i)).getType()));
                        newVar.setInitialValue();
                        variables.addVariable(newVar);
                        nodeStack.removeLast();
                    } else {
                        assert !valueStack.isEmpty();
                        Variable variable = new Variable((ds.getDeclarations().get(i)).getName().toString(), valueStack.getLast());
                        variables.addVariable(variable);
                        nodeStack.removeLast();
                        nodeStack.removeLast();
                        valueStack.removeLast();
                        if (nextEOG.getFirst() instanceof ForEachStatement) {
                            valueStack.add(variable.getValue());
                        }
                    }
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case AssignExpression ae -> {
                assert !valueStack.isEmpty();
                if (ae.getLhs().getFirst() instanceof SubscriptExpression se) {
                    assert ae.getLhs().size() == 1;
                    Value newValue = valueStack.getLast();
                    valueStack.removeLast();
                    Value oldValue = valueStack.getLast();
                    valueStack.removeLast();
                    oldValue.getArrayPosition().component1().arrayAssign(oldValue.getArrayPosition().component2(), newValue);
                } else {
                    Variable variable = variables.getVariable((nodeStack.get(nodeStack.size() - 2)).getName().toString());
                    if (variable == null || nodeStack.get(nodeStack.size() - 2) instanceof MemberExpression) { //class access
                        JavaObject classVal;
                        if (nodeStack.get(nodeStack.size() - 2).getName().getParent() == null) {    //this class
                            classVal = variables.getThisObject();
                        } else {
                            assert nodeStack.get(nodeStack.size() - 2).getName().getParent() != null;
                            //VariableName className = new VariableName(nodeStack.get(nodeStack.size() - 2).getName().getParent().toString());
                            classVal = valueStack.get(valueStack.size() - 2).getParentObject();
                            //classVal = (JavaObject) variables.getVariable(className).getValue();
                        }
                        classVal.changeField((nodeStack.get(nodeStack.size() - 2)).getName().getLocalName(), valueStack.getLast());
                    } else {
                        variable.setValue(valueStack.getLast());
                    }
                    nodeStack.removeLast();
                    //sometimes the value of assign is used after, so don't remove it
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case ShortCircuitOperator scop -> {
                assert scop.getPrevEOG().size() == 2;
                BooleanValue value1 = (BooleanValue) valueStack.get(valueStack.size() - 2);
                BooleanValue value2 = (BooleanValue) valueStack.getLast();
                valueStack.removeLast();
                valueStack.removeLast();
                if (Objects.equals(scop.getOperatorCode(), "||")) {
                    valueStack.add(value1.binaryOperation("||", value2));
                } else if (Objects.equals(scop.getOperatorCode(), "&&")) {
                    valueStack.add(value1.binaryOperation("&&", value2));
                } else {
                    throw new UnsupportedOperationException(scop.getOperatorCode() + " is not supported in ShortCircuitOperator");
                }
                assert nextEOG.size() == 1 || nextEOG.size() == 2;
                nextNode = nextEOG.getFirst();
            }
            case BinaryOperator bop -> {
                assert valueStack.size() >= 2 && !nodeStack.isEmpty();
                String operator = bop.getOperatorCode();
                assert operator != null;
                Value result = valueStack.get(valueStack.size() - 2).binaryOperation(operator, valueStack.getLast());
                assert nodeStack.size() >= 2;
                nodeStack.removeLast();
                nodeStack.removeLast();
                nodeStack.add(bop);
                valueStack.removeLast();
                valueStack.removeLast();
                valueStack.add(result);
                assert nextEOG.size() == 1 || (nextEOG.size() == 2 && nextEOG.getLast() instanceof ShortCircuitOperator);
                nextNode = nextEOG.getFirst();
            }
            case UnaryOperator uop -> {
                assert !valueStack.isEmpty() && !nodeStack.isEmpty();
                String operator = uop.getOperatorCode();
                assert operator != null;
                Value result = valueStack.getLast().unaryOperation(operator);
                nodeStack.removeLast();
                nodeStack.add(uop);
                valueStack.removeLast();
                valueStack.add(result);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case IfStatement ifStmt -> {
                if (valueStack.getLast() instanceof VoidValue) {
                    valueStack.removeLast();
                    valueStack.add(new BooleanValue());
                }
                assert valueStack.getLast() instanceof BooleanValue;
                BooleanValue condition = (BooleanValue) valueStack.getLast();
                valueStack.removeLast();
                boolean runThenBranch = true;
                boolean runElseBranch = true;
                if (condition.getInformation()) {
                    if (condition.getValue()) {
                        runElseBranch = false;
                        //Dead code detected -> remove else branch
                        if (ifStmt.getElseStatement() != null) {
                            TransformationUtil.disconnectFromPredecessor(nextEOG.getLast());
                            ifStmt.setElseStatement(null);
                        }
                        System.out.println("Dead code detected -> remove else branch");
                    } else {
                        runThenBranch = false;
                        //Dead code detected
                        TransformationUtil.disconnectFromPredecessor(nextEOG.getFirst());
                        ifStmt.setThenStatement(null);
                        if (ifStmt.getElseStatement() == null) {
                            TransformationUtil.disconnectFromPredecessor(ifStmt);
                            assert ifStmt.getScope() != null;
                            Block containingBlock = (Block) ifStmt.getScope().getAstNode();
                            assert containingBlock != null;
                            List<Statement> statements = containingBlock.getStatements();
                            statements.remove(ifStmt);
                            containingBlock.setStatements(statements);
                        }
                    }
                    System.out.println("Dead code detected -> remove then branch");
                }
                if (ifStmt.getThenStatement() == null) {
                    runThenBranch = false;
                }
                if (ifStmt.getElseStatement() == null) {
                    runElseBranch = false;
                }
                nodeStack.removeLast();     //remove condition
                assert nextEOG.size() == 2;
                VariableStore thenVariables = new VariableStore(variables);
                VariableStore elseVariables = new VariableStore(variables);
                //then statement
                if (runThenBranch) {
                    variables = thenVariables;
                    this.object = variables.getThisObject();
                    variables.newScope();   //2
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();    //3
                    if (nodeStack.getLast() == null) {
                        nodeStack.add(nextEOG.getLast());
                    }
                }
                //else statement
                if (runElseBranch) {
                    if (ifStmt.getElseStatement() instanceof IfStatement) {  //this loop is a loop with if else
                        ifElseCounter++;
                    }
                    variables = elseVariables;
                    this.object = variables.getThisObject();
                    variables.newScope();   //1
                    graphWalker(nextEOG.getLast());
                    variables.removeScope();
                    if (nodeStack.getLast() == null) {
                        nodeStack.add(nextEOG.getFirst());
                    }
                }
                //merge branches
                if (runThenBranch && runElseBranch) { //4
                    variables.merge(thenVariables);
                } else if (runThenBranch) {
                    if (!condition.getInformation()) {
                        variables.merge(elseVariables);
                        nodeStack.add(nextEOG.getLast());
                    } else {   //only then branch is run
                        //
                    }
                } else if (runElseBranch) {
                    if (!condition.getInformation()) {
                        variables.merge(thenVariables);
                    } else {    //only else branch is run
                        //
                    }
                } else {    //no branch is run
                    nodeStack.add(nextEOG.getLast());
                }
                //this.object = variables.getThisObject(); // Update object reference
                nextNode = nodeStack.getLast(); //5
                if (ifElseCounter > 0) {
                    ifElseCounter--;
                    return null;
                }
            }
            case SwitchStatement sw -> {    //ToDo delete dead Code in switch
                assert !valueStack.isEmpty();
                int branches = nextEOG.size();
                VariableStore originalVariables = new VariableStore(variables);
                VariableStore result = null;
                nodeStack.removeLast();
                for (Node branch : nextEOG) {
                    variables = new VariableStore(originalVariables);
                    this.object = variables.getThisObject();
                    variables.newScope();
                    graphWalker(branch);
                    variables.removeScope();
                    if (result == null) {
                        result = variables;
                    } else {
                        result.merge(variables);
                    }
                }
                variables = result;
                this.object = variables.getThisObject();
                nextNode = nodeStack.getLast();
                if (nextNode instanceof Block block) {  //scoped switch statements have an extra block
                    assert block.getNextEOG().size() == 1;
                    nextNode = block.getNextEOG().getFirst();
                }
                if (nextNode == null) {
                    return new VoidValue(); //ToDo: function return (CropArea:31)
                }
            }
            case CaseStatement cs -> {
                Value caseValue = valueStack.getLast();
                Value switchValue = valueStack.getLast();
                if (!Objects.equals(caseValue, switchValue)) {
                    return null;
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case DefaultStatement ds -> {
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case Block b -> {
                if (b.getScope() instanceof TryScope) {
                    assert nextEOG.size() == 1;
                    nextNode = nextEOG.getFirst();
                } else {
                    //assert block is exited
                    if (nextEOG.size() == 1) {          //end of if
                        nodeStack.add(nextEOG.getFirst());
                        return null;
                    } else if (nextEOG.isEmpty()) {     //at the end of a while loop or after throw statement
                        nodeStack.add(null);
                        return null;
                    } else {
                        assert false;
                        return null;
                    }
                }
            }
            case ReturnStatement ret -> {
                Value result;
                if (valueStack.isEmpty()) {
                    result = new VoidValue();
                } else {
                    result = valueStack.getLast();
                    valueStack.removeLast();
                }
                nodeStack.add(null);
                return result;
            }
            case ConstructExpression ce -> {
                nodeStack.add(ce);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case NewExpression ne -> {
                Declaration classNode = ((ConstructExpression) nodeStack.getLast()).getInstantiates();
                List<Value> arguments = new ArrayList<>();
                ConstructExpression ce = (ConstructExpression) nodeStack.getLast();
                nodeStack.removeLast(); //remove ConstructExpression
                if (!ce.getArguments().isEmpty()) {
                    int size = ce.getArguments().size();
                    for (int i = 0; i < size; i++) {
                        arguments.add(valueStack.getLast());
                        valueStack.removeLast();
                        nodeStack.removeLast();
                    }
                }
                Collections.reverse(arguments);
                JavaObject newObject;
                switch (ce.getType().getName().toString()) {
                    case "java.util.HashMap", "java.util.Map" ->
                            newObject = new de.jplag.java_cpg.ai.variables.objects.HashMap();
                    case "java.util.Scanner" -> newObject = new de.jplag.java_cpg.ai.variables.objects.Scanner();
                    case "java.util.ArrayList", "java.util.List" -> newObject = new JavaArray();
                    default -> newObject = new JavaObject();
                }
                valueStack.add(newObject);
                //run constructor
                if (classNode != null) {
                    AbstractInterpretation classAi = new AbstractInterpretation();
                    classAi.runClass((RecordDeclaration) classNode, newObject, arguments);
                }
                //
                nodeStack.add(ne);
                if (nextEOG.isEmpty()) {    //when used as a field initializer
                    Value value = valueStack.getLast();
                    valueStack.removeLast();
                    return value;
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case WhileStatement ws -> {
                assert nextEOG.size() == 2;
                //detect infinite loops when no Block inserted by cpg
                if (!lastVisitedLoopOrIf.isEmpty() && ws == lastVisitedLoopOrIf.getLast()) {
                    nodeStack.add(null);
                    return null;
                }
                lastVisitedLoopOrIf.addLast(ws);
                //evaluate condition
                assert !valueStack.isEmpty() && valueStack.getLast() instanceof BooleanValue;
                BooleanValue condition = (BooleanValue) valueStack.getLast();
                valueStack.removeLast();
                nodeStack.removeLast();
                if (!condition.getInformation() || condition.getValue()) {  //run body if the condition is true or unknown
                    if (recordingChanges) {     //higher level loop wants to know which variables change
                        variables.recordChanges();
                        variables.newScope();
                        graphWalker(nextEOG.getFirst());
                        variables.removeScope();
                        Set<Variable> changedVariables = variables.stopRecordingChanges();
                        for (Variable variable : changedVariables) {
                            variables.getVariable(variable.getName()).setToUnknown();
                        }
                    } else {
                        VariableStore originalVariables = this.variables;
                        //1: first loop run: detect variables that change in loop -> run loop with completely unknown variables + record changes
                        this.variables = new VariableStore(variables);
                        variables.setEverythingUnknown();
                        variables.recordChanges();
                        AbstractInterpretation.recordingChanges = true;
                        variables.newScope();
                        graphWalker(nextEOG.getFirst());
                        variables.removeScope();
                        AbstractInterpretation.recordingChanges = false;
                        Set<Variable> changedVariables = variables.stopRecordingChanges();
                        //2: second loop run with only changed variables unknown
                        this.variables = new VariableStore(originalVariables);
                        for (Variable variable : changedVariables) {
                            variables.getVariable(variable.getName()).setToUnknown();
                        }
                        variables.newScope();
                        graphWalker(nextEOG.getFirst());
                        variables.removeScope();
                        //3: restore variables and set changed variables to unknown
                        this.variables = originalVariables;
                        for (Variable variable : changedVariables) {
                            variables.getVariable(variable.getName()).setToUnknown();
                        }
                    }
                } else {
                    //Dead code detected, loop never runs
                    TransformationUtil.disconnectFromPredecessor(nextEOG.getFirst());
                    TransformationUtil.disconnectFromPredecessor(ws);
                    assert ws.getScope() != null;
                    Block containingBlock = (Block) ws.getScope().getAstNode();
                    assert containingBlock != null;
                    List<Statement> statements = containingBlock.getStatements();
                    statements.remove(ws);
                    containingBlock.setStatements(statements);
                    System.out.println("Dead code detected -> remove while");
                }
                //continue with next node after while
                lastVisitedLoopOrIf.removeLast();
                nextNode = nextEOG.getLast();
            }
            case ForStatement fs -> {
                assert nextEOG.size() == 2;
                //evaluate condition
                assert !valueStack.isEmpty() && valueStack.getLast() instanceof BooleanValue;
                BooleanValue condition = (BooleanValue) valueStack.getLast();
                valueStack.removeLast();
                nodeStack.removeLast();
                if (!condition.getInformation() || condition.getValue()) {
                    //run body if the condition is true or unknown
                    variables.recordChanges();
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();
                    Set<Variable> changedVariables = variables.stopRecordingChanges();
                    //merge if the loop has been run
                    for (Variable variable : changedVariables) {
                        variable.setToUnknown();
                    }
                } else {
                    //Dead code detected, loop never runs
                    TransformationUtil.disconnectFromPredecessor(nextEOG.getFirst());
                    TransformationUtil.disconnectFromPredecessor(fs);
                    assert fs.getScope() != null;
                    Block containingBlock = (Block) fs.getScope().getAstNode();
                    assert containingBlock != null;
                    List<Statement> statements = containingBlock.getStatements();
                    statements.remove(fs);
                    containingBlock.setStatements(statements);
                    System.out.println("Dead code detected -> remove for");
                }
                //continue with the next node after for
                nextNode = nextEOG.getLast();
            }
            case ForEachStatement fes -> {
                assert nextEOG.size() == 2;
                assert !valueStack.isEmpty();
                if (valueStack.getLast() instanceof VoidValue) {
                    valueStack.removeLast();
                    valueStack.add(new JavaArray());
                }
                JavaArray collection = (JavaArray) valueStack.getLast();
                //ToDo: set right variable value
                valueStack.removeLast();
                assert fes.getVariable() != null;
                String varName = (fes.getVariable().getDeclarations().getFirst()).getName().toString();
                Variable variable1 = new Variable(new VariableName(varName), collection.arrayAccess((INumberValue) Value.valueFactory(0)));
                variables.addVariable(variable1);
                if (collection.accessField("length") instanceof INumberValue length && length.getInformation() && (length.getValue() == 0)) {
                    //Dead code detected, loop never runs
                    TransformationUtil.disconnectFromPredecessor(nextEOG.getFirst());
                    TransformationUtil.disconnectFromPredecessor(fes);
                    assert fes.getScope() != null;
                    Block containingBlock = (Block) fes.getScope().getAstNode();
                    assert containingBlock != null;
                    List<Statement> statements = containingBlock.getStatements();
                    statements.remove(fes);
                    containingBlock.setStatements(statements);
                    System.out.println("Dead code detected -> remove for each");
                } else {
                    variables.recordChanges();
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();
                    Set<Variable> changedVariables = variables.stopRecordingChanges();
                    //merge if the loop has been run
                    for (Variable variable : changedVariables) {
                        variable.setToUnknown();
                    }
                }
                //continue with the next node after for each
                nextNode = nextEOG.getLast();
            }
            case InitializerListExpression ile -> {
                assert !ile.getInitializers().isEmpty();
                assert valueStack.size() >= ile.getInitializers().size();
                assert nodeStack.size() >= ile.getInitializers().size();
                List<Value> arguments = new ArrayList<>();
                for (int i = 0; i < ile.getInitializers().size(); i++) {
                    nodeStack.removeLast();
                    arguments.add(valueStack.getLast());
                    valueStack.removeLast();
                }
                assert arguments.stream().map(Value::getType).distinct().count() == 1;
                JavaArray list = new JavaArray(arguments);
                if (nextEOG.isEmpty()) {    //when used as a field initializer
                    return list;
                }
                valueStack.add(list);
                nodeStack.add(ile);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case NewArrayExpression nae -> {
                //either dimension or initializer is present
                if (!nae.getDimensions().isEmpty()) {
                    INumberValue dimension;
                    if (valueStack.getLast() instanceof VoidValue) {
                        dimension = (INumberValue) Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.INT);
                    } else {
                        dimension = (INumberValue) valueStack.getLast();
                    }
                    valueStack.removeLast();
                    //recover inner type
                    de.jplag.java_cpg.ai.variables.Type innerType = null;
                    if (((HasType) nae.getTypeObservers().iterator().next()).getType() instanceof PointerType pointerType) {
                        innerType = de.jplag.java_cpg.ai.variables.Type.fromCpgType(pointerType.elementType);
                    }
                    valueStack.add(new JavaArray(dimension, innerType));
                } else if (nae.getInitializer() != null) {
                    if (nae.getPrevEOG().getFirst() instanceof InitializerListExpression) {
                        //initializer has already been processed
                        assert valueStack.getLast() instanceof JavaArray;
                        assert nodeStack.getLast() instanceof InitializerListExpression;
                    } else {
                        throw new IllegalStateException("Unexpected value: " + nae);
                    }
                } else {
                    throw new IllegalStateException("Unexpected value: " + nae);
                }
                if (nextEOG.isEmpty()) {    //when used as a field initializer
                    Value value = valueStack.getLast();
                    valueStack.removeLast();
                    return value;
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case ConditionalExpression ce -> {
                assert nextEOG.size() == 2;
                BooleanValue condition = (BooleanValue) valueStack.getLast();
                valueStack.removeLast();
                nodeStack.removeLast();
                //paths have no block statements at the end
                //ToDo
                nextNode = nextEOG.getLast();
            }
            case BreakStatement bs -> {
                assert nextEOG.size() == 1;
                nodeStack.add(nextEOG.getFirst());
                return null;
            }
            case CatchClause cc -> {
                //nothing for now
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case TryStatement ts -> {
                //ignore for now
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case LambdaExpression le -> {
                FunctionDeclaration lambda = le.getFunction();
                //ToDo
                valueStack.add(Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.FUNCTION));
                nodeStack.add(le);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case EmptyStatement es -> {
                //occurs, for example, when while loop body is empty
                assert nextEOG.size() == 1;
                return null;
            }
            default -> throw new IllegalStateException("Unexpected value: " + node);
        }
        if (nextNode == null) {
            System.out.println("Debug");
        }
        return graphWalker(nextNode);
    }

    @Deprecated
    private JavaObject createEnum(EnumDeclaration enumDeclaration) {
        JavaObject enumObject = new JavaObject();
        int i = 0;
        for (EnumConstantDeclaration ec : enumDeclaration.getEntries()) {
            enumObject.setField(new Variable(new VariableName(ec.getName().toString()), new IntValue(i)));
            i++;
        }
        return enumObject;
    }

    @TestOnly
    protected VariableStore getVariables() {
        return variables;
    }

    /**
     * Runs a method in this abstract interpretation engine context with the given name and parameters.
     *
     * @param name the name of the method to run.
     * @return null if the method is not known.
     */
    public Value runMethod(@NotNull String name, List<Value> paramVars) {
        ArrayList<Node> oldNodeStack = this.nodeStack;      //Save stack
        ArrayList<Value> oldValueStack = this.valueStack;
        this.nodeStack = new ArrayList<>();
        this.valueStack = new ArrayList<>();
        MethodDeclaration md = methods.get(name);
        if (md == null) {
            return null;
        }
        variables.newScope();
        if (paramVars != null) {
            assert md.getParameters().size() == paramVars.size();
            for (int i = 0; i < paramVars.size(); i++) {
                variables.addVariable(new Variable(new VariableName(md.getParameters().get(i).getName().getLocalName()), paramVars.get(i)));
            }
        } else {
            assert md.getParameters().isEmpty();
        }
        Value result;
        assert md.getNextEOG().size() <= 1;
        if (md.getNextEOG().size() == 1) {
            result = graphWalker(md.getNextEOG().getFirst());
        } else {
            result = new VoidValue();
        }
        variables.removeScope();
        this.nodeStack = oldNodeStack;      //restore stack
        this.valueStack = oldValueStack;
        return result;
    }

}
