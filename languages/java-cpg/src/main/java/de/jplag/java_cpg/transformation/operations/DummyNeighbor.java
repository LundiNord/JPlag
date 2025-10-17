package de.jplag.java_cpg.transformation.operations;

import java.util.*;

import de.fraunhofer.aisec.cpg.graph.edges.Edge;
import org.jetbrains.annotations.Nullable;

import de.fraunhofer.aisec.cpg.graph.Node;

/**
 * A special singleton {@link Node} to serve as an intermediary neighbor for {@link Node}s while they are moved around.
 */
public class DummyNeighbor extends Node {

    private static final DummyNeighbor INSTANCE = new DummyNeighbor();

    /**
     * Maps detached nodes to the edge that they were previously a source of.
     */
    private final Map<Node, List<Edge<Node>>> sourceMap;

    /**
     * Maps detached nodes to the edge that they were previously a target of.
     */
    private final Map<Node, List<Edge<Node>>> targetMap;

    private DummyNeighbor() {
        sourceMap = new HashMap<>();
        targetMap = new HashMap<>();
    }

    /**
     * Gets the singleton instance.
     * @return the {@link DummyNeighbor} node
     */
    public static DummyNeighbor getInstance() {
        return INSTANCE;
    }

    /**
     * Adds the given {@code edge} as a former incoming edge of {@code edge.end}.
     * @param edge the edge
     */
    public void saveOriginalTarget(Edge<Node> edge) {
        if (edge.getEnd().equals(this)) {
            return;
        }
        targetMap.computeIfAbsent(edge.getEnd(), n -> new ArrayList<>()).add(edge);
    }

    /**
     * Adds the given {@code edge} as a former outgoing edge of {@code edge.start}.
     * @param edge the edge
     */
    public void saveOriginalSource(Edge<Node> edge) {
        if (edge.getStart().equals(this)) {
            return;
        }
        sourceMap.computeIfAbsent(edge.getStart(), n -> new ArrayList<>()).add(edge);
    }

    /**
     * Gets the registered former outgoing edges of the given node.
     * @param origSource the node
     * @return the former outgoing edges
     */
    public List<Edge<Node>> getOriginalEdgeOfSource(Node origSource) {
        return List.copyOf(sourceMap.getOrDefault(origSource, Collections.emptyList()));
    }

    /**
     * Gets the registered former incoming edges of the given node.
     * @param origTarget the node
     * @return the former incoming edges
     */
    public List<Edge<Node>> getOriginalEdgeOfTarget(Node origTarget) {
        return List.copyOf(targetMap.getOrDefault(origTarget, Collections.emptyList()));
    }

    /**
     * Clears the registered former incoming edges of the given node.
     * @param target the node
     */
    public void clearOriginalEdgesOfTarget(Node target) {
        List<Edge<Node>> edges = targetMap.getOrDefault(target, List.of());
        if (!edges.isEmpty()) {
            edges.clear();
        }
    }

    /**
     * Clears the saved edges.
     */
    public void clear() {
        targetMap.clear();
        sourceMap.clear();
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
