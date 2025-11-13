package de.jplag.java_cpg.ai.variables.objects;

import de.jplag.java_cpg.ai.variables.values.IntValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;

import java.util.List;

public class Math extends JavaObject {

    private String PATH = "java.lang";
    private String NAME = "Math";

    public Math() {
        super();
    }
    
    @Override
    public Value callMethod(String methodName, List<Value> paramVars) {
        assert methodName != null;
        switch (methodName) {
            case "abs" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof IntValue;
                return abs((IntValue) paramVars.getFirst());
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    private IntValue abs(IntValue x) {
        return x.abs();
    }

}
