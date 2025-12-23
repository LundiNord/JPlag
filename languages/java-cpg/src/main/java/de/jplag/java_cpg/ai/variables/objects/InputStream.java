package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Representation of the java.io.InputStream class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html">Oracle Docs</a></a>
 */
public class InputStream extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.io";
    private static final java.lang.String NAME = "InputStream";

    public InputStream() {
        super();
    }

    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new InputStream();
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof InputStream;
        // nothing to merge
    }

}
