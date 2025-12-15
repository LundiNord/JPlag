package de.jplag.java_cpg.ai.variables.values.numbers;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.IntInterval;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.Set;
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

    public IntSetValue(@NotNull Set<Integer> possibleNumbers) {
        super(Type.INT);
        values = new TreeSet<>();
        //ToDo: slice into intervals
        values.add(new IntInterval());
    }

    public IntSetValue(int lowerBound, int upperBound) {
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
    protected NumberSetValue<Integer, IntInterval> createInstance(TreeSet<IntInterval> values) {
        return new IntSetValue(values);
    }

    @NotNull
    @Override
    public Value copy() {
        return new IntSetValue(new TreeSet<>(values));
    }

    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(createInterval(0, 0));
    }

    /**
     * Used for testing.
     */
    @TestOnly
    public SortedSet<IntInterval> getIntervals() {
        return values;
    }

}
