package de.jplag.java_cpg.ai.variables.objects;

import java.util.List;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.arrays.JavaArray;

/**
 * Representation of the static java.util.Arrays class.
 * @author ujiqk
 * @version 1.0
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html">Oracle Docs</a></a>
 */
public class Arrays extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.util";
    private static final java.lang.String NAME = "Arrays";

    public Arrays() {
        super();
    }

    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars, MethodDeclaration method) {
        switch (methodName) {
            case "toString" -> {
                assert paramVars.size() == 1;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("toString", List.of(), null);
            }
            case "fill" -> {        // void fill(int[] a, int val) or void fill(int[] a, int fromIndex, int toIndex, int val)
                assert paramVars.size() == 2 || paramVars.size() == 4;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("fill", paramVars.subList(1, paramVars.size()), null);
            }
            case "sort" -> {        // void sort(int[] a) or void sort(int[] a, int fromIndex, int toIndex)
                assert paramVars.size() == 1 || paramVars.size() == 3;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("sort", paramVars.subList(1, paramVars.size()), null);
            }
            case "copyOfRange" -> { // int[] copyOfRange(int[] original, int from, int to)
                assert paramVars.size() == 3;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("copyOfRange", paramVars.subList(1, paramVars.size()), null);
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new Arrays();
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof Arrays;
        // nothing to merge
    }

}
