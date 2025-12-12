package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

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
    public Value copy() {
        return new CharValue(this.value, this.information);
    }

    @Override
    public void merge(@NotNull Value other) {
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
        this.value = '\u0000';
    }

}
