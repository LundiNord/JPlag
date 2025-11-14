package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

public class JavaArray extends Value {

    private final Type innerType;

    /**
     * a Java Array with no information and undefined size.
     */
    public JavaArray(Type innerType) {
        super(Type.ARRAY);
        this.innerType = innerType;
    }

    @Override
    public Value copy() {
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
}
