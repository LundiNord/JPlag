package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.IntInterval;

/**
 * Integer value represented as a set of intervals.
 * @author ujiqk
 * @version 1.0
 */
public class IntIntervalSetValue extends NumberIntervalSetValue<Integer, IntInterval> implements IIntNumber {

    /**
     * Integer value represented as a set of intervals with no information.
     */
    public IntIntervalSetValue() {
        super(Type.INT);
        values.add(new IntInterval());
    }

    private IntIntervalSetValue(TreeSet<IntInterval> values) {
        super(Type.INT, values);
    }

    /**
     * Constructor for IntIntervalSetValue that is known to be a single number.
     * @param number the single integer number
     */
    public IntIntervalSetValue(int number) {
        super(Type.INT);
        values.add(new IntInterval(number));
    }

    /**
     * Constructor for IntIntervalSetValue that is known to be one of the possible numbers.
     * @param possibleNumbers the possible integer numbers
     */
    public IntIntervalSetValue(@NotNull Set<Integer> possibleNumbers) {
        super(Type.INT);
        values = new TreeSet<>();
        // ToDo: slice into intervals
        values.add(new IntInterval());
    }

    /**
     * Constructor for IntIntervalSetValue that is known to be within a certain range.
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     */
    public IntIntervalSetValue(int lowerBound, int upperBound) {
        super(Type.INT);
        values.add(new IntInterval(lowerBound, upperBound));
    }

    @Override
    protected IntInterval createFullInterval() {
        return new IntInterval();
    }

    @Override
    protected IntInterval createInterval(Integer lowerBound, Integer upperBound) {
        return new IntInterval(lowerBound, upperBound);
    }

    @Override
    protected NumberIntervalSetValue<Integer, IntInterval> createInstance(TreeSet<IntInterval> values) {
        return new IntIntervalSetValue(values);
    }

    @NotNull
    @Override
    public Value copy() {
        return new IntIntervalSetValue(new TreeSet<>(values));
    }

    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(createInterval(0, 0));
    }

    /**
     * Use for testing only!
     * @return the set of intervals representing the integer value.
     */
    @TestOnly
    public SortedSet<IntInterval> getIntervals() {
        return values;
    }

}
