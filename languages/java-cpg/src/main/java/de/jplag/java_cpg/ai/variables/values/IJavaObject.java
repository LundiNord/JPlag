package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.AbstractInterpretation;
import de.jplag.java_cpg.ai.variables.Variable;

import java.util.List;

public interface IJavaObject extends IValue {

    Value callMethod(String methodName, List<Value> paramVars);

    Value accessField(String fieldName);

    void changeField(String fieldName, Value value);

    void setField(Variable field);

    void setAbstractInterpretation(AbstractInterpretation abstractInterpretation);

}
