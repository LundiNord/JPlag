package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.TranslationContext;
import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.passes.TranslationResultPass;
import org.jetbrains.annotations.NotNull;

public class Ai2Pass extends TranslationResultPass {

    public Ai2Pass(@NotNull TranslationContext ctx) {
        super(ctx);
        System.out.println("Test");
    }

    @Override
    public void cleanup() {
        System.out.println("Test");
    }

    @Override
    public void accept(TranslationResult translationResult) {
        System.out.println("Test");
    }
}
