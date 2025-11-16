package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IntValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Math extends JavaObject {

    private static final String PATH = "java.lang";
    private static final String NAME = "Math";

    public Math() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        assert methodName != null;
        switch (methodName) {
            case "abs" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof IntValue;
                return abs((IntValue) paramVars.getFirst());
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Pure
    private IntValue abs(@NotNull IntValue x) {
        return x.abs();
    }

    @Override
    public JavaObject copy() {
        return new Math();
    }

}
