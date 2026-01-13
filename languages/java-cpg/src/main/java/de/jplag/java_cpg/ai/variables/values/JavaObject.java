package de.jplag.java_cpg.ai.variables.values;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

/**
 * A Java object instance in the abstract interpretation. All big data types are also objects (arrays, collections,
 * maps, etc.).
 * @author ujiqk
 * @version 1.0
 */
public class JavaObject extends Value implements IJavaObject {

    // ToDo: save type of the object (class name)
    @Nullable
    private Scope fields;       // fields == null => object null
    @Nullable
    private AbstractInterpretation abstractInterpretation;  // the abstract interpretation engine for this object

    private JavaObject(@Nullable Scope fields, @Nullable AbstractInterpretation abstractInterpretation) {
        super(Type.OBJECT);
        this.fields = fields;
        this.abstractInterpretation = abstractInterpretation;
    }

    public JavaObject(@NotNull AbstractInterpretation abstractInterpretation) {
        super(Type.OBJECT);
        this.fields = new Scope();
        this.abstractInterpretation = abstractInterpretation;
        abstractInterpretation.setRelatedObject(this);
    }

    public JavaObject() {
        super(Type.OBJECT);
        this.fields = new Scope();
    }

    /**
     * Internal constructor for special classes like arrays.
     * @param type the type of the object.
     */
    protected JavaObject(Type type) {
        super(type);
        this.fields = new Scope();
    }

    /**
     * @param methodName the name of the method to call.
     * @param paramVars the parameters to pass to the method.
     * @return null if the method is not known.
     */
    public IValue callMethod(@NotNull String methodName, List<IValue> paramVars, MethodDeclaration method) {
        if (abstractInterpretation == null) {
            return new VoidValue();
        }
        return abstractInterpretation.runMethod(methodName, paramVars, method);
    }

    /**
     * @param fieldName the name of the field to access.
     * @return the value of the field or VoidValue if the field does not exist.
     */
    public IValue accessField(@NotNull String fieldName) {
        Variable result = fields.getVariable(new VariableName(fieldName));
        if (result == null) {
            return new VoidValue();
        }
        return result.getValue();
    }

    public void changeField(@NotNull String fieldName, IValue value) {
        Variable variable = fields.getVariable(new VariableName(fieldName));
        if (variable == null) {
            fields.addVariable(new Variable(fieldName, value));
            return;
        }
        variable.setValue(value);
    }

    /**
     * Sets a new field variable in this object.
     */
    public void setField(@NotNull Variable field) {
        this.fields.addVariable(field);
    }

    /**
     * Sets the abstract interpretation engine for this object. If you call methods on this object, this engine will be used
     * for execution.
     * @param abstractInterpretation the abstract interpretation engine or null.
     */
    public void setAbstractInterpretation(@Nullable AbstractInterpretation abstractInterpretation) {
        this.abstractInterpretation = abstractInterpretation;
    }

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        switch (operator) {
            case "==" -> {
                if (this.equals(other)) {
                    return new BooleanValue(true);
                } else {
                    return new BooleanValue();
                }
            }
            case "!=" -> {
                if (this.equals(other)) {
                    return new BooleanValue(false);
                } else {
                    return new BooleanValue();
                }
            }
            case "+" -> {
                if (other instanceof IStringValue stringValue) {
                    // case: JavaObject with toString method
                    IValue toStringResult = this.callMethod("toString", List.of(), null);
                    if (toStringResult instanceof IStringValue stringFromObject && stringValue.getInformation()
                            && stringFromObject.getInformation()) {
                        return new StringValue(stringValue.getValue() + stringFromObject.getValue());
                    }
                    return new StringValue();
                } else {
                    throw new UnsupportedOperationException(
                            "Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
                }
            }
            case "instanceof" -> {
                return new BooleanValue();
            }
            default -> throw new UnsupportedOperationException(
                    "Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        if (fields == null) {
            return new JavaObject(null, this.abstractInterpretation);
        }
        return new JavaObject(new Scope(this.fields), this.abstractInterpretation);
    }

    @Override
    public void merge(@NotNull IValue other) {
        if (!(other instanceof JavaObject)) {   // cannot merge different types
            setToUnknown();
            return;
        }
        if (fields == null || ((JavaObject) other).fields == null) {
            fields = new Scope();
            return;
        }
        this.fields.merge(((JavaObject) other).fields);
    }

    @Override
    public void setToUnknown() {
        if (fields != null) {
            fields.setEverythingUnknown();
        }
    }

    @Override
    public void setInitialValue() {
        if (fields != null) {
            fields.setEverythingUnknown();
        }
        fields = null;
    }

}
