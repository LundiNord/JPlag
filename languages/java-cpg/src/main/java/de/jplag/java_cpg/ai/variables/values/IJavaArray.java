package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * Also needs to extend JavaObject because Java arrays are objects.
 * @author ujiqk
 */
public interface IJavaArray extends IJavaObject {

    Value arrayAccess(INumberValue index);

    void arrayAssign(INumberValue index, Value value);

}
