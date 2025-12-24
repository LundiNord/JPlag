package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * Representation of the static java.lang.Math class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html">Oracle Docs</a>
 */
public class Math extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "Math";

    public Math() {
        super();
    }

    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars) {
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

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof Math;
        // Nothing to merge
    }

}
