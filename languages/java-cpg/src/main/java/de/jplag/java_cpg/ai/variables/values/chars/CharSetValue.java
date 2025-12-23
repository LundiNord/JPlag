package de.jplag.java_cpg.ai.variables.values.chars;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Char value representation that can hold a set of possible char values or be unknown.
 * @author ujiqk
 * @version 1.0
 */
public class CharSetValue extends Value implements ICharValue {

    private Set<Character> maybeContained;
    private boolean information;

    /**
     * an unknown char value.
     */
    public CharSetValue() {
        super(Type.CHAR);
        this.information = false;
    }

    /**
     * a exactly known char value.
     */
    public CharSetValue(char character) {
        super(Type.CHAR);
        this.information = true;
        this.maybeContained = Set.of(character);
    }

    /**
     * a char value that can be one of the given characters.
     */
    public CharSetValue(@NotNull Set<Character> characters) {
        super(Type.CHAR);
        this.information = true;
        this.maybeContained = characters;
    }

    /**
     * Copy constructor.
     */
    private CharSetValue(Set<Character> maybeContained, boolean information) {
        super(Type.CHAR);
        this.maybeContained = maybeContained;
        this.information = information;
    }

    @NotNull
    @Override
    public Value copy() {
        return new CharSetValue(this.maybeContained, this.information);
    }

    @Override
    public void merge(@NotNull Value other) {
        CharSetValue otherCharValue = (CharSetValue) other;
        if (!otherCharValue.information) {
            this.information = false;
        } else if (this.information) {
            this.maybeContained.addAll(otherCharValue.maybeContained);
        }
    }

    @Override
    public void setToUnknown() {
        this.information = false;
    }

    @Override
    public void setInitialValue() {
        this.information = true;
        this.maybeContained = Set.of(DEFAULT_VALUE);
    }

    @Override
    public boolean getInformation() {
        return this.information && this.maybeContained.size() == 1;
    }

    @Override
    public char getValue() {
        assert this.information;
        return this.maybeContained.iterator().next();
    }

}
