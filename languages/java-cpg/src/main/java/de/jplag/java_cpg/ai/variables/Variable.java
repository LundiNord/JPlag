package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.*;
import org.jetbrains.annotations.NotNull;

/**
 * A variable is a named value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class Variable {

    private final VariableName name;
    private Value value;

    public Variable(VariableName name, Value value) {
        assert value != null;
        assert name != null;
        this.name = name;
        this.value = value;
    }

    public Variable(VariableName name, String value) {
        assert name != null;
        this.name = name;
        this.value = new StringValue(value);
    }

    public Variable(VariableName name, int value) {
        assert name != null;
        this.name = name;
        this.value = new IntValue(value);
    }

    public Variable(VariableName name, boolean value) {
        assert name != null;
        this.name = name;
        this.value = new BooleanValue(value);
    }

    public Variable(@NotNull VariableName name, @NotNull Type type) {
        this.name = name;
        switch (type) {
            case INT -> this.value = new IntValue();
            case BOOLEAN -> this.value = new BooleanValue();
            case STRING -> this.value = new StringValue();
            case OBJECT -> this.value = new JavaObject();
            case ARRAY -> this.value = new JavaArray();
            case FLOAT -> this.value = new FloatValue();
            default -> throw new IllegalArgumentException("Unsupported type for variable initialization: " + type);
        }
    }

    /**
     * Copy constructor.
     *
     * @param variable the variable to deep copy.
     */
    public Variable(@NotNull Variable variable) {
        this.name = variable.name;
        this.value = variable.value.copy();
    }

    public Variable(String string, Value value) {
        assert string != null;
        assert value != null;
        this.name = new VariableName(string);
        this.value = value;
    }

    public VariableName getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        assert value != null;
        this.value = value;
    }

    /**
     * Merge content from another variable into this variable.
     * The provided variable must have the same name as this variable.
     * The actual merge behavior is delegated to the underlying {@link Value} implementation.
     *
     * @param value the variable whose content will be merged into this one; must have the same name.
     * @throws AssertionError if the names differ (when assertions are enabled).
     */
    public void merge(@NotNull Variable value) {
        assert value.name.equals(this.name);
        this.value.merge(value.value);
    }

    /**
     * Delete all content information in this variable.
     */
    public void setToUnknown() {
        value.setToUnknown();
    }

    /**
     * Reset all information in this variable expect type and name.
     */
    public void setInitialValue() {
        value.setInitialValue();
    }

}
