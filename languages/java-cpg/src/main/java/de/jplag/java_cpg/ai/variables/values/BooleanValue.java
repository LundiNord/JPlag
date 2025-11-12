package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

public class BooleanValue extends Value {

    private boolean value;


    public BooleanValue() {
        super(Type.BOOLEAN);
    }


    public BooleanValue(boolean value) {
        super(Type.BOOLEAN);
        this.value = value;
    }
    
}
