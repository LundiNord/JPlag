package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JavaObject extends Value {

    private final Scope fields;
    private AbstractInterpretation abstractInterpretation;  //the abstract interpretation engine for this object

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

    public AbstractInterpretation getAbstractInterpretation() {
        return abstractInterpretation;
    }

    public void setAbstractInterpretation(AbstractInterpretation abstractInterpretation) {
        this.abstractInterpretation = abstractInterpretation;
    }

    /**
     *
     * @param methodName
     * @param paramVars
     * @return null if the method is not known.
     */
    public Value callMethod(String methodName, List<Value> paramVars) {
        if (abstractInterpretation == null) {
            return new VoidValue();
        }
        return abstractInterpretation.runMethod(methodName, paramVars);
    }
    
    public Value accessField(String fieldName) {
        assert fieldName != null;
        Variable result = fields.getVariable(new VariableName(fieldName));
        if (result == null) {
            return new VoidValue();
        }
        return result.getValue();
    }

    public void changeField(String fieldName, Value value) {
        assert fieldName != null;
        Variable variable = fields.getVariable(new VariableName(fieldName));
        variable.setValue(value);
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
        this.fields.merge(((JavaObject) other).fields);
    }

    @Override
    public void setToUnknown() {
        fields.setEverythingUnknown();
    }

    @Override
    public void setInitialValue() {
        //nothing
    }

}
