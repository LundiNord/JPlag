package de.jplag.java_cpg.cpgUtil;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.Block;
import de.fraunhofer.aisec.cpg.helpers.SubgraphWalker;

public final class TransformationUtil {

    private static final Logger logger = LoggerFactory.getLogger(TransformationUtil.class);
    private static final DummyNeighbor DUMMY = DummyNeighbor.getInstance();

    private TransformationUtil() {
        /* should not be instantiated */
    }

    /**
     * Gets the {@link SubgraphWalker.Border} of the given node's sub-AST that links to outer nodes via EOG edges.
     * @param astRoot the root of the sub-AST
     * @return the EOG {@link SubgraphWalker.Border} of the AST
     */
    @NotNull
    private static SubgraphWalker.Border getEogBorders(@NotNull Node astRoot) {
        SubgraphWalker.Border result;
        if (astRoot instanceof Block block && !block.getStatements().isEmpty() && block.getNextEOG().isEmpty() && block.getPrevEOG().isEmpty()) {
            result = new SubgraphWalker.Border();
            SubgraphWalker.Border firstStatementBorder = getEogBorders(block.get(0));
            result.setEntries(firstStatementBorder.getEntries());
            SubgraphWalker.Border lastStatementBorder = getEogBorders(block.get(block.getStatements().size() - 1));
            result.setExits(lastStatementBorder.getExits());
        } else {
            result = SubgraphWalker.INSTANCE.getEOGPathEdges(astRoot);
            if (result.getEntries().isEmpty()) {
                Node entry = astRoot;
                while (!entry.getPrevEOG().isEmpty())
                    entry = entry.getPrevEOG().getFirst();
                result.setEntries(List.of(entry));
            }
            if (result.getExits().isEmpty()) {
                Node exit = astRoot;
                while (!exit.getNextEOG().isEmpty())
                    exit = exit.getNextEOG().getFirst();
                result.setExits(List.of(exit));
            }
        }
        checkBorder(astRoot, result);
        return result;
    }

    private static void checkBorder(Node astRoot, SubgraphWalker.Border result) {
        if (result.getEntries().isEmpty()) {
            logger.debug("AST subtree of {} has no EOG entry", astRoot);
        } else if (result.getEntries().size() > 1) {
            logger.debug("AST subtree of {} has multiple EOG entries", astRoot);
        }
    }

    public static List<Node> disconnectFromPredecessor(@NotNull Node node) {
        Node entry = getEntry(node);

        // ToDo
        return List.of();
    }

    /**
     * Gets the first EOG entry {@link Node} of the given {@link Node}.
     * @param node the node
     * @return the EOG entry node
     */
    private static Node getEntry(Node node) {
        return getEogBorders(node).getEntries().getFirst();
    }

}
