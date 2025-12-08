package de.jplag.java_cpg.ai.variables.values.numbers.helpers;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

public abstract class Interval<T extends Number & Comparable<T>> implements Comparable<Interval<T>> {

    protected T lowerBound;
    protected T upperBound;

    public abstract boolean getInformation();

    public abstract T getValue();

    public abstract Interval<T> copy();

    public abstract void setToUnknown();

    @Pure
    public abstract Interval<T> plus(@NotNull Interval<T> other);

    @Pure
    public abstract Interval<T> minus(@NotNull Interval<T> other);

    @Pure
    public abstract Interval<T> times(@NotNull Interval<T> other);

    @Pure
    public abstract Interval<T> divided(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue equal(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue notEqual(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue smaller(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue smallerEqual(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue bigger(@NotNull Interval<T> other);

    @Pure
    public abstract BooleanValue biggerEqual(@NotNull Interval<T> other);

    @Impure
    public abstract Interval<T> plusPlus();

    @Impure
    public abstract Interval<T> minusMinus();

    @Pure
    public abstract Interval<T> unaryMinus();

    @Pure
    public abstract Interval<T> abs();

    @Impure
    public abstract void merge(@NotNull Interval<T> other);

    public T getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(@NotNull T lowerBound) {
        assert lowerBound.compareTo(upperBound) <= 0;
        this.lowerBound = lowerBound;
    }

    public T getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(T upperBound) {
        assert lowerBound.compareTo(upperBound) <= 0;
        this.upperBound = upperBound;
    }

}
