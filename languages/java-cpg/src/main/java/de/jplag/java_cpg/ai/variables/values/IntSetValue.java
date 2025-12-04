package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.helpers.IntInterval;

import java.util.SortedSet;
import java.util.TreeSet;

public class IntSetValue extends NumberSetValue<Integer, IntInterval> {

    public IntSetValue() {
        super(Type.INT);
        values.add(new IntInterval());
    }

    private IntSetValue(TreeSet<IntInterval> values) {
        super(Type.INT, values);
    }

    public IntSetValue(int number) {
        super(Type.INT);
        values.add(new IntInterval(number));
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
    protected NumberSetValue<Integer, IntInterval> createInstance(TreeSet<IntInterval> values) {
        return new IntSetValue(values);
    }

    @Override
    public Value copy() {
        return new IntSetValue(new TreeSet<>(values));
    }

    /**
     * Used for testing.
     */
    public SortedSet<IntInterval> getIntervals() {
        return values;
    }

}
