package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;

public enum Type {
    INT,
    STRING,
    BOOLEAN,
    OBJECT,
    ARRAY,
    LIST,
    VOID;

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
            return ARRAY;   //in java pointer types are used only for arrays
        } else {
            throw new IllegalArgumentException("Unsupported CPG type: " + cpgType);
        }
    }

    public boolean checkEquals(de.fraunhofer.aisec.cpg.graph.types.Type cpgOther) {
        return switch (this) {
            case INT -> cpgOther.getClass() == de.fraunhofer.aisec.cpg.graph.types.IntegerType.class;
            case STRING -> cpgOther.getClass() == de.fraunhofer.aisec.cpg.graph.types.StringType.class;
            case BOOLEAN -> cpgOther.getClass() == de.fraunhofer.aisec.cpg.graph.types.BooleanType.class;
            case OBJECT -> cpgOther.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class;
            default -> false;
        };
    }

}
