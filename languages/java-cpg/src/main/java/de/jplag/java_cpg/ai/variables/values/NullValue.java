package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

public class NullValue extends Value {

    public NullValue() {
        super(Type.NULL);
    }

    @Override
    public Value copy() {
        return new NullValue();
    }

    @Override
    public void merge(@NotNull Value other) {
        //do nothing
    }

    @Override
    public void setToUnknown() {
        //do nothing
    }

    @Override
    public void setInitialValue() {
        //do nothing
    }

}
