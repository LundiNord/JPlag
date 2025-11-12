package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

public class StringValue extends Value {

    boolean information = false;
    private String value;

    /**
     * A string value with no information.
     */
    public StringValue() {
        super(Type.STRING);
        information = false;
    }

    public StringValue(String value) {
        super(Type.STRING);
        this.value = value;
        information = true;
    }

    @Override
    public IntValue parseInt() {
        if (!information) {
            return new IntValue();
        }
        return new IntValue(Integer.parseInt(value));
    }

}
