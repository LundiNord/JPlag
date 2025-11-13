package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.TranslationContext;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.passes.EOGStarterPass;
import org.jetbrains.annotations.NotNull;

public class AiPass extends EOGStarterPass {

    public AiPass(@NotNull TranslationContext ctx) {
        super(ctx);
        System.out.println("Test");
    }

    @Override
    public void cleanup() {

        System.out.println("Test");
    }

    @Override
    public void accept(Node node) {

        System.out.println("Test");
    }
}
