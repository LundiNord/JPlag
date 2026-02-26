package de.jplag.java_cpg.cpgUtil;

import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.Node;

/**
 * A special singleton {@link Node} to serve as an intermediary neighbor for {@link Node}s while they are moved around.
 */
public class DummyNeighbor extends Node {

    private static final DummyNeighbor INSTANCE = new DummyNeighbor();

    private DummyNeighbor() {
    }

    /**
     * Gets the singleton instance.
     * @return the {@link DummyNeighbor} node
     */
    public static DummyNeighbor getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other == INSTANCE;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
