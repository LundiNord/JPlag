package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Math extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "Math";

    public Math() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "abs" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof INumberValue;
                return paramVars.getFirst().unaryOperation("abs");
            }
            case "min" -> {
                assert paramVars.size() == 2;
                assert paramVars.get(0) instanceof INumberValue;
                assert paramVars.get(1) instanceof INumberValue;
                return paramVars.get(0).binaryOperation("min", paramVars.get(1));
            }
            case "max" -> {
                assert paramVars.size() == 2;
                assert paramVars.get(0) instanceof INumberValue;
                assert paramVars.get(1) instanceof INumberValue || paramVars.get(1) instanceof VoidValue;
                return paramVars.get(0).binaryOperation("max", paramVars.get(1));
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Math();
    }

}
