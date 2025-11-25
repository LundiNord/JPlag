package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HashMap extends JavaObject {

    private static final java.lang.String PATH = "java.util";
    private static final java.lang.String NAME = "HashMap";

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "put" -> {
                assert paramVars.size() == 2;
                return new VoidValue();
            }
            case "get" -> {
                assert paramVars.size() == 1;
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }
    
    @Override
    public JavaObject copy() {
        return new HashMap();
    }

}
