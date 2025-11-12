package de.jplag.java_cpg.ai.variables;

import java.util.ArrayList;

public class VariableStore {

    private final ArrayList<Scope> scopes = new ArrayList<>();
    private int currentScopeIndex = 0;

    public VariableStore() {
        scopes.add(new Scope());
    }

    public void addVariable(Variable variable) {
        scopes.get(currentScopeIndex).addVariable(variable);
    }

    /**
     * Returns the variable with the given name or null if it does not exist in any scope.
     * @param name
     * @return
     */
    public Variable getVariable(String name) {
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
        if (currentScopeIndex > 0) {
            scopes.remove(currentScopeIndex);
            currentScopeIndex--;
        }
    }


}
