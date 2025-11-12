package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * A variable is a named value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class Variable {

    private String name;
    private Value value;

    public Variable(String name, Value value) {
        assert value != null;
        assert name != null;
        this.name = name;
        this.value = value;
    }

    public Variable(String name, String value) {
        assert name != null;
        this.name = name;
        this.value = new StringValue(value);
    }
//
//    public Variable(String name, int value) {
//        super(value);
//        assert name != null;
//        this.name = name;
//    }
//
//    public Variable(String name, boolean value) {
//        super(value);
//        assert name != null;
//        this.name = name;
//    }

//    public Variable(String name, JavaArray value) {
//        super(value);
//        assert name != null;
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

}
