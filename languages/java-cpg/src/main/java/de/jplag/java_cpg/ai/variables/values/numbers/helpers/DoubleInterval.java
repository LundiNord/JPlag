package de.jplag.java_cpg.ai.variables.values.numbers.helpers;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

public class DoubleInterval extends Interval<Double> {

    public static final double MAX_VALUE = Double.MAX_VALUE;
    public static final double MIN_VALUE = -Double.MAX_VALUE;

    public DoubleInterval() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    public DoubleInterval(double number) {
        this.lowerBound = number;
        this.upperBound = number;
    }

    public DoubleInterval(double lowerBound, double upperBound) {
        assert lowerBound <= upperBound;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
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

    @Override
    public void setToUnknown() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    @Pure
    @Override
    public DoubleInterval plus(@NotNull Interval<Double> other) {
        double lo = lowerBound + other.lowerBound;
        double hi = upperBound + other.upperBound;
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval minus(@NotNull Interval<Double> other) {
        double lo = lowerBound - other.upperBound;
        double hi = upperBound - other.lowerBound;
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval times(@NotNull Interval<Double> other) {
        double p1 = lowerBound * other.lowerBound;
        double p2 = lowerBound * other.upperBound;
        double p3 = upperBound * other.lowerBound;
        double p4 = upperBound * other.upperBound;
        double lo = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        double hi = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return new DoubleInterval(lo, hi);
    }

    @Pure
    @Override
    public DoubleInterval divided(@NotNull Interval<Double> other) {
        if (other.lowerBound <= 0 && other.upperBound >= 0) {
            return new DoubleInterval(MIN_VALUE, MAX_VALUE);
        }
        double p1 = lowerBound / other.lowerBound;
        double p2 = lowerBound / other.upperBound;
        double p3 = upperBound / other.lowerBound;
        double p4 = upperBound / other.upperBound;
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
        lowerBound += 1.0;
        upperBound += 1.0;
        return this.copy();
    }

    @Impure
    @Override
    public DoubleInterval minusMinus() {
        lowerBound -= 1.0;
        upperBound -= 1.0;
        return this.copy();
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
    public void merge(@NotNull Interval<Double> other) {
        double smallerLowerBound = Math.min(this.lowerBound, other.lowerBound);
        double largerUpperBound = Math.max(this.upperBound, other.upperBound);
        assert smallerLowerBound <= largerUpperBound;
        this.lowerBound = smallerLowerBound;
        this.upperBound = largerUpperBound;
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
