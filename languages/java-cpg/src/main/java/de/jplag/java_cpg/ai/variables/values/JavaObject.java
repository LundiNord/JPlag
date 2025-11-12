package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;

public class JavaObject extends Value {

    public JavaObject() {
        super(Type.OBJECT);
    }

    @Override
    public IntValue parseInt() {
        throw new UnsupportedOperationException("Cannot parse boolean to int");
    }

}
