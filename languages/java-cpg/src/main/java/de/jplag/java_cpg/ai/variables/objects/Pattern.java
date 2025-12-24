package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;

/**
 * Representation of the java.util.regex.Pattern class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html">Oracle Docs</a></a>
 */
public class Pattern extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.util.regex";
    private static final java.lang.String NAME = "Pattern";

    public Pattern() {
        super();
    }

    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars) {
        switch (methodName) {
            case "matches" -> {
                assert paramVars.size() == 2;
                return new BooleanValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Pattern();
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof Pattern;
        // Nothing to merge
    }

}
