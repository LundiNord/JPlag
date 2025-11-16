package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InputStream extends JavaObject {

    private static final String PATH = "java.io";
    private static final String NAME = "InputStream";

    public InputStream() {
        super();
    }

    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        switch (methodName) {
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public JavaObject copy() {
        return new InputStream();
    }

}
