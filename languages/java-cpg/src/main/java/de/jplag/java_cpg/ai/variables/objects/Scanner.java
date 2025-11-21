package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Scanner extends JavaObject {

    private static final String PATH = "java.util";
    private static final String NAME = "Scanner";

    public Scanner() {
        super();
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "nextLine" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new StringValue();
            }
            default -> throw new UnsupportedOperationException(methodName + " is not supported in Scanner.");
        }
    }

    @Override
    public Value accessField(@NotNull String fieldName) {
        switch (fieldName) {
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported in Scanner.");
        }
    }

    @Override
    public JavaObject copy() {
        return new Scanner();
    }

}
