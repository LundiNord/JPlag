package de.jplag.java_cpg.ai.variables;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.NullValue;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * A variable is a named value.
 * @author ujiqk
 * @version 1.0
 */
public class Variable {

    private final VariableName name;
    private IValue value;
    private List<ChangeRecorder> changeRecorders = new ArrayList<>();

    public Variable(@NotNull VariableName name, @NotNull IValue value) {
        this.name = name;
        this.value = value;
    }

    public Variable(@NotNull String string, @NotNull IValue value) {
        this.name = new VariableName(string);
        this.value = value;
    }

    public Variable(@NotNull VariableName name, @NotNull Type type) {
        this.name = name;
        this.value = Value.valueFactory(type);
    }

    /**
     * Copy constructor.
     * @param variable the variable to deep copy.
     */
    public Variable(@NotNull Variable variable) {
        this.name = variable.name;
        this.value = variable.value.copy();
        this.changeRecorders = variable.changeRecorders;    // no deep copy
    }

    /**
     * @return The name of this variable in the program.
     */
    public VariableName getName() {
        return name;
    }

    /**
     * Triggers change recording.
     * @return The value of this variable.
     */
    public IValue getValue() {
        // trigger change recording because returned value could be modified outside.
        // ToDo: only trigger when actual changes happen
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
        return value;
    }

    /**
     * Set the value of this variable. Triggers change recording.
     * @param value the new value for this variable.
     */
    public void setValue(IValue value) {
        assert value != null;
        this.value = value;
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    /**
     * Merge content from another variable into this variable. The provided variable must have the same name as this
     * variable. The actual merge behavior is delegated to the underlying {@link Value} implementation.
     * @param value the variable whose content will be merged into this one; must have the same name.
     */
    public void merge(@NotNull Variable value) {
        assert this.changeRecorders.equals(value.changeRecorders);
        assert value.name.equals(this.name);
        this.value.merge(value.value);
    }

    /**
     * Delete all content information in this variable.
     */
    public void setToUnknown() {
        if (getValue() instanceof NullValue) {
            // replace null with unknown JavaObject
            setValue(Value.valueFactory(Type.OBJECT));
        } else {
            value.setToUnknown();
        }
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    /**
     * Reset all information in this variable expect type and name. Initial values depend on the type.
     */
    public void setInitialValue() {
        value.setInitialValue();
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    /**
     * @param changeRecorder the change recorder to add. It will be notified on value changes.
     */
    public void addChangeRecorder(ChangeRecorder changeRecorder) {
        this.changeRecorders.add(changeRecorder);
    }

    /**
     * @return the last added change recorder. It is removed from this variable.
     */
    public ChangeRecorder removeLastChangeRecorder() {
        assert !this.changeRecorders.isEmpty();
        return this.changeRecorders.removeLast();
    }

}
