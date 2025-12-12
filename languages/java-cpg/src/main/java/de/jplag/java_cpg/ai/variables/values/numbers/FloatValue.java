package de.jplag.java_cpg.ai.variables.values.numbers;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Represents a floating point value with optional exact information.
 */
public class FloatValue extends Value implements INumberValue {

    private double value;
    private boolean information;    //whether exact information is available

    /**
     * a IntValue with no information.
     */
    public FloatValue() {
        super(Type.FLOAT);
        information = false;
    }

    public FloatValue(double value) {
        super(Type.FLOAT);
        this.value = value;
        information = true;
    }

    public FloatValue(double lowerBound, double upperBound) {
        super(Type.FLOAT);
        assert lowerBound <= upperBound;
        if (lowerBound == upperBound) {
            this.value = lowerBound;
            this.information = true;
        } else {
            this.information = false;
        }
    }

    public FloatValue(@NotNull Set<Double> values) {
        super(Type.FLOAT);
        if (values.size() == 1) {
            this.value = values.iterator().next();
            this.information = true;
        } else {
            this.information = false;
        }
    }

    private FloatValue(double value, boolean information) {
        super(Type.FLOAT);
        this.value = value;
        this.information = information;
    }

    @Deprecated //replaced by unaryOperation?
    public FloatValue abs() {
        if (information) {
            return new FloatValue(Math.abs(value));
        }
        return new FloatValue();
    }

    /**
     * @return whether exact information is available.
     */
    public boolean getInformation() {
        return information;
    }

    /**
     * @return the value. Only call if information is true.
     */
    public double getValue() {
        assert information;
        return value;
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (other instanceof VoidValue) {
            return new VoidValue();
        }
        assert other instanceof FloatValue || other instanceof IntValue;
        switch (operator) {
            case "+" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new FloatValue(this.value + ((INumberValue) other).getValue());
                } else {
                    return new FloatValue();
                }
            }
            case "<" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new BooleanValue(this.value < ((INumberValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new BooleanValue(this.value > ((INumberValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "-" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new FloatValue(this.value - ((INumberValue) other).getValue());
                } else {
                    return new FloatValue();
                }
            }
            case "!=" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new BooleanValue(this.value != ((INumberValue) other).getValue());
                } else {
                    return new BooleanValue();
                }
            }
            case "*" -> {
                if (information && ((INumberValue) other).getInformation()) {
                    return new FloatValue(this.value * ((INumberValue) other).getValue());
                } else {
                    return new FloatValue();
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
                    return new FloatValue(this.value);
                } else {
                    return new BooleanValue();
                }
            }
            case "-" -> {
                if (information) {
                    this.value = -this.value;
                    return new FloatValue(this.value);
                } else {
                    return new FloatValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @Override
    public Value copy() {
        return new FloatValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof FloatValue;
        FloatValue otherFloat = (FloatValue) other;
        if (this.information && otherFloat.information && this.value == otherFloat.value) {
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
        value = 0.0;
        information = true;
    }

}
