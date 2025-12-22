package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.VariableName;
import de.jplag.java_cpg.ai.variables.values.JavaArray;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Arrays extends JavaObject {

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
    public Value callMethod(@NotNull java.lang.String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "toString" -> {
                assert paramVars.size() == 1;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("toString", List.of());
            }
            case "fill" -> {        //void fill(int[] a, int val) or void fill(int[] a, int fromIndex, int toIndex, int val)
                assert paramVars.size() == 2 || paramVars.size() == 4;
                JavaArray array = (JavaArray) paramVars.getFirst();
                return array.callMethod("fill", paramVars.subList(1, paramVars.size()));
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
    public void merge(@NotNull Value other) {
        assert other instanceof Arrays;
    }

}
