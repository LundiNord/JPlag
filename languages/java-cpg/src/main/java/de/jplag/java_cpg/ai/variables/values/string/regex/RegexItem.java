package de.jplag.java_cpg.ai.variables.values.string.regex;

import org.jetbrains.annotations.NotNull;

/**
 * Simplified regex syntax with only single or multiple characters.
 * @author ujiqk
 * @version 1.0
 */
public abstract class RegexItem {

    public static RegexItem merge(@NotNull RegexItem one, @NotNull RegexItem two) {
        return one.merge(two);
    }

    public abstract RegexItem merge(RegexItem other);

}
