package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IValue {

    @NotNull
    Type getType();

    Value binaryOperation(@NotNull String operator, @NotNull Value other);

    Value unaryOperation(@NotNull String operator);

    @NotNull
    Value copy();

    void merge(@NotNull Value other);

    void setToUnknown();

    void setInitialValue();

    JavaObject getParentObject();

    void setParentObject(@Nullable JavaObject parentObject);

    Pair<JavaArray, INumberValue> getArrayPosition();

    void setArrayPosition(JavaArray array, INumberValue index);

}
