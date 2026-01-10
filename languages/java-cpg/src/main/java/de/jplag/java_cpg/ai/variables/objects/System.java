package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.string.StringValue;

/**
 * Representation of the static java.lang.System class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/System.html">Oracle Docs</a></a>
 */
public class System extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.lang";
    private static final java.lang.String NAME = "System";

    public System() {
        super();
    }

    @Pure
    @NotNull
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars, MethodDeclaration method) {
        switch (methodName) {
            case "lineSeparator" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new StringValue("\n");
            }
            case "exit" -> {
                throw new UnsupportedOperationException("System.exit() called");
            }
            case "currentTimeMillis" -> {
                assert paramVars == null || paramVars.isEmpty();
                return Value.valueFactory(Type.INT);
            }
            default -> throw new UnsupportedOperationException(methodName + " is not supported in " + PATH + "." + NAME);
        }
    }

    @Override
    public Value accessField(@NotNull java.lang.String fieldName) {
        switch (fieldName) {
            case "out", "err" -> {
                return new PrintStream();
            }
            case "in" -> {
                return new InputStream();
            }
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported in " + PATH + "." + NAME);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new System();
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof System;
        // Nothing to merge
    }

}
