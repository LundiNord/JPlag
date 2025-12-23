package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

//ToDo: not currently used, needed for lambda functions etc.
public class FunctionValue extends Value {

    public FunctionValue() {
        super(Type.FUNCTION);
    }


    @NotNull
    @Override
    public Value copy() {
        return new FunctionValue();
    }

    @Override
    public void merge(@NotNull Value other) {
        //ToDo
    }

    @Override
    public void setToUnknown() {
        //ToDo
    }

    @Override
    public void setInitialValue() {
        //ToDo
    }

}
