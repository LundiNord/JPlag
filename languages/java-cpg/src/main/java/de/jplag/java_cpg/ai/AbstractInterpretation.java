package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.graph.Name;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.*;
import de.fraunhofer.aisec.cpg.graph.statements.DeclarationStatement;
import de.fraunhofer.aisec.cpg.graph.statements.IfStatement;
import de.fraunhofer.aisec.cpg.graph.statements.ReturnStatement;
import de.fraunhofer.aisec.cpg.graph.statements.SwitchStatement;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.*;
import de.fraunhofer.aisec.cpg.graph.types.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbstractInterpretation {

    private final ArrayList<Node> nodeStack;    //Stack for EOG traversal
    private final ArrayList<Value> valueStack;  //Stack for values during EOG traversal
    private VariableStore variables;      //Scoped variable store

    public AbstractInterpretation() {
        variables = new VariableStore();
        nodeStack = new ArrayList<>();
        valueStack = new ArrayList<>();
    }

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
                        mainClassVar.setField(new Variable(name.getLocalName(), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type)));
                    } else {
                        assert ((Literal<?>) fd.getInitializer()).getValue() != null;
                        Value value = valueResolver(((Literal<?>) fd.getInitializer()).getValue());
                        mainClassVar.setField(new Variable(name.getLocalName(), value));
                    }
                }
                variables.addVariable(new Variable("Main", mainClassVar));
                variables.addVariable(new Variable("System", new de.jplag.java_cpg.ai.variables.objects.System()));
                variables.addVariable(new Variable("Math", new de.jplag.java_cpg.ai.variables.objects.Math()));
                variables.addVariable(new Variable("Integer", new de.jplag.java_cpg.ai.variables.objects.Integer()));
                assert mainClas.getMethods().stream().map(MethodDeclaration::getName)
                        .filter(x -> x.getLocalName().equals("main")).count() == 1;
                for (MethodDeclaration md : mainClas.getMethods()) {
                    if (md.getName().getLocalName().equals("main")) {
                        //Run main method
                        List<Node> eog = md.getNextEOG();
                        assert eog.size() == 1;
                        variables.newScope();
                        variables.addVariable(new Variable("args", new JavaArray(de.jplag.java_cpg.ai.variables.Type.STRING)));
                        graphWalker(eog.getFirst());
                    }
                }
                System.out.println("Test");
            }
            //ignore include declaration for now
        }
    }

    private void runClass(RecordDeclaration rd, JavaObject objectInstance) {
        assert !rd.getConstructors().isEmpty();

        for (FieldDeclaration fd : rd.getFields()) {
            Type type = fd.getType();
            Name name = fd.getName();
            if (fd.getInitializer() == null) {      //no initial value
                objectInstance.setField(new Variable(name.getLocalName(), de.jplag.java_cpg.ai.variables.Type.fromCpgType(type)));  //ToDo array inner type lost here
            } else if (!(fd.getInitializer() instanceof ProblemExpression)) {
                assert ((Literal<?>) fd.getInitializer()).getValue() != null;
                Value value = valueResolver(((Literal<?>) fd.getInitializer()).getValue());
                objectInstance.setField(new Variable(name.getLocalName(), value));
            }
        }
        variables.addVariable(new Variable(rd.getName().getLocalName(), objectInstance));
        variables.addVariable(new Variable("System", new de.jplag.java_cpg.ai.variables.objects.System()));
        variables.addVariable(new Variable("Math", new de.jplag.java_cpg.ai.variables.objects.Math()));
        variables.addVariable(new Variable("Integer", new de.jplag.java_cpg.ai.variables.objects.Integer()));

        //Run constructor method
        ConstructorDeclaration constr = rd.getConstructors().getFirst();    //ToDo
        List<Node> eog = constr.getNextEOG();
        assert eog.size() == 1;
        variables.newScope();
        variables.addVariable(new Variable("args", new JavaArray(de.jplag.java_cpg.ai.variables.Type.STRING)));
        graphWalker(eog.getFirst());

        System.out.println("Test");
    }

    private void graphWalker(@NotNull Node node) {
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
                assert l.getValue() != null;
                valueStack.add(valueResolver(l.getValue()));
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case MemberExpression me -> {
                assert nodeStack.getLast() instanceof Reference;
                if (me.getRefersTo() instanceof FieldDeclaration) {
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
                nodeStack.add(ref);
                valueStack.add(variables.getVariable(ref.getName().toString()).getValue());
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
            case MemberCallExpression mce -> {  //adds its value to the value stack
                assert nodeStack.get(nodeStack.size() - 2) instanceof MemberExpression;
                Name memberName = (nodeStack.get(nodeStack.size() - 2)).getName();
                assert memberName.getParent() != null;              //ToDo refactor to use variable store
                //JavaObject javaObject = (JavaObject) variables.getVariable(memberName.getParent().getLocalName()).getValue();   //for now only one parameter
                JavaObject javaObject = (JavaObject) valueStack.get(valueStack.size() - 2);
                assert !valueStack.isEmpty();
                Value result = javaObject.callMethod(memberName.getLocalName(), List.of(valueStack.getLast()));
                valueStack.removeLast();    //remove parameter
                valueStack.removeLast();    //remove object reference
                valueStack.add(result);
                nodeStack.removeLast();
                nodeStack.removeLast();
                nodeStack.add(mce);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case DeclarationStatement ds -> {
                assert !valueStack.isEmpty();
                assert nodeStack.get(nodeStack.size() - 2) instanceof VariableDeclaration;
                Variable variable = new Variable((nodeStack.get(nodeStack.size() - 2)).getName().toString(), valueStack.getLast());
                variables.addVariable(variable);
                nodeStack.removeLast();
                nodeStack.removeLast();
                valueStack.removeLast();
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case AssignExpression ae -> {
                assert valueStack.size() >= 2;
                assert nodeStack.get(nodeStack.size() - 2) instanceof Reference;
                Variable variable = variables.getVariable((nodeStack.get(nodeStack.size() - 2)).getName().toString());
                variable.setValue(valueStack.getLast());
                nodeStack.removeLast();
                nodeStack.removeLast();
                valueStack.removeLast();
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
                    assert nodeStack.getLast() == nodeStack.get(nodeStack.size() - 2);
                    nodeStack.removeLast();
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
                throw new IllegalStateException("Not implemented yet");
            }
            case Block b -> {
                //assert block is exited
                variables.removeScope();
                assert nextEOG.size() == 1;
                nodeStack.add(nextEOG.getFirst());
                return;
            }
            case ReturnStatement ret -> {
                //ToDo handle return values
                variables.removeScope();
                return;
            }
            case ConstructExpression ce -> {
                nodeStack.add(ce);
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            case NewExpression ne -> {
                Declaration classNode = ((ConstructExpression) nodeStack.getLast()).getInstantiates();
                assert classNode != null;
                nodeStack.removeLast();
                nodeStack.add(ne);
                //ToDo parameters
                JavaObject newObject = new JavaObject();
                valueStack.add(newObject);
                //run constructor
                AbstractInterpretation classAi = new AbstractInterpretation();
                classAi.runClass((RecordDeclaration) classNode, newObject);
                //
                assert nextEOG.size() == 1;
                nextNode = nextEOG.getFirst();
            }
            default -> throw new IllegalStateException("Unexpected value: " + node);
        }
        graphWalker(nextNode);
    }

    private Value refResolver(@NotNull Reference ref) {
        String varName = ref.getName().toString();
        Variable variable = variables.getVariable(varName);
        return variable.getValue();
    }

    private Value valueResolver(@NotNull Object value) {
        switch (value) {
            case String s -> {
                return new StringValue(s);
            }
            case Integer i -> {
                return new IntValue(i);
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

}
