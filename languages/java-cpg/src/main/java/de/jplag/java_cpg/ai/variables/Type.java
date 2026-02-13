package de.jplag.java_cpg.ai.variables;

import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.ARRAY;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.BOOLEAN;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.FLOAT;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.INT;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.LIST;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.OBJECT;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.STRING;
import static de.jplag.java_cpg.ai.variables.Type.TypeEnum.VOID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.types.ObjectType;

/**
 * Enumeration of supported variable types.
 * @author ujiqk
 * @version 1.0
 */
public class Type {

    /**
     * Enumeration of supported variable types.
     * @author ujiqk
     * @version 1.0
     */
    public enum TypeEnum {
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
        UNKNOWN,
        VOID;
    }

    private final @NotNull TypeEnum typeEnum;
    private final @Nullable String typeName;    // only used for the OBJECT type to save the class name
    private final @Nullable Type innerType;

    /**
     * Constructor for non-OBJECT, non-ARRAY, non-LIST types.
     * @param typeEnum the type enum of this type.
     */
    public Type(@NotNull TypeEnum typeEnum) {
        this(typeEnum, null, null);
        // assert typeEnum != OBJECT && typeEnum != ARRAY && typeEnum != LIST;
        assert typeEnum != ARRAY && typeEnum != LIST;
    }

    /**
     * Constructor for Array/List Types.
     * @param typeEnum the type enum of this type.
     * @param innerType the inner type of this type; only valid for the ARRAY and LIST types, otherwise null.
     */
    public Type(@NotNull TypeEnum typeEnum, @Nullable Type innerType) {
        this(typeEnum, null, innerType);
        assert typeEnum == ARRAY || typeEnum == LIST;
    }

    /**
     * Constructor for Object Types.
     * @param typeEnum the type enum of this type.
     * @param typeName the type name of this type; only valid for the OBJECT type, otherwise null.
     */
    public Type(@NotNull TypeEnum typeEnum, @Nullable String typeName) {
        this(typeEnum, typeName, null);
        assert typeEnum == OBJECT;
    }

    /**
     * Constructor for Types.
     * @param typeEnum the type enum of this type.
     * @param typeName the type name of this type; only valid for the OBJECT type, otherwise null.
     * @param innerType the inner type of this type; only valid for the ARRAY and LIST types, otherwise null.
     */
    public Type(@NotNull TypeEnum typeEnum, @Nullable String typeName, @Nullable Type innerType) {
        this.typeEnum = typeEnum;
        this.typeName = typeName;
        this.innerType = innerType;
        assert typeEnum == OBJECT || typeName == null;
        assert (typeEnum == ARRAY || typeEnum == LIST) || innerType == null;
    }

    /**
     * @return the type name of this type; only valid for the OBJECT type, otherwise null.
     */
    public String getTypeName() {
        assert this.typeEnum == OBJECT;
        assert typeName != null;
        return typeName;
    }

    /**
     * @return the inner type of this type; only valid for the ARRAY and LIST types, otherwise null.
     */
    public Type getInnerType() {
        assert this.typeEnum == ARRAY || this.typeEnum == LIST;
        assert innerType != null;
        return innerType;
    }

    /**
     * @return the type enum of this type.
     */
    public @NotNull TypeEnum getTypeEnum() {
        return typeEnum;
    }

    /**
     * @param cpgType CPG type to convert.
     * @return the corresponding Type enum.
     * @throws IllegalArgumentException if the CPG type is not supported.
     */
    public static Type fromCpgType(@NotNull de.fraunhofer.aisec.cpg.graph.types.Type cpgType) {
        if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IntegerType.class) {
            return new Type(INT);
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.StringType.class) {
            return new Type(STRING);
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.BooleanType.class) {
            return new Type(BOOLEAN);
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class && cpgType.getName().toString().contains("[]")) {
            return new Type(ARRAY);     // ToDo: handle inner type
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ObjectType.class) {
            String name = cpgType.getName().toString();
            name = name.split("<")[0]; // remove generics
            switch (name) {
                case "java.util.ArrayList", "java.util.List", "java.util.Vector", "java.util.LinkedList", "java.util.PriorityQueue" -> {
                    ObjectType objectType = (ObjectType) cpgType;
                    Type innerCpgType;
                    if (objectType.getGenerics().isEmpty()) {
                        innerCpgType = new Type(VOID);
                    } else {
                        assert objectType.getGenerics().size() == 1 : "Expected exactly one generic type for List, but got "
                                + objectType.getGenerics().size();
                        de.fraunhofer.aisec.cpg.graph.types.Type innerType = objectType.getGenerics().getFirst();
                        innerCpgType = de.jplag.java_cpg.ai.variables.Type.fromCpgType(innerType);
                    }
                    return new Type(LIST, innerCpgType);
                }
                default -> {
                    return new Type(OBJECT, name);   // we lose generics here
                }
            }
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.PointerType.class) {
            Type elementType = fromCpgType(((de.fraunhofer.aisec.cpg.graph.types.PointerType) cpgType).getElementType());
            return new Type(ARRAY, elementType);   // in java pointer types are used only for arrays
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.FloatingPointType.class) {
            return new Type(FLOAT);
        } else if ((cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.IncompleteType.class && cpgType.getName().getLocalName().equals("void"))
                || cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.UnknownType.class) {
            return new Type(VOID);
        } else if (cpgType.getClass() == de.fraunhofer.aisec.cpg.graph.types.ParameterizedType.class) {
            return new Type(VOID);
        } else {
            throw new IllegalArgumentException("Unsupported CPG type: " + cpgType);
        }
    }

}
