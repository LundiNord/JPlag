package de.jplag.java_cpg.ai.variables.values.arrays;

import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.*;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

public class JavaLengthArray extends JavaObject implements IJavaArray {

    private final Type innerType;
    private INumberValue length;

    public JavaLengthArray() {
        super(Type.ARRAY);
        this.innerType = Type.UNKNOWN;
    }

    public JavaLengthArray(@NotNull Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
    }

    public JavaLengthArray(@NotNull Type innerType, @NotNull INumberValue length) {
        super(Type.ARRAY);
        this.innerType = innerType;
        this.length = length;
    }

    public JavaLengthArray(List<IValue> values) {
        super(Type.ARRAY);
        this.innerType = values.getFirst().getType();
        this.length = (INumberValue) Value.valueFactory(values.size());
    }

    @Override
    public IValue arrayAccess(INumberValue index) {
        // if no information, return an unknown value of the inner type
        if (innerType == null || innerType == Type.UNKNOWN) {
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

    @Override
    public void arrayAssign(INumberValue index, IValue value) {
        // do nothing
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
                assert paramVars.size() == 1;
                // information lost
                length.setToUnknown();
                return new VoidValue();
            }
            case "get", "elementAt" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof INumberValue;
                return arrayAccess((INumberValue) paramVars.getFirst());
            }
            case "contains" -> {
                assert paramVars.size() == 1;
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "getLast" -> {
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
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public IValue accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                return this.length;
            }
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported for JavaArray");
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        INumberValue newLength = (INumberValue) this.length.copy();
        return new JavaLengthArray(this.innerType, newLength);
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof JavaLengthArray;
        assert Objects.equals(this.innerType, ((JavaLengthArray) other).innerType);
        if (this.length == null || ((JavaLengthArray) other).length == null) {
            this.length = null;
        } else {
            this.length.merge(((JavaLengthArray) other).length);
        }
    }

    @Override
    public void setToUnknown() {
        length = null;
    }

    @Override
    public void setInitialValue() {
        length = null;
    }

}
