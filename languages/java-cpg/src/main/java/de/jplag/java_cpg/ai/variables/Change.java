package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.Value;

public class Change {

    private final Value oldValue;
    private final Value newValue;

    public Change(Value oldValue, Value newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

}
