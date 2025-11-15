package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

/**
 * A Java Array.
 * Java arrays are objects.
 *
 * @author ujiqk
 * @version 1.0
 */
public class JavaArray extends JavaObject {

    private final Type innerType;

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray(Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
    }

    public JavaArray() {
        super(Type.ARRAY);
        this.innerType = null;
    }

    @Override
    public JavaArray copy() {
        return new JavaArray(innerType);
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof JavaArray;
        assert this.innerType.equals(((JavaArray) other).innerType);
    }

    public Value arrayAccess(IntValue index) {
        //if no information, return an unknown value of the inner type
        switch (innerType) {
            case INT:
                return new IntValue();
            case BOOLEAN:
                return new BooleanValue();
            case STRING:
                return new StringValue();
            case OBJECT:
                return new JavaObject();
            default:
                throw new UnsupportedOperationException("Array of type " + innerType + " not supported");
        }
    }

    @Override
    public Value accessField(@NotNull String fieldName) {
        switch (fieldName) {
            case "length" -> {
                return new IntValue();
            }
            default ->
                    throw new UnsupportedOperationException("Field " + fieldName + " is not supported for JavaArray");
        }
    }
}
