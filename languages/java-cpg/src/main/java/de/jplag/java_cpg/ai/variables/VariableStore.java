package de.jplag.java_cpg.ai.variables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;

/**
 * Stores variables in different scopes.
 * @author ujiqk
 * @version 1.0
 */
public class VariableStore {

    private final ArrayList<Scope> scopes = new ArrayList<>();
    private int currentScopeIndex = 0;
    private VariableName thisObject;

    /**
     * Copy constructor. Performs deep copy down the values.
     * @param variableStore the variable store to copy.
     */
    public VariableStore(@NotNull VariableStore variableStore) {
        this.currentScopeIndex = variableStore.currentScopeIndex;
        for (Scope p : variableStore.scopes) {
            this.scopes.add(new Scope(p));
        }
        thisObject = variableStore.thisObject;
    }

    /**
     * Default constructor. Initializes with one {@link Scope}.
     */
    public VariableStore() {
        scopes.add(new Scope());
    }

    /**
     * @param variable the variable to add to the current scope.
     */
    public void addVariable(Variable variable) {
        scopes.get(currentScopeIndex).addVariable(variable);
    }

    /**
     * @param thisName the name of the {@link JavaObject} this variable store is associated with.
     */
    public void setThisName(VariableName thisName) {
        this.thisObject = thisName;
    }

    /**
     * @return the {@link JavaObject} this variable store is associated with, or null if not set.
     */
    public JavaObject getThisObject() {
        if (thisObject == null) {
            return null;
        }
        Variable variable = getVariable(thisObject);
        if (variable == null) {
            return null;
        }
        IValue value = variable.getValue();
        if (value instanceof JavaObject javaObject) {
            return javaObject;
        }
        return null;
    }

    /**
     * @param name the name of the variable to retrieve.
     * @return the variable with the given name or null if it does not exist.
     */
    public Variable getVariable(VariableName name) {
        assert name != null;
        for (int i = currentScopeIndex; i >= 0; i--) {
            Variable variable = scopes.get(i).getVariable(name);
            if (variable != null) {
                return variable;
            }
        }
        return null;
    }

    /**
     * @param name the name of the variable to retrieve.
     * @return the variable with the given name or null if it does not exist.
     */
    public Variable getVariable(String name) {
        return getVariable(new VariableName(name));
    }

    /**
     * Creates a new scope on top of the current one.
     */
    public void newScope() {
        scopes.add(new Scope());
        currentScopeIndex++;
    }

    /**
     * Removes the current scope.
     */
    public void removeScope() {
        if (currentScopeIndex > 0) {
            scopes.remove(currentScopeIndex);
            currentScopeIndex--;
        }
        assert currentScopeIndex >= 0;
    }

    /**
     * Merges the information from another variable store into this one. Used for merging variable states after control-flow
     * joins. Merges scopes up to the minimum scope depth of both stores.
     * @param other the other variable store to merge.
     */
    public void merge(@NotNull VariableStore other) {
        // In complex control-flow, the scope depth can differ.
        int targetIndex = Math.min(this.currentScopeIndex, other.currentScopeIndex);
        if (this.currentScopeIndex > targetIndex) {
            // remove scopes from the end until we match the target index
            for (int i = this.currentScopeIndex; i > targetIndex; i--) {
                // scopes are always appended at the end; safe to remove by index
                scopes.remove(i);
            }
            this.currentScopeIndex = targetIndex;
        }
        for (int i = 0; i <= targetIndex; i++) {
            Scope thisScope = this.scopes.get(i);
            Scope otherScope = other.scopes.get(i);
            thisScope.merge(otherScope);
        }
    }

    /**
     * Sets all variables in all scopes to completely unknown.
     */
    public void setEverythingUnknown() {
        for (Scope scope : scopes) {
            scope.setEverythingUnknown();
        }
    }

    /**
     * Starts recording changes to variables in all scopes.
     */
    public void recordChanges() {
        ChangeRecorder changeRecorder = new ChangeRecorder();
        for (Scope scope : scopes) {
            scope.addChangeRecorder(changeRecorder);
        }
    }

    /**
     * Stops recording changes to variables in all scopes and returns the set of changed variables. Method assumes that
     * recordChanges() was called before.
     * @return the set of changed variables.
     */
    @NotNull
    public Set<Variable> stopRecordingChanges() {
        List<ChangeRecorder> recorders = new ArrayList<>();
        for (Scope scope : scopes) {
            recorders.add(scope.removeLastChangeRecorder());
        }
        ChangeRecorder first = recorders.stream().filter(Objects::nonNull).findFirst().orElse(null);
        assert recorders.stream().allMatch(r -> (r == first) || (r == null));
        if (first == null) {
            return new HashSet<>();
        }
        return first.getChangedVariables();
    }

}
