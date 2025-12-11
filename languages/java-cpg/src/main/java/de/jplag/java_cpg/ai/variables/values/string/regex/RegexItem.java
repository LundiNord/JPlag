package de.jplag.java_cpg.ai.variables.values.string.regex;

public abstract class RegexItem {

    public static RegexItem merge(RegexItem one, RegexItem two) {
        return one.merge(two);
    }

    public abstract RegexItem merge(RegexItem other);

}
