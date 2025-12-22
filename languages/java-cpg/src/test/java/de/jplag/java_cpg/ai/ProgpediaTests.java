package de.jplag.java_cpg.ai;

import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.graph.Component;
import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.VariableStore;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.translate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProgpediaTests {

    @NotNull
    private static AbstractInterpretation interpretFromResource(String resourceDir) throws ParsingException, InterruptedException {
        ClassLoader classLoader = DeadCodeDetectionTest.class.getClassLoader();
        File submissionsRoot = new File(Objects.requireNonNull(classLoader.getResource(resourceDir)).getFile());
        Set<File> submissionDirectories = Set.of(submissionsRoot);
        TranslationResult result = translate(submissionDirectories);
        AbstractInterpretation interpretation = new AbstractInterpretation();

        assert result.getComponents().size() == 1;
        Component comp = result.getComponents().getFirst();
        assert comp.getTranslationUnits().size() == 1;
        interpretation.runMain(comp.getTranslationUnits().getFirst());
        return interpretation;
    }

    private static Stream<String> acceptedResourceDirs() {
        ClassLoader classLoader = DeadCodeDetectionTest.class.getClassLoader();
        java.net.URL url = classLoader.getResource("java/progpedia/00000006/ACCEPTED");
        if (url == null) return Stream.empty();
        File base = new File(Objects.requireNonNull(url).getFile());
        File[] dirs = base.listFiles(File::isDirectory);
        if (dirs == null) return Stream.empty();
        return Arrays.stream(dirs).map(f -> "java/progpedia/00000006/ACCEPTED/" + f.getName() + "/");
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
    void testSingle() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000006/ACCEPTED/00130_00001");
        VariableStore variableStore = interpretation.getVariables();
        assertNotNull(variableStore);
    }

}
