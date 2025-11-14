package de.jplag.java_cpg.ai.variables;

import java.util.ArrayList;

public class VariableStore {

    private final ArrayList<Scope> scopes = new ArrayList<>();
    private int currentScopeIndex = 0;

    private VariableStore(ArrayList<Scope> scopes, int currentScopeIndex) {
        this.scopes.addAll(scopes);
        this.currentScopeIndex = currentScopeIndex;
    }

    /**
     * Copy constructor
     *
     * @param variableStore
     */
    public VariableStore(VariableStore variableStore) {
        this.currentScopeIndex = variableStore.currentScopeIndex;
        for (Scope p : variableStore.scopes) {
            this.scopes.add(new Scope(p));
        }
    }

    public VariableStore() {
        scopes.add(new Scope());
    }

    public void addVariable(Variable variable) {
        scopes.get(currentScopeIndex).addVariable(variable);
    }

    /**
     * Returns the variable with the given name or null if it does not exist in any scope.
     *
     * @param name
     * @return
     */
    public Variable getVariable(String name) {
        assert name != null;
        for (int i = currentScopeIndex; i >= 0; i--) {
            Variable variable = scopes.get(i).getVariable(name);
            if (variable != null) {
                return variable;
            }
        }
        return null;
    }

    public void newScope() {
        scopes.add(new Scope());
        currentScopeIndex++;
    }

    public void removeScope() {
        assert currentScopeIndex > 0;
        if (currentScopeIndex > 0) {
            scopes.remove(currentScopeIndex);
            currentScopeIndex--;
        }
    }

    public void merge(VariableStore other) {
        assert this.currentScopeIndex == other.currentScopeIndex;
        for (int i = 0; i <= currentScopeIndex; i++) {
            Scope thisScope = this.scopes.get(i);
            Scope otherScope = other.scopes.get(i);
            thisScope.merge(otherScope);
        }
    }

}
