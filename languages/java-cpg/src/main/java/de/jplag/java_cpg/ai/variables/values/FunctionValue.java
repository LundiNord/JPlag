package de.jplag.java_cpg.ai.variables.values;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;

// ToDo: not currently used, needed for lambda functions etc.

/**
 * Represents a function. Is used in lambdas.
 * @author ujiqk
 * @version 1.0
 */
public class FunctionValue extends Value {

    /**
     * a FunctionValue with no information.
     */
    public FunctionValue() {
        super(Type.FUNCTION);
    }

    @NotNull
    @Override
    public Value copy() {
        return new FunctionValue();
    }

    @Override
    public FunctionValue merge(@NotNull IValue other) {
        // ToDo
        return null;
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return null;
    }

}
