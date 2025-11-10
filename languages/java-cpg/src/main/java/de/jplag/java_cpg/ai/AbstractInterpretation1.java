package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration;
import de.fraunhofer.aisec.cpg.helpers.SubgraphWalker;

import java.util.List;

public class AbstractInterpretation1 {

    public static TranslationResult translationResult(TranslationResult translationResult) {

        List<Component> componentList = translationResult.getComponents();
        List<TranslationUnitDeclaration> translationUnits = componentList.getFirst().getTranslationUnits();

        for (TranslationUnitDeclaration translationUnit : translationUnits) {


            SubgraphWalker.IterativeGraphWalker walker = new SubgraphWalker.IterativeGraphWalker();

            walker.iterate(translationUnit);

            //SubgraphWalker walker2 = SubgraphWalker().getEOGPathEdges();

            System.out.println(walker);
        }




        return translationResult;
    }

}
