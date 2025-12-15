package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Java Array representation.
 * Java arrays are objects.
 * Lists are modeled as Java arrays.
 *
 * @author ujiqk
 * @version 1.0
 */
public class JavaArray extends JavaObject implements IJavaArray {

    private final Type innerType;
    private List<Value> values;     //values = null: no information about the array

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray(Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
    }

    public JavaArray(@NotNull List<Value> values) {
        super(Type.ARRAY);
        assert values.stream().map(Value::getType).distinct().count() == 1;
        this.innerType = values.getFirst().getType();
        this.values = values;
    }

    public JavaArray() {
        super(Type.ARRAY);
        this.innerType = null;
    }

    public JavaArray(INumberValue length) {
        super(Type.ARRAY);
        this.innerType = null;  //maybe update later
        if (length.getInformation()) {
            int len = Math.max(0, (int) length.getValue());
            values = new ArrayList<>(len);
            Value placeholder = new VoidValue();
            for (int i = 0; i < len; i++) {
                values.add(placeholder.copy());
            }
        }
        //ToDo: length
    }

    /**
     * Access an element of the array.
     *
     * @param index the index to access, does not have to contain information.
     * @return the superset of possible values at the given indexes.
     */
    public Value arrayAccess(INumberValue index) {
        if (values != null && index.getInformation()) {
            int idx = (int) index.getValue();
            if (idx >= 0 && idx < values.size()) {
                return values.get(idx);
            }
        }
        //if no information, return an unknown value of the inner type
        if (innerType == null) {
            return new VoidValue();
        }
        return switch (innerType) {
            case INT -> Value.valueFactory(Type.INT);
            case BOOLEAN -> new BooleanValue();
            case STRING -> Value.valueFactory(Type.STRING);
            case OBJECT -> new JavaObject();
            case ARRAY -> new JavaArray();
            case FLOAT -> Value.valueFactory(Type.FLOAT);
            case CHAR -> Value.valueFactory(Type.CHAR);
            default -> throw new UnsupportedOperationException("Array of type " + innerType + " not supported");
        };
    }

    public void arrayAssign(INumberValue index, Value value) {
        if (values != null && index.getInformation()) {
            int idx = (int) index.getValue();
            if (idx >= 0 && idx < values.size()) {
                values.set(idx, value);
            }
        } else {
            //no information about the array, set to unknown
            values = null;
        }
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "toString" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new StringValue();
            }
            case "add" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    assert paramVars.getFirst().getType().equals(innerType);
                    values.add(paramVars.getFirst());
                }
                return new VoidValue();
            }
            case "stream" -> {
                assert paramVars == null || paramVars.isEmpty();
                return this;
            }
            case "size" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null) {
                    return Value.valueFactory(values.size());
                }
                return Value.valueFactory(Type.INT);
            }
            case "map" -> {
                //ToDo
                return this;
            }
            case "max" -> {
                //ToDo
                if (innerType == Type.INT) {
                    return Value.valueFactory(Type.INT);
                } else if (innerType == Type.FLOAT) {
                    return Value.valueFactory(Type.FLOAT);
                } else {
                    return new VoidValue();
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public Value accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                if (values != null) {
                    return Value.valueFactory(values.size());
                }
                return Value.valueFactory(Type.INT);
            }
            default ->
                    throw new UnsupportedOperationException("Field " + fieldName + " is not supported for JavaArray");
        }
    }

    @NotNull
    @Override
    public JavaArray copy() {
        List<Value> newValues = new ArrayList<>();
        if (values == null) {
            return new JavaArray(innerType);
        }
        for (Value value : values) {
            newValues.add(value.copy());
        }
        return new JavaArray(newValues);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof JavaArray;
        assert Objects.equals(this.innerType, ((JavaArray) other).innerType);
        if (this.values == null || ((JavaArray) other).values == null || this.values.size() != ((JavaArray) other).values.size()) {
            this.values = null;
        } else {
            for (int i = 0; i < this.values.size(); i++) {
                this.values.get(i).merge(((JavaArray) other).values.get(i));
            }
        }
    }

    @Override
    public void setToUnknown() {
        values = null;
    }

}
