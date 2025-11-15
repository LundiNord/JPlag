package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JavaObject extends Value {

    private final Scope fields = new Scope();

    public JavaObject() {
        super(Type.OBJECT);
    }

    /**
     * Internal constructor for special classes like arrays.
     *
     * @param type
     */
    protected JavaObject(Type type) {
        super(type);
    }

    public Value callMethod(String methodName, List<Value> paramVars) {
        //ToDo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Value accessField(String fieldName) {
        assert fieldName != null;
        return fields.getVariable(fieldName).getValue();
    }

    public void setField(Variable field) {
        this.fields.addVariable(field);
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
