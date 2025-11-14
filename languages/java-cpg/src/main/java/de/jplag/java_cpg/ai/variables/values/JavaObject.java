package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JavaObject extends Value {

    public JavaObject() {
        super(Type.OBJECT);
    }

    public Value callMethod(String methodName, List<Value> paramVars) {
        //ToDo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Value accessField(String fieldName) {
        //ToDo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public JavaObject copy() {
        return new JavaObject();
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof JavaObject;
    }

}
