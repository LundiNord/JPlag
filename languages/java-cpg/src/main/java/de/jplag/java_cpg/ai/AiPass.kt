package de.jplag.java_cpg.ai

import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.graph.Component
import de.fraunhofer.aisec.cpg.graph.declarations.*
import de.fraunhofer.aisec.cpg.graph.records
import de.fraunhofer.aisec.cpg.passes.TranslationResultPass
import de.fraunhofer.aisec.cpg.passes.configuration.DependsOn
import de.fraunhofer.aisec.cpg.passes.configuration.ExecuteBefore
import de.jplag.java_cpg.passes.CpgTransformationPass
import de.jplag.java_cpg.passes.TokenizationPass
import de.jplag.java_cpg.transformation.matching.edges.CpgNthEdge
import de.jplag.java_cpg.transformation.matching.edges.Edges.*
import de.jplag.java_cpg.transformation.operations.RemoveOperation
import java.net.URI

/**
 * A CPG Pass performing Abstract Interpretation on the CPG Translation Result.
 */
@DependsOn(CpgTransformationPass::class)
@ExecuteBefore(TokenizationPass::class)
class AiPass(ctx: TranslationContext) : TranslationResultPass(ctx) {
    //passes have to be in kotlin!

    /**
     * Empty cleanup method.
     */
    override fun cleanup() {
        // Nothing to do
    }

    override fun accept(p0: TranslationResult) {
        var visitedLinesRecorder = VisitedLinesRecorder()
        val abstractInterpretation = AbstractInterpretation(visitedLinesRecorder, removeDeadCode)
        val comp: Component = p0.components.first()
        for (translationUnit in comp.translationUnits) {
            if (translationUnit.name.parent?.localName?.endsWith("Main") == true || translationUnit.name.toString()
                    .endsWith("Main.java") || comp.translationUnits.size == 1
            ) {
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
                        visitedLinesRecorder.recordDetectedDeadLines(fileName, startLine, endLine)
                    }
                    if (completelyDead && removeDeadCode) {
                        println("Dead code (class) detected: ${recordDeclaration.name}")
                        // Try removing from TU directly
                        val tuIndex = translationUnit.declarations.indexOf(recordDeclaration)
                        if (tuIndex != -1) {
                            val edge =
                                CpgNthEdge<TranslationUnitDeclaration, Declaration>(
                                    TRANSLATION_UNIT__DECLARATIONS,
                                    tuIndex
                                )
                            RemoveOperation.apply(translationUnit, recordDeclaration, edge, true)
                        }
                        // Try removing from Namespaces
                        for (ns in translationUnit.declarations.filterIsInstance<NamespaceDeclaration>()) {
                            val nsIndex = ns.declarations.indexOf(recordDeclaration)
                            if (nsIndex != -1) {
                                val edge =
                                    CpgNthEdge<NamespaceDeclaration, Declaration>(
                                        NAMESPACE_DECLARATION__DECLARATIONS,
                                        nsIndex
                                    )
                                RemoveOperation.apply(ns, recordDeclaration, edge, true)
                            }
                        }
                        continue
                    }
                    for (method in recordDeclaration.methods) {
                        val startLine: Int = method.location?.region?.startLine ?: -1
                        val endLine: Int = method.location?.region?.endLine ?: -1
                        val methodCompletelyDead: Boolean =
                            visitedLinesRecorder.checkIfCompletelyDead(fileName, startLine, endLine)
                        if (methodCompletelyDead) {
                            visitedLinesRecorder.recordDetectedDeadLines(fileName, startLine, endLine)
                        }
                        if (methodCompletelyDead && removeDeadCode) {
                            println("Dead code (method) detected: ${method.name} in class ${recordDeclaration.name}")
                            val index = recordDeclaration.methods.indexOf(method)
                            if (index != -1) {
                                val edge =
                                    CpgNthEdge<RecordDeclaration, MethodDeclaration>(RECORD_DECLARATION__METHODS, index)
                                RemoveOperation.apply(recordDeclaration, method, edge, true)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Error while detecting dead classes and methods", e)
        }
    }

    companion object AiPassCompanion {
        var removeDeadCode: Boolean = true
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
