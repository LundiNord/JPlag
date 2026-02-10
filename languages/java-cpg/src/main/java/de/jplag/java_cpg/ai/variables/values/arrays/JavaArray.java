package de.jplag.java_cpg.ai.variables.values.arrays;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.JavaLanguageFeatureNotSupportedException;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * A Java Array representation. Java arrays are objects. Lists are modeled as Java arrays.
 * @author ujiqk
 * @version 1.0
 */
public class JavaArray extends JavaObject implements IJavaArray {

    private final Type innerType;
    @Nullable
    private List<IValue> values;     // values = null: array == null
    private boolean information;

    /**
     * a Java Array with no information and undefined size.
     * @param innerType the type of the array elements.
     */
    public JavaArray(Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
        this.values = null;
        this.information = false;
    }

    /**
     * a Java Array with exact information.
     * @param values the values of the array in the correct order.
     * @throws UnsupportedOperationException if the inner type is not supported.
     */
    public JavaArray(@NotNull List<IValue> values) {
        super(Type.ARRAY);
        Type innerTypeTemp = Type.UNKNOWN;
        if (values.isEmpty()) {
            innerType = Type.UNKNOWN;
            this.values = values;
            this.information = true;
            return;
        }
        for (IValue value : values) {
            if (innerTypeTemp != Type.UNKNOWN) {
                assert value.getType() == innerTypeTemp || value.getType() == Type.UNKNOWN : "Inconsistent types in array initialization: "
                        + innerTypeTemp + " and " + value.getType();
                continue;
            }
            if (value.getType() != Type.UNKNOWN) {
                innerTypeTemp = value.getType();
            }
        }
        List<IValue> newValues = new ArrayList<>();
        for (IValue value : values) {   // exchange void values with unknown values of the inner type
            if (value.getType() == Type.UNKNOWN) {
                switch (innerTypeTemp) {
                    case INT -> newValues.add(Value.valueFactory(Type.INT));
                    case BOOLEAN -> newValues.add(new BooleanValue());
                    case STRING -> newValues.add(Value.valueFactory(Type.STRING));
                    case OBJECT -> newValues.add(new JavaObject(innerTypeTemp.getTypeName()));
                    case ARRAY, LIST -> newValues.add(new JavaArray());
                    case FLOAT -> newValues.add(Value.valueFactory(Type.FLOAT));
                    case CHAR -> newValues.add(Value.valueFactory(Type.CHAR));
                    default -> throw new UnsupportedOperationException("Array of type " + innerTypeTemp + " not supported");
                }
            } else {
                newValues.add(value);
            }
        }
        this.values = newValues;
        this.innerType = innerTypeTemp;
        this.information = true;
    }

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray() {
        super(Type.ARRAY);
        this.innerType = Type.UNKNOWN;
        this.values = null;
        this.information = false;
    }

    /**
     * a Java Array with exact length and type information.
     * @param length the length of the array; must contain information.
     * @param innerType the type of the array elements.
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
     * @throws UnsupportedOperationException if the inner type is not supported.
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
            case OBJECT -> new JavaObject(innerType.getTypeName());
            case ARRAY, LIST -> new JavaArray();
            case FLOAT -> Value.valueFactory(Type.FLOAT);
            case CHAR -> Value.valueFactory(Type.CHAR);
            case VOID, UNKNOWN -> new VoidValue();
            default -> throw new UnsupportedOperationException("Array of type " + innerType + " not supported");
        };
    }

    /**
     * Assign a value to a position in the array.
     * @param index the index to assign to; does not have to contain information.
     * @param value the value to assign.
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
                return Value.getNewStringValue();
            }
            case "add" -> {
                if (paramVars.size() == 1) {
                    if (information) {
                        assert paramVars.getFirst().getType().equals(innerType);
                        values.add(paramVars.getFirst());
                    }
                    return new VoidValue();
                } else if (paramVars.size() == 2) { // index, element
                    if (information) {
                        assert paramVars.getFirst() instanceof INumberValue;
                        INumberValue index = (INumberValue) paramVars.getFirst();
                        if (index.getInformation()) {
                            int idx = (int) index.getValue();
                            if (idx >= 0 && idx <= values.size()) {
                                assert paramVars.getLast().getType().equals(innerType);
                                values.add(idx, paramVars.getLast());
                            } else {
                                values = null; // no information
                                this.information = false;
                            }
                        } else {
                            values = null; // no information
                            this.information = false;
                        }
                    }
                    return new VoidValue();
                } else {
                    throw new UnsupportedOperationException("add with " + paramVars.size() + " parameters is not supported");
                }
            }
            case "stream", "toArray" -> {
                if (paramVars != null && paramVars.size() == 1) {
                    return paramVars.getFirst();
                }
                assert paramVars == null || paramVars.isEmpty() : "Method " + methodName + " does not take parameters but " + paramVars.size()
                        + " were given";
                return this;
            }
            case "size" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information) {
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
                if (information) {
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
                if (paramVars == null || paramVars.isEmpty()) {    // remove head
                    return this.callMethod("removeFirst", null, method);
                }
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
                if (information) {
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
                if (information) {
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
                if (information && !values.isEmpty()) {
                    return values.getLast();
                }
                // no information
                return arrayAccess((INumberValue) Value.valueFactory(1));
            }
            case "removeLast", "pop" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information && !values.isEmpty() && information) {
                    return values.removeLast();
                }
                // no information
                values = null;
                this.information = false;
                return Value.valueFactory(innerType);
            }
            case "addLast", "push" -> {
                assert paramVars.size() == 1;
                if (information) {
                    assert paramVars.getFirst().getType().equals(innerType);
                    values.add(paramVars.getFirst());
                }
                return new VoidValue();
            }
            case "removeFirst", "poll" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information && !values.isEmpty()) {
                    return values.removeFirst();
                }
                // no information
                values = null;
                this.information = false;
                return Value.valueFactory(innerType);
            }
            case "isEmpty" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information) {
                    return Value.valueFactory(values.isEmpty());
                }
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "fill" -> {        // void fill(int[] a, int val) or void fill(int[] a, int fromIndex, int toIndex, int val)
                assert paramVars.size() == 1 || paramVars.size() == 3;
                if (information) {
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
                                this.information = false;
                            }
                        } else {
                            values = null; // no information
                            this.information = false;
                        }
                    }
                }
                return new VoidValue();
            }
            case "sort" -> {        // void// sort(int[] a) or void sort(int[] a, int fromIndex, int toIndex)
                if (paramVars.size() == 1) {    // with Comparator
                    this.values = null; // ToDo
                    this.information = false;
                    return new VoidValue();
                }
                assert paramVars.size() == 0 || paramVars.size() == 2;
                if (information) {
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
                                this.information = false;
                                values = null; // no information
                            }
                        } else {
                            this.information = false;
                            values = null; // no information
                        }
                    }
                }
                return new VoidValue();
            }
            case "copyOfRange" -> { // int[] copyOfRange(int[] original, int from, int to)
                assert paramVars.size() == 2;
                if (information) {
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
                if (information) {
                    values.clear();
                }
                return new VoidValue();
            }
            case "getFirst", "peekFirst" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (information && !values.isEmpty()) {
                    return values.getFirst();
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
                    if (this.information && otherArray.information) {
                        for (IValue val : otherArray.values) {
                            assert val.getType().equals(this.innerType);
                            this.values.add(val);
                        }
                    } else {
                        this.information = false;
                        this.values = null;
                    }
                } else {
                    this.information = false;
                    this.values = null;
                }
                return new VoidValue();
            }
            case "addFirst" -> {
                assert paramVars.size() == 1;
                if (information) {
                    assert paramVars.getFirst().getType().equals(innerType);
                    values.addFirst(paramVars.getFirst());
                }
                return new VoidValue();
            }
            case "iterator" -> throw new JavaLanguageFeatureNotSupportedException("Iterators are not supported");
            case "clone" -> {
                assert paramVars == null || paramVars.isEmpty();
                return this.copy();
            }
            case "filter", "collect" -> {
                return Value.valueFactory(Type.LIST);
            }
            case "findFirst" -> {
                if (information) {
                    if (!values.isEmpty()) {
                        return values.getFirst();
                    }
                }
                return new VoidValue();
            }
            case "forEach" -> {
                return Value.valueFactory(Type.LIST);
            }
            case "set" -> {
                assert paramVars.size() == 2;
                if (information) {
                    assert paramVars.getFirst() instanceof INumberValue;
                    INumberValue index = (INumberValue) paramVars.getFirst();
                    if (index.getInformation()) {
                        int idx = (int) index.getValue();
                        if (idx >= 0 && idx < values.size()) {
                            assert paramVars.getLast().getType().equals(innerType);
                            values.set(idx, paramVars.getLast());
                        } else {
                            information = false;
                        }
                    } else {
                        information = false;
                    }
                }
                return new VoidValue();
            }
            case "offer" -> {
                assert paramVars.size() == 1;
                setToUnknown();
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "containsAll" -> {
                assert paramVars.size() == 1;
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "removeAll", "retainAll" -> {
                assert paramVars.size() == 1;
                setToUnknown();
                return Value.valueFactory(Type.BOOLEAN);
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public IValue accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                if (information) {
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

    @NotNull
    @Override
    public JavaObject copy(Map<JavaObject, JavaObject> copiedObjects) {
        return copy();
    }

    @Override
    public JavaArray merge(@NotNull IValue other) {
        // if (other instanceof VoidValue || other instanceof IJavaObject) { // cannot merge different types
        // other = new JavaArray();
        // }
        JavaArray otherArray = (JavaArray) other;
        // if (this.innerType == null && otherArray.innerType != null) {
        // this.innerType = otherArray.innerType;
        // }
        // if (!(Objects.equals(this.innerType, otherArray.innerType))) {
        // this.values = null;
        // return;
        // }
        assert Objects.equals(this.innerType, otherArray.innerType);
        if (!information || !otherArray.information) {
            this.information = false;
        } else {
            if (values == null && otherArray.values == null) {
                // nothing to do, both are unknown
            } else if (values == null || otherArray.values == null) {
                this.information = false;
            } else if (values.size() != otherArray.values.size()) {
                this.information = false;   // ToDo: we could also merge up to the minimum size and set the rest to unknown
            } else {
                for (int i = 0; i < this.values.size(); i++) {
                    this.values.get(i).merge(otherArray.values.get(i));
                }
            }
        }
        return this;
    }

    @Override
    public void setToUnknown() {
        information = false;
    }

    @Override
    public void setToUnknown(Set<IJavaObject> visited) {
        setToUnknown();
    }

    @Override
    public void setInitialValue(Set<IJavaObject> visited) {
        setInitialValue();
    }

    @Override
    public void setInitialValue() {
        values = null;
    }

}
