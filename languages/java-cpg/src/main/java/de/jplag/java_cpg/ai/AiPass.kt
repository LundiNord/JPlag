package de.jplag.java_cpg.ai

import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.graph.Component
import de.fraunhofer.aisec.cpg.graph.declarations.Declaration
import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration
import de.fraunhofer.aisec.cpg.graph.declarations.RecordDeclaration
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration
import de.fraunhofer.aisec.cpg.graph.records
import de.fraunhofer.aisec.cpg.passes.TranslationResultPass
import de.fraunhofer.aisec.cpg.passes.configuration.DependsOn
import de.jplag.java_cpg.passes.CpgTransformationPass
import de.jplag.java_cpg.transformation.matching.edges.CpgNthEdge
import de.jplag.java_cpg.transformation.matching.edges.Edges.RECORD_DECLARATION__METHODS
import de.jplag.java_cpg.transformation.matching.edges.Edges.TRANSLATION_UNIT__DECLARATIONS
import de.jplag.java_cpg.transformation.operations.RemoveOperation
import java.net.URI

/**
 * A CPG Pass performing Abstract Interpretation on the CPG Translation Result.
 */
@DependsOn(CpgTransformationPass::class)
class AiPass(ctx: TranslationContext) : TranslationResultPass(ctx) {
    //passes have to be in kotlin!

    val visitedLinesRecorder = VisitedLinesRecorder()
    val abstractInterpretation: AbstractInterpretation = AbstractInterpretation(visitedLinesRecorder)

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
        println("Abstract Interpretation finished.")
        //find dead methods and classes
        try {
            for (translationUnit in comp.translationUnits) {
                for (recordDeclaration in translationUnit.records) {
                    val fileName: URI = recordDeclaration.location?.artifactLocation?.uri ?: URI.create("unknown")
                    val startLine: Int = recordDeclaration.location?.region?.startLine ?: -1
                    val endLine: Int = recordDeclaration.location?.region?.endLine ?: -1
                    val completelyDead: Boolean =
                        visitedLinesRecorder.checkIfCompletelyDead(fileName, startLine, endLine)
                    if (completelyDead) {
                        println("Dead code (class) detected: ${recordDeclaration.name}")
                        val index = translationUnit.declarations.indexOf(recordDeclaration)
                        val edge =
                            CpgNthEdge<TranslationUnitDeclaration, Declaration>(TRANSLATION_UNIT__DECLARATIONS, index)
                        RemoveOperation.apply(translationUnit, recordDeclaration, edge, true)
                        continue
                    }
                    for (method in recordDeclaration.methods) {
                        val startLine: Int = method.location?.region?.startLine ?: -1
                        val endLine: Int = method.location?.region?.endLine ?: -1
                        val methodCompletelyDead: Boolean =
                            visitedLinesRecorder.checkIfCompletelyDead(fileName, startLine, endLine)
                        if (methodCompletelyDead) {
                            println("Dead code (method) detected: ${method.name} in class ${recordDeclaration.name}")
                            val index = recordDeclaration.methods.indexOf(method)
                            val edge =
                                CpgNthEdge<RecordDeclaration, MethodDeclaration>(RECORD_DECLARATION__METHODS, index)
                            RemoveOperation.apply(recordDeclaration, method, edge, true)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Error while detecting dead classes and methods", e)
        }
    }
}

/**
 * Enumeration of different representations for integers.
 */
enum class IntAiType {
    INTERVALS,
    DEFAULT,
    SET,
}

/**
 * Enumeration of different representations for floating-point numbers.
 */
enum class FloatAiType {
    SET,
    DEFAULT,
}

/**
 * Enumeration of different representations for strings.
 */
enum class StringAiType {
    CHAR_INCLUSION,
    REGEX,
    DEFAULT,
}

/**
 * Enumeration of different representations for characters.
 */
enum class CharAiType {
    SET,
    DEFAULT,
}

/**
 * Enumeration of different representations for arrays.
 */
enum class ArrayAiType {
    LENGTH,
    DEFAULT,
}
