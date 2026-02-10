package de.jplag.java_cpg.ai.variables.values.numbers;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;

public abstract class NumberSetValue<T extends Comparable<T>> extends Value implements INumberValue {

    protected final Set<T> maybeContained;
    protected final boolean information;

    protected NumberSetValue(@NotNull Type type) {
        super(type);
        this.information = false;
        this.maybeContained = new HashSet<>();
    }

    protected NumberSetValue(@NotNull T value, @NotNull Type type) {
        super(type);
        this.information = true;
        this.maybeContained = new HashSet<>();
        this.maybeContained.add(value);
    }

    protected NumberSetValue(@NotNull Set<T> characters, @NotNull Type type) {
        super(type);
        this.information = true;
        this.maybeContained = characters;
    }

    protected abstract NumberSetValue<T> createNewInstance(Set<T> maybeContained);

    protected abstract NumberSetValue<T> createNewUnknownInstance();

    protected abstract T subtract(T c1, T c2);

    protected abstract T add(T c1, T c2);

    /**
     * Copy constructor.
     */
    protected NumberSetValue(Set<T> maybeContained, boolean information, @NotNull Type type) {
        super(type);
        this.maybeContained = maybeContained;
        this.information = information;
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        NumberSetValue<T> otherNumberSetValue = (NumberSetValue<T>) other;
        switch (operator) {
            case "!=" -> {
                if (!this.information || !otherNumberSetValue.information) {
                    return new BooleanValue();
                }
                for (T c1 : this.maybeContained) {     // true if disjoint
                    for (T c2 : otherNumberSetValue.maybeContained) {
                        if (c1 == c2) {
                            return new BooleanValue(false);
                        }
                    }
                }
                return new BooleanValue(true);
            }
            case "==" -> {
                if (!this.information || !otherNumberSetValue.information) {
                    return new BooleanValue();
                }
                if (this.maybeContained.size() == 1 && otherNumberSetValue.maybeContained.size() == 1
                        && this.maybeContained.iterator().next().equals(otherNumberSetValue.maybeContained.iterator().next())) {
                    return new BooleanValue(true);
                } else if (this.maybeContained.size() == 1 && otherNumberSetValue.maybeContained.size() == 1
                        && !this.maybeContained.iterator().next().equals(otherNumberSetValue.maybeContained.iterator().next())) {
                    return new BooleanValue(false);
                }
                return new BooleanValue();
            }
            case "-" -> {
                if (this.information && otherNumberSetValue.information) {
                    Set<T> maybeContainedCopy = Set.copyOf(this.maybeContained);
                    Set<T> result = new HashSet<>();
                    for (T c1 : maybeContainedCopy) {
                        for (T c2 : otherNumberSetValue.maybeContained) {
                            result.add(subtract(c1, c2));
                        }
                    }
                    return createNewInstance(result);
                } else {
                    return createNewUnknownInstance();
                }
            }
            case "+" -> {
                if (this.information && otherNumberSetValue.information) {
                    Set<T> maybeContainedCopy = Set.copyOf(this.maybeContained);
                    Set<T> result = new HashSet<>();
                    for (T c1 : maybeContainedCopy) {
                        for (T c2 : otherNumberSetValue.maybeContained) {
                            result.add(add(c1, c2));
                        }
                    }
                    return createNewInstance(result);
                } else {
                    return createNewUnknownInstance();
                }
            }
            default -> {
                return new UnknownValue();
            }
        }
    }

    @Override
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            default -> {
                return new VoidValue();
            }
        }
    }

}
