package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Representation of the java.io.PrintStream class.
 *
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html">Oracle Docs</a></a>
 */
public class PrintStream extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.io";
    private static final java.lang.String NAME = "PrintStream";

    public PrintStream() {
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
            case "println", "print", "printf" -> {
                //do nothing
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new PrintStream();
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof PrintStream;
        // Nothing to merge
    }

}
