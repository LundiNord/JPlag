package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Double extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "Double";

    public Double() {
        super();
    }

    @NotNull
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "parseDouble" -> {
                assert paramVars.size() == 1;
                Value value = paramVars.getFirst();
                switch (value) {
                    case StringValue str -> {
                        return str.callMethod("parseDouble", paramVars);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + value);
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Double();
    }

    @Override
    public void merge(@NotNull Value other) {
        // Nothing to merge
    }

}
