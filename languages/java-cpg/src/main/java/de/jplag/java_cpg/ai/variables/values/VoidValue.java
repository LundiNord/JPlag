package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

public class VoidValue extends Value {

    public VoidValue() {
        super(Type.VOID);
    }

    @Override
    public Value copy() {
        return new VoidValue();
    }

    @Override
    public void merge(@NotNull Value other) {
        //do nothing
    }

    @Override
    public void setToUnknown() {
        //do nothing
    }

}
