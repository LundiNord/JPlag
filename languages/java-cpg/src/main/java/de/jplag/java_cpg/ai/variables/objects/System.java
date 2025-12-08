package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class System extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "System";

    public System() {
        super();
    }

    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "lineSeparator" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new StringValue("\n");
            }
            default ->
                    throw new UnsupportedOperationException(methodName + " is not supported in " + PATH + "." + NAME);
        }
    }

    @Override
    public Value accessField(@NotNull java.lang.String fieldName) {
        switch (fieldName) {
            case "out", "err" -> {
                return new PrintStream();
            }
            case "in" -> {
                return new InputStream();
            }
            default ->
                    throw new UnsupportedOperationException("Field " + fieldName + " is not supported in " + PATH + "." + NAME);
        }
    }

    @Override
    public JavaObject copy() {
        return new System();
    }

}
