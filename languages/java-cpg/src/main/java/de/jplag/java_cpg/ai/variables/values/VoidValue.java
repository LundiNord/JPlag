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
            case "==", ">", "<", ">=", "<=", "!=" -> {
                return new BooleanValue();
            }
            case "-" -> {
                if (other instanceof IntValue) {
                    return new IntValue();
                } else if (other instanceof FloatValue) {
                    return new FloatValue();
                }
                return new VoidValue();
            }
            case "+" -> {
                return switch (other) {
                    case IntValue ignored -> new IntValue();
                    case FloatValue ignored -> new FloatValue();
                    case StringValue ignored -> new StringValue();
                    default -> new VoidValue();
                };
            }
            default ->
                    throw new UnsupportedOperationException("Operator " + operator + " not supported for VoidValue.");
        }
    }

    @Override
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "!" -> {
                return new BooleanValue();
            }
            default ->
                    throw new IllegalArgumentException("Unary operation " + operator + " not supported for " + getType());
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
