package de.jplag.java_cpg.ai.variables.values;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;

/**
 * Boolean value representation with a possible lack of information.
 * @author ujiqk
 * @version 1.0
 */
public class BooleanValue extends Value implements IBooleanValue {

    private boolean value;
    private boolean information;

    /**
     * Creates a BooleanValue without exact information.
     */
    public BooleanValue() {
        super(Type.BOOLEAN);
        information = false;
    }

    /**
     * Creates a BooleanValue with exact information.
     */
    public BooleanValue(boolean value) {
        super(Type.BOOLEAN);
        this.value = value;
        information = true;
    }

    private BooleanValue(boolean value, boolean information) {
        super(Type.BOOLEAN);
        this.value = value;
        this.information = information;
    }

    /**
     * @return whether exact information is available
     */
    public boolean getInformation() {
        return information;
    }

    /**
     * Assumes that exact information is available.
     * @return the boolean value.
     */
    public boolean getValue() {
        assert information;
        return value;
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        if (other instanceof VoidValue) {
            return new BooleanValue();
        }
        BooleanValue otherBool = (BooleanValue) other;
        switch (operator) {
            case "||" -> {
                if (this.getInformation() && otherBool.getInformation()) {
                    return new BooleanValue(this.getValue() || otherBool.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "&&" -> {
                if (this.getInformation() && otherBool.getInformation()) {
                    return new BooleanValue(this.getValue() && otherBool.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "==" -> {
                if (this.getInformation() && otherBool.getInformation()) {
                    return new BooleanValue(this.getValue() == otherBool.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "!=" -> {
                if (this.getInformation() && otherBool.getInformation()) {
                    return new BooleanValue(this.getValue() != otherBool.getValue());
                } else {
                    return new BooleanValue();
                }
            }
            default -> throw new UnsupportedOperationException(
                    "Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return new BooleanValue(value, information);
    }

    @Override
    public void merge(@NotNull IValue other) {
        if (other instanceof VoidValue) {
            this.information = false;
            return;
        }
        assert other instanceof BooleanValue;
        BooleanValue otherBool = (BooleanValue) other;
        if (this.information && otherBool.information && this.value == otherBool.value) {
            // keep information
        } else {
            this.information = false;
        }
    }

    @Override
    public void setToUnknown() {
        this.information = false;
    }

    @Override
    public void setInitialValue() {
        value = false;
        information = true;
    }

    @Pure
    @Override
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "!" -> {
                if (information) {
                    return new BooleanValue(!value);
                } else {
                    return new BooleanValue();
                }
            }
            default -> throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

}
