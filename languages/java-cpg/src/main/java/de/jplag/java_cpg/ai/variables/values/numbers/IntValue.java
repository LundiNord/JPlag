package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Set;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Represents an integer value with optional exact information.
 * @author ujiqk
 * @version 1.0
 */
public class IntValue extends BasicNumberValue implements INumberValue, IIntNumber {

    private final int value;
    private final boolean information;    // whether exact information is available

    /**
     * a IntValue with no information.
     */
    public IntValue() {
        super(Type.INT);
        information = false;
        value = 0;
    }

    /**
     * Constructor for IntValue with exact information.
     * @param value the integer value.
     */
    public IntValue(int value) {
        super(Type.INT);
        this.value = value;
        information = true;
    }

    /**
     * Constructor for IntValue with exact information from a double value.
     * @param value the integer value as double.
     */
    public IntValue(double value) {
        super(Type.INT);
        this.value = (int) value;
        information = true;
    }

    /**
     * Constructor for IntValue with a set of possible values.
     * @param possibleValues the set of possible integer values.
     */
    public IntValue(@NotNull Set<Integer> possibleValues) {
        super(Type.INT);
        if (possibleValues.size() == 1) {
            this.value = possibleValues.iterator().next();
            this.information = true;
        } else {
            this.information = false;
            value = 0;
        }
    }

    /**
     * Constructor for IntValue with a range.
     * @param lowerBound the lower bound of the range.
     * @param upperBound the upper bound of the range.
     */
    public IntValue(int lowerBound, int upperBound) {
        super(Type.INT);
        assert lowerBound <= upperBound;
        if (lowerBound == upperBound) {
            this.value = lowerBound;
            this.information = true;
        } else {
            this.information = false;
            value = 0;
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

    /**
     * @return the exact value. Only valid if getInformation() returns true.
     */
    public double getValue() {
        assert information;
        return value;
    }

    @NotNull
    @Override
    public Value copy() {
        return new IntValue(value, information);
    }

    @Override
    public IntValue merge(@NotNull IValue other) {
        if (other instanceof JavaObject javaObject) {   // could be an Integer object
            if (javaObject.accessField("value") instanceof IntValue intValue) {
                other = intValue;
            } else {
                return new IntValue();
            }
        }
        assert other instanceof INumberValue;
        INumberValue otherInt = (INumberValue) other;
        if (this.information && otherInt.getInformation() && this.value == otherInt.getValue()) {
            // keep information
            return this;
        } else {
            return new IntValue();
        }
    }

    @Override
    @Pure
    public @NotNull IntValue getInitialValue() {
        return new IntValue(0);
    }

    @Override
    protected BasicNumberValue getInstanceWithValue(double value) {
        return new IntValue((int) value);
    }

    @Override
    protected BasicNumberValue getUnknownInstance() {
        return new IntValue();
    }

}
