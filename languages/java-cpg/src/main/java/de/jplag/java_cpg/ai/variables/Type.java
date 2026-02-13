package de.jplag.java_cpg.ai.variables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.types.ObjectType;

/**
 * Enumeration of supported variable types.
 * @author ujiqk
 * @version 1.0
 */
public enum Type {
    INT("java.lang.Integer", null),
    FLOAT("java.lang.Float", null),
    STRING("java.lang.String", null),
    BOOLEAN("java.lang.Boolean", null),
    OBJECT(null, null),
    ARRAY(null, null),
    LIST(null, null),
    NULL(null, null),
    FUNCTION(null, null),
    CHAR(null, null),
    UNKNOWN(null, null),
    VOID(null, null);

    private @Nullable String typeName;    // only used for the OBJECT type to save the class name
    private @Nullable Type innerType;

    Type(@Nullable String typeName, @Nullable Type innerType) {
        this.typeName = typeName;
        this.innerType = innerType;
    }

    /**
     * @return the type name of this type; only valid for the OBJECT type, otherwise null.
     */
    public String getTypeName() {
        assert this == OBJECT;
        assert typeName != null;
        return typeName;
    }

    /**
     * @return the inner type of this type; only valid for the ARRAY and LIST types, otherwise null.
     */
    public Type getInnerType() {
        assert this == ARRAY || this == LIST;
        assert innerType != null;
        return innerType;
    }

    /**
     * @param typeName the type name to set; only valid for the OBJECT type.
     */
    public void setTypeName(String typeName) {
        assert this == OBJECT;
        this.typeName = typeName;
    }

    /**
     * @param innerType the type name to set; only valid for the OBJECT type.
     */
    public void setInnerType(Type innerType) {
        assert this == ARRAY || this == LIST;
        this.innerType = innerType;
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
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class && cpgType.getName().toString().contains("[]")) {
            return ARRAY;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class) {
            String name = cpgType.getName().toString();
            name = name.split("<")[0]; // remove generics
            switch (name) {
                case "java.util.ArrayList", "java.util.List", "java.util.Vector", "java.util.LinkedList", "java.util.PriorityQueue" -> {
                    ObjectType objectType = (ObjectType) cpgType;
                    Type innerCpgType;
                    if (objectType.getGenerics().isEmpty()) {
                        innerCpgType = Type.VOID;
                    } else {
                        assert objectType.getGenerics().size() == 1 : "Expected exactly one generic type for List, but got "
                                + objectType.getGenerics().size();
                        de.fraunhofer.aisec.cpg.graph.types.Type innerType = objectType.getGenerics().getFirst();
                        innerCpgType = de.jplag.java_cpg.ai.variables.Type.fromCpgType(innerType);
                    }
                    Type result = LIST;
                    result.setInnerType(innerCpgType);
                    return result;
                }
                default -> {
                    Type result = OBJECT;
                    result.setTypeName(name);       // we lose generics here
                    return result;
                }
            }
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.PointerType.class) {
            Type elementType = fromCpgType(((de.fraunhofer.aisec.cpg.graph.types.PointerType) cpgType).getElementType());
            Type result = ARRAY;
            result.setInnerType(elementType);
            return result;   // in java pointer types are used only for arrays
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.FloatingPointType.class) {
            return FLOAT;
        } else if ((cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IncompleteType.class && cpgType.getName().getLocalName().equals("void"))
                || cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.UnknownType.class) {
            return VOID;
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ParameterizedType.class) {
            return VOID;
        } else {
            throw new IllegalArgumentException("Unsupported CPG type: " + cpgType);
        }
    }

}
