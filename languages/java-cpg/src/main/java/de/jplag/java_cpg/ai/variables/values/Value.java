package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.CharAiType;
import de.jplag.java_cpg.ai.FloatAiType;
import de.jplag.java_cpg.ai.IntAiType;
import de.jplag.java_cpg.ai.StringAiType;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.*;
import de.jplag.java_cpg.ai.variables.values.string.StringCharInclValue;
import de.jplag.java_cpg.ai.variables.values.string.StringRegexValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Anonymous typed value.
 *
 * @author ujiqk
 * @version 1.0
 */
public abstract class Value implements IValue {

    private static IntAiType usedIntAiType = IntAiType.DEFAULT;
    private static FloatAiType usedFloatAiType = FloatAiType.DEFAULT;
    private static StringAiType usedStringAiType = StringAiType.DEFAULT;
    private static CharAiType usedCharAiType = CharAiType.DEFAULT;

    @NotNull
    private final Type type;
    @Nullable
    private Pair<JavaArray, INumberValue> arrayPosition;    //necessary for an array assign to work
    @Nullable
    private JavaObject parentObject;                        //necessary for some field access

    protected Value(@NotNull Type type) {
        this.type = type;
    }

    /**
     * The default is {@link IntAiType#DEFAULT}.
     *
     * @param intAiType the type to use for integer values.
     */
    public static void setUsedIntAiType(@NotNull IntAiType intAiType) {
        usedIntAiType = intAiType;
    }

    public static void setUsedFloatAiType(@NotNull FloatAiType floatAiType) {
        usedFloatAiType = floatAiType;
    }

    public static void setUsedStringAiType(@NotNull StringAiType stringAiType) {
        usedStringAiType = stringAiType;
    }

    public static void setUsedCharAiType(@NotNull CharAiType charAiType) {
        usedCharAiType = charAiType;
    }

    /**
     * Constructs a Value instance based on the provided type.
     *
     * @param type the type of the value.
     * @return a Value instance corresponding to the specified type.
     */
    @NotNull
    public static Value valueFactory(@NotNull Type type) {
        return switch (type) {
            case INT -> getNewIntValue();
            case STRING -> getNewStringValue();
            case BOOLEAN -> new BooleanValue();
            case OBJECT -> new JavaObject();
            case VOID -> new VoidValue();
            case ARRAY, LIST -> new JavaArray();
            case NULL -> new NullValue();
            case FLOAT -> getNewFloatValue();
            case FUNCTION -> new FunctionValue();
            case CHAR -> getNewCharValue();
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        };
    }

    @NotNull
    public static Value valueFactory(Object value) {
        if (value == null) {
            return new NullValue();
        }
        switch (value) {
            case String s -> {
                return getNewStringValue(s);
            }
            case Integer i -> {
                return getNewIntValue(i);
            }
            case Boolean b -> {
                return new BooleanValue(b);
            }
            case Double d -> {
                return getNewFloatValue(d);
            }
            case Long l -> {    //ToDo handle long values properly
                return getNewIntValue(l.intValue());
            }
            case Character c -> {
                return getNewCharValue(c);
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    /**
     * Value factory for when a value could have multiple possible values.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> Value valueFactory(@NotNull Set<T> values) {
        assert !values.isEmpty();
        Object first = values.iterator().next();
        return switch (first) {
            case String ignored -> getNewStringValue((Set<String>) values);
            case Integer ignored -> getNewIntValue((Set<Integer>) values);
            case Double ignored -> getNewFloatValue((Set<Double>) values);
            case Character ignored -> getNewCharValue((Set<Character>) values);
            default -> throw new IllegalStateException("Unexpected value type in list: " + first.getClass());
        };
    }

    /**
     * Value factory for when a value has lower/upper bounds.
     */
    @NotNull
    public static <T> Value valueFactory(@NotNull T lowerBound, @NotNull T upperBound) {
        return switch (lowerBound) {
            case String ignored -> throw new IllegalStateException("Strings dont have bounds");
            case Integer ignored -> getNewIntValue((int) lowerBound, (int) upperBound);
            case Double ignored -> getNewFloatValue((double) lowerBound, (double) upperBound);
            default -> throw new IllegalStateException("Unexpected value type in bound : " + lowerBound.getClass());
        };
    }

    @NotNull
    private static Value getNewIntValue() {
        return switch (usedIntAiType) {
            case INTERVALS -> new IntIntervalValue();
            case DEFAULT -> new IntValue();
            case SET -> new IntSetValue();
        };
    }

    @NotNull
    private static Value getNewIntValue(int number) {
        return switch (usedIntAiType) {
            case INTERVALS -> new IntIntervalValue(number);
            case DEFAULT -> new IntValue(number);
            case SET -> new IntSetValue(number);
        };
    }

    @NotNull
    private static Value getNewIntValue(@NotNull Set<Integer> possibleNumbers) {
        return switch (usedIntAiType) {
            case INTERVALS -> new IntIntervalValue(possibleNumbers);
            case DEFAULT -> new IntValue(possibleNumbers);
            case SET -> new IntSetValue(possibleNumbers);
        };
    }

    @NotNull
    private static Value getNewIntValue(int lowerBound, int upperBound) {
        return switch (usedIntAiType) {
            case INTERVALS -> new IntIntervalValue(lowerBound, upperBound);
            case DEFAULT -> new IntValue(lowerBound, upperBound);
            case SET -> new IntSetValue(lowerBound, upperBound);
        };
    }

    @NotNull
    private static Value getNewFloatValue() {
        return switch (usedFloatAiType) {
            case DEFAULT -> new FloatValue();
            case SET -> new FloatSetValue();
        };
    }

    @NotNull
    private static Value getNewFloatValue(double number) {
        return switch (usedFloatAiType) {
            case DEFAULT -> new FloatValue(number);
            case SET -> new FloatSetValue(number);
        };
    }

    @NotNull
    private static Value getNewFloatValue(@NotNull Set<Double> possibleNumbers) {
        return switch (usedFloatAiType) {
            case DEFAULT -> new FloatValue(possibleNumbers);
            case SET -> new FloatSetValue(possibleNumbers);
        };
    }

    @NotNull
    private static Value getNewFloatValue(double lowerBound, double upperBound) {
        return switch (usedFloatAiType) {
            case DEFAULT -> new FloatValue(lowerBound, upperBound);
            case SET -> new FloatSetValue(lowerBound, upperBound);
        };
    }

    @NotNull
    private static Value getNewStringValue() {
        return switch (usedStringAiType) {
            case DEFAULT -> new StringValue();
            case CHAR_INCLUSION -> new StringCharInclValue();
            case REGEX -> new StringRegexValue();
        };
    }

    @NotNull
    private static Value getNewStringValue(String value) {
        return switch (usedStringAiType) {
            case DEFAULT -> new StringValue(value);
            case CHAR_INCLUSION -> new StringCharInclValue(value);
            case REGEX -> new StringRegexValue(value);
        };
    }

    @NotNull
    private static Value getNewStringValue(@NotNull Set<String> values) {
        return switch (usedStringAiType) {
            case DEFAULT -> new StringValue();
            case CHAR_INCLUSION -> new StringCharInclValue(values);
            case REGEX -> new StringRegexValue(values);
        };
    }

    @NotNull
    private static Value getNewCharValue() {
        return switch (usedCharAiType) {
            case DEFAULT -> new CharValue();
            case SET -> new CharSetValue();
        };
    }

    @NotNull
    private static Value getNewCharValue(char character) {
        return switch (usedCharAiType) {
            case DEFAULT -> new CharValue(character);
            case SET -> new CharSetValue(character);
        };
    }

    @NotNull
    private static Value getNewCharValue(@NotNull Set<Character> characters) {
        return switch (usedCharAiType) {
            case DEFAULT -> new CharValue(characters);
            case SET -> new CharSetValue(characters);
        };
    }

    @NotNull
    public Type getType() {
        return type;
    }

    /**
     * Performs a binary operation between this value and another value.
     *
     * @param operator the operator.
     * @param other    the other value.
     * @return the result value. VoidValue if the operation does not return a value.
     */
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
    }

    /**
     * Performs a unary operation on this value.
     *
     * @param operator the operator.
     * @return the result value. VoidValue if the operation does not return a value.
     */
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "throw" -> {
                return new VoidValue();
            }
            default ->
                    throw new IllegalArgumentException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    /**
     * Creates and returns a deep copy of this value.
     *
     * @return a deep copy of this value.
     */
    @NotNull
    public abstract Value copy();

    /**
     * Merges the information of another instance of the same value into this one.
     * Types should be the same.
     * For example, when a value has different content in different branches of an if statement.
     *
     * @param other other value.
     */
    public abstract void merge(@NotNull Value other);

    /**
     * Delete all information in this value.
     */
    public abstract void setToUnknown();

    /**
     * Resets all information about this value except its type.
     * The initial value depends on the specific value type.
     */
    public abstract void setInitialValue();

    @Nullable
    public JavaObject getParentObject() {
        return parentObject;
    }

    public void setParentObject(@Nullable JavaObject parentObject) {
        this.parentObject = parentObject;
    }

    public Pair<JavaArray, INumberValue> getArrayPosition() {
        return arrayPosition;
    }

    public void setArrayPosition(JavaArray array, INumberValue index) {
        this.arrayPosition = new Pair<>(array, index);
    }

}
