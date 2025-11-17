package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.graph.Name;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.*;
import de.fraunhofer.aisec.cpg.graph.statements.*;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.*;
import de.fraunhofer.aisec.cpg.graph.types.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbstractInterpretation {

    private final ArrayList<Node> nodeStack;    //Stack for EOG traversal
    private final ArrayList<Value> valueStack;  //Stack for values during EOG traversal
    private final HashMap<String, MethodDeclaration> methods;    //the methods available for the class
    private VariableStore variables;      //Scoped variable store
    private JavaObject object;          //the java object this ai engine does ai for

    public AbstractInterpretation() {
        variables = new VariableStore();
        nodeStack = new ArrayList<>();
        valueStack = new ArrayList<>();
        methods = new HashMap<>();
    }

    @Deprecated //ToDo unite with runClass method
    public void runMain(@NotNull TranslationUnitDeclaration tud) {
        assert tud.getDeclarations().stream().map(Declaration::getClass).filter(x -> x.equals(NamespaceDeclaration.class)).count() == 1;
        for (Declaration declaration : tud.getDeclarations()) {
            if (declaration instanceof NamespaceDeclaration) {
                RecordDeclaration mainClas = (RecordDeclaration) ((NamespaceDeclaration) declaration).getDeclarations().getFirst();
                JavaObject mainClassVar = new JavaObject();
                for (FieldDeclaration fd : mainClas.getFields()) {
                    Type type = fd.getType();
                    Name name = fd.getName();
                    if (fd.getInitializer() == null) {      //no initial value
                        mainClassVar.setField(new Variable(new VariableName(name.toString()), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type)));
                    } else {
                        assert ((Literal<?>) fd.getInitializer()).getValue() != null;
                        Value value = valueResolver(((Literal<?>) fd.getInitializer()).getValue());
                        mainClassVar.setField(new Variable(new VariableName(name.toString()), value));
                    }
                }
                mainClassVar.setAbstractInterpretation(this);
                variables.addVariable(new Variable(new VariableName("Main"), mainClassVar));
                variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.System.getName(), new de.jplag.java_cpg.ai.variables.objects.System()));
                variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Math.getName(), new de.jplag.java_cpg.ai.variables.objects.Math()));
                variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Integer.getName(), new de.jplag.java_cpg.ai.variables.objects.Integer()));
                this.object = mainClassVar;
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
                System.out.println("Test");
            }
            //ignore include declaration for now
        }
    }

    private void runClass(@NotNull RecordDeclaration rd, JavaObject objectInstance, List<Value> constructorArgs) {
        assert !rd.getConstructors().isEmpty();
        for (FieldDeclaration fd : rd.getFields()) {
            Type type = fd.getType();
            Name name = fd.getName();
            if (fd.getInitializer() == null) {      //no initial value
                objectInstance.setField(new Variable(new VariableName(name.toString()), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type)));  //ToDo array inner type lost here
            } else if (!(fd.getInitializer() instanceof ProblemExpression)) {
                assert ((Literal<?>) fd.getInitializer()).getValue() != null;
                Value value = valueResolver(((Literal<?>) fd.getInitializer()).getValue());
                objectInstance.setField(new Variable(new VariableName(name.toString()), value));
            }
        }
        objectInstance.setAbstractInterpretation(this);
        variables.addVariable(new Variable(new VariableName(rd.getName().toString()), objectInstance));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.System.getName(), new de.jplag.java_cpg.ai.variables.objects.System()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Math.getName(), new de.jplag.java_cpg.ai.variables.objects.Math()));
        variables.addVariable(new Variable(de.jplag.java_cpg.ai.variables.objects.Integer.getName(), new de.jplag.java_cpg.ai.variables.objects.Integer()));
        this.object = objectInstance;
        for (MethodDeclaration md : rd.getMethods()) {
            methods.put(md.getName().getLocalName(), md);
        }
        //Run constructor method //ToDo arguments
        ConstructorDeclaration constr = rd.getConstructors().getFirst();    //ToDo
        List<Node> eog = constr.getNextEOG();
        if (eog.size() == 1) {
            variables.newScope();
            variables.addVariable(new Variable(new VariableName("args"), new JavaArray(de.jplag.java_cpg.ai.variables.Type.STRING)));
            graphWalker(eog.getFirst());
        } else if (eog.isEmpty()) { //empty constructor
            return;
        } else {
            throw new IllegalStateException("Unexpected value: " + eog.size());
        }
        System.out.println("Test");
    }

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
                valueStack.add(valueResolver(l.getValue()));
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case MemberExpression me -> {
                assert nodeStack.getLast() instanceof Reference;
                if (me.getRefersTo() instanceof FieldDeclaration || me.getRefersTo() instanceof EnumConstantDeclaration) {
                    assert valueStack.getLast() instanceof JavaObject;
                    assert nodeStack.getLast() instanceof Reference;
                    nodeStack.removeLast();
                    //like Reference
                    nodeStack.add(me);
                    assert me.getName().getParent() != null;
                    JavaObject javaObject = (JavaObject) valueStack.getLast();
                    valueStack.removeLast();    //remove object reference
                    Value result = javaObject.accessField(me.getName().getLocalName());
                    valueStack.add(result);
                } else if (me.getRefersTo() instanceof MethodDeclaration) {
                    nodeStack.removeLast();
                    nodeStack.add(me);
                } else {
                    throw new IllegalStateException("Unexpected value: " + me.getRefersTo());
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
                    } else {    //unknown reference
                        Declaration x = ref.getRefersTo();
                        assert x instanceof EnumDeclaration;    //ToDo for now
                        JavaObject enumObject = createEnum((EnumDeclaration) x);
                        valueStack.add(enumObject);
                        variables.addVariable(new Variable(new VariableName(ref.getName().toString()), enumObject));
                    }
                }
                nodeStack.add(ref);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case SubscriptExpression se -> {    //adds its value to the value stack
                assert nodeStack.get(nodeStack.size() - 2) instanceof Reference;
                assert nodeStack.getLast() instanceof Literal;
                assert !valueStack.isEmpty();
                IntValue indexLiteral = (IntValue) valueStack.getLast();
                valueStack.removeLast();    //remove index value
                valueStack.removeLast();    //remove array reference
                assert indexLiteral != null;
                Value ref = refResolver((Reference) nodeStack.get(nodeStack.size() - 2));
                assert ref instanceof JavaArray;
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
                    JavaObject javaObject = (JavaObject) valueStack.getLast();
                    result = javaObject.callMethod(memberName.getLocalName(), null);
                } else {
                    assert nodeStack.get(nodeStack.size() - mce.getArguments().size() - 1) instanceof MemberExpression;     //first arguments
                    List<Value> argumentList = new ArrayList<>();
                    for (int i = 0; i < mce.getArguments().size(); i++) {
                        argumentList.add(valueStack.getLast());
                        nodeStack.removeLast();
                        valueStack.removeLast();
                    }
                    assert nodeStack.getLast() instanceof MemberExpression;
                    Name memberName = (nodeStack.getLast()).getName();
                    assert memberName.getParent() != null;
                    assert !valueStack.isEmpty();
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
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case DeclarationStatement ds -> {
                assert !valueStack.isEmpty();
                Variable variable;
                if (nodeStack.get(nodeStack.size() - 2) instanceof VariableDeclaration) {
                    variable = new Variable((nodeStack.get(nodeStack.size() - 2)).getName().toString(), valueStack.getLast());
                } else if (nodeStack.get(nodeStack.size() - 3) instanceof VariableDeclaration) {    //if new expression
                    variable = new Variable((nodeStack.get(nodeStack.size() - 3)).getName().toString(), valueStack.getLast());
                } else {
                    throw new IllegalStateException("Unexpected value: " + nodeStack.get(nodeStack.size() - 2).getClass());
                }
                variables.addVariable(variable);
                nodeStack.removeLast();
                nodeStack.removeLast();
                valueStack.removeLast();
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case AssignExpression ae -> {
                assert valueStack.size() >= 1;
                assert nodeStack.get(nodeStack.size() - 2) instanceof Reference;
                Variable variable = variables.getVariable((nodeStack.get(nodeStack.size() - 2)).getName().toString());
                if (variable == null) { //class access
                    assert nodeStack.get(nodeStack.size() - 2).getName().getParent() != null;
                    VariableName className = new VariableName(nodeStack.get(nodeStack.size() - 2).getName().getParent().toString());
                    JavaObject classVal = (JavaObject) variables.getVariable(className).getValue();
                    classVal.changeField((nodeStack.get(nodeStack.size() - 2)).getName().getLocalName(), valueStack.getLast());
                } else {
                    variable.setValue(valueStack.getLast());
                }
                nodeStack.removeLast();
                nodeStack.removeLast();
                valueStack.removeLast();
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case BinaryOperator bop -> {
                assert valueStack.size() >= 2 && !nodeStack.isEmpty();
                assert valueStack.get(valueStack.size() - 2).getType() == valueStack.getLast().getType();
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
                assert nextEOG.size() == 1;
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
                assert !valueStack.isEmpty() && valueStack.getLast() instanceof BooleanValue;
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
                nodeStack.removeLast();     //removes itself
                assert nextEOG.size() == 2;
                VariableStore beforeIfVariables = new VariableStore(variables);
                VariableStore thenVariables = new VariableStore(variables);
                VariableStore elseVariables = new VariableStore(variables);
                //then statement
                if (runThenBranch && ifStmt.getThenStatement() != null) {
                    variables = thenVariables;
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                }
                //else statement
                if (runElseBranch && ifStmt.getElseStatement() != null) {
                    variables = elseVariables;
                    variables.newScope();
                    graphWalker(nextEOG.getLast());
                }
                //merge branches
                if (runThenBranch && runElseBranch && ifStmt.getThenStatement() != null && ifStmt.getElseStatement() != null) {
                    variables.merge(thenVariables);
                } else if (ifStmt.getThenStatement() != null || ifStmt.getElseStatement() != null) {  //if one branch is not existent, merge with variables before if
                    variables.merge(beforeIfVariables);
                    if (ifStmt.getThenStatement() != null) {
                        nodeStack.add(nextEOG.getLast());
                    } else {
                        nodeStack.add(nextEOG.getLast());
                    }
                }
                nextNode = nodeStack.getLast();
                nodeStack.removeLast();
                if (nodeStack.size() >= 2 && nodeStack.getLast() == nodeStack.get(nodeStack.size() - 2)) {
                    nodeStack.removeLast();
                }
            }
            case SwitchStatement sw -> {
                assert !valueStack.isEmpty();
                int branches = nextEOG.size();


                throw new IllegalStateException("Not implemented yet");
            }
            case Block b -> {
                //assert block is exited
                variables.removeScope();
                assert nextEOG.size() == 1;
                nodeStack.add(nextEOG.getFirst());
                return null;
            }
            case ReturnStatement ret -> {
                //ToDo handle return values
                variables.removeScope();
                Value result;
                if (valueStack.isEmpty()) {
                    result = new VoidValue();
                } else {
                    result = valueStack.getLast();
                    valueStack.removeLast();
                }
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
                if (!((ConstructExpression) nodeStack.getLast()).getArguments().isEmpty()) {
                    for (int i = 0; i < ((ConstructExpression) nodeStack.getLast()).getArguments().size(); i++) {
                        arguments.add(valueStack.getLast());
                        valueStack.removeLast();
                    }
                }
                nodeStack.removeLast();
                JavaObject newObject = new JavaObject();
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
                    VariableStore bodyVariables = new VariableStore(variables);
                    VariableStore oldVariables = variables;
                    variables = bodyVariables;
                    variables.newScope();
                    graphWalker(nextEOG.getFirst());
                    //merge if the loop has been run
                    //for now set all changed variables to unknown
                    //ToDo
                    System.out.println(valueStack.getLast());
                } else {
                    //Dead code detected, loop never runs
                }
                //continue with next node after while
                nextNode = nextEOG.getLast();
            }
            default -> throw new IllegalStateException("Unexpected value: " + node);
        }
        return graphWalker(nextNode);
    }

    private Value refResolver(@NotNull Reference ref) {
        VariableName varName = new VariableName(ref.getName().toString());
        Variable variable = variables.getVariable(varName);
        return variable.getValue();
    }

    private Value valueResolver(Object value) {
        if (value == null) {
            return new NullValue();
        }
        switch (value) {
            case String s -> {
                return new StringValue(s);
            }
            case Integer i -> {
                return new IntValue(i);
            }
            case Boolean b -> {
                return new BooleanValue(b);
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

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

    protected ArrayList<Node> getNodeStack() {
        return nodeStack;
    }

    protected ArrayList<Value> getValueStack() {
        return valueStack;
    }

    protected JavaObject getObject() {
        return object;
    }

    /**
     *
     * @param name
     * @return null if the method is not known.
     */
    public Value runMethod(String name, List<Value> paramVars) {
        MethodDeclaration md = methods.get(name);
        if (md == null) {
            return null;
        }
        variables.newScope();
        assert md.getParameters().size() == paramVars.size();
        for (Value param : paramVars) {
            variables.addVariable(new Variable(new VariableName(md.getName().toString()), param));
        }
        nodeStack.removeLast();
        valueStack.removeLast();
        assert md.getNextEOG().size() == 1;
        return graphWalker(md.getNextEOG().getFirst());
    }

}
