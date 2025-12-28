package de.jplag.java_cpg.ai.variables.values.string;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.arrays.IJavaArray;
import de.jplag.java_cpg.ai.variables.values.arrays.JavaArray;
import de.jplag.java_cpg.ai.variables.values.chars.ICharValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * String representation. Strings are objects with added functionality.
 * @author ujiqk
 * @version 1.0
 */
public class StringValue extends JavaObject implements IStringValue {

    private boolean information;
    private String value;

    /**
     * A string value with no information.
     */
    public StringValue() {
        super(Type.STRING);
        information = false;
    }

    /**
     * A string value with exact information.
     */
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
    public IValue callMethod(@NotNull String methodName, List<IValue> paramVars, MethodDeclaration method) {
        switch (methodName) {
            case "length" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information) {
                    return Value.valueFactory(value.length());
                } else {
                    return Value.valueFactory(Type.INT);
                }
            }
            case "parseInt" -> {
                assert paramVars.size() == 1;
                StringValue str = (StringValue) paramVars.getFirst();
                if (str.getInformation()) {
                    return Value.valueFactory(Integer.parseInt(str.getValue()));
                } else {
                    return Value.valueFactory(Type.INT);
                }
            }
            case "parseDouble" -> {
                assert paramVars.size() == 1;
                StringValue str = (StringValue) paramVars.getFirst();
                if (str.getInformation()) {
                    return Value.valueFactory(Double.parseDouble(str.getValue()));
                } else {
                    return Value.valueFactory(Type.FLOAT);
                }
            }
            case "startsWith" -> {
                assert paramVars.size() == 1;
                StringValue prefix = (StringValue) paramVars.getFirst();
                if (information && prefix.getInformation()) {
                    return Value.valueFactory(value.startsWith(prefix.getValue()));
                } else {
                    return Value.valueFactory(Type.BOOLEAN);
                }
            }
            case "equals" -> {
                assert paramVars.size() == 1;
                if (paramVars.getFirst() instanceof VoidValue) {
                    paramVars.set(0, Value.valueFactory(Type.STRING));
                }
                StringValue other = (StringValue) paramVars.getFirst();
                if (information && other.getInformation()) {
                    return Value.valueFactory(value.equals(other.getValue()));
                } else {
                    return Value.valueFactory(Type.BOOLEAN);
                }
            }
            case "split" -> {   // public String[] split(String regex)
                assert paramVars.size() == 1;
                StringValue regexValue = (StringValue) paramVars.getFirst();
                if (!information || !regexValue.getInformation()) {
                    return Value.getNewArayValue(Type.STRING);
                }
                assert regexValue.getValue() != null && value != null;
                String[] parts = value.split(regexValue.getValue());
                IJavaArray array = Value.getNewArayValue(Type.STRING);
                for (int i = 0; i < parts.length; i++) {
                    array.arrayAssign((INumberValue) Value.valueFactory(i), new StringValue(parts[i]));
                }
                return array;
            }
            case "charAt" -> {   // public char charAt(int index)
                assert paramVars.size() == 1;
                INumberValue indexValue = (INumberValue) paramVars.getFirst();
                if (!information || !indexValue.getInformation()) {
                    return Value.valueFactory(Type.CHAR);
                }
                assert value != null;
                double index = indexValue.getValue();
                if (index < 0 || index >= value.length()) {
                    return new VoidValue();
                }
                return Value.valueFactory(value.charAt((int) index));
            }
            case "toCharArray" -> {   // public char[] toCharArray()
                assert paramVars == null || paramVars.isEmpty();
                if (!information) {
                    return new JavaArray(Type.CHAR);
                }
                assert value != null;
                char[] chars = value.toCharArray();
                JavaArray array = new JavaArray(Type.CHAR);
                for (int i = 0; i < chars.length; i++) {
                    array.arrayAssign((INumberValue) Value.valueFactory(i), Value.valueFactory(chars[i]));
                }
                return array;
            }
            case "concat" -> {   // public String concat(String str)
                assert paramVars.size() == 1;
                StringValue str = (StringValue) paramVars.getFirst();
                if (information && str.getInformation()) {
                    return new StringValue(this.value + str.getValue());
                } else {
                    return new StringValue();
                }
            }
            case "substring" -> {   // public String substring(int beginIndex, int endIndex)
                assert paramVars.size() == 2;
                INumberValue beginIndexValue = (INumberValue) paramVars.get(0);
                INumberValue endIndexValue = (INumberValue) paramVars.get(1);
                if (information && beginIndexValue.getInformation() && endIndexValue.getInformation()) {
                    int beginIndex = (int) beginIndexValue.getValue();
                    int endIndex = (int) endIndexValue.getValue();
                    if (beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex) {
                        return new VoidValue();
                    }
                    return new StringValue(this.value.substring(beginIndex, endIndex));
                } else {
                    return new StringValue();
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public Value accessField(@NotNull String fieldName) {
        throw new UnsupportedOperationException("Access field not supported in StringValue");
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        if (other instanceof VoidValue) {
            return new VoidValue();
        }
        if (operator.equals("+") && other instanceof INumberValue inumbervalue) {
            if (information && inumbervalue.getInformation()) {
                return new StringValue(this.value + inumbervalue.getValue());
            } else {
                return new StringValue();
            }
        } else if (operator.equals("+") && other instanceof IStringValue stringValue) {
            if (information && stringValue.getInformation()) {
                return new StringValue(this.value + stringValue.getValue());
            } else {
                return new StringValue();
            }
        } else if (operator.equals("+") && other instanceof ICharValue charValue) {
            if (information && charValue.getInformation()) {
                return new StringValue(this.value + charValue.getValue());
            } else {
                return new StringValue();
            }
        } else if (operator.equals("==") && other instanceof NullValue) {
            if (information) {
                return Value.valueFactory(this.value == null);
            } else {
                return Value.valueFactory(Type.BOOLEAN);
            }
        }
        throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new StringValue(value, information);
    }

    @Override
    public void merge(@NotNull IValue other) {
        if (other instanceof VoidValue) {
            this.information = false;
            this.value = null;
            return;
        }
        assert other instanceof StringValue;
        StringValue otherString = (StringValue) other;
        if (this.information && otherString.information && java.util.Objects.equals(this.value, otherString.value)) {
            // keep value
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

    public boolean getInformation() {
        return information;
    }

    public String getValue() {
        assert information;
        return value;
    }

}
