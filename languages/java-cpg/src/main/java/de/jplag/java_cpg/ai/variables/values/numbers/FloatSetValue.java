package de.jplag.java_cpg.ai.variables.values.numbers;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.DoubleInterval;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FloatSetValue extends NumberSetValue<Double, DoubleInterval> {

    public FloatSetValue() {
        super(Type.FLOAT);
        values.add(new DoubleInterval());
    }

    private FloatSetValue(TreeSet<DoubleInterval> values) {
        super(Type.FLOAT, values);
    }

    public FloatSetValue(double number) {
        super(Type.FLOAT);
        values.add(new DoubleInterval(number));
    }

    public FloatSetValue(@NotNull Set<Double> possibleNumbers) {
        super(Type.INT);
        values = new TreeSet<>();
        //ToDo: slice into intervals
        values.add(new DoubleInterval());
    }

    public FloatSetValue(double lowerBound, double upperBound) {
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
    protected NumberSetValue<Double, DoubleInterval> createInstance(TreeSet<DoubleInterval> values) {
        return new FloatSetValue(values);
    }

    @Override
    public Value copy() {
        return new FloatSetValue(new TreeSet<>(values));
    }

    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(createInterval(0d, 0d));
    }

    /**
     * Used for testing.
     */
    @TestOnly
    public SortedSet<DoubleInterval> getIntervals() {
        return values;
    }

}
