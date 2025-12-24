package de.jplag.java_cpg.ai.variables.values;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.arrays.IJavaArray;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

import kotlin.Pair;

/**
 * Interface for all values.
 * @author ujiqk
 * @version 1.0
 */
public interface IValue {

    @NotNull
    Type getType();

    IValue binaryOperation(@NotNull String operator, @NotNull IValue other);

    IValue unaryOperation(@NotNull String operator);

    /**
     * Creates and returns a deep copy of this value.
     * @return a deep copy of this value.
     */
    @NotNull
    IValue copy();

    /**
     * Merges the information of another instance of the same value into this one. Types should be the same. For example,
     * when a value has different content in different branches of an if statement.
     * @param other other value.
     */
    void merge(@NotNull IValue other);

    /**
     * Delete all information in this value.
     */
    void setToUnknown();

    /**
     * Resets all information about this value except its type. The initial value depends on the specific value type.
     */
    void setInitialValue();

    IJavaObject getParentObject();

    void setParentObject(@Nullable IJavaObject parentObject);

    Pair<IJavaArray, INumberValue> getArrayPosition();

    void setArrayPosition(IJavaArray array, INumberValue index);

}
