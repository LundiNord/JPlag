package de.jplag.java_cpg.ai.variables.values;

import java.util.Set;
import java.util.TreeSet;

import kotlin.Pair;

public class Information<T> {
    // information container

    private boolean information;                // marker for no information
    private T singleValueInformation;           // represents exactly known value
    private Set<T> setValueInformation;         // represents a set of possible values
    private Pair<T, T> intervalInformation;     // represents an interval of possible values (for numbers)
    private TreeSet<T> setValueInterval;        // represents a set of intervals of possible values (for numbers)

}
