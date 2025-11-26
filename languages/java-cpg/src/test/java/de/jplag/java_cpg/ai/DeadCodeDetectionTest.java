package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.*;
import de.fraunhofer.aisec.cpg.frontends.java.JavaLanguage;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.fraunhofer.aisec.cpg.graph.declarations.TranslationUnitDeclaration;
import de.fraunhofer.aisec.cpg.passes.*;
import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.INumberValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test that only use the CPG library.
 *
 * @author ujiqk
 * @version 1.0
 */
class DeadCodeDetectionTest {

    public static JavaObject getMainObject(@NotNull AbstractInterpretation interpretation) {
        VariableStore variableStore = interpretation.getVariables();
        return (JavaObject) variableStore.getVariable("Main").getValue();
    }

    @NotNull
    public static AbstractInterpretation interpretFromResource(String resourceDir) throws ParsingException, InterruptedException {
        ClassLoader classLoader = DeadCodeDetectionTest.class.getClassLoader();
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

    private static TranslationResult translate(@NotNull Set<File> files) throws ParsingException, InterruptedException {
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
                    ImportResolver.class, SymbolResolver.class, DynamicInvokeResolver.class,
                    FilenameMapper.class, ReplaceCallCastPass.class,
                    EvaluationOrderGraphPass.class, ControlDependenceGraphPass.class,
                    ProgramDependenceGraphPass.class, DFGPass.class));
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
    private static <T extends Pass<?>> KClass<T> getKClass(Class<T> javaPassClass) {
        return JvmClassMappingKt.getKotlinClass(javaPassClass);
    }

    /**
     * a simple test with the main function only
     */
    @Test
    void testSimple() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue());
    }

    /**
     * a simple test with the main function calling another function.
     */
    @Test
    void testSimple2() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple2");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
    }

    /**
     * a slightly more complex test with the main function calling other functions.
     * with for loop and throw exception.
     */
    @Test
    void testSimple3() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple3");
        JavaObject main = getMainObject(interpretation);
        assertEquals(1, ((INumberValue) main.accessField("result")).getValue());
        assertEquals(2, ((INumberValue) main.accessField("result2")).getValue());
    }

    /**
     * simple switch test
     */
    @Test
    void testSwitch() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/switch");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple switch test
     */
    @Test
    void testSwitch2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/switch2");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simplest loop test
     */
    @Test
    void testLoop() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/loop");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
    }

    /**
     * simplest for each loop test
     */
    @Test
    void testForEach() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/forEach");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());  //z
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());  //y
    }

    /**
     * nondeterministic test! (completes sometimes in debug mode)
     * test creating a new class instance
     */
    @Test
    void testNewClass() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/new");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * test if without else
     */
    @Test
    void testIf() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/if");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * test if with || in condition
     */
    @Test
    void testIfOr() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/ifOr");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * test undetermined exception throw
     */
    @Test
    void testException() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/exception");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple enum test
     */
    @Test
    @Disabled
    void testEnum() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/enum");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
    }

    /**
     * simple hashmap test
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
     * Test the programming course final project: QueensFarming
     */
    @Test
    void testQueensFarming() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/complex");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * Test another programming course final project.
     */
    @Test
    void testQueensFarming2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/complex2");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * simple break statement test
     */
    @Test
    void testBreak() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/break");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        //ToDo
    }

    /**
     * simple try/catch test
     */
    @Test
    void testTryCatch() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(101, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * a simple try /catch test with throw inside called method
     */
    @Test
    void testTryCatch2() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try2");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(250, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(200, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple try/catch test with nothing thrown
     */
    @Test
    void testTryCatch3() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/try3");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(200, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * simple stream test
     */
    @Test
    @Disabled
    void testStream() throws ParsingException, InterruptedException {
        AbstractInterpretation interpretation = interpretFromResource("java/ai/stream");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());          //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

}
