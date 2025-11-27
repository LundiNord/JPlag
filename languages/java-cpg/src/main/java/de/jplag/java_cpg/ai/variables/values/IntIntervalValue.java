package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

/**
 * Represents integer values as intervals.
 *
 * @author ujiqk
 * @version 1.0
 */
public class IntIntervalValue extends Value implements INumberValue {       //FixMe: Overflows

    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int MIN_VALUE = Integer.MIN_VALUE;

    private int lowerBound;
    private int upperBound;

    public IntIntervalValue() {
        super(Type.INT);
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    public IntIntervalValue(int lowerBound, int upperBound) {
        super(Type.INT);
        assert lowerBound <= upperBound;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public IntIntervalValue(int number) {
        super(Type.INT);
        this.lowerBound = number;
        this.upperBound = number;
    }

    private static int safeAbs(int x) {
        if (x == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return Math.abs(x);
    }

    @Override
    public boolean getInformation() {
        return lowerBound == upperBound;
    }

    @Override
    public double getValue() {
        assert lowerBound == upperBound;
        return lowerBound;
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (!(other instanceof INumberValue)) {
            other = new IntIntervalValue();
        }
        IntIntervalValue otherValue = (IntIntervalValue) other;
        switch (operator) {
            case "+" -> {
                long loSum = (long) lowerBound + (long) otherValue.lowerBound;  //ToDo: overflow
                long hiSum = (long) upperBound + (long) otherValue.upperBound;
                int lo = loSum > MAX_VALUE ? MAX_VALUE : (loSum < MIN_VALUE ? MIN_VALUE : (int) loSum);
                int hi = hiSum > MAX_VALUE ? MAX_VALUE : (hiSum < MIN_VALUE ? MIN_VALUE : (int) hiSum);
                return new IntIntervalValue(lo, hi);
            }
            case "<" -> {
                if (lowerBound < otherValue.lowerBound && upperBound < otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound >= otherValue.upperBound && upperBound >= otherValue.lowerBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (lowerBound > otherValue.lowerBound && upperBound > otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound <= otherValue.upperBound && upperBound <= otherValue.lowerBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "-" -> {
                long loSum = (long) lowerBound - (long) otherValue.lowerBound;
                long hiSum = (long) upperBound - (long) otherValue.upperBound;
                int lo = loSum > MAX_VALUE ? MAX_VALUE : (loSum < MIN_VALUE ? MIN_VALUE : (int) loSum);
                int hi = hiSum > MAX_VALUE ? MAX_VALUE : (hiSum < MIN_VALUE ? MIN_VALUE : (int) hiSum);
                return new IntIntervalValue(lo, hi);
            }
            case "!=" -> {  //ToDo
                if (lowerBound != otherValue.lowerBound && upperBound != otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound == otherValue.lowerBound && upperBound == otherValue.upperBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "==" -> {
                if (lowerBound == otherValue.lowerBound && upperBound == otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound != otherValue.upperBound && upperBound != otherValue.lowerBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "*" -> {
                long p1 = (long) lowerBound * otherValue.lowerBound;
                long p2 = (long) lowerBound * otherValue.upperBound;
                long p3 = (long) upperBound * otherValue.lowerBound;
                long p4 = (long) upperBound * otherValue.upperBound;
                long loLong = Math.min(Math.min(p1, p2), Math.min(p3, p4));
                long hiLong = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                int lo = loLong > MAX_VALUE ? MAX_VALUE : (loLong < MIN_VALUE ? MIN_VALUE : (int) loLong);
                int hi = hiLong > MAX_VALUE ? MAX_VALUE : (hiLong < MIN_VALUE ? MIN_VALUE : (int) hiLong);
                return new IntIntervalValue(lo, hi);
            }
            case "/" -> {
                if (otherValue.lowerBound <= 0 && otherValue.upperBound >= 0) {
                    return new IntIntervalValue(MIN_VALUE, MAX_VALUE);
                }
                long p1 = (long) lowerBound / (long) otherValue.lowerBound;
                long p2 = (long) lowerBound / (long) otherValue.upperBound;
                long p3 = (long) upperBound / (long) otherValue.lowerBound;
                long p4 = (long) upperBound / (long) otherValue.upperBound;
                long loLong = Math.min(Math.min(p1, p2), Math.min(p3, p4));
                long hiLong = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                int lo = loLong > MAX_VALUE ? MAX_VALUE : (loLong < MIN_VALUE ? MIN_VALUE : (int) loLong);
                int hi = hiLong > MAX_VALUE ? MAX_VALUE : (hiLong < MIN_VALUE ? MIN_VALUE : (int) hiLong);
                return new IntIntervalValue(lo, hi);
            }
            case "<=" -> {  //ToDo
                if (lowerBound <= otherValue.lowerBound && upperBound <= otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound > otherValue.upperBound && upperBound > otherValue.lowerBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">=" -> {  //ToDo
                if (lowerBound >= otherValue.lowerBound && upperBound >= otherValue.upperBound) {
                    return new BooleanValue(true);
                } else if (lowerBound < otherValue.upperBound && upperBound < otherValue.lowerBound) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @Override
    @Impure
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "++" -> {
                lowerBound++;
                upperBound++;
                return this.copy();
            }
            case "--" -> {
                lowerBound--;
                upperBound--;
                return this.copy();
            }
            case "-" -> {
                return new IntIntervalValue(-lowerBound, -upperBound);
            }
            case "abs" -> {
                if (upperBound < 0) {
                    return new IntIntervalValue(safeAbs(upperBound), safeAbs(lowerBound));
                } else if (lowerBound >= 0) {
                    return new IntIntervalValue(lowerBound, upperBound);
                } else {
                    int max = Math.max(safeAbs(lowerBound), safeAbs(upperBound));
                    return new IntIntervalValue(0, max);
                }
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @Override
    public Value copy() {
        return new IntIntervalValue(lowerBound, upperBound);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof IntIntervalValue;
        int smallerLowerBound = Math.min(this.lowerBound, ((IntIntervalValue) other).lowerBound);
        int largerUpperBound = Math.max(this.upperBound, ((IntIntervalValue) other).upperBound);
        assert smallerLowerBound <= largerUpperBound;
        this.lowerBound = smallerLowerBound;
        this.upperBound = largerUpperBound;
    }

    @Override
    public void setToUnknown() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

    //ToDo remove setToUnknown() or setInitialValue()
    @Override
    public void setInitialValue() {
        this.lowerBound = MIN_VALUE;
        this.upperBound = MAX_VALUE;
    }

}
