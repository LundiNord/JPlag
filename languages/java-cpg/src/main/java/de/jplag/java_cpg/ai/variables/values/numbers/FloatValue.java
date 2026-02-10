package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Represents a floating point value with optional exact information.
 */
public class FloatValue extends BasicNumberValue implements INumberValue, IFloatNumber {

    private final double value;
    private final boolean information;    // whether exact information is available

    /**
     * a IntValue with no information.
     */
    public FloatValue() {
        super(Type.FLOAT);
        information = false;
        value = 0.0;
    }

    /**
     * Constructor for FloatValue with exact information.
     * @param value the float value.
     */
    public FloatValue(double value) {
        super(Type.FLOAT);
        this.value = value;
        information = true;
    }

    /**
     * Constructor for FloatValue with a range.
     * @param lowerBound the lower bound of the range.
     * @param upperBound the upper bound of the range.
     */
    public FloatValue(double lowerBound, double upperBound) {
        super(Type.FLOAT);
        assert lowerBound <= upperBound;
        if (lowerBound == upperBound) {
            this.value = lowerBound;
            this.information = true;
        } else {
            this.information = false;
            this.value = 0.0;
        }
    }

    /**
     * Constructor for FloatValue with a set of possible values.
     * @param values the set of possible float values.
     */
    public FloatValue(@NotNull Set<Double> values) {
        super(Type.FLOAT);
        if (values.size() == 1) {
            this.value = values.iterator().next();
            this.information = true;
        } else {
            this.information = false;
            this.value = 0.0;
        }
    }

    private FloatValue(double value, boolean information) {
        super(Type.FLOAT);
        this.value = value;
        this.information = information;
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

    @NotNull
    @Override
    public Value copy() {
        return new FloatValue(value, information);
    }

    @Override
    public FloatValue merge(@NotNull IValue other) {
        assert other instanceof FloatValue;
        FloatValue otherFloat = (FloatValue) other;
        if (this.information && otherFloat.information && this.value == otherFloat.value) {
            // keep information
            return this;
        } else {
            return new FloatValue();
        }
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return new FloatValue(0.0);
    }

    @Override
    protected BasicNumberValue getInstanceWithValue(double value) {
        return new FloatValue(value);
    }

    @Override
    protected BasicNumberValue getUnknownInstance() {
        return new FloatValue();
    }

}
