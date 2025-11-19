package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

public class IntValue extends Value implements INumberValue {

    private int value;
    private boolean information;    //whether exact information is available

    /**
     * a IntValue with no information.
     */
    public IntValue() {
        super(Type.INT);
        information = false;
    }

    public IntValue(int value) {
        super(Type.INT);
        this.value = value;
        information = true;
    }

    public IntValue(double value) {
        super(Type.INT);
        this.value = (int) value;
        information = true;
    }

    private IntValue(int value, boolean information) {
        super(Type.INT);
        this.value = value;
        this.information = information;
    }

    @Deprecated //replaced by unaryOperation?
    public IntValue abs() {
        if (information) {
            return new IntValue(Math.abs(value));
        }
        return new IntValue();
    }

    /**
     * @return whether exact information is available
     */
    public boolean getInformation() {
        return information;
    }

    public double getValue() {
        assert information;
        return value;
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        assert other instanceof IntValue;
        switch (operator) {
            case "+" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(this.value + ((IntValue) other).getValue());
                } else {
                    return new IntValue();
                }
            }
            case "<" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value < ((IntValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "-" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(this.value - ((IntValue) other).getValue());
                } else {
                    return new IntValue();
                }
            }
            case "!=" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value != ((IntValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @Override
    @Impure
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "++" -> {
                if (information) {
                    this.value += 1;
                    return new IntValue(this.value);
                } else {
                    return new BooleanValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @Override
    public Value copy() {
        return new IntValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof IntValue;
        IntValue otherInt = (IntValue) other;
        if (this.information && otherInt.information && this.value == otherInt.value) {
            //keep information
        } else {
            this.information = false;
        }
    }

    @Override
    public void setToUnknown() {
        this.information = false;
    }

}
