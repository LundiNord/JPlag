package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.helpers.DoubleInterval;

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

    /**
     * Used for testing.
     */
    public SortedSet<DoubleInterval> getIntervals() {
        return values;
    }

}
