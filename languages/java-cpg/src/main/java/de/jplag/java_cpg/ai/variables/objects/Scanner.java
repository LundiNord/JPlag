package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Scanner extends JavaObject {

    private static final java.lang.String PATH = "java.util";
    private static final java.lang.String NAME = "Scanner";

    public Scanner() {
        super();
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "nextLine" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new StringValue();
            }
            case "close" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException(methodName + " is not supported in Scanner.");
        }
    }

    @Override
    public Value accessField(@NotNull java.lang.String fieldName) {
        switch (fieldName) {
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported in Scanner.");
        }
    }

    @Override
    public JavaObject copy() {
        return new Scanner();
    }

}
