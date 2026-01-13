package de.jplag.java_cpg.ai.variables.values;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.FloatValue;
import de.jplag.java_cpg.ai.variables.values.numbers.IntValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

/**
 * Void typed value. Represents no value or completely unknown value.
 * @author ujiqk
 * @version 1.0
 */
public class VoidValue extends Value {

    public VoidValue() {
        super(Type.VOID);
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        switch (operator) {
            case "==", ">", "<", ">=", "<=", "!=" -> {
                return new BooleanValue();
            }
            case "+", "-", "*", "/" -> {
                return switch (other) {
                    case IntValue ignored -> new IntValue();
                    case FloatValue ignored -> new FloatValue();
                    case StringValue ignored -> new StringValue();
                    default -> new VoidValue();
                };
            }
            case "&", "^" -> {
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException("Operator " + operator + " not supported for VoidValue.");
        }
    }

    @Override
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "!" -> {
                return new BooleanValue();
            }
            case "--", "++", "abs", "+", "-" -> {
                return new VoidValue();
            }
            default -> throw new IllegalArgumentException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return new VoidValue();
    }

    @Override
    public void merge(@NotNull IValue other) {
        // do nothing
    }

    @Override
    public void setToUnknown() {
        // do nothing
    }

    @Override
    public void setInitialValue() {
        // nothing
    }

}
