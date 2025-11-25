package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StringValue extends JavaObject {

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

    public Value callMethod(String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "length" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information) {
                    return new IntValue(value.length());
                } else {
                    return new IntValue();
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    public Value accessField(String fieldName) {
        throw new UnsupportedOperationException("Access field not supported in StringValue");
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (other instanceof VoidValue) {
            return new VoidValue();
        }
        if (operator.equals("+") && other instanceof INumberValue inumbervalue) {
            if (information && inumbervalue.getInformation()) {
                return new StringValue(this.value + inumbervalue.getValue());
            } else {
                return new StringValue();
            }
        } else if (operator.equals("+") && other instanceof StringValue stringvalue) {
            if (information && stringvalue.getInformation()) {
                return new StringValue(this.value + stringvalue.getValue());
            } else {
                return new StringValue();
            }
        }
        throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
    }

    @Override
    public JavaObject copy() {
        return new StringValue(value, information);
    }

    @Override
    public void merge(@NotNull Value other) {
        if (other instanceof VoidValue) {
            this.information = false;
            this.value = null;
            return;
        }
        assert other instanceof StringValue;
        StringValue otherString = (StringValue) other;
        if (this.information && otherString.information && java.util.Objects.equals(this.value, otherString.value)) {
            //keep value
        } else {
            this.information = false;
            this.value = null;
        }
    }

    @Override
    public void setToUnknown() {
        this.information = false;
        this.value = null;
    }

    @Override
    public void setInitialValue() {
        information = true;
        value = null;
    }

    private boolean getInformation() {
        return information;
    }

    private String getValue() {
        assert information;
        return value;
    }

}
