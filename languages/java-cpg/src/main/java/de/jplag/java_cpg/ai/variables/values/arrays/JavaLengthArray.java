package de.jplag.java_cpg.ai.variables.values.arrays;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * Represents a Java array by its length and inner type.
 * @author ujiqk
 */
public class JavaLengthArray extends JavaObject implements IJavaArray {

    private final Type innerType;
    private INumberValue length;        // array == null -> length == null

    /**
     * Creates a new JavaLengthArray with an unknown inner type and length.
     */
    public JavaLengthArray() {
        super(Type.ARRAY);
        this.innerType = Type.UNKNOWN;
        this.length = Value.getNewIntValue();
    }

    /**
     * Creates a new JavaLengthArray with the given inner type and unknown length.
     * @param innerType The inner type of the array.
     */
    public JavaLengthArray(@NotNull Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
        this.length = Value.getNewIntValue();
    }

    /**
     * Creates a new JavaLengthArray with the given inner type and length.
     * @param innerType The inner type of the array.
     * @param length The length of the array.
     */
    public JavaLengthArray(@NotNull Type innerType, @Nullable INumberValue length) {
        super(Type.ARRAY);
        this.length = length;
        this.innerType = innerType;
    }

    /**
     * Creates a new JavaLengthArray with the inner type and length derived from the given values.
     * @param values The values to derive the inner type and length from.
     */
    public JavaLengthArray(@NotNull List<IValue> values) {
        super(Type.ARRAY);
        if (values.isEmpty()) {
            this.innerType = Type.UNKNOWN;
        } else {
            this.innerType = values.getFirst().getType();
        }
        this.length = (INumberValue) Value.valueFactory(values.size());
    }

    @Override
    public IValue arrayAccess(INumberValue index) {
        // if no information, return an unknown value of the inner type
        if (innerType == Type.UNKNOWN) {
            return new UnknownValue();
        }
        return switch (innerType) {
            case INT -> Value.valueFactory(Type.INT);
            case BOOLEAN -> new BooleanValue();
            case STRING -> Value.valueFactory(Type.STRING);
            case OBJECT -> new JavaObject(innerType.getTypeName());
            case ARRAY -> Value.valueFactory(Type.ARRAY);
            case LIST -> Value.valueFactory(Type.LIST);
            case FLOAT -> Value.valueFactory(Type.FLOAT);
            case CHAR -> Value.valueFactory(Type.CHAR);
            default -> throw new UnsupportedOperationException("Array of type " + innerType + " not supported");
        };
    }

    @Override
    public void arrayAssign(INumberValue index, IValue value) {
        // do nothing
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
                    length.unaryOperation("++");
                } else if (paramVars.size() == 2) { // index, element
                    // do nothing, length stays the same
                } else {
                    throw new UnsupportedOperationException("add with " + paramVars.size() + " parameters is not supported");
                }
                return new VoidValue();
            }
            case "stream" -> {
                assert paramVars == null || paramVars.isEmpty();
                return this;
            }
            case "size" -> {
                assert paramVars == null || paramVars.isEmpty();
                return this.length;
            }
            case "map" -> {
                // ToDo
                return this;
            }
            case "max" -> {
                return arrayAccess((INumberValue) Value.valueFactory(1));
            }
            case "indexOf", "lastIndexOf" -> {
                assert paramVars.size() == 1;
                return Value.valueFactory(Type.INT);
            }
            case "remove" -> {
                assert paramVars == null || paramVars.size() == 1 || paramVars.isEmpty();
                // information lost
                length = Value.getNewIntValue();
                return new VoidValue();
            }
            case "get", "elementAt" -> {
                assert paramVars.size() == 1;
                if (paramVars.getFirst() instanceof VoidValue) {
                    paramVars.set(0, Value.getNewIntValue());
                }
                assert paramVars.getFirst() instanceof INumberValue : "Parameter for get/elementAt must be a number value but was "
                        + paramVars.getFirst().getClass();
                return arrayAccess((INumberValue) paramVars.getFirst());
            }
            case "contains" -> {
                assert paramVars.size() == 1;
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "getLast", "findFirst", "findAny" -> {
                assert paramVars == null || paramVars.isEmpty();
                return arrayAccess((INumberValue) Value.valueFactory(1));
            }
            case "removeLast", "removeFirst" -> {
                assert paramVars == null || paramVars.isEmpty();
                this.length.unaryOperation("--");
                return new VoidValue();
            }
            case "addLast" -> {
                assert paramVars.size() == 1;
                this.length.unaryOperation("++");
                return new VoidValue();
            }
            case "isEmpty" -> {
                assert paramVars == null || paramVars.isEmpty();
                if (length.getInformation()) {
                    return Value.valueFactory(length.getValue() == 0);
                }
                return Value.valueFactory(Type.BOOLEAN);
            }
            default -> {
                return new VoidValue();
            }
        }
    }

    @Override
    public IValue accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                return this.length;
            }
            default -> {
                return new UnknownValue();
            }
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        if (this.length == null) {
            return new JavaLengthArray(this.innerType);
        }
        INumberValue newLength = (INumberValue) this.length.copy();
        return new JavaLengthArray(this.innerType, newLength);
    }

    @NotNull
    @Override
    public JavaObject copy(Map<JavaObject, JavaObject> copiedObjects) {
        return copy();
    }

    @Override
    public JavaLengthArray merge(@NotNull IValue other) {
        // if (other instanceof VoidValue) {
        // other = new JavaLengthArray(this.innerType);
        // }
        assert other instanceof JavaLengthArray : "Can only merge with another JavaLengthArray but got " + other.getClass();
        final JavaLengthArray otherArray = (JavaLengthArray) other;
        // if (this.innerType == Type.UNKNOWN) {
        // this.innerType = otherArray.innerType;
        // }
        if (otherArray.innerType != Type.UNKNOWN) {
            assert Objects.equals(this.innerType, otherArray.innerType) : "Cannot merge arrays of different inner types: " + this.innerType + " and "
                    + ((JavaLengthArray) other).innerType;
        }
        if (this.length == null || otherArray.length == null) {
            this.length = null;
        } else {
            this.length.merge(otherArray.length);
        }
        return this;
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return new JavaLengthArray(this.innerType, null);
    }

    @Override
    public void setToUnknown() {
        length = Value.getNewIntValue();
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
        length = null;
    }

}
