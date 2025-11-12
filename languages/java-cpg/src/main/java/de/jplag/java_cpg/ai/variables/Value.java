package de.jplag.java_cpg.ai.variables;

/**
 * Anonymous typed value.
 * @author ujiqk
 * @version 1.0 */
public class Value {

    private final Type type;
    private String stringValue;
    private int intValue;
    private boolean booleanValue;
    private JavaObject objectValue;
    private JavaArray arrayValue;

    public Value(String value) {
        type = Type.STRING;
        this.stringValue = value;
    }

    public Value(int value) {
        type = Type.INT;
        this.intValue = value;
    }

    public Value(boolean value) {
        type = Type.BOOLEAN;
        this.booleanValue = value;
    }

    public Value(JavaObject value) {
        type = Type.OBJECT;
        this.objectValue = value;
    }

    public Value(JavaArray value) {
        type = Type.ARRAY;
        this.arrayValue = value;
    }

    public Type getType() {
        return type;
    }

}
