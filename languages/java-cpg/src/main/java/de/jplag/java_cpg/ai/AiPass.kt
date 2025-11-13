package de.jplag.java_cpg.ai

import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration
import de.fraunhofer.aisec.cpg.passes.DynamicInvokeResolver
import de.fraunhofer.aisec.cpg.passes.ProgramDependenceGraphPass
import de.fraunhofer.aisec.cpg.passes.TranslationUnitPass
import de.fraunhofer.aisec.cpg.passes.configuration.DependsOn

/**
 *
 */
@DependsOn(DynamicInvokeResolver::class)
@DependsOn(ProgramDependenceGraphPass::class)
class AiPass(ctx: TranslationContext) : TranslationUnitPass(ctx) {
    //passes have to be in kotlin!

    var abstractInterpretation: AbstractInterpretation = AbstractInterpretation()

    override fun accept(translationUnitDeclaration: TranslationUnitDeclaration) {
        runCatching {
            abstractInterpretation.runMain(translationUnitDeclaration)
        }.onFailure { t ->
            log.error("AI pass failed for translation unit: ${translationUnitDeclaration.name}", t)
            throw t
        }
        println("Test")
    }

    override fun cleanup() {
        // Nothing to do
    }

}
