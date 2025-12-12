package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Boolean extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "Boolean";

    public Boolean() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "parseBoolean" -> {
                assert paramVars.size() == 1;
                Value value = paramVars.getFirst();
                switch (value) {
                    case IStringValue str -> {
                        return str.callMethod("parseBoolean", paramVars);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + value);
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public JavaObject copy() {
        return new Boolean();
    }

}
