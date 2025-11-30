package de.jplag.java_cpg.ai.variables;

import java.util.Set;

public class ChangeRecorder {

    public final Set<Variable> changedVariables = new java.util.HashSet<>();

    public ChangeRecorder() {
        //empty
    }

    public void recordChange(Variable variable) {
        changedVariables.add(variable);
    }

    public Set<Variable> getChangedVariables() {
        return changedVariables;
    }

}
