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
import de.jplag.java_cpg.ai.variables.values.VoidValue;

public class HashMap extends JavaObject implements ISpecialObject {

    private static final java.lang.String PATH = "java.util";
    private static final java.lang.String NAME = "HashMap";

    @NotNull
    @Pure
    public static VariableName getName() {
        return new VariableName(PATH + "." + NAME);
    }

    @Override
    public IValue callMethod(@NotNull java.lang.String methodName, List<IValue> paramVars, MethodDeclaration method) {
        // ToDo: not yet implemented
        switch (methodName) {
            case "put" -> {
                assert paramVars.size() == 2;
                return new VoidValue();
            }
            case "get" -> {
                assert paramVars.size() == 1;
                return new VoidValue();
            }
            case "containsKey" -> {
                assert paramVars.size() == 1;
                return new VoidValue();
            }
            case "size" -> {
                assert paramVars == null || paramVars.size() == 0;
                return Value.valueFactory(Type.INT);
            }
            case "remove" -> {
                assert paramVars.size() == 1;
                return new VoidValue();
            }
            case "putAll" -> {
                assert paramVars.size() == 1;
                return new VoidValue();
            }
            case "clear" -> {
                assert paramVars == null || paramVars.size() == 0;
                return new VoidValue();
            }
            case "keySet" -> {
                assert paramVars == null || paramVars.size() == 0;
                return new VoidValue();
            }
            case "clone" -> {
                assert paramVars == null || paramVars.size() == 0;
                return new HashMap();
            }
            case "entrySet" -> {
                assert paramVars == null || paramVars.size() == 0;
                return new VoidValue();
            }
            case "getOrDefault" -> {
                assert paramVars.size() == 2;
                return new VoidValue();
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @NotNull
    @Override
    public JavaObject copy() {
        return new HashMap();
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof HashMap;
        // Nothing to merge yet
    }

}
