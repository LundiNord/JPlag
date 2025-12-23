package de.jplag.java_cpg.ai.variables.values.string;

import de.jplag.java_cpg.ai.variables.values.IJavaObject;
import org.jetbrains.annotations.Nullable;

/**
 * Strings are objects with added functionality.
 *
 * @author ujiqk
 * @version 1.0
 */
public interface IStringValue extends IJavaObject {

    boolean getInformation();

    @Nullable
    String getValue();

}
