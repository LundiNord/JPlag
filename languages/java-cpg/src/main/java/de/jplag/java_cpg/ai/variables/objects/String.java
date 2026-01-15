package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

/**
 * Representation of the static java.lang.String class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html">Oracle Docs</a>
 */
public class String extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "String";

    /**
     * Creates a new representation of the java.lang.String class.
     */
    public String() {
        super();
    }

    /**
     * @return The variable name java.lang.String
     */
    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars, MethodDeclaration method) {
        switch (methodName) {
            case "format" -> {
                assert !paramVars.isEmpty();
                return new StringValue();
            }
            case "join" -> {
                assert paramVars.size() >= 2;
                assert paramVars.stream().map(StringValue.class::isInstance).reduce(true, (a, b) -> a && b);
                // Possibility 1: first delimiter, then strings to join
                if (paramVars.stream().allMatch(x -> x instanceof StringValue stringValue && stringValue.getInformation())) {
                    java.lang.String joinedString = java.lang.String.join(((StringValue) paramVars.get(0)).getValue(),
                            paramVars.subList(1, paramVars.size()).stream().map(x -> ((StringValue) x).getValue()).toArray(java.lang.String[]::new));
                    return new StringValue(joinedString);
                }
                // ToDo
                // Possibility 2: first delimiter, then iterable of strings to join
                // Possibility 3: (String prefix, String suffix, String delimiter, String[] elements, int size)
                return new StringValue();
            }
            case "valueOf" -> {
                assert paramVars.size() == 1;
                return new StringValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new String();
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof String;
        // Nothing to merge
    }

}
