package de.jplag.java_cpg.ai

import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.graph.Component
import de.fraunhofer.aisec.cpg.graph.Node
import de.fraunhofer.aisec.cpg.graph.declarations.NamespaceDeclaration
import de.fraunhofer.aisec.cpg.graph.records
import de.fraunhofer.aisec.cpg.passes.TranslationResultPass
import de.fraunhofer.aisec.cpg.passes.configuration.DependsOn
import de.fraunhofer.aisec.cpg.passes.configuration.ExecuteBefore
import de.jplag.java_cpg.passes.CpgTransformationPass
import de.jplag.java_cpg.passes.TokenizationPass
import java.net.URI
import java.util.*

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
                    if (checkIfCompletelyDead(recordDeclaration, visitedLinesRecorder) && removeDeadCode) {
                        println("Dead code (class) detected: ${recordDeclaration.name}")
                        // Try removing from Translation Unit directly
                        val tuIndex = translationUnit.declarations.indexOf(recordDeclaration)
                        if (tuIndex > 0) {
                            translationUnit.declarationEdges.removeAt(tuIndex - 1)
                        }
                        // Try removing from Namespaces
                        for (ns in translationUnit.declarations.filterIsInstance<NamespaceDeclaration>()) {
                            val nsIndex = ns.declarations.indexOf(recordDeclaration)
                            if (nsIndex > 0) {
                                ns.declarations.removeAt(nsIndex - 1)
                            }
                        }
                        continue
                    }
                    for (method in recordDeclaration.methods) {
                        if (checkIfCompletelyDead(method, visitedLinesRecorder) && removeDeadCode) {
                            println("Dead code (method) detected: ${method.name} in class ${recordDeclaration.name}")
                            val index = recordDeclaration.methods.indexOf(method)
                            recordDeclaration.methodEdges.removeAt(index - 1)
                        }
                    }
                    //inner classes
                    for (innerClass in recordDeclaration.records) {
                        if (checkIfCompletelyDead(innerClass, visitedLinesRecorder) && removeDeadCode) {
                            println("Dead code (class) detected: ${recordDeclaration.name}")
                            val tuIndex = recordDeclaration.declarations.indexOf(innerClass)
                            recordDeclaration.recordEdges.removeAt(tuIndex - 1)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Error while detecting dead classes and methods", e)
        }
        //Debug
        val visitedLines: Set<Int> = visitedLinesRecorder.visitedLines.values.firstOrNull() ?: emptySet()
        val sortedVisitedLines = TreeSet<Int>(visitedLines)

        println("Abstract Interpretation code removal finished.")
    }

    fun checkIfCompletelyDead(node: Node, visitedLinesRecorder: VisitedLinesRecorder): Boolean {
        val fileName: URI = node.location?.artifactLocation?.uri ?: URI.create("unknown")
        val startLine: Int = node.location?.region?.startLine ?: -1
        val endLine: Int = node.location?.region?.endLine ?: -1
        val completelyDead: Boolean = visitedLinesRecorder.checkIfCompletelyDead(fileName, startLine, endLine)
        if (completelyDead) {
            visitedLinesRecorder.recordDetectedDeadLines(fileName, startLine, endLine)
        }
        return completelyDead
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
