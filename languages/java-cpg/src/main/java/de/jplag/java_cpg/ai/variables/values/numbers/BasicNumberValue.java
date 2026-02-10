package de.jplag.java_cpg.ai.variables.values.numbers;

import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;

public abstract class BasicNumberValue extends Value implements INumberValue {

    protected BasicNumberValue(@NotNull Type type) {
        super(type);
    }

    protected abstract BasicNumberValue getInstanceWithValue(double value);

    protected abstract BasicNumberValue getUnknownInstance();

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        INumberValue otherNumber = (INumberValue) other;
        switch (operator) {
            case "+" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(this.getValue() + otherNumber.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "<" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() < otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() > otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "-" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(this.getValue() - otherNumber.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "!=" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() != otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "==" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() == otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "*" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(this.getValue() * otherNumber.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "/" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(this.getValue() / otherNumber.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "<=" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() <= otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case ">=" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return new BooleanValue(this.getValue() >= otherNumber.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "max" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(Math.max(this.getValue(), otherNumber.getValue()));
                } else {
                    return getUnknownInstance();
                }
            }
            case "min" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(Math.min(this.getValue(), otherNumber.getValue()));
                } else {
                    return getUnknownInstance();
                }
            }
            case "%" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(this.getValue() % otherNumber.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "pow" -> {
                if (getInformation() && otherNumber.getInformation()) {
                    return getInstanceWithValue(Math.pow(this.getValue(), otherNumber.getValue()));
                } else {
                    return getUnknownInstance();
                }
            }
            default -> throw new UnsupportedOperationException(
                    "Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @Override
    @Impure
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "++" -> {
                if (getInformation()) {
                    return getInstanceWithValue(this.getValue() + 1);
                } else {
                    return getUnknownInstance();
                }
            }
            case "--" -> {
                if (getInformation()) {
                    return getInstanceWithValue(this.getValue() - 1);
                } else {
                    return getUnknownInstance();
                }
            }
            case "-" -> {
                if (getInformation()) {
                    return getInstanceWithValue(-this.getValue());
                } else {
                    return getUnknownInstance();
                }
            }
            case "abs" -> {
                if (getInformation()) {
                    return getInstanceWithValue(Math.abs(this.getValue()));
                } else {
                    return getUnknownInstance();
                }
            }
            case "sin" -> {
                if (getInformation()) {
                    return Value.valueFactory(Math.sin(this.getValue()));
                } else {
                    return Value.valueFactory(Type.FLOAT);
                }
            }
            case "sqrt" -> {
                if (getInformation()) {
                    return Value.valueFactory(Math.sqrt(this.getValue()));
                } else {
                    return Value.valueFactory(Type.FLOAT);
                }
            }
            default -> throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

}
