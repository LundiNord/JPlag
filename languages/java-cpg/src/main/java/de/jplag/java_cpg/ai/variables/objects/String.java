package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.StringValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class String extends JavaObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "String";

    public String() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "format" -> {
                assert !paramVars.isEmpty();
                return new StringValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public JavaObject copy() {
        return new String();
    }

}
