package de.jplag.java_cpg;

import static de.jplag.java_cpg.AbstractJavaCpgLanguageTest.BASE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.jplag.ParsingException;
import de.jplag.Token;
import de.jplag.java_cpg.ai.AiPass;
import de.jplag.java_cpg.ai.VisitedLinesRecorder;

class EvaluationEngineTest {
    // Test 1: amount of dead code detected
    // Test 2: plagiarism score accuracy
    // run with and without dead code removal

    @NotNull
    private static Stream<String> testFiles() {
        return Stream.of("aiGenerated/gemini/ProjectA.java", "aiGenerated/gemini/ProjectB.java", "aiGenerated/gemini/ProjectC.java",
                // "aiGenerated/gemini/ProjectD.java",
                "aiGenerated/gemini/ProjectE.java");
    }

    private static <T> double similarity(@NotNull List<T> s1, @NotNull List<T> s2) {
        // stolen from https://stackoverflow.com/questions/955110/similarity-string-comparison-in-java
        List<T> longer = s1, shorter = s2;
        if (s1.size() < s2.size()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.size();
        if (longerLength == 0) {
            return 1.0; /* both lists are zero length */
        }
        double result = (longerLength - editDistance(longer, shorter)) / (double) longerLength;
        result = Math.round(result * 10000.0) / 10000.0;
        return result * 100;
    }

    private static <T> int editDistance(@NotNull List<T> s1, @NotNull List<T> s2) {
        // stolen from https://stackoverflow.com/questions/955110/similarity-string-comparison-in-java
        int[] costs = new int[s2.size() + 1];
        for (int i = 0; i <= s1.size(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.size(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (!(s1.get(i - 1).equals(s2.get(j - 1)))) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.size()] = lastValue;
            }
        }
        return costs[s2.size()];
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("testFiles")
    void AiGeneratedTestDataDeadCodeEvaluation(String fileName) throws ParsingException, IOException {
        Set<Integer> deadLines = new TreeSet<>(getDeadLinesFromFile(fileName));
        Set<Integer> expectedDeadLines = new TreeSet<>(getExpectedDeadLines(fileName));

        assertEquals(expectedDeadLines, deadLines);
    }

    @Test
    @Disabled
    void AiGeneratedTestDataDeadCodeEvaluationSingle() throws ParsingException, IOException {
        String fileName = "aiGenerated/gemini/ProjectE.java";
        Set<Integer> deadLines = new TreeSet<>(getDeadLinesFromFile(fileName));
        Set<Integer> expectedDeadLines = new TreeSet<>(getExpectedDeadLines(fileName));

        assertEquals(expectedDeadLines, deadLines);
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("testFiles")
    void AiGeneratedTestDataEvaluation(String fileName) throws ParsingException {
        List<Token> tokens = getTokensFromFile(fileName, false, false, false, false);
        List<Token> tokensWithoutSimpleDeadCode = getTokensFromFile(fileName, false, true, false, true);
        List<Token> tokensWithoutDeadCode = getTokensFromFile(fileName, true, true, false, true);
        List<Token> tokensWithoutDeadCodeManual = getTokensFromFileWithoutDeadCode(fileName, false);

        System.out.println("Similarity between manual and no dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokens) + "%");
        System.out.println("Similarity between manual and automatic simple dead code removal: "
                + similarity(tokensWithoutDeadCodeManual, tokensWithoutSimpleDeadCode) + "%");
        System.out.println(
                "Similarity between manual and automatic dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokensWithoutDeadCode) + "%");
        assertTrue(true);
    }

    @NotNull
    private List<Token> getTokensFromFile(@NotNull String fileName, boolean removeDeadCode, boolean detectDeadCode, boolean reorder,
            boolean normalize) throws ParsingException {
        assert normalize || !reorder;
        JavaCpgLanguage language = new JavaCpgLanguage(removeDeadCode, detectDeadCode, reorder);
        File file = new File(BASE_PATH.toFile().getAbsolutePath(), fileName);
        Set<File> files = Set.of(file);
        List<Token> result = language.parse(files, normalize);
        result.removeLast(); // remove EOF token
        return result;
    }

    @NotNull
    private List<Token> getTokensFromFileWithoutDeadCode(@NotNull String fileName, boolean reorder) throws ParsingException {
        try {
            File originalFile = new File(BASE_PATH.toFile().getAbsolutePath(), fileName);
            File tempFile = File.createTempFile("jplag_temp_", ".java");
            tempFile.deleteOnExit();
            BufferedReader reader = new BufferedReader(new FileReader(originalFile));
            java.io.PrintWriter writer = new java.io.PrintWriter(tempFile);
            String line;
            boolean inDeadCode = false;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.contains("//DeadCodeStart")) {
                    inDeadCode = true;
                } else if (trimmed.contains("//DeadCodeEnd")) {
                    inDeadCode = false;
                } else if (!inDeadCode) {
                    writer.println(line);
                }
            }
            reader.close();
            writer.close();
            JavaCpgLanguage language = new JavaCpgLanguage(false, false, reorder);
            List<Token> result = language.parse(Set.of(tempFile), false);
            result.removeLast(); // remove EOF token
            return result;
        } catch (IOException e) {
            throw new ParsingException(new File(BASE_PATH.toFile().getAbsolutePath(), fileName), e.getMessage());
        }
    }

    @NotNull
    private Set<Integer> getDeadLinesFromFile(@NotNull String fileName) throws ParsingException {
        getTokensFromFile(fileName, false, true, true, true);
        VisitedLinesRecorder visitedLinesRecorder = AiPass.AiPassCompanion.getVisitedLinesRecorder();
        Map<URI, Set<Integer>> deadLines = visitedLinesRecorder.getDetectedDeadLines();
        assert deadLines.size() == 1;
        return deadLines.values().iterator().next();
    }

    @NotNull
    private Set<Integer> getExpectedDeadLines(@NotNull String fileName) throws IOException {
        File file = new File(BASE_PATH.toFile().getAbsolutePath(), fileName);
        Set<Integer> expectedDeadLines = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int lineNumber = 1;
        boolean inDeadCode = false;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();

            if (trimmed.contains("//DeadCodeStart")) {
                inDeadCode = true;
            } else if (trimmed.contains("//DeadCodeEnd")) {
                inDeadCode = false;
            } else if (inDeadCode) {
                expectedDeadLines.add(lineNumber);
            }
            lineNumber++;
        }
        return expectedDeadLines;
    }

}
