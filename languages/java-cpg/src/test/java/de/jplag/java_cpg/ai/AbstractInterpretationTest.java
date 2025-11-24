// Java
package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.*;
import de.fraunhofer.aisec.cpg.frontends.java.JavaLanguage;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration;
import de.fraunhofer.aisec.cpg.passes.*;
import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.IntValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.passes.*;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractInterpretationTest {

    /**
     * a simple test with the main function only
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testSimple() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((IntValue) main.accessField("result")).getInformation());
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue());
    }

    /**
     * a simple test with the main function calling another function.
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testSimple2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple2");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((IntValue) main.accessField("result")).getInformation());
        assertFalse(((IntValue) main.accessField("result2")).getInformation());
    }

    /**
     * a slightly more complex test with the main function calling other functions.
     * with for loop and throw exception.
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testSimple3() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple3");
        JavaObject main = getMainObject(interpretation);
        assertEquals(1, ((IntValue) main.accessField("result")).getValue());
        assertEquals(2, ((IntValue) main.accessField("result2")).getValue());
    }

    /**
     * simple switch test
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testSwitch() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/switch");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertFalse(((IntValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple switch test
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testSwitch2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/switch2");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertFalse(((IntValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simplest loop test
     */
    @Test
    void testLoop() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/loop");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((IntValue) main.accessField("result")).getInformation());
        assertFalse(((IntValue) main.accessField("result2")).getInformation());
    }

    /**
     * simplest for each loop test
     */
    @Test
    void testForEach() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/forEach");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((IntValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * nondeterministic test! (completes sometimes in debug mode)
     * test creating a new class instance
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testNewClass() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/new");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * test if without else
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testIf() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/if");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((IntValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * test undetermined exception throw
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testException() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/exeption");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((IntValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((IntValue) main.accessField("result2")).getValue()); //y
    }

//    /**
//     * simple enum test
//     *
//     * @throws ParsingException
//     * @throws InterruptedException
//     */
//    @Test
//    void testEnum() throws ParsingException, InterruptedException {
//        AbstractInterpretation interpretation = interpretFromResource("java/ai/enum");
//        JavaObject main = getMainObject(interpretation);
//        assertEquals(400, ((IntValue) main.accessField("result")).getValue());
//        assertFalse(((IntValue) main.accessField("result2")).getInformation());
//    }

    /**
     * simple hashmap test
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testHashMap() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/map");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        //assertEquals(1, ((IntValue) main.accessField("result")).getValue());
        //assertEquals(2, ((IntValue) main.accessField("result")).getValue());
    }

    /**
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testQueensFarming() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/complex");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * simple try/catch test
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testTryCatch() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(400, ((IntValue) main.accessField("result")).getValue());  //z
        assertEquals(101, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * a simple try /catch test with throw inside called method
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testTryCatch2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try2");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(250, ((IntValue) main.accessField("result")).getValue());  //z
        assertEquals(200, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple try/catch test with nothing thrown
     *
     * @throws ParsingException
     * @throws InterruptedException
     */
    @Test
    void testTryCatch3() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try3");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(400, ((IntValue) main.accessField("result")).getValue());  //z
        assertEquals(200, ((IntValue) main.accessField("result2")).getValue()); //y
    }

    private TranslationResult translate(@NotNull Set<File> files) throws ParsingException, InterruptedException {
        InferenceConfiguration inferenceConfiguration =
                InferenceConfiguration.builder().inferRecords(true).inferDfgForUnresolvedCalls(true).build();
        TranslationResult translationResult;
        try {
            TranslationConfiguration.Builder configBuilder =
                    new TranslationConfiguration.Builder().inferenceConfiguration(inferenceConfiguration)
                            .sourceLocations(files.toArray(new File[]{})).registerLanguage(new JavaLanguage());
            List<Class<? extends Pass<?>>> passClasses = new ArrayList<>(List.of(
                    TypeResolver.class, TypeHierarchyResolver.class,
                    JavaExternalTypeHierarchyResolver.class, JavaImportResolver.class,
                    ImportResolver.class, SymbolResolver.class, PrepareTransformationPass.class, FixAstPass.class, DynamicInvokeResolver.class,
                    FilenameMapper.class, ReplaceCallCastPass.class,
                    AstTransformationPass.class, EvaluationOrderGraphPass.class,
                    ControlDependenceGraphPass.class, ProgramDependenceGraphPass.class,
                    DfgSortPass.class, CpgTransformationPass.class));
            for (Class<? extends Pass<?>> passClass : passClasses) {
                configBuilder.registerPass(getKClass(passClass));
            }
            translationResult = TranslationManager.builder().config(configBuilder.build()).build().analyze().get();
        } catch (ExecutionException | ConfigurationException e) {
            throw new ParsingException(List.copyOf(files).getFirst(), e);
        }
        return translationResult;
    }

    @NotNull
    private <T extends Pass<?>> KClass<T> getKClass(Class<T> javaPassClass) {
        return JvmClassMappingKt.getKotlinClass(javaPassClass);
    }

    private JavaObject getMainObject(@NotNull AbstractInterpretation interpretation) {
        VariableStore variableStore = interpretation.getVariables();
        return (JavaObject) variableStore.getVariable("Main").getValue();
    }

    @NotNull
    private AbstractInterpretation interpretFromResource(String resourceDir) throws ParsingException, InterruptedException {
        ClassLoader classLoader = getClass().getClassLoader();
        File submissionsRoot = new File(Objects.requireNonNull(classLoader.getResource(resourceDir)).getFile());
        Set<File> submissionDirectories = Set.of(submissionsRoot);
        TranslationResult result = translate(submissionDirectories);
        AbstractInterpretation interpretation = new AbstractInterpretation();

        Component comp = result.getComponents().getFirst();
        for (TranslationUnitDeclaration translationUnit : comp.getTranslationUnits()) {
            Assertions.assertNotNull(translationUnit.getName().getParent());
            if (translationUnit.getName().getParent().getLocalName().endsWith("Main")) {
                interpretation.runMain(translationUnit);
            }
        }
        return interpretation;
    }

}
