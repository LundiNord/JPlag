package de.jplag.java_cpg.ai.variables.values.numbers.helpers;

import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;

/**
 * Interval implementation for Double values.
 * @author ujiqk
 * @version 1.0
 */
public class DoubleInterval extends Interval<Double> {

    /**
     * The maximum value for Double intervals.
     */
    public static final double MAX_VALUE = Double.MAX_VALUE;
    /**
     * The minimum value for Double intervals.
     */
    public static final double MIN_VALUE = -Double.MAX_VALUE;

    /**
     * Creates a new Double interval representing the whole range of Double values.
     */
    public DoubleInterval() {
        super(MIN_VALUE, MAX_VALUE);
    }

    /**
     * Creates a new Double interval representing a single number.
     * @param number the number
     */
    public DoubleInterval(double number) {
        super(number, number);
    }

    /**
     * Creates a new Double interval with the given bounds.
     * @param lowerBound the lower bound.
     * @param upperBound the upper bound.
     */
    public DoubleInterval(double lowerBound, double upperBound) {
        assert lowerBound <= upperBound;
        super(lowerBound, upperBound);
    }

    /**
     * Safely extracts the lower bound as a double value. Handles both Double and Integer intervals due to type erasure.
     */
    @Pure
    private static double getLowerBound(@NotNull final Interval<Double> interval) {
        return ((Number) interval.lowerBound).doubleValue();
    }

    /**
     * Safely extracts the upper bound as a double value. Handles both Double and Integer intervals due to type erasure.
     */
    @Pure
    private static double getUpperBound(@NotNull final Interval<Double> interval) {
        return ((Number) interval.upperBound).doubleValue();
    }

    @Override
    public boolean getInformation() {
        return lowerBound.equals(upperBound);
    }

    @Override
    public Double getValue() {
        assert lowerBound.equals(upperBound);
        return lowerBound;
    }

    @Override
    public DoubleInterval copy() {
        return new DoubleInterval(lowerBound, upperBound);
    }

    @Pure
    @Override
    public DoubleInterval plus(@NotNull Interval<Double> other) {
        double lo = lowerBound + getLowerBound(other);
        double hi = upperBound + getUpperBound(other);
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval minus(@NotNull Interval<Double> other) {
        double lo = lowerBound - getUpperBound(other);
        double hi = upperBound - getLowerBound(other);
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval times(@NotNull Interval<Double> other) {
        double p1 = lowerBound * getLowerBound(other);
        double p2 = lowerBound * getUpperBound(other);
        double p3 = upperBound * getLowerBound(other);
        double p4 = upperBound * getUpperBound(other);
        double lo = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        double hi = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval divided(@NotNull Interval<Double> other) {
        if (getLowerBound(other) <= 0 && getUpperBound(other) >= 0) {
            return new DoubleInterval(MIN_VALUE, MAX_VALUE);
        }
        double p1 = lowerBound / getLowerBound(other);
        double p2 = lowerBound / getUpperBound(other);
        double p3 = upperBound / getLowerBound(other);
        double p4 = upperBound / getUpperBound(other);
        double lo = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        double hi = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public BooleanValue equal(@NotNull Interval<Double> other) {
        if (lowerBound.equals(upperBound) && other.lowerBound.equals(other.upperBound) && lowerBound.equals(other.lowerBound)) {
            return new BooleanValue(true);
        } else if (upperBound < other.lowerBound || lowerBound > other.upperBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    @Override
    public BooleanValue notEqual(@NotNull Interval<Double> other) {
        if (upperBound < other.lowerBound || lowerBound > other.upperBound) {
            return new BooleanValue(true);
        } else if (lowerBound.equals(upperBound) && other.lowerBound.equals(other.upperBound) && lowerBound.equals(other.lowerBound)) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    @Override
    public BooleanValue smaller(@NotNull Interval<Double> other) {
        if (upperBound < other.lowerBound) {
            return new BooleanValue(true);
        } else if (lowerBound >= other.upperBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    @Override
    public BooleanValue smallerEqual(@NotNull Interval<Double> other) {
        if (upperBound <= other.lowerBound) {
            return new BooleanValue(true);
        } else if (lowerBound > other.upperBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    @Override
    public BooleanValue bigger(@NotNull Interval<Double> other) {
        if (lowerBound > other.upperBound) {
            return new BooleanValue(true);
        } else if (upperBound <= other.lowerBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    @Override
    public BooleanValue biggerEqual(@NotNull Interval<Double> other) {
        if (lowerBound >= other.upperBound) {
            return new BooleanValue(true);
        } else if (upperBound < other.lowerBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Impure
    @Override
    public DoubleInterval plusPlus() {
        double lowerBound = this.lowerBound + 1.0;
        double upperBound = this.upperBound + 1.0;
        return new DoubleInterval(lowerBound, upperBound);
    }

    @Impure
    @Override
    public DoubleInterval minusMinus() {
        double lowerBound = this.lowerBound - 1.0;
        double upperBound = this.upperBound - 1.0;
        return new DoubleInterval(lowerBound, upperBound);
    }

    @Pure
    @Override
    public DoubleInterval unaryMinus() {
        return new DoubleInterval(-upperBound, -lowerBound);
    }

    @Pure
    @Override
    public DoubleInterval abs() {
        if (upperBound < 0) {
            return new DoubleInterval(Math.abs(upperBound), Math.abs(lowerBound));
        } else if (lowerBound >= 0) {
            return new DoubleInterval(lowerBound, upperBound);
        } else {
            double max = Math.max(Math.abs(lowerBound), Math.abs(upperBound));
            return new DoubleInterval(0, max);
        }
    }

    @Impure
    @Override
    public DoubleInterval merge(@NotNull Interval<Double> other) {
        double smallerLowerBound = Math.min(this.lowerBound, other.lowerBound);
        double largerUpperBound = Math.max(this.upperBound, other.upperBound);
        assert smallerLowerBound <= largerUpperBound;
        return new DoubleInterval(smallerLowerBound, largerUpperBound);
    }

    @Override
    public int compareTo(@NotNull Interval<Double> o) {
        if (!this.lowerBound.equals(o.lowerBound)) {
            return Double.compare(this.lowerBound, o.lowerBound);
        } else {
            return Double.compare(this.upperBound, o.upperBound);
        }
    }

}
