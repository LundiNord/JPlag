package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A variable is a named value.
 *
 * @author ujiqk
 * @version 1.0
 */
public class Variable {

    private final VariableName name;
    private Value value;
    private List<ChangeRecorder> changeRecorders = new ArrayList<>();

    public Variable(VariableName name, Value value) {
        assert value != null;
        assert name != null;
        this.name = name;
        this.value = value;
    }

    public Variable(String string, Value value) {
        assert string != null;
        assert value != null;
        this.name = new VariableName(string);
        this.value = value;
    }

    public Variable(@NotNull VariableName name, @NotNull Type type) {
        this.name = name;
        this.value = Value.valueFactory(type);
    }

    /**
     * Copy constructor.
     *
     * @param variable the variable to deep copy.
     */
    public Variable(@NotNull Variable variable) {
        this.name = variable.name;
        this.value = variable.value.copy();
        this.changeRecorders = variable.changeRecorders;    //no deep copy
    }

    public VariableName getName() {
        return name;
    }

    public Value getValue() {
        for (ChangeRecorder changeRecorder : changeRecorders) { //ToDo: only when changed after?
            changeRecorder.recordChange(this);
        }
        return value;
    }

    public void setValue(Value value) {
        assert value != null;
        this.value = value;
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    /**
     * Merge content from another variable into this variable.
     * The provided variable must have the same name as this variable.
     * The actual merge behavior is delegated to the underlying {@link Value} implementation.
     *
     * @param value the variable whose content will be merged into this one; must have the same name.
     * @throws AssertionError if the names differ (when assertions are enabled).
     */
    public void merge(@NotNull Variable value) {
        assert this.changeRecorders == value.changeRecorders;
        assert value.name.equals(this.name);
        this.value.merge(value.value);
    }

    /**
     * Delete all content information in this variable.
     */
    public void setToUnknown() {
        value.setToUnknown();
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    /**
     * Reset all information in this variable expect type and name.
     * Initial values depend on the type.
     */
    public void setInitialValue() {
        value.setInitialValue();
        for (ChangeRecorder changeRecorder : changeRecorders) {
            changeRecorder.recordChange(this);
        }
    }

    public void addChangeRecorder(ChangeRecorder changeRecorder) {
        this.changeRecorders.add(changeRecorder);
    }

    public ChangeRecorder removeLastChangeRecorder() {
        assert !this.changeRecorders.isEmpty();
        return this.changeRecorders.removeLast();
    }

}
