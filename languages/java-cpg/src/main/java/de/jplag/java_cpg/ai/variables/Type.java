package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Enumeration of supported variable types.
 * @author ujiqk
 * @version 1.0
 */
public enum Type {
    INT(null),
    FLOAT(null),
    STRING(null),
    BOOLEAN(null),
    OBJECT(null),
    ARRAY(null),
    LIST(null),
    NULL(null),
    FUNCTION(null),
    CHAR(null),
    UNKNOWN(null),
    VOID(null);

    private final @Nullable String typeName;    // only used for the OBJECT type to save the class name

    Type(@Nullable String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        assert this == OBJECT;
        assert typeName != null;
        return typeName;
    }

    /**
     * @param cpgType CPG type to convert.
     * @return the corresponding Type enum.
     * @throws IllegalArgumentException if the CPG type is not supported.
     */
    public static Type fromCpgType(@NotNull de.fraunhofer.aisec.cpg.graph.types.Type cpgType) {
        if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IntegerType.class) {
            return INT;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.StringType.class) {
            return STRING;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.BooleanType.class) {
            return BOOLEAN;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class) {
            // FixMe: this is not correct, we need to save the class name for the object type
            return OBJECT;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.PointerType.class) {
            return ARRAY;   // in java pointer types are used only for arrays
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.FloatingPointType.class) {
            return FLOAT;
        } else if (cpgType.getName().getLocalName().equals("void")) {
            return VOID;
        } else if ((cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IncompleteType.class)
                || cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.UnknownType.class) {
            return UNKNOWN;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ParameterizedType.class) {
            return OBJECT;
        } else {
            throw new IllegalArgumentException("Unsupported CPG type: " + cpgType);
        }
    }

    @Override
    public String toString() {
        return "JPlag cpg ai built in type: " + this.name().toLowerCase();
    }

}
