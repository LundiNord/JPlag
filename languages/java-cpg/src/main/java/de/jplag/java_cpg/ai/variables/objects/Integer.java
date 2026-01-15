package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.string.IStringValue;

/**
 * Representation of the static java.lang.Integer class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html">Oracle Docs</a>
 */
public class Integer extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "Integer";

    /**
     * Representation of the static java.lang.Integer class.
     */
    public Integer() {
        super();
    }

    /**
     * @return The variable name representing java.lang.Integer.
     */
    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars, MethodDeclaration method) {
        switch (methodName) {
            case "parseInt" -> {
                assert paramVars.size() == 1;
                IValue value = paramVars.getFirst();
                switch (value) {
                    case IStringValue str -> {
                        return str.callMethod("parseInt", paramVars, null);
                    }
                    case VoidValue ignored -> {
                        return VoidValue.valueFactory(Type.INT);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + value);
                }
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Integer();
    }

    @Override
    public void merge(@NotNull IValue other) {
        // Nothing to merge
        assert other instanceof Integer;
    }

}
