package de.jplag.java_cpg.ai.variables;

public enum Type {
    INT,
    STRING,
    BOOLEAN,
    OBJECT,
    ARRAY,
    LIST;

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
