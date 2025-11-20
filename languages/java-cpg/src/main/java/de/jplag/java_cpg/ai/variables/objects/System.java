package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

public class System extends JavaObject {

    private static final String PATH = "java.lang";
    private static final String NAME = "System";

    public System() {
        super();
    }

    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value accessField(@NotNull String fieldName) {
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
