package de.jplag.java_cpg.ai.variables;

import java.util.HashMap;

class Scope {

    private final HashMap<String, Variable> variables;

    protected Scope() {
        variables = new HashMap<>();
    }

    /**
     * Overwrites or adds a variable in this scope.
     * @param variable
     */
    public void addVariable(Variable variable) {
        variables.put(variable.getName(), variable);
    }

    /**
     * Gets a variable by its name or null if it does not exist in this scope.
     * @param name
     * @return
     */
    public Variable getVariable(String name) {
        return variables.get(name);
    }

}
