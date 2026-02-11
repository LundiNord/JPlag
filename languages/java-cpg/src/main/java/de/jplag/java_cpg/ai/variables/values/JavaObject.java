package de.jplag.java_cpg.ai.variables.values;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Scope;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.Variable;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;

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
    private final @NotNull String typeName;
    @Nullable
    private AbstractInterpretation abstractInterpretation;  // the abstract interpretation engine for this object

    private JavaObject(@Nullable Scope fields, @Nullable AbstractInterpretation abstractInterpretation, @NotNull String typeName) {
        super(Type.OBJECT);
        this.fields = fields;
        this.abstractInterpretation = abstractInterpretation;
        this.typeName = typeName;
    }

    /**
     * Constructor for a Java object with an abstract interpretation engine and no info.
     * @param abstractInterpretation the abstract interpretation engine where methods will be executed.
     */
    public JavaObject(@NotNull AbstractInterpretation abstractInterpretation, @NotNull String typeName) {
        super(Type.OBJECT);
        this.fields = new Scope();
        this.typeName = typeName;
        this.abstractInterpretation = abstractInterpretation;
        abstractInterpretation.setRelatedObject(this);
    }

    public JavaObject(@NotNull AbstractInterpretation abstractInterpretation) {
        super(Type.OBJECT);
        this.fields = new Scope();
        this.typeName = "Deeeeebug";
        this.abstractInterpretation = abstractInterpretation;
        abstractInterpretation.setRelatedObject(this);
        throw new RuntimeException();
    }

    /**
     * Default constructor for a Java object with no abstract interpretation engine and no info.
     */
    public JavaObject(@NotNull String typeName) {
        super(Type.OBJECT);
        this.fields = new Scope();
        this.typeName = typeName;
    }

    public JavaObject() {
        super(Type.OBJECT);
        this.fields = new Scope();
        this.typeName = "Deeeeeeebug";
        throw new RuntimeException();
    }

    /**
     * Internal constructor for special classes like arrays.
     * @param type the type of the object.
     */
    protected JavaObject(Type type) {
        super(type);
        this.typeName = type.toString();
        this.fields = new Scope();
    }

    /**
     * @param methodName the name of the method to call.
     * @param paramVars the parameters to pass to the method.
     * @param method the cpg method declaration of the method to call.
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
        // if (fields == null) {
        // return new VoidValue(); // ToDo: is this correct?
        // }
        assert fields != null;
        Variable result = fields.getVariable(new VariableName(fieldName));
        if (result == null) {
            return new VoidValue();
        }
        return result.getValue();
    }

    /**
     * Changes the value of an existing field variable in this object. If the field does not exist, it will be created.
     * @param fieldName the name of the field to change.
     * @param value the new value of the field.
     */
    public void changeField(@NotNull String fieldName, IValue value) {
        // if (fields == null) {
        // // reset information
        // fields = new Scope(); // ToDo: is this correct?
        // return;
        // }
        assert fields != null;
        Variable variable = fields.getVariable(new VariableName(fieldName));
        if (variable == null) {
            fields.addVariable(new Variable(fieldName, value));
            return;
        }
        variable.setValue(value);
    }

    /**
     * @param field Sets a new field variable in this object.
     */
    public void setField(@NotNull Variable field) {
        assert this.fields != null;
        this.fields.addVariable(field);
    }

    /**
     * Sets the abstract interpretation engine for this object. If you call methods on this object, this engine will be used
     * for execution.
     * @param abstractInterpretation the abstract interpretation engine or null.
     */
    public void setAbstractInterpretation(@Nullable AbstractInterpretation abstractInterpretation) {
        this.abstractInterpretation = abstractInterpretation;
        if (abstractInterpretation != null) {
            abstractInterpretation.setRelatedObject(this);
        }
    }

    @Override
    public boolean hasAbstractInterpretation() {
        return this.abstractInterpretation != null;
    }

    @Override
    public boolean isNull() {
        return fields == null;
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
                        return Value.getNewStringValue(stringValue.getValue() + stringFromObject.getValue());
                    }
                    return Value.getNewStringValue();
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
        return copy(new IdentityHashMap<>());
    }

    /**
     * Deep copy of this object.
     * @param copiedObjects map of already copied objects to handle cyclic references.
     * @return the copied object.
     */
    @Override
    @NotNull
    public JavaObject copy(Map<JavaObject, JavaObject> copiedObjects) {
        if (fields == null) {
            return new JavaObject(null, this.abstractInterpretation, this.typeName);
        }
        if (copiedObjects.containsKey(this)) {
            return copiedObjects.get(this);
        }
        JavaObject newObject = new JavaObject(new Scope(), this.abstractInterpretation, this.typeName);
        copiedObjects.put(this, newObject);
        newObject.fields = new Scope(this.fields, copiedObjects);
        return newObject;
    }

    @Override
    public JavaObject merge(@NotNull IValue other) {
        return merge(other, Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    @Override
    public JavaObject merge(@NotNull IValue other, Set<JavaObject> visited) {
        // if (!(other instanceof JavaObject otherObj)) {
        // setToUnknown();
        // return this;
        // }
        JavaObject otherObj = (JavaObject) other;
        if (fields == null && otherObj.fields == null) {
            return this;
        }
        if (fields == null || otherObj.fields == null) {
            fields = new Scope();
            return this;
        }
        // Cycle detection
        if (visited.contains(this)) {
            return this;
        }
        visited.add(this);
        this.fields.merge(otherObj.fields, visited);
        return this;
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return new JavaObject(typeName);
    }

    @Override
    public void setToUnknown() {
        setToUnknown(Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    @Override
    public void setToUnknown(Set<IJavaObject> visited) {
        if (visited.contains(this)) {
            return;
        }
        visited.add(this);
        if (fields != null) {
            fields.setEverythingUnknown(visited);
        } else {
            fields = new Scope();
        }
    }

    @Override
    public void setInitialValue() {
        setInitialValue(Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    @Override
    public void setInitialValue(Set<IJavaObject> visited) {
        if (visited.contains(this)) {
            return;
        }
        visited.add(this);
        if (fields != null) {
            fields.setEverythingInitialValue(visited);
        }
        fields = null;
    }

}
