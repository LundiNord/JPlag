package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;

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
    public Value accessField(String fieldName) {
        switch (fieldName) {
            case "out" -> {
                return new PrintStream();
            }
            default ->
                    throw new UnsupportedOperationException("Field " + fieldName + " is not supported in " + PATH + "." + NAME);
        }
    }

}
