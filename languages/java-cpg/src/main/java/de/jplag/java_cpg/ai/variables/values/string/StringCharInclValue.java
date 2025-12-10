package de.jplag.java_cpg.ai.variables.values.string;

import de.jplag.java_cpg.ai.variables.Type;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.VoidValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StringCharInclValue extends JavaObject implements IStringValue {

    //String=null <--> certainContained=null
    @Nullable
    Set<Character> certainContained;
    Set<Character> maybeContained;

    /**
     * A string value with no information.
     */
    public StringCharInclValue() {
        super(Type.STRING);
        certainContained = new HashSet<>();
        maybeContained = allCharactersSet();
    }

    public StringCharInclValue(@Nullable String value) {
        super(Type.STRING);
        maybeContained = new HashSet<>();
        if (value == null) {
            certainContained = null;
            return;
        }
        certainContained = new HashSet<>();
        for (char c : value.toCharArray()) {
            certainContained.add(c);
        }
    }

    private StringCharInclValue(@Nullable Set<Character> certainContained, Set<Character> maybeContained) {
        super(Type.STRING);
        this.certainContained = certainContained;
        this.maybeContained = maybeContained;
    }

    @Override
    public Value callMethod(@NotNull String methodName, List<Value> paramVars) {
        switch (methodName) {
            case "length" -> {
                assert paramVars == null || paramVars.isEmpty();
                return Value.valueFactory(Type.INT);
            }
            case "parseInt" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof IStringValue;
                return Value.valueFactory(Type.INT);
            }
            case "parseDouble" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof IStringValue;
                return Value.valueFactory(Type.FLOAT);
            }
            case "startsWith" -> {
                assert paramVars.size() == 1;
                assert paramVars.getFirst() instanceof IStringValue;
                return Value.valueFactory(Type.BOOLEAN);
            }
            case "equals" -> {
                assert paramVars.size() == 1;
                StringCharInclValue other = (StringCharInclValue) paramVars.getFirst();
                if (!Objects.equals(this.certainContained, other.certainContained) && this.maybeContained.isEmpty() && other.maybeContained.isEmpty()) {
                    return Value.valueFactory(false);
                } else {
                    return Value.valueFactory(Type.BOOLEAN);
                }
            }
            case "toUpperCase" -> {
                Set<Character> newCertain = new HashSet<>();
                if (this.certainContained != null) {
                    for (Character c : certainContained) {
                        newCertain.add(Character.toUpperCase(c));
                    }
                }
                Set<Character> newMaybe = new HashSet<>();
                for (Character c : maybeContained) {
                    newMaybe.add(Character.toUpperCase(c));
                }
                return new StringCharInclValue(newCertain, newMaybe);
            }
            default -> throw new UnsupportedOperationException(methodName);
        }
    }

    @Override
    public Value accessField(String fieldName) {
        throw new UnsupportedOperationException("Access field not supported in StringValue");
    }

    @Override
    public Value binaryOperation(@NotNull String operator, @NotNull Value other) {
        if (other instanceof VoidValue) {
            return new StringCharInclValue();
        }
        if (operator.equals("+") && other instanceof INumberValue inumbervalue) {
            if (inumbervalue.getInformation()) {        //ToDo: what to do with intervals?
                Set<Character> newCertain = certainContained == null ? null : new HashSet<>(certainContained);
                assert newCertain != null;
                newCertain.addAll(doubleToCharSet(inumbervalue.getValue()));
                return new StringCharInclValue(newCertain, new HashSet<>(maybeContained));
            } else {
                return new StringCharInclValue();
            }
        } else if (operator.equals("+") && other instanceof StringCharInclValue stringValue) {
            assert !(this.certainContained == null || stringValue.certainContained == null);
            Set<Character> newCertain = new HashSet<>(this.certainContained);
            newCertain.addAll(stringValue.certainContained);
            Set<Character> newMaybe = new HashSet<>(this.maybeContained);
            newMaybe.addAll(stringValue.maybeContained);
            return new StringCharInclValue(newCertain, newMaybe);
        }
        throw new UnsupportedOperationException("Binary operation " + operator + " not supported between " + getType() + " and " + other.getType());
    }

    @Override
    public JavaObject copy() {
        return new StringCharInclValue(
                certainContained == null ? null : new HashSet<>(certainContained),
                new HashSet<>(maybeContained)
        );
    }

    @Override
    public void merge(@NotNull Value other) {
        if (other instanceof VoidValue) {
            this.certainContained = new HashSet<>();
            this.maybeContained = allCharactersSet();
            return;
        }
        assert other instanceof StringCharInclValue;
        StringCharInclValue otherString = (StringCharInclValue) other;
        if (this.certainContained == null || otherString.certainContained == null) {
            this.certainContained = null;
        } else {
            Set<Character> removed = new HashSet<>(this.certainContained);
            this.certainContained.retainAll(otherString.certainContained);
            removed.removeAll(this.certainContained);
            this.maybeContained.addAll(removed);
            Set<Character> otherCertainNotInThis = new HashSet<>(otherString.certainContained);
            otherCertainNotInThis.removeAll(this.certainContained);
            this.maybeContained.addAll(otherCertainNotInThis);
            this.maybeContained.addAll(otherString.maybeContained);
        }
    }

    @Override
    public void setToUnknown() {
        this.certainContained = new HashSet<>();
        this.maybeContained = allCharactersSet();
    }

    @Override
    public void setInitialValue() {
        this.certainContained = null;
        this.maybeContained = new HashSet<>();
    }

    @NotNull
    private Set<Character> allCharactersSet() {
        Set<Character> allChars = new HashSet<>();
        for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
            allChars.add(c);
        }
        return allChars;
    }

    @NotNull
    private Set<Character> doubleToCharSet(double value) {
        Set<Character> charSet = new HashSet<>();
        String str = Double.toString(value);
        for (char c : str.toCharArray()) {
            charSet.add(c);
        }
        return charSet;
    }

    public boolean getInformation() {
        return false;
    }

    public String getValue() {
        assert getInformation();
        return null;
    }

    /**
     * Only for testing purposes.
     */
    @Nullable
    @TestOnly
    public Set<Character> getCertainContained() {
        return this.certainContained;
    }

    /**
     * Only for testing purposes.
     */
    @TestOnly
    public Set<Character> getMaybeContained() {
        return this.maybeContained;
    }

}
