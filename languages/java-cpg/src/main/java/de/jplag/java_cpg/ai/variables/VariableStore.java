package de.jplag.java_cpg.ai.variables;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VariableStore {

    private final ArrayList<Scope> scopes = new ArrayList<>();
    private int currentScopeIndex = 0;
    private VariableName thisObject;

    private VariableStore(ArrayList<Scope> scopes, int currentScopeIndex) {
        this.scopes.addAll(scopes);
        this.currentScopeIndex = currentScopeIndex;
    }

    /**
     * Copy constructor
     *
     * @param variableStore
     */
    public VariableStore(@NotNull VariableStore variableStore) {
        this.currentScopeIndex = variableStore.currentScopeIndex;
        for (Scope p : variableStore.scopes) {
            this.scopes.add(new Scope(p));
        }
        thisObject = variableStore.thisObject;
    }

    public VariableStore() {
        scopes.add(new Scope());
    }

    public void addVariable(Variable variable) {
        scopes.get(currentScopeIndex).addVariable(variable);
    }

    public void setThisName(VariableName thisName) {
        this.thisObject = thisName;
    }

    public JavaObject getThisObject() {
        if (thisObject == null) {
            return null;
        }
        Variable variable = getVariable(thisObject);
        if (variable == null) {
            return null;
        }
        Value value = variable.getValue();
        if (value instanceof JavaObject javaObject) {
            return javaObject;
        }
        return null;
    }

    /**
     * Returns the variable with the given name or null if it does not exist in any scope.
     *
     * @param name
     * @return
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

    public Variable getVariable(String name) {
        return getVariable(new VariableName(name));
    }

    public void newScope() {
        scopes.add(new Scope());
        currentScopeIndex++;
    }

    public void removeScope() {
        if (currentScopeIndex > 0) {
            scopes.remove(currentScopeIndex);
            currentScopeIndex--;
        }
        assert currentScopeIndex >= 0;
    }

    public void merge(@NotNull VariableStore other) {
        assert this.currentScopeIndex == other.currentScopeIndex;
        for (int i = 0; i <= currentScopeIndex; i++) {
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

}
