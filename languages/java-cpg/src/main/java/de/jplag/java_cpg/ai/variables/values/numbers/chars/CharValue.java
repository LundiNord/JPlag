package de.jplag.java_cpg.ai.variables.values.numbers.chars;

import java.util.Set;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.BasicNumberValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;

/**
 * Char value representation that can hold a single char value or be unknown.
 * @author ujiqk
 * @version 1.0
 */
public class CharValue extends BasicNumberValue implements ICharValue {

    private final char value;
    private final boolean information;

    /**
     * an unknown char value.
     */
    public CharValue() {
        super(Type.CHAR);
        this.information = false;
        this.value = DEFAULT_VALUE;
    }

    /**
     * an exactly known char value.
     * @param value the known character
     */
    public CharValue(char value) {
        super(Type.CHAR);
        this.information = true;
        this.value = value;
    }

    /**
     * a char value that can be one of the given characters.
     * @param values the possible characters
     */
    public CharValue(@NotNull Set<Character> values) {
        super(Type.CHAR);
        if (values.size() == 1) {
            this.information = true;
            this.value = values.iterator().next();
        } else {
            this.information = false;
            this.value = DEFAULT_VALUE;
        }
    }

    /**
     * Copy constructor.
     */
    private CharValue(char value, boolean information) {
        super(Type.CHAR);
        this.information = information;
        this.value = value;
    }

    @NotNull
    @Override
    public Value copy() {
        return new CharValue(this.value, this.information);
    }

    @Override
    public CharValue merge(@NotNull IValue other) {
        if (!(other instanceof INumberValue otherNumberValue)) {
            throw new IllegalArgumentException("Cannot merge " + getType() + " with " + other.getType());
        }
        if (!otherNumberValue.getInformation() || (this.information && this.value != (char) otherNumberValue.getValue())) {
            return new CharValue();
        }
        return this;
    }

    @Override
    public boolean getInformation() {
        return this.information;
    }

    @Override
    public double getValue() {
        assert getInformation();
        return this.value;
    }

    @Override
    @Pure
    public @NotNull CharValue getInitialValue() {
        return new CharValue(DEFAULT_VALUE);
    }

    @Override
    protected BasicNumberValue getInstanceWithValue(double value) {
        return new CharValue((char) value);
    }

    @Override
    protected BasicNumberValue getUnknownInstance() {
        return new CharValue();
    }

}
