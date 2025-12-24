package de.jplag.java_cpg.ai.variables.values.string;

import org.jetbrains.annotations.Nullable;

import de.jplag.java_cpg.ai.variables.values.IJavaObject;

/**
 * Strings are objects with added functionality.
 * @author ujiqk
 * @version 1.0
 */
public interface IStringValue extends IJavaObject {

    boolean getInformation();

    @Nullable
    String getValue();

}
