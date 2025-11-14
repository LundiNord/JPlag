package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

public class StringValue extends Value {

    private boolean information;
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

    private StringValue(String value, boolean information) {
        super(Type.STRING);
        this.value = value;
        this.information = information;
    }

    @Override
    public IntValue parseInt() {
        if (!information) {
            return new IntValue();
        }
        return new IntValue(Integer.parseInt(value));
    }

    @Override
    public Value copy() {
        return new StringValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof StringValue;
        StringValue otherString = (StringValue) other;
        if (this.information && otherString.information && this.value.equals(otherString.value)) {
            //keep value
        } else {
            this.information = false;
            this.value = null;
        }
    }

}
