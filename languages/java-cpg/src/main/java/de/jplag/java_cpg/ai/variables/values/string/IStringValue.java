package de.jplag.java_cpg.ai.variables.values.string;

import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IStringValue {

    Value callMethod(@NotNull String methodName, List<Value> paramVars);

}
