package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

/**
 * Anonymous typed value.
 *
 * @author ujiqk
 * @version 1.0
 */
public abstract class Value {

    private final Type type;

    protected Value(Type type) {
        this.type = type;
    }

    public static Value valueFactory(Type type) {
        return switch (type) {
            case INT -> new IntValue();
            case STRING -> new StringValue();
            case BOOLEAN -> new BooleanValue();
            case OBJECT -> new JavaObject();
            case VOID -> new VoidValue();
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        };
    }

    public Type getType() {
        return type;
    }

    public IntValue parseInt() {
        throw new UnsupportedOperationException("Cannot parse " + getType() + " to int");
    }

    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
    }

    public Value unaryOperation(@NotNull String operator) {
        throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
    }

    public abstract Value copy();

    public abstract void merge(@NotNull Value other);
}
