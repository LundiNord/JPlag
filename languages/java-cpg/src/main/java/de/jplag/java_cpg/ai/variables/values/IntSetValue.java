package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.helpers.IntInterval;
import org.checkerframework.dataflow.qual.Impure;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Represents possible integer values as a set of intervals.
 *
 * @author ujiqk
 * @version 1.0
 */
public class IntSetValue extends Value implements INumberValue {

    private TreeSet<IntInterval> values;

    public IntSetValue() {
        super(Type.INT);
        values = new TreeSet<>();
        values.add(new IntInterval());
    }

    private IntSetValue(TreeSet<IntInterval> values) {
        super(Type.INT);
        this.values = values;
    }

    public IntSetValue(int number) {
        super(Type.INT);
        values = new TreeSet<>();
        values.add(new IntInterval(number));
    }

    @Override
    public boolean getInformation() {
        return values.size() == 1 && values.getFirst().getInformation();
    }

    @Override
    public double getValue() {
        assert getInformation();
        return values.getFirst().getValue();
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (!(other instanceof INumberValue)) {
            other = new IntSetValue();
        }
        IntSetValue otherValue = (IntSetValue) other;
        switch (operator) {
            case "+" -> {   //https://en.wikipedia.org/wiki/Minkowski_addition
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : otherValue.values) {
                    for (IntInterval value : values) {
                        newValues.add(interval.plus(value));
                    }
                }
                IntSetValue newValue = new IntSetValue(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "-" -> {
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : otherValue.values) {
                    for (IntInterval value : values) {
                        newValues.add(value.minus(interval));
                    }
                }
                IntSetValue newValue = new IntSetValue(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "<" -> {   //values < otherValue.values <--> values.upperBound < otherValue.values.lowerBound
                if (values.getLast().getUpperBound() < otherValue.values.getFirst().getLowerBound()) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound() >= otherValue.values.getLast().getUpperBound()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">" -> {
                if (values.getLast().getUpperBound() > otherValue.values.getFirst().getLowerBound()) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound() <= otherValue.values.getLast().getUpperBound()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "<=" -> {
                if (values.getLast().getUpperBound() <= otherValue.values.getFirst().getLowerBound()) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound() > otherValue.values.getLast().getUpperBound()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case ">=" -> {
                if (values.getLast().getUpperBound() >= otherValue.values.getFirst().getLowerBound()) {
                    return new BooleanValue(true);
                } else if (values.getFirst().getLowerBound() < otherValue.values.getLast().getUpperBound()) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "==" -> {
                BooleanValue equal = null;
                if (values.size() == otherValue.values.size()) {
                    Iterator<IntInterval> otherInterval = otherValue.values.iterator();
                    for (IntInterval interval : values) {
                        BooleanValue result = interval.equal(otherInterval.next());
                        if (!result.getInformation()) {
                            return new BooleanValue();
                        } else if (result.getValue()) {
                            switch (equal) {
                                case null -> equal = new BooleanValue(true);
                                case BooleanValue b when b.getValue() -> {
                                } // continue
                                case BooleanValue b when !b.getValue() -> {
                                    return new BooleanValue();
                                }
                                default -> throw new IllegalStateException("Unexpected value: " + equal);
                            }
                        } else {
                            switch (equal) {
                                case null -> equal = new BooleanValue(false);
                                case BooleanValue b when !b.getValue() -> {
                                } // continue
                                case BooleanValue b when b.getValue() -> {
                                    return new BooleanValue();
                                }
                                default -> throw new IllegalStateException("Unexpected value: " + equal);
                            }
                        }
                    }
                } else {
                    return new BooleanValue(false);
                }
                return equal;
            }
            case "!=" -> {
                BooleanValue result = (BooleanValue) this.binaryOperation("==", other);
                switch (result) {
                    case BooleanValue b when b.getInformation() -> {
                        return new BooleanValue(!b.getValue());
                    }
                    case BooleanValue b when !b.getInformation() -> {
                        return new BooleanValue();
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + result);
                }
            }
            case "*" -> {
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : otherValue.values) {
                    for (IntInterval value : values) {
                        newValues.add(interval.times(value));
                    }
                }
                IntSetValue newValue = new IntSetValue(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            case "/" -> {
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : otherValue.values) {
                    for (IntInterval value : values) {
                        newValues.add(interval.divided(value));
                    }
                }
                IntSetValue newValue = new IntSetValue(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
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
                values.forEach(IntInterval::plusPlus);
                return this.copy();
            }
            case "--" -> {
                values.forEach(IntInterval::minusMinus);
                return this.copy();
            }
            case "-" -> {
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : values) {
                    newValues.add(interval.unaryMinus());
                }
                return new IntSetValue(newValues);
            }
            case "abs" -> {
                TreeSet<IntInterval> newValues = new TreeSet<>();
                for (IntInterval interval : values) {
                    newValues.add(interval.abs());
                }
                IntSetValue newValue = new IntSetValue(newValues);
                newValue.mergeOverlappingIntervals();
                return newValue;
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @Override
    public Value copy() {
        return new IntSetValue(new TreeSet<>(values));
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof IntSetValue;
        TreeSet<IntInterval> otherValues = ((IntSetValue) other).values;
        this.values.addAll(otherValues);
        mergeOverlappingIntervals();
    }

    @Override
    public void setToUnknown() {
        values = new TreeSet<>();
        values.add(new IntInterval());
    }

    //ToDo remove setToUnknown() or setInitialValue()
    @Override
    public void setInitialValue() {
        values = new TreeSet<>();
        values.add(new IntInterval());
    }

    private void mergeOverlappingIntervals() {
        //https://www.geeksforgeeks.org/dsa/merging-intervals/#expected-approach-checking-overlapping-intervals-only-onlogn-time-and-o1-space
        if (values.size() < 2) {
            return;
        }
        TreeSet<IntInterval> newValues = new TreeSet<>();
        newValues.add(values.first());
        values.remove(values.first());

        for (IntInterval interval : values) {
            IntInterval lastInterval = newValues.last();

            if (lastInterval.getLowerBound() >= interval.getLowerBound()) {
                lastInterval.setUpperBound(Math.max(lastInterval.getUpperBound(), interval.getUpperBound()));
            } else {
                newValues.add(interval);
            }
        }
    }

}
