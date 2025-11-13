package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

public class IntValue extends Value {

    private int value;
    private boolean information;    //whether exact information is available

    /**
     * a IntValue with no information.
     */
    public IntValue() {
        super(Type.INT);
        information = false;
    }

    public IntValue(int value) {
        super(Type.INT);
        this.value = value;
        information = true;
    }

    public IntValue abs() {
        if (information) {
            return new IntValue(Math.abs(value));
        }
        return new IntValue();
    }

}
