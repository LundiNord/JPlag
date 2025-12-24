package de.jplag.java_cpg.ai.variables.values.chars;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.BooleanValue;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Char value representation that can hold a single char value or be unknown.
 * @author ujiqk
 * @version 1.0
 */
public class CharValue extends Value implements ICharValue {

    private char value;
    private boolean information;

    public CharValue() {
        super(Type.CHAR);
        this.information = false;
    }

    public CharValue(char value) {
        super(Type.CHAR);
        this.information = true;
        this.value = value;
    }

    public CharValue(@NotNull Set<Character> values) {
        super(Type.CHAR);
        if (values.size() == 1) {
            this.information = true;
            this.value = values.iterator().next();
        } else {
            this.information = false;
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

    @Override
    public IValue binaryOperation(@NotNull String operator, @NotNull IValue other) {
        switch (operator) {
            case "==" -> {
                CharValue otherCharValue = (CharValue) other;
                if (this.information && otherCharValue.information) {
                    return new BooleanValue(this.value == otherCharValue.value);
                } else {
                    return new BooleanValue();
                }
            }
            case "!=" -> {
                CharValue otherCharValue = (CharValue) other;
                if (this.information && otherCharValue.information) {
                    return new BooleanValue(this.value != otherCharValue.value);
                } else {
                    return new BooleanValue();
                }
            }
            default -> throw new IllegalArgumentException("Unknown binary operator: " + operator + " for " + getType());
        }
    }

    @Override
    public IValue unaryOperation(@NotNull String operator) {
        switch (operator) {
            default -> throw new IllegalArgumentException("Unary operation " + operator + " not supported for " + getType());
        }
    }

    @NotNull
    @Override
    public Value copy() {
        return new CharValue(this.value, this.information);
    }

    @Override
    public void merge(@NotNull IValue other) {
        assert other instanceof CharValue;
        CharValue otherCharValue = (CharValue) other;
        if (!otherCharValue.information) {
            this.information = false;
        } else if (this.information) {
            if (this.value != otherCharValue.value) {
                this.information = false;
            }
        }
    }

    @Override
    public void setToUnknown() {
        this.information = false;
    }

    @Override
    public void setInitialValue() {
        this.information = true;
        this.value = DEFAULT_VALUE;
    }

    @Override
    public boolean getInformation() {
        return this.information;
    }

    @Override
    public char getValue() {
        assert getInformation();
        return this.value;
    }

}
