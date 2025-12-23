package de.jplag.java_cpg.ai.variables.values.numbers;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

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

    public IntValue(@NotNull Set<Integer> possibleValues) {
        super(Type.INT);
        if (possibleValues.size() == 1) {
            this.value = possibleValues.iterator().next();
            this.information = true;
        } else {
            this.information = false;
        }
    }

    public IntValue(int lowerBound, int upperBound) {
        super(Type.INT);
        if (lowerBound == upperBound) {
            this.value = lowerBound;
            this.information = true;
        } else {
            this.information = false;
        }
    }

    private IntValue(int value, boolean information) {
        super(Type.INT);
        this.value = value;
        this.information = information;
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
        if (!(other instanceof INumberValue)) {
            other = new IntValue();
        }
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
            case ">" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value > ((IntValue) other).getValue());
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
            case "==" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value == ((IntValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "*" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(this.value * ((IntValue) other).getValue());
                } else {
                    return new IntValue();
                }
            }
            case "/" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(this.value / ((IntValue) other).getValue());
                } else {
                    return new IntValue();
                }
            }
            case "<=" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value <= ((IntValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case ">=" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new BooleanValue(this.value >= ((IntValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "max" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(Math.max(this.value, ((IntValue) other).getValue()));
                } else {
                    return new IntValue();
                }
            }
            case "min" -> {
                if (information && ((IntValue) other).getInformation()) {
                    return new IntValue(Math.min(this.value, ((IntValue) other).getValue()));
                } else {
                    return new IntValue();
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
                    return new IntValue();
                }
            }
            case "--" -> {
                if (information) {
                    this.value -= 1;
                    return new IntValue(this.value);
                } else {
                    return new IntValue();
                }
            }
            case "-" -> {
                if (information) {
                    this.value = -this.value;
                    return new IntValue(this.value);
                } else {
                    return new IntValue();
                }
            }
            case "abs" -> {
                if (information) {
                    return new IntValue(Math.abs(this.value));
                } else {
                    return new IntValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return new IntValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        if (other instanceof VoidValue) {
            this.information = false;
            return;
        }
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

    @Override
    public void setInitialValue() {
        value = 0;
        information = true;
    }

}
