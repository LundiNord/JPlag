package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.graph.Name;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.*;
import de.fraunhofer.aisec.cpg.graph.statements.DeclarationStatement;
import de.fraunhofer.aisec.cpg.graph.statements.IfStatement;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.*;
import de.fraunhofer.aisec.cpg.graph.types.ObjectType;
import de.fraunhofer.aisec.cpg.graph.types.StringType;
import de.fraunhofer.aisec.cpg.graph.types.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.objects.Math;
import de.jplag.java_cpg.ai.variables.values.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbstractInterpretation {

    private final VariableStore variables;      //Scoped variable store
    private final ArrayList<Node> nodeStack;    //Stack for EOG traversal
    private final ArrayList<Value> valueStack;  //Stack for values during EOG traversal

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
                for (FieldDeclaration fd : mainClas.getFields()) {
                    Type type = fd.getType();
                    Name name = fd.getName();
                    if (fd.getInitializer() == null) {      //no initial value
                        addVariable(type, name, null);
                    } else {
                        String value = fd.getInitializer().getCode();   //FixMe hacky way please fix me - only works for string constants
                        addVariable(type, name, value);
                    }
                    System.out.println("Test");
                }
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

    @Deprecated //FixMe
    private void addVariable(Type type, Name name, String code) {
        assert name != null;
        assert type != null;
        switch (type) {
            case StringType ignored -> {
                String value = code;
                if (value != null && value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                } else {
                    value = null;
                }
                variables.addVariable(new Variable(name.toString(), value));
            }
            case ObjectType ignored -> {
                if (name.getParent() == null && name.getLocalName().equals("Math")) {   //special case java.lang.Math
                    variables.addVariable(new Variable(name.toString(), new Math()));   //ToDo: what if Math class present?
                    break;
                }
                if (name.getParent() == null && name.getLocalName().equals("Integer")) {   //special case java.lang.Math
                    variables.addVariable(new Variable(name.toString(), new de.jplag.java_cpg.ai.variables.objects.Integer()));
                    break;
                }
                //ToDo: handle different object types
                variables.addVariable(new Variable(name.toString(), new JavaObject()));    //for now only null
            }
            default -> throw new IllegalStateException("Unknown type: " + type);
        }
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
                nodeStack.removeLast();
                valueStack.removeLast();    //remove object reference
                nodeStack.add(me);
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
                assert memberName.getParent() != null;
                JavaObject javaObject = (JavaObject) variables.getVariable(memberName.getParent().getLocalName()).getValue();   //for now only one parameter
                assert !valueStack.isEmpty();
                Value result = javaObject.callMethod(memberName.getLocalName(), List.of(valueStack.getLast()));
                valueStack.removeLast();
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
                nodeStack.removeLast();
                //nodeStack.removeLast();   //ToDo: only sometimes
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
                if (condition.getInformation()) {
                    if (condition.getValue()) {
                        nextNode = ifStmt.getThenStatement();   //Block
                    } else {
                        nextNode = ifStmt.getElseStatement();
                    }
                } else {
                    
                }


                assert nextEOG.size() > 1;
                nextNode = nextEOG.getFirst();  //ToDo
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
