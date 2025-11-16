package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final HashMap<VariableName, Variable> variables = new HashMap<>();

    /**
     * Copy constructor
     *
     * @param scope
     */
    public Scope(@NotNull Scope scope) {
        for (Map.Entry<VariableName, Variable> entry : scope.variables.entrySet()) {
            VariableName clonedKey = entry.getKey();
            Variable clonedValue = new Variable(entry.getValue());
            this.variables.put(clonedKey, clonedValue);
        }
    }

    public Scope() {
        //empty
    }

    /**
     * Overwrites or adds a variable in this scope.
     *
     * @param variable
     */
    public void addVariable(Variable variable) {
        variables.put(variable.getName(), variable);
    }

    /**
     * Gets a variable by its name or null if it does not exist in this scope.
     *
     * @param name
     * @return
     */
    public Variable getVariable(VariableName name) {
        return variables.get(name);
    }

    public void merge(Scope otherScope) {
        //assert: both scopes contain the same variables with potentially different values
        for (Map.Entry<VariableName, Variable> entry : otherScope.variables.entrySet()) {
            assert this.variables.containsKey(entry.getKey());
            this.variables.get(entry.getKey()).merge(entry.getValue());
        }
    }
}
