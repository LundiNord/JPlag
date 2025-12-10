package de.jplag.java_cpg.ai

import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.graph.Component
import de.fraunhofer.aisec.cpg.passes.TranslationResultPass
import de.fraunhofer.aisec.cpg.passes.configuration.DependsOn
import de.jplag.java_cpg.passes.CpgTransformationPass

/**
 * Abstract Interpretation Pass
 */
@DependsOn(CpgTransformationPass::class)
class AiPass(ctx: TranslationContext) : TranslationResultPass(ctx) {
    //passes have to be in kotlin!

    var abstractInterpretation: AbstractInterpretation = AbstractInterpretation()

    /**
     * Empty cleanup method.
     */
    override fun cleanup() {
        // Nothing to do
    }

    override fun accept(p0: TranslationResult) {
        val comp: Component = p0.components.first()
        for (translationUnit in comp.translationUnits) {
            if (translationUnit.name.parent?.localName?.endsWith("Main") == true) {
                runCatching {
                    abstractInterpretation.runMain(translationUnit)
                }.onFailure { t ->
                    log.error("AI pass failed for translation unit: ${translationUnit.name}", t)
                    throw t
                }
            }
        }
        println("Test")
    }

}

enum class IntAiType {
    INTERVALS,
    DEFAULT,
    SET,
}

enum class FloatAiType {
    SET,
    DEFAULT,
}

enum class StringAiType {
    CHAR_INCLUSION,
    REGEX,
    DEFAULT,
}
