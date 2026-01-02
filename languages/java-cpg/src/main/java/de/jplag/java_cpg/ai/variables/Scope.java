package de.jplag.java_cpg.ai.variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A scope containing variables.
 * @author ujiqk
 * @version 1.0
 */
public class Scope {

    private HashMap<VariableName, Variable> variables = new HashMap<>();

    /**
     * Copy constructor. Performs a deep copy of the variables.
     * @param scope the scope to copy.
     */
    public Scope(@NotNull Scope scope) {
        for (Map.Entry<VariableName, Variable> entry : scope.variables.entrySet()) {
            VariableName clonedKey = entry.getKey();
            Variable clonedValue = new Variable(entry.getValue());
            this.variables.put(clonedKey, clonedValue);
        }
    }

    /**
     * Default constructor.
     */
    public Scope() {
        // empty
    }

    /**
     * Overwrites or adds a variable in this scope.
     * @param variable the variable to add.
     */
    public void addVariable(Variable variable) {
        variables.put(variable.getName(), variable);
    }

    /**
     * Gets a variable by its name or null if it does not exist in this scope.
     * @param name the name of the variable.
     * @return the variable or null if it does not exist in this scope.
     */
    public Variable getVariable(VariableName name) {
        return variables.get(name);
    }

    /**
     * Merges the information of another instance of the same scope into this one. The same variables with potentially
     * different values must exist in both scopes.
     * @param otherScope the other scope to merge into this one.
     */
    public void merge(@NotNull Scope otherScope) {
        // assert: both scopes contain the same variables with potentially different values
        for (Map.Entry<VariableName, Variable> entry : otherScope.variables.entrySet()) {
            if (!(this.variables.containsKey(entry.getKey()))) {
                System.out.println("Debug");
            }
            assert this.variables.containsKey(entry.getKey());
            this.variables.get(entry.getKey()).merge(entry.getValue());
        }
        otherScope.variables = this.variables;
    }

    /**
     * Sets all variables to completely unknown.
     */
    public void setEverythingUnknown() {
        for (Variable variable : variables.values()) {
            variable.setToUnknown();
        }
    }

    /**
     * Sets all variables to their initial value. The initial value depends on the variable type.
     */
    public void setEverythingInitialValue() {
        for (Variable variable : variables.values()) {
            variable.setInitialValue();
        }
    }

    /**
     * Starts recording changes to all variables.
     */
    public void addChangeRecorder(@NotNull ChangeRecorder changeRecorder) {
        for (Map.Entry<VariableName, Variable> entry : variables.entrySet()) {
            entry.getValue().addChangeRecorder(changeRecorder);
        }
    }

    /**
     * Stops recording changes to all variables and returns the ChangeRecorder. If addChangeRecorder() was not called
     * before, null is returned.
     * @return the ChangeRecorder that was removed, or null if no recorders existed.
     */
    @Nullable
    public ChangeRecorder removeLastChangeRecorder() {
        List<ChangeRecorder> recorders = new ArrayList<>();
        for (Map.Entry<VariableName, Variable> entry : variables.entrySet()) {
            recorders.add(entry.getValue().removeLastChangeRecorder());
        }
        if (recorders.isEmpty()) {
            return null;
        }
        ChangeRecorder first = recorders.getFirst();
        assert recorders.stream().allMatch(r -> r == first);
        return first;
    }

}
