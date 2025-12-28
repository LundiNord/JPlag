package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;

/**
 * Enumeration of supported variable types.
 * @author ujiqk
 * @version 1.0
 */
public enum Type {
    INT,
    FLOAT,
    STRING,
    BOOLEAN,
    OBJECT,
    ARRAY,
    LIST,
    NULL,
    FUNCTION,
    CHAR,
    UNKNOWN,    // ToDo: split void value to void and unknown??
    VOID;

    /**
     * @param cpgType CPG type to convert.
     * @return the corresponding Type enum.
     */
    public static Type fromCpgType(@NotNull de.fraunhofer.aisec.cpg.graph.types.Type cpgType) {
        if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IntegerType.class) {
            return INT;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.StringType.class) {
            return STRING;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.BooleanType.class) {
            return BOOLEAN;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class) {
            return OBJECT;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.PointerType.class) {
            return ARRAY;   // in java pointer types are used only for arrays
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.FloatingPointType.class) {
            return FLOAT;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IncompleteType.class
                && cpgType.getName().getLocalName().equals("void")) {
            return VOID;
        } else {
            throw new IllegalArgumentException("Unsupported CPG type: " + cpgType);
        }
    }

}
