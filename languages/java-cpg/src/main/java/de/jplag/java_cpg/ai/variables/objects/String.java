package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class String extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "String";

    public String() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
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
                    java.lang.String joinedString = java.lang.String.join(
                            ((StringValue) paramVars.get(0)).getValue(),
                            paramVars.subList(1, paramVars.size()).stream()
                                    .map(x -> ((StringValue) x).getValue())
                                    .toArray(java.lang.String[]::new)
                    );
                    return new StringValue(joinedString);
                }
                //ToDo
                // Possibility 2: first delimiter, then iterable of strings to join
                // Possibility 3: (String prefix, String suffix, String delimiter, String[] elements, int size)
                return new StringValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public JavaObject copy() {
        return new String();
    }

}
