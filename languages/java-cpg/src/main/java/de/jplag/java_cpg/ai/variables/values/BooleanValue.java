package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.checkerframework.dataflow.qual.Pure;
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

    @Pure
    @Override
    public Value unaryOperation(@NotNull String operator) {
        switch (operator) {
            case "!" -> {
                if (information) {
                    return new BooleanValue(!value);
                } else {
                    return new BooleanValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Unary operation " + operator + " not supported for " + getType());
        }
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

    @Override
    public void setToUnknown() {
        this.information = false;
    }

}
