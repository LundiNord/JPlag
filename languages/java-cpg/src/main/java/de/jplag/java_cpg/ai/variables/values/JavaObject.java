package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JavaObject extends Value {

    private final Scope fields;

    private JavaObject(Scope fields) {
        super(Type.OBJECT);
        this.fields = fields;
    }

    public JavaObject() {
        super(Type.OBJECT);
        this.fields = new Scope();
    }

    /**
     * Internal constructor for special classes like arrays.
     *
     * @param type
     */
    protected JavaObject(Type type) {
        super(type);
        this.fields = new Scope();
    }

    public Value callMethod(String methodName, List<Value> paramVars) {
        //ToDo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Value accessField(String fieldName) {
        assert fieldName != null;
        Variable result = fields.getVariable(fieldName);
        if (result == null) {
            return new VoidValue();
        }
        return result.getValue();
    }

    public void setField(Variable field) {
        this.fields.addVariable(field);
    }

    @Override
    public JavaObject copy() {
        return new JavaObject(new Scope(this.fields));
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof JavaObject;
    }

}
