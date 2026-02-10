package de.jplag.java_cpg.ai.variables.values;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.IFloatNumber;
import de.jplag.java_cpg.ai.variables.values.numbers.IIntNumber;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;

/**
 * UnknownValue typed value. Represents completely unknown value.
 * @author ujiqk
 * @version 1.0
 */
public class UnknownValue extends Value {

    /**
     * Creates a new Void typed value. Represents no value or completely unknown value.
     */
    public UnknownValue() {
        super(Type.VOID);
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        switch (operator) {
            case "==", ">", "<", ">=", "<=", "!=", "instanceof" -> {
                return new BooleanValue();
            }
            case "+", "-", "*", "/", "%" -> {
                return switch (other) {
                    case IIntNumber _ -> Value.valueFactory(Type.INT);
                    case IFloatNumber _ -> Value.valueFactory(Type.FLOAT);
                    case IStringValue _ -> Value.valueFactory(Type.STRING);
                    default -> this;
                };
            }
            case "&", "^" -> {
                return this;
            }
            default -> throw new UnsupportedOperationException("Operator " + operator + " not supported for UnknownValue.");
        }
    }

    @Override
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "!" -> {
                return new BooleanValue();
            }
            case "--", "++", "abs", "+", "-", "throw" -> {
                return this;
            }
            default -> throw new IllegalArgumentException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return this;
    }

    @Override
    public UnknownValue merge(@NotNull IValue other) {
        return this;
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return this;
    }

}
