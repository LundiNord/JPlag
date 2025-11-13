package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

public class BooleanValue extends Value {

    private boolean value;
    private boolean information;

    public BooleanValue() {
        super(Type.BOOLEAN);
        information = false;
    }

    public BooleanValue(boolean value) {
        super(Type.BOOLEAN);
        this.value = value;
        information = true;
    }

    /**
     * @return whether exact information is available
     */
    public boolean getInformation() {
        return information;
    }

    public boolean getValue() {
        assert information;
        return value;
    }

}
