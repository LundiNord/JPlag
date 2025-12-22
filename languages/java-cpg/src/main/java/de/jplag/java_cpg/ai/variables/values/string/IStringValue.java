package de.jplag.java_cpg.ai.variables.values.string;

import de.jplag.java_cpg.ai.variables.values.IJavaObject;
import org.jetbrains.annotations.Nullable;

public interface IStringValue extends IJavaObject {

    boolean getInformation();

    @Nullable
    String getValue();

}
