package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.IntValue;
import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

/**
 * A variable is a named value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class Variable {

    private final String name;
    private Value value;

    public Variable(String name, Value value) {
        assert value != null;
        assert name != null;
        this.name = name;
        this.value = value;
    }

    public Variable(String name, String value) {
        assert name != null;
        this.name = name;
        this.value = new StringValue(value);
    }

    public Variable(String name, int value) {
        assert name != null;
        this.name = name;
        this.value = new IntValue(value);
    }

    public Variable(String name, boolean value) {
        assert name != null;
        this.name = name;
        this.value = new BooleanValue(value);
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

    public String getName() {
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
