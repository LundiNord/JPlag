package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.helpers.Interval;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Abstract base class for numeric values represented as sets of intervals.
 *
 * @param <T> The type of number (Integer, Double, etc.)
 * @param <I> The interval type for this number
 */
public abstract class NumberSetValue<T extends Number & Comparable<T>, I extends Interval<T>> extends Value implements INumberValue {

    protected TreeSet<I> values;

    protected NumberSetValue(Type type) {
        super(type);
        values = new TreeSet<>();
    }

    protected NumberSetValue(Type type, TreeSet<I> values) {
        super(type);
        this.values = values;
    }

    protected abstract I createFullInterval();

    protected abstract I createInterval(T lowerBound, T upperBound);

    protected abstract NumberSetValue<T, I> createInstance(TreeSet<I> values);

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
    @SuppressWarnings("unchecked")  //ToDo unschön
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (!(other instanceof NumberSetValue)) {
            other = createInstance(new TreeSet<>());
        }
        NumberSetValue<T, I> otherValue = (NumberSetValue<T, I>) other;
        switch (operator) {
            case "+" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) interval.plus(value));
                    }
                }
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "-" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) value.minus(interval));
                    }
                }
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "<" -> {
                if (values.getLast().getUpperBound().compareTo(otherValue.values.getFirst().getLowerBound()) < 0) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound().compareTo(otherValue.values.getLast().getUpperBound()) >= 0) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (values.getFirst().getLowerBound().compareTo(otherValue.values.getLast().getUpperBound()) > 0) {
                    return new BooleanValue(true);
                } else if (values.getLast().getUpperBound().compareTo(otherValue.values.getFirst().getLowerBound()) <= 0) {
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
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "/" -> {
                TreeSet<I> newValues = new TreeSet<>();
                for (I interval : otherValue.values) {
                    for (I value : values) {
                        newValues.add((I) value.divided(interval));
                    }
                }
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "min" -> {
                T upper = values.getLast().getUpperBound().compareTo(otherValue.values.getLast().getUpperBound()) < 0
                        ? values.getLast().getUpperBound()
                        : otherValue.values.getLast().getUpperBound();
                //include all intervals in the result but all are capped at upper
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
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "max" -> {
                T lower = values.getFirst().getLowerBound().compareTo(otherValue.values.getFirst().getLowerBound()) > 0
                        ? values.getFirst().getLowerBound()
                        : otherValue.values.getFirst().getLowerBound();
                //include all intervals in the result, but all are floored at lower
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
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            default ->
                    throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @Override
    @Impure
    @SuppressWarnings("unchecked")  //ToDo unschön
    public Value unaryOperation(@NotNull String operator) {
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
                NumberSetValue<T, I> newValue = createInstance(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void merge(@NotNull Value other) {
        assert other.getClass().equals(this.getClass());
        TreeSet<I> otherValues = ((NumberSetValue<T, I>) other).values;
        this.values.addAll(otherValues);
        mergeOverlappingIntervals();
    }

    @Override
    public void setToUnknown() {
        values = new TreeSet<>();
        values.add(createFullInterval());
    }

    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(createFullInterval());
    }

    protected void mergeOverlappingIntervals() {
        if (values.size() < 2) {
            return;
        }
        TreeSet<I> newValues = new TreeSet<>();
        newValues.add(values.first());
        values.remove(values.first());
        for (I interval : values) {
            I lastInterval = newValues.last();
            if (lastInterval.getUpperBound().compareTo(interval.getLowerBound()) >= 0) {
                T maxUpper = lastInterval.getUpperBound().compareTo(interval.getUpperBound()) > 0
                        ? lastInterval.getUpperBound()
                        : interval.getUpperBound();
                lastInterval.setUpperBound(maxUpper);
            } else {
                newValues.add(interval);
            }
        }
        this.values = newValues;
    }

}
