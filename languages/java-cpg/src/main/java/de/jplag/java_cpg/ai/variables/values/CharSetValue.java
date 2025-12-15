package de.jplag.java_cpg.ai.variables.values;

import de.jplag.java_cpg.ai.variables.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CharSetValue extends Value implements ICharValue {

    private Set<Character> maybeContained;
    private boolean information;

    public CharSetValue() {
        super(Type.CHAR);
        this.information = false;
    }

    public CharSetValue(char character) {
        super(Type.CHAR);
        this.information = true;
        this.maybeContained = Set.of(character);
    }

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
        this.maybeContained = Set.of('\u0000');
    }

}
