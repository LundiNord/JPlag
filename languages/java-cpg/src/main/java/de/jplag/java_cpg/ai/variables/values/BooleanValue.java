package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

/**
 * Boolean value representation with a possible lack of information.
 *
 * @author ujiqk
 * @version 1.0
 */
public class BooleanValue extends Value {

    private boolean value;
    private boolean information;

    public BooleanValue() {
        super(Type.BOOLEAN);
        information = false;
    }

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

    public boolean getValue() {
        assert information;
        return value;
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
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
            default ->
                    throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
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
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return new BooleanValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        if (other instanceof VoidValue) {
            this.information = false;
            return;
        }
        assert other instanceof BooleanValue;
        BooleanValue otherBool = (BooleanValue) other;
        if (this.information && otherBool.information && this.value == otherBool.value) {
            //keep information
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

}
