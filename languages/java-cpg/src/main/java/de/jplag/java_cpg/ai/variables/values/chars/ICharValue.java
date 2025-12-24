package de.jplag.java_cpg.ai.variables.values.chars;

/**
 * Interface for all java char value representations.
 * @author ujiqk
 * @version 1.0
 */
public interface ICharValue {

    /**
     * The initial default value for char values in java.
     */
    char DEFAULT_VALUE = '\u0000';

    /**
     * @return if exact information about the char value is known.
     */
    boolean getInformation();

    /**
     * @return the exact char value, if known.
     */
    char getValue();

}
