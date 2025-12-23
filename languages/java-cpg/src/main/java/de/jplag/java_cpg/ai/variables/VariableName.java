package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a variable name, potentially with a path (e.g., for namespaced or class-scoped variables). Path is
 * currently not used in equals and hashCode methods.
 * @author ujiqk
 * @version 1.0
 */
public class VariableName {

    private final String localName;
    private final String path;

    public VariableName(@NotNull String name) {
        String[] names = name.split("\\.");
        if (names.length > 1) {
            this.path = String.join(".", java.util.Arrays.copyOfRange(names, 0, names.length - 1));
            this.localName = names[names.length - 1];
        } else {
            this.path = "";
            this.localName = name;
        }
    }

    public String getLocalName() {
        return localName;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        int result = localName.hashCode();
        // result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        VariableName that = (VariableName) o;
        // return localName.equals(that.localName) && path.equals(that.path);
        return localName.equals(that.localName);
    }

    @Override
    public String toString() {
        return "VariableName{" + "localName='" + localName + '\'' + ", path='" + path + '\'' + '}';
    }

}
