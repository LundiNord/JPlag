package de.jplag.java_cpg.ai.variables.values.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

/**
 * A Java Array representation. Java arrays are objects. Lists are modeled as Java arrays.
 * @author ujiqk
 * @version 1.0
 */
public class JavaArray extends JavaObject implements IJavaArray {

    private final Type innerType;
    @Nullable
    private List<IValue> values;     // values = null: no information about the array

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray(Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
    }

    /**
     * a Java Array with exact information.
     * @param values the values of the array in the correct order.
     */
    public JavaArray(@NotNull List<IValue> values) {
        super(Type.ARRAY);
        assert values.stream().map(IValue::getType).distinct().count() == 1;
        this.innerType = values.getFirst().getType();
        this.values = values;
    }

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray() {
        super(Type.ARRAY);
        this.innerType = null;
    }

    /**
     * a Java Array with exact length and type information.
     */
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

    private JavaArray(@Nullable Type innerType, @Nullable List<IValue> values) {
        super(Type.ARRAY);
        this.innerType = innerType;
        this.values = values;
    }

    /**
     * Access an element of the array.
     * @param index the index to access; does not have to contain information.
     * @return the superset of possible values at the given indexes.
     */
    public IValue arrayAccess(INumberValue index) {
        if (values != null && index.getInformation()) {
            int idx = (int) index.getValue();
            if (idx >= 0 && idx < values.size()) {
                return values.get(idx);
            }
        }
        // if no information, return an unknown value of the inner type
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

    /**
     * Assign a value to a position in the array.
     */
    public void arrayAssign(INumberValue index, IValue value) {
        if (values != null && index.getInformation()) {
            int idx = (int) index.getValue();
            if (idx >= 0 && idx < values.size()) {
                values.set(idx, value);
            }
        } else {
            // no information about the array, set to unknown
            values = null;
        }
    }

    @Override
    public IValue callMethod(@NotNull String methodName, List<IValue> paramVars, MethodDeclaration method) {
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
                } else if (paramVars.size() == 2) { // index, element
                    if (values != null) {
                        assert paramVars.getFirst() instanceof INumberValue;
                        INumberValue index = (INumberValue) paramVars.getFirst();
                        if (index.getInformation()) {
                            int idx = (int) index.getValue();
                            if (idx >= 0 && idx <= values.size()) {
                                assert paramVars.getLast().getType().equals(innerType);
                                values.add(idx, paramVars.getLast());
                            } else {
                                values = null; // no information
                            }
                        } else {
                            values = null; // no information
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
                // ToDo
                return this;
            }
            case "max" -> {
                // ToDo
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
                // either remove(int index) or remove(Object o) -> ToDo: cannot distinguish with Integer parameter
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
                if (paramVars.getFirst() instanceof VoidValue) {
                    paramVars.set(0, Value.valueFactory(Type.INT));
                }
                assert paramVars.getFirst() instanceof INumberValue;
                return arrayAccess((INumberValue) paramVars.getFirst());
            }
            case "contains" -> {
                assert paramVars.size() == 1;
                if (values != null) {
                    for (IValue value : values) {
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
            case "getLast", "peek" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.getLast();
                }
                // no information
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
                // no information
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
            case "removeFirst", "poll" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.removeFirst();
                }
                // no information
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
            case "fill" -> {        // void fill(int[] a, int val) or void fill(int[] a, int fromIndex, int toIndex, int val)
                assert paramVars.size() == 1 || paramVars.size() == 3;
                if (values != null) {
                    if (paramVars.size() == 1) {
                        IValue val = paramVars.getFirst();
                        for (int i = 0; i < values.size(); i++) {
                            values.set(i, val);
                        }
                    } else {
                        assert paramVars.getFirst() instanceof INumberValue;
                        assert paramVars.get(1) instanceof INumberValue;
                        INumberValue fromIndex = (INumberValue) paramVars.getFirst();
                        INumberValue toIndex = (INumberValue) paramVars.get(1);
                        if (fromIndex.getInformation() && toIndex.getInformation()) {
                            int fromIdx = (int) fromIndex.getValue();
                            int toIdx = (int) toIndex.getValue();
                            if (fromIdx >= 0 && toIdx <= values.size() && fromIdx <= toIdx) {
                                IValue val = paramVars.get(2);
                                for (int i = fromIdx; i < toIdx; i++) {
                                    values.set(i, val);
                                }
                            } else {
                                values = null; // no information
                            }
                        } else {
                            values = null; // no information
                        }
                    }
                }
                return new VoidValue();
            }
            case "sort" -> {        // void// sort(int[] a) or void sort(int[] a, int fromIndex, int toIndex)
                if (paramVars.size() == 1) {    // with Comparator
                    this.values = null; // ToDo
                    return new VoidValue();
                }
                assert paramVars.size() == 0 || paramVars.size() == 2;
                if (values != null) {
                    if (paramVars.size() == 0) {
                        values.sort(null);
                    } else {
                        assert paramVars.getFirst() instanceof INumberValue;
                        assert paramVars.get(1) instanceof INumberValue;
                        INumberValue fromIndex = (INumberValue) paramVars.getFirst();
                        INumberValue toIndex = (INumberValue) paramVars.get(1);
                        if (fromIndex.getInformation() && toIndex.getInformation()) {
                            int fromIdx = (int) fromIndex.getValue();
                            int toIdx = (int) toIndex.getValue();
                            if (fromIdx >= 0 && toIdx <= values.size() && fromIdx <= toIdx) {
                                List<IValue> sublist = values.subList(fromIdx, toIdx);
                                sublist.sort(null);
                                for (int i = fromIdx; i < toIdx; i++) {
                                    values.set(i, sublist.get(i - fromIdx));
                                }
                            } else {
                                values = null; // no information
                            }
                        } else {
                            values = null; // no information
                        }
                    }
                }
                return new VoidValue();
            }
            case "copyOfRange" -> { // int[] copyOfRange(int[] original, int from, int to)
                assert paramVars.size() == 2;
                if (values != null) {
                    assert paramVars.getFirst() instanceof INumberValue;
                    assert paramVars.get(1) instanceof INumberValue;
                    INumberValue fromIndex = (INumberValue) paramVars.getFirst();
                    INumberValue toIndex = (INumberValue) paramVars.get(1);
                    if (fromIndex.getInformation() && toIndex.getInformation()) {
                        int fromIdx = (int) fromIndex.getValue();
                        int toIdx = (int) toIndex.getValue();
                        if (fromIdx >= 0 && toIdx <= values.size() && fromIdx <= toIdx) {
                            List<IValue> sublist = new ArrayList<>(values.subList(fromIdx, toIdx));
                            return new JavaArray(sublist);
                        }
                    }
                }
                return new JavaArray(innerType);
            }
            case "clear" -> {
                if (values != null) {
                    values.clear();
                }
                return new VoidValue();
            }
            case "getFirst", "peekFirst" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (values != null && !values.isEmpty()) {
                    return values.getFirst();
                }
                // no information
                if (innerType == null) {
                    return new VoidValue();
                }
                return arrayAccess((INumberValue) Value.valueFactory(0));
            }
            case "removeFirstOccurrence" -> {
                assert paramVars.size() == 1;
                if (values == null) {
                    return Value.valueFactory(false);
                }
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i).equals(paramVars.getFirst())) {
                        values.remove(i);
                        return Value.valueFactory(true);
                    }
                }
                return Value.valueFactory(false);
            }
            case "addAll" -> {
                assert paramVars.size() == 1;
                if (paramVars.getFirst() instanceof JavaArray otherArray) {
                    if (this.values != null && otherArray.values != null) {
                        for (IValue val : otherArray.values) {
                            assert val.getType().equals(this.innerType);
                            this.values.add(val);
                        }
                    } else {
                        this.values = null;
                    }
                } else {
                    this.values = null;
                }
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public IValue accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                if (values != null) {
                    return Value.valueFactory(values.size());
                }
                return Value.valueFactory(Type.INT);
            }
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported for JavaArray");
        }
    }

    @NotNull
    @Override
    public JavaArray copy() {
        List<IValue> newValues = new ArrayList<>();
        if (values == null) {
            return new JavaArray(innerType);
        }
        for (IValue value : values) {
            newValues.add(value.copy());
        }
        return new JavaArray(innerType, newValues);
    }

    @Override
    public void merge(@NotNull IValue other) {
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
