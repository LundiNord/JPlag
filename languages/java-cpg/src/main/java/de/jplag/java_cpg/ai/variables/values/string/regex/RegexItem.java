package de.jplag.java_cpg.ai.variables.values.string.regex;

import org.jetbrains.annotations.NotNull;

public abstract class RegexItem {
    
    public static RegexItem merge(@NotNull RegexItem one, @NotNull RegexItem two) {
        return one.merge(two);
    }

    public abstract RegexItem merge(@NotNull RegexItem other);

}
