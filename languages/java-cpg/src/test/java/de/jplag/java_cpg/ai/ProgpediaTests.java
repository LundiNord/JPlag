package de.jplag.java_cpg.ai;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.translate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.Value;

/**
 * Tests from the Progpedia data set.
 * <p>
 * José Carlos Paiva, José Paulo Leal, and Álvaro Figueira. PROGpedia. Dec. 2022. 10.5281/zenodo.7449056
 * <a href="https://zenodo.org/records/7449056">zenodo.org/records/7449056</a> (visited on 11/04/2025).
 * @author ujiqk
 * @version 1.0
 */
class ProgpediaTests {

    @NotNull
    private static AbstractInterpretation interpretFromResource(String resourceDir) throws ParsingException, InterruptedException {
        ClassLoader classLoader = DeadCodeDetectionTest.class.getClassLoader();
        File submissionsRoot = new File(Objects.requireNonNull(classLoader.getResource(resourceDir)).getFile());
        Set<File> submissionDirectories = Set.of(submissionsRoot);
        TranslationResult result = translate(submissionDirectories);
        AbstractInterpretation interpretation = new AbstractInterpretation(new VisitedLinesRecorder());

        assert result.getComponents().size() == 1;
        Component comp = result.getComponents().getFirst();
        assert comp.getTranslationUnits().size() == 1;
        interpretation.runMain(comp.getTranslationUnits().getFirst());
        return interpretation;
    }

    @NotNull
    private static Stream<String> acceptedResourceDirs() {
        return Stream
                .of("00000006", "00000016", "00000018", "00000019", "00000021", "00000022", "00000023", "00000034", "00000035", "00000039",
                        "00000042", "00000043", "00000045", "00000048", "00000053", "00000056")
                .flatMap(problemId -> Stream.of("ACCEPTED", "WRONG_ANSWER").flatMap(category -> getResourceDirsForProblem(problemId, category)));
    }

    private static Stream<String> getResourceDirsForProblem(String problemId, String category) {
        ClassLoader classLoader = DeadCodeDetectionTest.class.getClassLoader();
        java.net.URL url = classLoader.getResource("java/progpedia/" + problemId + "/" + category);
        if (url == null)
            return Stream.empty();
        File base = new File(Objects.requireNonNull(url).getFile());
        File[] dirs = base.listFiles(File::isDirectory);
        if (dirs == null)
            return Stream.empty();
        return Arrays.stream(dirs).map(f -> "java/progpedia/" + problemId + "/" + category + "/" + f.getName() + "/");
    }

    @ParameterizedTest
    @MethodSource("acceptedResourceDirs")
    void testProgpedia(String resourceDir) throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource(resourceDir);
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    @Test
    @Disabled
    void testSingle() throws ParsingException, InterruptedException {       // for(Node cursor=invert.top;cursor!=null;cursor=cursor.next)
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000006/ACCEPTED/00130_00001");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    // @Test
    // @Disabled
    // void testSingle2() throws ParsingException, InterruptedException {
    // Value.setUsedIntAiType(IntAiType.DEFAULT);
    // Value.setUsedFloatAiType(FloatAiType.DEFAULT);
    // Value.setUsedStringAiType(StringAiType.DEFAULT);
    // AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000039/WRONG_ANSWER/00233_00002");
    // VariableStore variableStore = interpretation.getVariables();
    // assertNotNull(variableStore);
    // }

    @Test
    @Disabled
    void testSingle3() throws ParsingException, InterruptedException {  // for (int k=0, i=0; i<ns; i++) for (int j=i+1; j<ns; j++, k++)
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000039/WRONG_ANSWER/00216_00001");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    @Test
    @Disabled
    void testSingle4() throws ParsingException, InterruptedException {  // class has the same name as an array
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000045/ACCEPTED/00072_00002");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    @Test
    @Disabled
    void testSingle5() throws ParsingException, InterruptedException {  // pesquisabinaria
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000039/WRONG_ANSWER/00142_00003");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    // @Test
    // @Disabled
    // void testSingle6() throws ParsingException, InterruptedException {
    // Value.setUsedIntAiType(IntAiType.DEFAULT);
    // Value.setUsedFloatAiType(FloatAiType.DEFAULT);
    // Value.setUsedStringAiType(StringAiType.DEFAULT);
    // AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000039/ACCEPTED/00075_00010");
    // VariableStore variableStore = interpretation.getVariables();
    // assertNotNull(variableStore);
    // }

    // @Test
    // @Disabled
    // void testSingle7() throws ParsingException, InterruptedException {
    // Value.setUsedIntAiType(IntAiType.DEFAULT);
    // Value.setUsedFloatAiType(FloatAiType.DEFAULT);
    // Value.setUsedStringAiType(StringAiType.DEFAULT);
    // AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000053/ACCEPTED/00103_00004");
    // VariableStore variableStore = interpretation.getVariables();
    // assertNotNull(variableStore);
    // }

    @Test
    @Disabled
    void testSingle8() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000048/ACCEPTED/00158_00001");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

    // @Test
    // @Disabled
    // void testSingle9() throws ParsingException, InterruptedException {
    // Value.setUsedIntAiType(IntAiType.DEFAULT);
    // Value.setUsedFloatAiType(FloatAiType.DEFAULT);
    // Value.setUsedStringAiType(StringAiType.DEFAULT);
    // AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000045/ACCEPTED/00203_00002");
    // VariableStore variableStore = interpretation.getVariables();
    // assertNotNull(variableStore);
    // }

    @Test
    @Disabled
    void testSingle10() throws ParsingException, InterruptedException {     // FixMe
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000019/ACCEPTED/00196_00001");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

}
