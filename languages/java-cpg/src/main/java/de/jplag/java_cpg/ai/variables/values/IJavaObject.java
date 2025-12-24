package de.jplag.java_cpg.ai.variables.values;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Variable;

public interface IJavaObject extends IValue {

    IValue callMethod(@NotNull String methodName, List<IValue> paramVars);

    IValue accessField(@NotNull String fieldName);

    void changeField(@NotNull String fieldName, IValue value);

    void setField(@NotNull Variable field);

    void setAbstractInterpretation(@Nullable AbstractInterpretation abstractInterpretation);

}
