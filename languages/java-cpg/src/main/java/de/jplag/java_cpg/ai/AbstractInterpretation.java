package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.graph.Name;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.*;
import de.fraunhofer.aisec.cpg.graph.scopes.TryScope;
import de.fraunhofer.aisec.cpg.graph.statements.*;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.*;
import de.fraunhofer.aisec.cpg.graph.types.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.*;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Abstract Interpretation engine for Java programs.
 * This class is the interface between the CPG Graph and the Abstract Interpretation Data Structures.
 *
 * @author ujiqk
 * @version 1.0
 */
public class AbstractInterpretation {

    private final HashMap<String, MethodDeclaration> methods;    //the methods available for the class
    private ArrayList<Node> nodeStack;    //Stack for EOG traversal
    private ArrayList<Value> valueStack;  //Stack for values during EOG traversal
    private VariableStore variables;      //Scoped variable store
    private JavaObject object;          //the java object this ai engine does ai for

    public AbstractInterpretation() {
        variables = new VariableStore();
        nodeStack = new ArrayList<>();
        valueStack = new ArrayList<>();
        methods = new HashMap<>();
    }

    /**
     * Starts the abstract interpretation by running the main method.
     *
     * @param tud TranslationUnitDeclaration graph node representing the whole program.
     */
    public void runMain(@NotNull TranslationUnitDeclaration tud) {
        assert tud.getDeclarations().stream().map(Declaration::getClass).filter(x -> x.equals(NamespaceDeclaration.class)).count() == 1;
        for (Declaration declaration : tud.getDeclarations()) {
            if (declaration instanceof NamespaceDeclaration) {      //ToDo: what if not in package
                RecordDeclaration mainClas = (RecordDeclaration) ((NamespaceDeclaration) declaration).getDeclarations().getFirst();
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
                    }
                }
            }
            //ignore include declaration for now
        }
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
            } else if (!(fd.getInitializer() instanceof ProblemExpression)) {
                if (fd.getInitializer() instanceof Literal<?> literal) {
                    Value value = Value.valueFactory(literal.getValue());
                    objectInstance.setField(new Variable(new VariableName(name.toString()), value));
                } else if (fd.getInitializer() instanceof NewExpression newExpression) {
                    JavaObject newObject;
                    switch ((newExpression.getInitializer()).getType().getName().toString()) {
                        case "java.util.HashMap", "java.util.Map" ->
                                newObject = new de.jplag.java_cpg.ai.variables.objects.HashMap();
                        case "java.util.Scanner" -> newObject = new de.jplag.java_cpg.ai.variables.objects.Scanner();
                        case "java.util.ArrayList", "java.util.List" -> newObject = new JavaArray();
                        default -> newObject = new JavaObject();
                    }
                    Declaration classNode = ((ConstructExpression) newExpression.getInitializer()).getInstantiates();
                    if (classNode != null) {    //run constructor
                        AbstractInterpretation classAi = new AbstractInterpretation();
                        classAi.runClass((RecordDeclaration) classNode, newObject, List.of());
                    }
                    objectInstance.setField(new Variable(new VariableName(name.toString()), newObject));
                } else if (fd.getInitializer() instanceof InitializerListExpression initializerList) {
                    List<Value> arguments = new ArrayList<>();
                    for (Expression init : initializerList.getInitializers()) {
                        //ToDo handle nested initializer lists
                    }
                    //assert arguments.stream().map(Value::getType).distinct().count() == 1;
                    //JavaArray list = new JavaArray(arguments);
                    JavaArray list = new JavaArray();
                    objectInstance.setField(new Variable(new VariableName(name.toString()), list));
                } else if (fd.getInitializer() instanceof MemberCallExpression mce) {
                    Value result;
                    if (mce.getArguments().isEmpty()) {     //no arguments

                        Name memberName = mce.getCallee().getName();
                        JavaObject javaObject = (JavaObject) variables.getVariable(mce.getName().getParent().toString()).getValue();
                        result = javaObject.callMethod(memberName.getLocalName(), null);
                    } else {
                        assert false;   //ToDo
                        List<Value> argumentList = new ArrayList<>();
                        for (int i = 0; i < mce.getArguments().size(); i++) {
                            argumentList.add(valueStack.getLast());
                            nodeStack.removeLast();
                            valueStack.removeLast();
                        }
                        Collections.reverse(argumentList);
                        Name memberName = mce.getCallee().getName();
                        assert memberName.getParent() != null;
                        assert !valueStack.isEmpty();
                        JavaObject javaObject = (JavaObject) valueStack.getLast();         //for now only one parameter
                        result = javaObject.callMethod(memberName.getLocalName(), argumentList);
                    }
                    if (result == null) {       //if method reference isn't known
                        result = Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.fromCpgType(mce.getType()));
                    }
                    objectInstance.setField(new Variable(new VariableName(name.toString()), result));
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
        switch (node) {
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
                valueStack.add(((JavaArray) ref).arrayAccess(indexLiteral));
                nodeStack.removeLast();
                nodeStack.removeLast();
                nodeStack.add(se);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case MemberCallExpression mce -> {//adds its value to the value stack
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
                    if (!(nodeStack.get(nodeStack.size() - mce.getArguments().size() - 1) instanceof MemberExpression)) {
                        System.out.println("Debug");
                    }
                    assert nodeStack.get(nodeStack.size() - mce.getArguments().size() - 1) instanceof MemberExpression;     //first arguments
                    List<Value> argumentList = new ArrayList<>();
                    for (int i = 0; i < mce.getArguments().size(); i++) {
                        argumentList.add(valueStack.getLast());
                        nodeStack.removeLast();
                        valueStack.removeLast();
                    }
                    Collections.reverse(argumentList);
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
                if (nextEOG.size() != 1) {
                    System.out.println("Debug");
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case DeclarationStatement ds -> {
                assert !valueStack.isEmpty();
                Variable variable = new Variable((ds.getDeclarations().getFirst()).getName().toString(), valueStack.getLast());
                variables.addVariable(variable);
                nodeStack.removeLast();
                nodeStack.removeLast();
                valueStack.removeLast();
                if (nextEOG.getFirst() instanceof ForEachStatement) {
                    valueStack.add(variable.getValue());
                }
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case AssignExpression ae -> {
                assert !valueStack.isEmpty();
                if (ae.getLhs().getFirst() instanceof SubscriptExpression) {
                    assert ae.getLhs().size() == 1;
                    Value newValue = valueStack.getLast();
                    valueStack.removeLast();
                    Value oldValue = valueStack.getLast();
                    valueStack.removeLast();
                    //ToDo: how to access array
                } else {
                    Variable variable = variables.getVariable((nodeStack.get(nodeStack.size() - 2)).getName().toString());
                    if (variable == null || nodeStack.get(nodeStack.size() - 2) instanceof MemberExpression) { //class access
                        JavaObject classVal;
                        if (nodeStack.get(nodeStack.size() - 2).getName().getParent() == null) {    //this class
                            classVal = variables.getThisObject();
                        } else {
                            VariableName className = new VariableName(nodeStack.get(nodeStack.size() - 2).getName().getParent().toString());
                            classVal = (JavaObject) variables.getVariable(className).getValue();
                        }
                        classVal.changeField((nodeStack.get(nodeStack.size() - 2)).getName().getLocalName(), valueStack.getLast());
                    } else {
                        variable.setValue(valueStack.getLast());
                    }
                    nodeStack.removeLast();
                    nodeStack.removeLast();
                    valueStack.removeLast();
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
                valueStack.add(value1.binaryOperation("||", value2));
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case BinaryOperator bop -> {
                assert valueStack.size() >= 2 && !nodeStack.isEmpty();
                //assert valueStack.get(valueStack.size() - 2).getType() == valueStack.getLast().getType();
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
                        //Dead code detected
                    } else {
                        runThenBranch = false;
                        //Dead code detected
                    }
                }
                if (ifStmt.getThenStatement() == null) {
                    runThenBranch = false;
                } else if (ifStmt.getElseStatement() == null) {
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
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();
                    if (nodeStack.getLast() == null) {
                        nodeStack.add(nextEOG.getLast());
                    }
                }
                //else statement
                if (runElseBranch) {
                    variables = elseVariables;
                    this.object = variables.getThisObject();
                    variables.newScope();
                    graphWalker(nextEOG.getLast());
                    variables.removeScope();
                    if (nodeStack.getLast() == null) {
                        nodeStack.add(nextEOG.getFirst());
                    }
                }
                //merge branches
                if (runThenBranch && runElseBranch) {
                    variables.merge(thenVariables);
                } else if (runThenBranch) {
                    variables = thenVariables;
                    if (!condition.getInformation()) {
                        variables.merge(elseVariables);
                        nodeStack.add(nextEOG.getLast());
                    } else {   //only then branch is run
                        //
                    }
                } else if (runElseBranch) {
                    variables = elseVariables;
                    if (!condition.getInformation()) {
                        variables.merge(thenVariables);
                    } else {    //only else branch is run
                        //
                    }
                } else {    //no branch is run
                    nodeStack.add(nextEOG.getLast());
                }
                this.object = variables.getThisObject(); // Update object reference
                nextNode = nodeStack.getLast();
            }
            case SwitchStatement sw -> {
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
                    return new VoidValue(); //ToDo: funktion return (CropArea:31)
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
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case WhileStatement ws -> {
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
                }
                //continue with next node after while
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
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();
                    //merge if the loop has been run
                    //for now set all variables to unknown
                    variables.setEverythingUnknown();
                    object.setToUnknown();
                } else {
                    //Dead code detected, loop never runs
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
                JavaObject collection = (JavaObject) valueStack.getLast();
                valueStack.removeLast();
                if (collection.accessField("length") instanceof IntValue length && length.getInformation() && (length.getValue() == 0)) {
                    //Dead code detected, loop never runs
                } else {
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    variables.removeScope();
                    //merge if the loop has been run
                    //for now set all variables to unknown
                    variables.setEverythingUnknown();
                    object.setToUnknown();
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
                valueStack.add(list);
                nodeStack.add(ile);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case NewArrayExpression nae -> {
                //either dimension or initializer is present
                if (nae.getDimensions() != null) {
                    INumberValue dimension;
                    if (valueStack.getLast() instanceof VoidValue) {
                        dimension = (INumberValue) Value.valueFactory(de.jplag.java_cpg.ai.variables.Type.INT);
                    } else {
                        dimension = (INumberValue) valueStack.getLast();
                    }
                    valueStack.removeLast();
                    valueStack.add(new JavaArray(dimension));
                } else if (nae.getInitializer() != null) {
                    assert false;   //ToDo
                } else {
                    throw new IllegalStateException("Unexpected value: " + nae);
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
