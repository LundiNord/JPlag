package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.DoubleInterval;

/**
 * Float value represented as a set of intervals.
 * @author ujiqk
 * @version 1.0
 */
public class FloatIntervalSetValue extends NumberIntervalSetValue<Double, DoubleInterval> implements IFloatNumber {

    /**
     * Default constructor c Float value represented as a set of intervals with no information.
     */
    public FloatIntervalSetValue() {
        super(Type.FLOAT);
        values.add(new DoubleInterval());
    }

    private FloatIntervalSetValue(TreeSet<DoubleInterval> values) {
        super(Type.FLOAT, values);
    }

    /**
     * Constructor for FloatIntervalSetValue that is known to be a single number.
     * @param number the single float number
     */
    public FloatIntervalSetValue(double number) {
        super(Type.FLOAT);
        values.add(new DoubleInterval(number));
    }

    /**
     * Constructor for FloatIntervalSetValue that is known to be one of the possible numbers.
     * @param possibleNumbers the possible float numbers
     */
    public FloatIntervalSetValue(@NotNull Set<Double> possibleNumbers) {
        super(Type.INT);
        values = new TreeSet<>();
        // ToDo: slice into intervals
        values.add(new DoubleInterval());
    }

    /**
     * Constructor for FloatIntervalSetValue that is known to be within a certain range.
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     */
    public FloatIntervalSetValue(double lowerBound, double upperBound) {
        super(Type.INT);
        values.add(new DoubleInterval(lowerBound, upperBound));
    }

    @Override
    protected DoubleInterval createFullInterval() {
        return new DoubleInterval();
    }

    @Override
    protected DoubleInterval createInterval(Double lowerBound, Double upperBound) {
        return new DoubleInterval(lowerBound, upperBound);
    }

    @Override
    protected NumberIntervalSetValue<Double, DoubleInterval> createInstance(TreeSet<DoubleInterval> values) {
        return new FloatIntervalSetValue(values);
    }

    @NotNull
    @Override
    public Value copy() {
        return new FloatIntervalSetValue(new TreeSet<>(values));
    }

    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(createInterval(0d, 0d));
    }

    /**
     * Use for testing purposes only.
     * @return the set of intervals representing the float value.
     */
    @TestOnly
    public SortedSet<DoubleInterval> getIntervals() {
        return values;
    }

}
