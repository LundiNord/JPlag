package de.jplag.java_cpg.ai.variables.values.helpers;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

public class IntInterval implements Comparable<Object> {

    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int MIN_VALUE = Integer.MIN_VALUE;

    private int lowerBound;
    private int upperBound;

    public IntInterval() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    public IntInterval(int number) {
        this.lowerBound = number;
        this.upperBound = number;
    }

    public IntInterval(int lowerBound, int upperBound) {
        assert lowerBound <= upperBound;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    private static int safeAbs(int x) {
        if (x == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return Math.abs(x);
    }

    public boolean getInformation() {
        return lowerBound == upperBound;
    }

    public double getValue() {
        assert lowerBound == upperBound;
        return lowerBound;
    }

    public IntInterval copy() {
        return new IntInterval(lowerBound, upperBound);
    }

    public void setToUnknown() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    @Pure
    public IntInterval plus(@NotNull IntInterval other) {
        long loSum = (long) lowerBound + (long) other.lowerBound;
        long hiSum = (long) upperBound + (long) other.upperBound;
        int lo = loSum > MAX_VALUE ? MAX_VALUE : (loSum < MIN_VALUE ? MIN_VALUE : (int) loSum);
        int hi = hiSum > MAX_VALUE ? MAX_VALUE : (hiSum < MIN_VALUE ? MIN_VALUE : (int) hiSum);
        return new IntInterval(lo, hi);
    }

    @Pure
    public IntInterval minus(@NotNull IntInterval other) {
        long loSum = (long) lowerBound - (long) other.lowerBound;
        long hiSum = (long) upperBound - (long) other.upperBound;
        int lo = loSum > MAX_VALUE ? MAX_VALUE : (loSum < MIN_VALUE ? MIN_VALUE : (int) loSum);
        int hi = hiSum > MAX_VALUE ? MAX_VALUE : (hiSum < MIN_VALUE ? MIN_VALUE : (int) hiSum);
        return new IntInterval(lo, hi);
    }

    @Pure
    public IntInterval times(IntInterval other) {
        long p1 = (long) lowerBound * other.lowerBound;
        long p2 = (long) lowerBound * other.upperBound;
        long p3 = (long) upperBound * other.lowerBound;
        long p4 = (long) upperBound * other.upperBound;
        long loLong = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        long hiLong = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        int lo = loLong > MAX_VALUE ? MAX_VALUE : (loLong < MIN_VALUE ? MIN_VALUE : (int) loLong);
        int hi = hiLong > MAX_VALUE ? MAX_VALUE : (hiLong < MIN_VALUE ? MIN_VALUE : (int) hiLong);
        return new IntInterval(lo, hi);
    }

    @Pure
    public IntInterval divided(@NotNull IntInterval other) {
        if (other.lowerBound <= 0 && other.upperBound >= 0) {
            return new IntInterval(MIN_VALUE, MAX_VALUE);
        }
        long p1 = (lowerBound == MIN_VALUE && other.lowerBound == -1)
                ? (long) MAX_VALUE : (long) lowerBound / (long) other.lowerBound;
        long p2 = (lowerBound == MIN_VALUE && other.upperBound == -1)
                ? (long) MAX_VALUE : (long) lowerBound / (long) other.upperBound;
        long p3 = (upperBound == MIN_VALUE && other.lowerBound == -1)
                ? (long) MAX_VALUE : (long) upperBound / (long) other.lowerBound;
        long p4 = (upperBound == MIN_VALUE && other.upperBound == -1)
                ? (long) MAX_VALUE : (long) upperBound / (long) other.upperBound;
        long loLong = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        long hiLong = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        int lo = loLong > MAX_VALUE ? MAX_VALUE : (loLong < MIN_VALUE ? MIN_VALUE : (int) loLong);
        int hi = hiLong > MAX_VALUE ? MAX_VALUE : (hiLong < MIN_VALUE ? MIN_VALUE : (int) hiLong);
        return new IntInterval(lo, hi);
    }

    //FixMe
    @Pure
    public BooleanValue equal(IntInterval other) {
        if (lowerBound == upperBound && other.lowerBound == other.upperBound && lowerBound == other.lowerBound) {
            return new BooleanValue(true);
        } else if (upperBound < other.lowerBound || lowerBound > other.upperBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    public BooleanValue smaller(IntInterval other) {
        if (upperBound < other.lowerBound) {
            return new BooleanValue(true);
        } else if (lowerBound >= other.upperBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Pure
    public BooleanValue bigger(IntInterval other) {
        if (lowerBound > other.upperBound) {
            return new BooleanValue(true);
        } else if (upperBound <= other.lowerBound) {
            return new BooleanValue(false);
        } else {
            return new BooleanValue();
        }
    }

    @Impure
    public IntInterval plusPlus() {
        long newLower = (long) lowerBound + 1;
        long newUpper = (long) upperBound + 1;
        lowerBound = newLower > MAX_VALUE ? MAX_VALUE : (int) newLower;
        upperBound = newUpper > MAX_VALUE ? MAX_VALUE : (int) newUpper;
        return this.copy();
    }

    @Impure
    public IntInterval minusMinus() {
        long newLower = (long) lowerBound - 1;
        long newUpper = (long) upperBound - 1;
        lowerBound = newLower < MIN_VALUE ? MIN_VALUE : (int) newLower;
        upperBound = newUpper < MIN_VALUE ? MIN_VALUE : (int) newUpper;
        return this.copy();
    }

    @Pure
    public IntInterval unaryMinus() {
        int newLower = (upperBound == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -upperBound;
        int newUpper = (lowerBound == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -lowerBound;
        return new IntInterval(newLower, newUpper);
    }

    @Pure
    public IntInterval abs() {
        if (upperBound < 0) {
            return new IntInterval(safeAbs(upperBound), safeAbs(lowerBound));
        } else if (lowerBound >= 0) {
            return new IntInterval(lowerBound, upperBound);
        } else {
            int max = Math.max(safeAbs(lowerBound), safeAbs(upperBound));
            return new IntInterval(0, max);
        }
    }

    /**
     * Compares starting point (lowerBound) of intervals.
     * If they are equal, compare end point (upperBound).
     *
     * @return negative: less than, zero: equal, positive: greater than
     */
    @Override
    public int compareTo(@NotNull Object o) {
        if (o instanceof IntInterval interval) {
            if (this.lowerBound != interval.lowerBound) {
                return Integer.compare(this.lowerBound, interval.lowerBound);
            } else {
                return Integer.compare(this.upperBound, interval.upperBound);
            }
        } else {
            throw new IllegalArgumentException("Cannot compare IntInterval with " + o.getClass());
        }
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        assert upperBound >= lowerBound;
        this.upperBound = upperBound;
    }


}
