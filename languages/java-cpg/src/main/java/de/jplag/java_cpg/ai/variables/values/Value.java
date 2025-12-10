package de.jplag.java_cpg.ai.variables.values;

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

/**
 * Anonymous typed value.
 *
 * @author ujiqk
 * @version 1.0
 */
public abstract class Value {

    private static IntAiType usedIntAiType = IntAiType.DEFAULT;
    private static FloatAiType usedFloatAiType = FloatAiType.DEFAULT;
    private static StringAiType usedStringAiType = StringAiType.DEFAULT;

    private final Type type;
    private Pair<JavaArray, INumberValue> arrayPosition;    //necessary for an array assign to work

    protected Value(Type type) {
        this.type = type;
    }

    public static IntAiType getUsedIntAiType() {
        return usedIntAiType;
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
            case ARRAY -> new JavaArray();
            case FLOAT -> getNewFloatValue();
            case FUNCTION -> new FunctionValue();
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
            case Character c -> {   //ToDo handle char
                return new StringValue(String.valueOf(c));
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
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

    public Pair<JavaArray, INumberValue> getArrayPosition() {
        return arrayPosition;
    }

    public void setArrayPosition(JavaArray array, INumberValue index) {
        this.arrayPosition = new Pair<>(array, index);
    }

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
     * Initial value depends on the specific value type.
     */
    public abstract void setInitialValue();

}
