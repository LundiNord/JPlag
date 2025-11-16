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

    public Variable(VariableName name, Type type) {
        assert name != null;
        this.name = name;
        switch (type) {
            case INT -> this.value = new IntValue();
            case BOOLEAN -> this.value = new BooleanValue();
            case STRING -> this.value = new StringValue();
            case OBJECT -> this.value = new JavaObject();
            case ARRAY -> this.value = new JavaArray();
            default -> throw new IllegalArgumentException("Unsupported type for variable initialization: " + type);
        }
    }

    /**
     * Copy constructor
     *
     * @param variable
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

    public void merge(@NotNull Variable value) {
        assert value.name.equals(this.name);
        this.value.merge(value.value);
    }
}
