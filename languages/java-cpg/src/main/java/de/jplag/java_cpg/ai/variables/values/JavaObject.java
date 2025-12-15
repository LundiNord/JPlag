package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A Java object in the abstract interpretation.
 * All big data types are also objects (arrays, collections, maps, etc.).
 *
 * @author ujiqk
 * @version 1.0
 */
public class JavaObject extends Value implements IJavaObject {

    private final Scope fields;
    private AbstractInterpretation abstractInterpretation;  //the abstract interpretation engine for this object

    private JavaObject(Scope fields, AbstractInterpretation abstractInterpretation) {
        super(Type.OBJECT);
        this.fields = fields;
        this.abstractInterpretation = abstractInterpretation;
    }

    public JavaObject() {
        super(Type.OBJECT);
        this.fields = new Scope();
    }

    /**
     * Internal constructor for special classes like arrays.
     *
     * @param type the type of the object.
     */
    protected JavaObject(Type type) {
        super(type);
        this.fields = new Scope();
    }

    public AbstractInterpretation getAbstractInterpretation() {
        return abstractInterpretation;
    }

    /**
     * Sets the abstract interpretation engine for this object.
     * If you call methods on this object, this engine will be used for execution.
     *
     * @param abstractInterpretation the abstract interpretation engine or null.
     */
    public void setAbstractInterpretation(AbstractInterpretation abstractInterpretation) {
        this.abstractInterpretation = abstractInterpretation;
    }

    /**
     * @param methodName the name of the method to call.
     * @param paramVars  the parameters to pass to the method.
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
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        switch (operator) {
            case "==" -> {
                if (this.equals(other)) {
                    return new BooleanValue(true);
                } else {
                    return new BooleanValue();
                }
            }
            default ->
                    throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new JavaObject(new Scope(this.fields), this.abstractInterpretation);
    }

    @Override
    public void merge(@NotNull Value other) {
        if (!(other instanceof JavaObject)) {   //cannot merge different types
            setToUnknown();
            return;
        }
        this.fields.merge(((JavaObject) other).fields);
        assert java.util.Objects.equals(this.abstractInterpretation, ((JavaObject) other).abstractInterpretation);
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
