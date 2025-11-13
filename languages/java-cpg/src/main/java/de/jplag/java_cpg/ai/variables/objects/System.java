package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class System extends JavaObject {

    private String PATH = "java.lang";
    private String NAME = "System";

    public System() {
        super();
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "out" -> {
                assert paramVars.size() == 1;

                //ToDo
                return null;
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }


}
