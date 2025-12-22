package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @Nullable
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

    public JavaArray(@NotNull INumberValue length, Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
        if (length.getInformation()) {
            int len = Math.max(0, (int) length.getValue());
            values = new ArrayList<>(len);
            Value placeholder = new VoidValue();
            for (int i = 0; i < len; i++) {
                values.add(placeholder.copy());
            }
        }
    }

    /**
     * Copy Constructor.
     */
    private JavaArray(@Nullable Type innerType, @Nullable List<Value> values) {
        super(Type.ARRAY);
        this.innerType = innerType;
        this.values = values;
    }

    /**
     * Access an element of the array.
     *
     * @param index the index to access; does not have to contain information.
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
            case ARRAY, LIST -> new JavaArray();
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
                if (paramVars.size() == 1) {
                    if (values != null) {
                        assert paramVars.getFirst().getType().equals(innerType);
                        values.add(paramVars.getFirst());
                    }
                    return new VoidValue();
                } else if (paramVars.size() == 2) { //index, element
                    if (values != null) {
                        assert paramVars.getFirst() instanceof INumberValue;
                        INumberValue index = (INumberValue) paramVars.getFirst();
                        if (index.getInformation()) {
                            int idx = (int) index.getValue();
                            if (idx >= 0 && idx <= values.size()) {
                                assert paramVars.getLast().getType().equals(innerType);
                                values.add(idx, paramVars.getLast());
                            } else {
                                values = null; //no information
                            }
                        } else {
                            values = null; //no information
                        }
                    }
                    return new VoidValue();
                } else {
                    throw new UnsupportedOperationException("add with " + paramVars.size() + " parameters is not supported");
                }
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
            case "indexOf" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        if (values.get(i).equals(paramVars.getFirst())) {
                            return Value.valueFactory(i);
                        }
                    }
                    return Value.valueFactory(-1);
                }
                return Value.valueFactory(Type.INT);
            }
            case "remove" -> {
                assert paramVars.size() == 1;
                if (values == null) {
                    return new VoidValue();
                }
                //either remove(int index) or remove(Object o) -> ToDo: cannot distinguish with Integer parameter
                if (paramVars.getFirst() instanceof INumberValue number) {
                    if (number.getInformation()) {
                        return values.remove((int) number.getValue());
                    }
                    return new VoidValue();
                } else {
                    for (int i = 0; i < values.size(); i++) {
                        if (values.get(i).equals(paramVars.getFirst())) {
                            values.remove(i);
                            return Value.valueFactory(true);
                        }
                    }
                    return Value.valueFactory(false);
                }
            }
            case "get", "elementAt" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof INumberValue;
                return arrayAccess((INumberValue) paramVars.getFirst());
            }
            case "contains" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    for (Value value : values) {
                        if (value.equals(paramVars.getFirst())) {
                            return Value.valueFactory(true);
                        }
                    }
                    return Value.valueFactory(false);
                }
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "lastIndexOf" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    for (int i = values.size() - 1; i >= 0; i--) {
                        if (values.get(i).equals(paramVars.getFirst())) {
                            return Value.valueFactory(i);
                        }
                    }
                    return Value.valueFactory(-1);
                }
                return Value.valueFactory(Type.INT);
            }
            case "getLast" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.getLast();
                }
                //no information
                if (innerType == null) {
                    return new VoidValue();
                }
                return arrayAccess((INumberValue) Value.valueFactory(1));
            }
            case "removeLast" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.removeLast();
                }
                //no information
                values = null;
                return new VoidValue();
            }
            case "addLast" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    assert paramVars.getFirst().getType().equals(innerType);
                    values.add(paramVars.getFirst());
                }
                return new VoidValue();
            }
            case "removeFirst" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.removeFirst();
                }
                //no information
                values = null;
                return new VoidValue();
            }
            case "isEmpty" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null) {
                    return Value.valueFactory(values.isEmpty());
                }
                return Value.valueFactory(Type.BOOLEAN);
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
        return new JavaArray(innerType, newValues);
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

    @Override
    public void setInitialValue() {
        values = null;
    }

}
