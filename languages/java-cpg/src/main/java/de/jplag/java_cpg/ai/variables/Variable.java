package de.jplag.java_cpg.ai.variables;

public class Variable extends Value {

    private String name;

    public Variable(String name, String value) {
        super(value);
        assert name != null;
        this.name = name;
    }

    public Variable(String name, int value) {
        super(value);
        assert name != null;
        this.name = name;
    }

    public Variable(String name, boolean value) {
        super(value);
        assert name != null;
        this.name = name;
    }

    public Variable(String name, JavaObject value) {
        super(value);
        assert name != null;
        this.name = name;
    }

    public Variable(String name, JavaArray value) {
        super(value);
        assert name != null;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Variable createFromVar(String name, Value value) {
        assert value != null;
        assert name != null;
        Variable result = (Variable) value;
        result.name = name;
        return result;
    }

}
