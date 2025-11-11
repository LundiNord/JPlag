package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.graph.declarations.ConstructorDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.Declaration;
import de.fraunhofer.aisec.cpg.graph.declarations.FieldDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.IncludeDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.MethodDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.NamespaceDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.RecordDeclaration;
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration;
import de.fraunhofer.aisec.cpg.graph.edge.Dataflow;
import de.fraunhofer.aisec.cpg.graph.edge.PropertyEdge;
import de.fraunhofer.aisec.cpg.graph.statements.Statement;
import de.fraunhofer.aisec.cpg.graph.statements.expressions.Expression;
import de.fraunhofer.aisec.cpg.helpers.SubgraphWalker;

import java.util.List;
import java.util.Set;

public class AbstractInterpretation1 {

    public TranslationResult translationResult(TranslationResult translationResult) {
        List<Component> componentList = translationResult.getComponents();
        List<TranslationUnitDeclaration> translationUnits = componentList.getFirst().getTranslationUnits();

        translationUnits.stream().filter(x -> x.getName().toString().contains("Main.java")).forEach(this::graphWalker);

//        for (TranslationUnitDeclaration translationUnit : translationUnits) {   //replace with stream
//            if (!translationUnit.getName().toString().contains("Main.java")) {
//                continue;
//            }
//            graphWalker(translationUnit);
//            List<Node> eog = translationUnit.getEogStarters();                  //
//            List<Declaration> dec = translationUnit.getDeclarations();          //all declarations (also from imports)
//            List<IncludeDeclaration> incl = translationUnit.getIncludes();      //imports
//            List<NamespaceDeclaration> name = translationUnit.getNamespaces();  //edu.kit.informatik
//            List<Statement> stat = translationUnit.getStatements();             //0
//            System.out.println("Test");
//        }
        return translationResult;
    }

    void graphWalker(Node node) {

        switch (node) {
            case TranslationUnitDeclaration tud -> {
                List<Declaration> declarations = tud.getDeclarations();
//                Set<Node> dfg = tud.getNextDFG();
//                List<Dataflow> dfgE = tud.getNextDFGEdges();
//                List<Node> eog = tud.getNextEOG();
//                List<PropertyEdge<Node>> eogE = tud.getNextEOGEdges();
                for (Declaration declaration : declarations) {
                    if (declaration instanceof NamespaceDeclaration) {
                        graphWalker(declaration);
                    }
                    //ignore include declaration for now
                }
                System.out.println("Test");
            }
            case NamespaceDeclaration nd -> {
                for (Declaration declaration : nd.getDeclarations()) {
                    graphWalker(declaration);
                }
                System.out.println("Test");
            }
            case RecordDeclaration rd -> {
                //List<Declaration> declarations = rd.getDeclarations();
                List<Node> eog = rd.getEogStarters();
                for (Declaration declaration : rd.getDeclarations()) {  //ToDo: order?
                    graphWalker(declaration);
                }
                System.out.println("Test");
            }
            case FieldDeclaration fd -> {
                FieldDeclaration def = fd.getDefinition();
                Expression expr = fd.getInitializer();
                //ToDo
                //ToDo: implement scoped variable store?
                System.out.println("Test");
            }
            case ConstructorDeclaration cd -> {
                //List<Declaration> declarations = cd.getDeclarations();
                for (Declaration declaration : cd.getDeclarations()) {  //ToDo: order?
                    graphWalker(declaration);
                }
                System.out.println("Test");
            }
            case MethodDeclaration md -> {
                List<Declaration> declarations = md.getDeclarations();
                //ToDo
                System.out.println("Test");
            }
            default -> System.out.println("Error: other node: " + node.getClass());
        }



    }

}
