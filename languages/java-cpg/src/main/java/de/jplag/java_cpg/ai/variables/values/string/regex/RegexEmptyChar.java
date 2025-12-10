package de.jplag.java_cpg.ai.variables.values.string.regex;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

//ToDo remove class?
public class RegexEmptyChar extends RegexItem {

    public RegexEmptyChar() {
        //empty
    }

    public RegexItem merge(@NotNull RegexItem other) {
        switch (other) {
            case RegexChars o -> {
                return o.merge(this);
            }
            case RegexChar o -> {
                return new RegexChars(Arrays.asList(o.getContent(), null));
            }
            case RegexEmptyChar ignored -> {
                return this;
            }
            default -> throw new IllegalStateException("Unexpected value: " + other);
        }
    }

}
