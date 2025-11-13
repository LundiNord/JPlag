package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.IntValue;
import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;

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

}
