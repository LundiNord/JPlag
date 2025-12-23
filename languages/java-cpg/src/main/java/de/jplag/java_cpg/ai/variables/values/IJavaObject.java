package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Variable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IJavaObject extends IValue {

    Value callMethod(@NotNull String methodName, List<Value> paramVars);

    Value accessField(@NotNull String fieldName);

    void changeField(@NotNull String fieldName, Value value);

    void setField(@NotNull Variable field);

    void setAbstractInterpretation(@Nullable AbstractInterpretation abstractInterpretation);

}
