package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the Java null value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class NullValue extends Value {

    public NullValue() {
        super(Type.NULL);
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        switch (operator) {
            case "==" -> {
                return new BooleanValue(other instanceof NullValue);
            }
            default ->
                    throw new UnsupportedOperationException("Operator " + operator + " not supported for NullValue.");
        }
    }

    @Override
    public Value copy() {
        return new NullValue();
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
        //do nothing
    }

}
