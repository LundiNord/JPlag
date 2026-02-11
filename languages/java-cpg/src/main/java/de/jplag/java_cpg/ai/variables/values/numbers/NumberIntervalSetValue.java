package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.chars.ICharValue;
import de.jplag.java_cpg.ai.variables.values.numbers.helpers.Interval;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;

/**
 * Abstract base class for numeric values represented as sets of intervals.
 * @param <T> The type of number (Integer, Double, etc.)
 * @param <I> The interval type for this number
 */
public abstract class NumberIntervalSetValue<T extends Number & Comparable<T>, I extends Interval<T>> extends Value implements INumberValue {

    protected final TreeSet<I> values;

    protected NumberIntervalSetValue(Type type) {
        super(type);
        values = new TreeSet<>();
    }

    protected NumberIntervalSetValue(Type type, TreeSet<I> values) {
        super(type);
        this.values = values;
    }

    protected NumberIntervalSetValue(Type type, Set<T> values) {
        super(type);
        this.values = new TreeSet<>();
        if (values.isEmpty()) {
            return;
        }
        // slice the values into intervals
        TreeSet<T> sortedValues = new TreeSet<>(values);
        T rangeStart = sortedValues.first();
        T rangeEnd = rangeStart;
        for (T value : sortedValues) {
            if (value.equals(rangeStart)) {
                continue; // Skip the first element
            }
            // Check if this value is consecutive to the current range
            if (isConsecutive(rangeEnd, value)) {
                rangeEnd = value;
            } else {
                // The current range is complete, add it and start a new range
                this.values.add(createInterval(rangeStart, rangeEnd));
                rangeStart = value;
                rangeEnd = value;
            }
        }
        this.values.add(createInterval(rangeStart, rangeEnd));
    }

    protected abstract I createInterval(T lowerBound, T upperBound);

    protected abstract NumberIntervalSetValue<T, I> createInstance(TreeSet<I> values);

    /**
     * Checks if two values are consecutive and should be grouped in the same interval. For integers, this means they differ
     * by 1. For floats, they are only consecutive if equal.
     * @param current the current end of the range
     * @param next the next value to consider
     * @return true if the values should be in the same interval
     */
    protected abstract boolean isConsecutive(T current, T next);

    @Override
    public boolean getInformation() {
        return values.size() == 1 && values.getFirst().getInformation();
    }

    @Override
    public double getValue() {
        assert getInformation();
        return values.getFirst().getValue().doubleValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        if (other instanceof ICharValue) {
            other = createInstance(new TreeSet<>());
        } else if (other instanceof IStringValue stringValue) {
            return stringValue.binaryOperation(operator, this);
        }
        NumberIntervalSetValue<T, I> otherValue = (NumberIntervalSetValue<T, I>) other;
        if (this.values.isEmpty() || otherValue.values.isEmpty()) {
            return new UnknownValue();
        }
        switch (operator) {
            case "+" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) interval.plus(value));
                    }
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            case "-" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) value.minus(interval));
                    }
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            case "<" -> {
                if (values.getLast().getUpperBound().doubleValue() < otherValue.values.getFirst().getLowerBound().doubleValue()) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound().doubleValue() >= otherValue.values.getLast().getUpperBound().doubleValue()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (values.getFirst().getLowerBound().doubleValue() > otherValue.values.getLast().getUpperBound().doubleValue()) {
                    return new BooleanValue(true);
                } else if (values.getLast().getUpperBound().doubleValue() <= otherValue.values.getFirst().getLowerBound().doubleValue()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "<=" -> {
                if (values.getLast().getUpperBound().compareTo(otherValue.values.getFirst().getLowerBound()) <= 0) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound().compareTo(otherValue.values.getLast().getUpperBound()) > 0) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">=" -> {
                if (values.getFirst().getLowerBound().compareTo(otherValue.values.getLast().getUpperBound()) >= 0) {
                    return new BooleanValue(true);
                } else if (values.getLast().getUpperBound().compareTo(otherValue.values.getFirst().getLowerBound()) < 0) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "==" -> {
                if (values.size() != otherValue.values.size()) {
                    return new BooleanValue(false);
                }
                BooleanValue equal = null;
                Iterator<I> otherInterval = otherValue.values.iterator();
                for (I interval : values) {
                    BooleanValue result = interval.equal(otherInterval.next());
                    if (!result.getInformation()) {
                        return new BooleanValue();
                    }
                    if (equal == null) {
                        equal = result;
                    } else if (equal.getValue() != result.getValue()) {
                        return new BooleanValue();
                    }
                }
                return equal;
            }
            case "!=" -> {
                BooleanValue result = (BooleanValue) this.binaryOperation("==", other);
                if (result.getInformation()) {
                    return new BooleanValue(!result.getValue());
                }
                return new BooleanValue();
            }
            case "*" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) interval.times(value));
                    }
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            case "/" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) value.divided(interval));
                    }
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            case "min" -> {
                T upper = values.getLast().getUpperBound().compareTo(otherValue.values.getLast().getUpperBound()) < 0
                        ? values.getLast().getUpperBound()
                        : otherValue.values.getLast().getUpperBound();
                // include all intervals in the result but all are capped at upper
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    T upperBound = interval.getUpperBound().compareTo(upper) < 0 ? interval.getUpperBound() : upper;
                    T lowerBound = interval.getLowerBound().compareTo(upperBound) < 0 ? interval.getLowerBound() : upperBound;
                    newValues.add(createInterval(lowerBound, upperBound));
                }
                for (I interval : values) {
                    T upperBound = interval.getUpperBound().compareTo(upper) < 0 ? interval.getUpperBound() : upper;
                    T lowerBound = interval.getLowerBound().compareTo(upperBound) < 0 ? interval.getLowerBound() : upperBound;
                    newValues.add(createInterval(lowerBound, upperBound));
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            case "max" -> {
                T lower = values.getFirst().getLowerBound().compareTo(otherValue.values.getFirst().getLowerBound()) > 0
                        ? values.getFirst().getLowerBound()
                        : otherValue.values.getFirst().getLowerBound();
                // include all intervals in the result, but all are floored at lower
                TreeSet<I> newValues = new TreeSet<>();
                try {
                    for (I interval : otherValue.values) {
                        T lowerBound = interval.getLowerBound().compareTo(lower) > 0 ? interval.getLowerBound() : lower;
                        T upperBound = interval.getUpperBound().compareTo(lowerBound) > 0 ? interval.getUpperBound() : lowerBound;
                        newValues.add(createInterval(lowerBound, upperBound));
                    }
                    for (I interval : values) {
                        T lowerBound = interval.getLowerBound().compareTo(lower) > 0 ? interval.getLowerBound() : lower;
                        T upperBound = interval.getUpperBound().compareTo(lowerBound) > 0 ? interval.getUpperBound() : lowerBound;
                        newValues.add(createInterval(lowerBound, upperBound));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create interval instance", e);
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            default -> {
                return new VoidValue();
            }
        }
    }

    @Override
    @Impure
    @SuppressWarnings("unchecked")
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "++" -> {
                values.forEach(Interval::plusPlus);
                return this.copy();
            }
            case "--" -> {
                values.forEach(Interval::minusMinus);
                return this.copy();
            }
            case "-" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : values) {
                    newValues.add((I) interval.unaryMinus());
                }
                return createInstance(newValues);
            }
            case "abs" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : values) {
                    newValues.add((I) interval.abs());
                }
                return createInstance(mergeOverlappingIntervals(newValues));
            }
            default -> {
                return new VoidValue();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberIntervalSetValue<T, I> merge(@NotNull IValue other) {
        // if (other instanceof VoidValue) {
        // return createInstance(new TreeSet<>());
        // }
        if (other instanceof IFloatNumber floatNumber) {    // can happen because some casts are not explicit in eog
            if (floatNumber.getInformation()) {
                int value = (int) floatNumber.getValue();
                TreeSet<I> newValues = new TreeSet<>();
                newValues.add(createInterval((T) Integer.valueOf(value), (T) Integer.valueOf(value)));
                other = createInstance(newValues);
            } else {
                other = createInstance(new TreeSet<>());
            }
        }
        assert other.getClass().equals(this.getClass()) : "Cannot merge different value types" + this.getClass() + " and " + other.getClass();
        TreeSet<I> otherValues = ((NumberIntervalSetValue<T, I>) other).values;
        this.values.addAll(otherValues);
        return createInstance(mergeOverlappingIntervals(this.values));
    }

    @Pure
    protected @NotNull TreeSet<I> mergeOverlappingIntervals(@NotNull TreeSet<I> valuesToMerge) {
        if (valuesToMerge.size() < 2) {
            return valuesToMerge;
        }
        TreeSet<I> newValues = new TreeSet<>();
        newValues.add(valuesToMerge.first());
        valuesToMerge.remove(valuesToMerge.first());
        for (I interval : valuesToMerge) {
            I lastInterval = newValues.last();
            if (lastInterval.getUpperBound().compareTo(interval.getLowerBound()) >= 0) {
                T maxUpper = lastInterval.getUpperBound().compareTo(interval.getUpperBound()) > 0 ? lastInterval.getUpperBound()
                        : interval.getUpperBound();
                newValues.remove(lastInterval);
                newValues.add(createInterval(lastInterval.getLowerBound(), maxUpper));
            } else {
                newValues.add(interval);
            }
        }
        return newValues;
    }

}
