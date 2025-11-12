package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

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

    public Type getType() {
        return type;
    }

    public IntValue parseInt() {
        throw new UnsupportedOperationException("Cannot parse " + getType() + " to int");
    }

}
