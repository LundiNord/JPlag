package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;

/**
 * Representation of the java.util.Scanner class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html">Oracle Docs</a>
 */
public class Scanner extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.util";
    private static final java.lang.String NAME = "Scanner";

    public Scanner() {
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
            case "nextLine", "next" -> {
                assert paramVars == null || paramVars.isEmpty();
                return Value.valueFactory(Type.STRING);
            }
            case "close" -> {
                assert paramVars == null || paramVars.isEmpty();
                return new VoidValue();
            }
            case "nextInt" -> {
                assert paramVars == null || paramVars.isEmpty();
                return Value.valueFactory(Type.INT);
            }
            case "hasNextInt", "hasNext" -> {
                assert paramVars == null || paramVars.isEmpty() || (paramVars.size() == 1 && paramVars.get(0).getType() == Type.STRING);
                return Value.valueFactory(Type.BOOLEAN);
            }

            default -> throw new UnsupportedOperationException(methodName + " is not supported in Scanner.");
        }
    }

    @Override
    public Value accessField(@NotNull java.lang.String fieldName) {
        switch (fieldName) {
            default -> throw new UnsupportedOperationException("Field " + fieldName + " is not supported in Scanner.");
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Scanner();
    }

    @Override
    public void merge(@NotNull Value other) {
        assert other instanceof Scanner;
        // Nothing to merge
    }

}
