package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

public class System extends JavaObject {

    private static final String PATH = "java.lang";
    private static final String NAME = "System";

    public System() {
        super();
    }

//    @Override
//    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
//        switch (methodName) {
//            default -> throw new UnsupportedOperationException(methodName);
//        }
//    }

    @Override
    public Value accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "out", "err" -> {
                return new PrintStream();
            }
            default ->
                    throw new UnsupportedOperationException("Field " + fieldName + " is not supported in " + PATH + "." + NAME);
        }
    }

}
