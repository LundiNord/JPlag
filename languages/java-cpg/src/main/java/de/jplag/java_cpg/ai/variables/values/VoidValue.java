package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

/**
 * Void typed value.
 * Represents no value or completely unknown value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class VoidValue extends Value {

    public VoidValue() {
        super(Type.VOID);
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        switch (operator) {
            case "==" -> {
                return new BooleanValue();
            }
            default ->
                    throw new UnsupportedOperationException("Operator " + operator + " not supported for VoidValue.");
        }
    }

    @Override
    public Value copy() {
        return new VoidValue();
    }

    @Override
    public void merge(@NotNull Value other) {
        //do nothing
    }

    @Override
    public void setToUnknown() {
        //do nothing
    }

    @Override
    public void setInitialValue() {
        //nothing
    }

}
