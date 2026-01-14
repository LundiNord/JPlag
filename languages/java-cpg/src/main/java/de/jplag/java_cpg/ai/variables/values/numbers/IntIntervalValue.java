package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Set;

import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.IntInterval;

/**
 * Represents integer values as intervals.
 * @author ujiqk
 * @version 1.0
 */
public class IntIntervalValue extends Value implements INumberValue {

    private final IntInterval interval;

    public IntIntervalValue() {
        super(Type.INT);
        interval = new IntInterval();
    }

    public IntIntervalValue(int lowerBound, int upperBound) {
        super(Type.INT);
        interval = new IntInterval(lowerBound, upperBound);
    }

    public IntIntervalValue(int number) {
        super(Type.INT);
        interval = new IntInterval(number);
    }

    public IntIntervalValue(@NotNull Set<Integer> possibleValues) {
        super(Type.INT);
        java.util.List<Integer> values = possibleValues.stream().toList();
        interval = new IntInterval(values.getFirst(), values.getLast());
    }

    private IntIntervalValue(IntInterval interval) {
        super(Type.INT);
        this.interval = interval;
    }

    @Override
    public boolean getInformation() {
        return interval.getInformation();
    }

    @Override
    public double getValue() {
        return interval.getValue();
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        if (!(other instanceof INumberValue)) {
            other = new IntIntervalValue();
        }
        IntIntervalValue otherValue = (IntIntervalValue) other; // ToDo: what if other float?
        switch (operator) {
            case "+" -> {
                IntInterval newInterval = this.interval.copy().plus(otherValue.interval);
                return new IntIntervalValue(newInterval);
            }
            case "-" -> {
                IntInterval newInterval = this.interval.copy().minus(otherValue.interval);
                return new IntIntervalValue(newInterval);
            }
            case "<" -> {
                return this.interval.copy().smaller(otherValue.interval);
            }
            case ">" -> {
                return this.interval.copy().bigger(otherValue.interval);
            }
            case "<=" -> {
                return this.interval.copy().smallerEqual(otherValue.interval);
            }
            case ">=" -> {
                return this.interval.copy().biggerEqual(otherValue.interval);
            }
            case "==" -> {
                return this.interval.copy().equal(otherValue.interval);
            }
            case "!=" -> {
                return this.interval.copy().notEqual(otherValue.interval);
            }
            case "*" -> {
                IntInterval newInterval = this.interval.copy().times(otherValue.interval);
                return new IntIntervalValue(newInterval);
            }
            case "/" -> {
                IntInterval newInterval = this.interval.copy().divided(otherValue.interval);
                return new IntIntervalValue(newInterval);
            }
            default -> throw new UnsupportedOperationException(
                    "Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @Override
    @Impure
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "++" -> {
                this.interval.plusPlus();
                return this.copy();
            }
            case "--" -> {
                this.interval.minusMinus();
                return this.copy();
            }
            case "-" -> {
                IntInterval newInterval = this.interval.copy().unaryMinus();
                return new IntIntervalValue(newInterval);
            }
            case "abs" -> {
                IntInterval newInterval = this.interval.copy().abs();
                return new IntIntervalValue(newInterval);
            }
            default -> throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public IValue copy() {
        return new IntIntervalValue(interval.copy());
    }

    @Override
    public void merge(@NotNull IValue other) {
        if (other instanceof VoidValue) {
            other = new IntIntervalValue();
        }
        assert other instanceof IntIntervalValue;
        this.interval.merge(((IntIntervalValue) other).interval);
    }

    @Override
    public void setToUnknown() {
        interval.setToUnknown();
    }

    @Override
    public void setInitialValue() {
        interval.setUpperBound(0);
        interval.setLowerBound(0);
    }

}
