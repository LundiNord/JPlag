package de.jplag.java_cpg.ai.variables.values.arrays;

import de.jplag.java_cpg.ai.variables.values.IJavaObject;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * Also needs to extend JavaObject because Java arrays are objects.
 * @author ujiqk
 */
public interface IJavaArray extends IJavaObject {

    IValue arrayAccess(INumberValue index);

    void arrayAssign(INumberValue index, IValue value);

}
