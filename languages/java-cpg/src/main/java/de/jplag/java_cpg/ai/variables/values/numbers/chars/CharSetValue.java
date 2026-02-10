package de.jplag.java_cpg.ai.variables.values.numbers.chars;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.IValue;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.NumberSetValue;

/**
 * Char value representation that can hold a set of possible char values or be unknown.
 * @author ujiqk
 * @version 1.0
 */
public class CharSetValue extends NumberSetValue<Character> implements ICharValue {

    /**
     * an unknown char value.
     */
    public CharSetValue() {
        super(Type.CHAR);
    }

    /**
     * an exactly known char value.
     * @param character the known character
     */
    public CharSetValue(char character) {
        super(character, Type.CHAR);
    }

    /**
     * a char value that can be one of the given characters.
     * @param characters the possible characters
     */
    public CharSetValue(@NotNull Set<Character> characters) {
        super(characters, Type.CHAR);
    }

    /**
     * Copy constructor.
     */
    private CharSetValue(Set<Character> maybeContained, boolean information) {
        super(maybeContained, information, Type.CHAR);
    }

    @NotNull
    @Override
    public Value copy() {
        return new CharSetValue(this.maybeContained, this.information);
    }

    @Override
    public CharSetValue merge(@NotNull IValue other) {
        // if (other instanceof VoidValue) {
        // return new CharSetValue();
        // }
        CharSetValue otherCharValue = (CharSetValue) other;
        if (!otherCharValue.information) {
            return new CharSetValue();
        } else if (this.information) {
            Set<Character> newMaybeContainedCopy = new HashSet<>(Set.copyOf(this.maybeContained));
            newMaybeContainedCopy.addAll(otherCharValue.maybeContained);
            return new CharSetValue(newMaybeContainedCopy);
        }
        return new CharSetValue();
    }

    @NotNull
    @Override
    public IValue getInitialValue() {
        return new CharSetValue(DEFAULT_VALUE);
    }

    @Override
    public boolean getInformation() {
        return this.information && this.maybeContained.size() == 1;
    }

    @Override
    public double getValue() {
        assert this.information;
        return this.maybeContained.iterator().next();
    }

    @Override
    protected NumberSetValue<Character> createNewInstance(Set<Character> maybeContained) {
        return new CharSetValue(maybeContained);
    }

    @Override
    protected NumberSetValue<Character> createNewUnknownInstance() {
        return new CharSetValue();
    }

    @Override
    protected Character subtract(Character c1, Character c2) {
        return (char) (c1 - c2);
    }

    @Override
    protected Character add(Character c1, Character c2) {
        return (char) (c1 + c2);
    }

}
