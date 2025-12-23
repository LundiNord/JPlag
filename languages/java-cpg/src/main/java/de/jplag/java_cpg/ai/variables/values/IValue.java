package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for all values.
 *
 * @author ujiqk
 * @version 1.0
 */
public interface IValue {

    @NotNull
    Type getType();

    Value binaryOperation(@NotNull String operator, @NotNull Value other);

    Value unaryOperation(@NotNull String operator);

    /**
     * Creates and returns a deep copy of this value.
     *
     * @return a deep copy of this value.
     */
    @NotNull
    Value copy();

    /**
     * Merges the information of another instance of the same value into this one.
     * Types should be the same.
     * For example, when a value has different content in different branches of an if statement.
     *
     * @param other other value.
     */
    void merge(@NotNull Value other);

    /**
     * Delete all information in this value.
     */
    void setToUnknown();

    /**
     * Resets all information about this value except its type.
     * The initial value depends on the specific value type.
     */
    void setInitialValue();

    JavaObject getParentObject();

    void setParentObject(@Nullable JavaObject parentObject);

    Pair<JavaArray, INumberValue> getArrayPosition();

    void setArrayPosition(JavaArray array, INumberValue index);

}
