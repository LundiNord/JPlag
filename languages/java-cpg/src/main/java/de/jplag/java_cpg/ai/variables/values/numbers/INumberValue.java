package de.jplag.java_cpg.ai.variables.values.numbers;

/**
 * Interface for number values.
 *
 * @author ujiqk
 * @version 1.0
 */
public interface INumberValue {

    /**
     * @return if exact information is available.
     */
    boolean getInformation();

    /**
     * @return the exact value. Only valid if getInformation() returns true.
     */
    double getValue();

}
