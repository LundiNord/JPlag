package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

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

    private BooleanValue(boolean value, boolean information) {
        super(Type.BOOLEAN);
        this.value = value;
        this.information = information;
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

    @Override
    public Value copy() {
        return new BooleanValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof BooleanValue;
        BooleanValue otherBool = (BooleanValue) other;
        if (this.information && otherBool.information && this.value == otherBool.value) {
            //keep information
        } else {
            this.information = false;
        }
    }
}
