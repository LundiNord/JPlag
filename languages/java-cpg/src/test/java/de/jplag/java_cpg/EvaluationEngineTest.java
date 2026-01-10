package de.jplag.java_cpg;

import static de.jplag.java_cpg.AbstractJavaCpgLanguageTest.BASE_PATH;
import static de.jplag.options.JPlagOptions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.jplag.*;
import de.jplag.clustering.ClusteringOptions;
import de.jplag.exceptions.ExitException;
import de.jplag.highlightextraction.FrequencyAnalysisOptions;
import de.jplag.merging.MergingOptions;
import de.jplag.options.JPlagOptions;

import kotlin.Pair;

class EvaluationEngineTest {
    // Test 1: amount of dead code detected
    // Test 2: plagiarism score accuracy
    // run with and without dead code removal
    // ToDo: assert that dead code removal does not remove non-dead code
    // ToDo look at Project H, perplexityLabs 2
    // maybe: tokensWithoutSimpleDeadCode: disable other graph normalizations except dead code removal
    // claude: project 5 dead code
    // gemini project L , comparator ; projectN, project Q
    // network controller.java
    // grok: project1

    @NotNull
    private static Stream<String> testFiles() {
        return Stream.of("aiGenerated/gemini/ProjectA.java", "aiGenerated/gemini/ProjectB.java", "aiGenerated/gemini/ProjectC.java",
                // "aiGenerated/gemini/ProjectD.java",
                "aiGenerated/gemini/ProjectE.java", "aiGenerated/gemini/ProjectF.java", "aiGenerated/gemini/ProjectG.java",
                "aiGenerated/gemini/ProjectH.java", "aiGenerated/gemini/ProjectI.java", "aiGenerated/gemini/ProjectJ.java",
                "aiGenerated/gemini/ProjectK.java", "aiGenerated/gemini/ProjectL.java", "aiGenerated/gemini/ProjectM.java",
                "aiGenerated/gemini/ProjectN.java", "aiGenerated/gemini/ProjectO.java", "aiGenerated/gemini/ProjectP.java",
                "aiGenerated/gemini/ProjectQ.java",
                // "aiGenerated/gemini/ProjectR.java", "aiGenerated/gemini/ProjectS.java", //anonymus class causes problems
                "aiGenerated/gemini/ProjectT.java", "aiGenerated/gemini/ProjectU.java", "aiGenerated/gemini/Project1.java",
                "aiGenerated/gemini/Project2.java", "aiGenerated/gemini/Project3.java", "aiGenerated/gemini/Project4.java",
                // "aiGenerated/gemini/Project5.java", //bug in GraphTransformations
                "aiGenerated/gemini/Project7.java", "aiGenerated/gemini/Project8.java",
                //
                "aiGenerated/claude/Project1.java", "aiGenerated/claude/Project2.java", "aiGenerated/claude/Project3.java",
                "aiGenerated/claude/Project4.java", "aiGenerated/claude/Project5.java", "aiGenerated/claude/Project6.java",
                "aiGenerated/claude/Project7.java", "aiGenerated/claude/Project8.java", "aiGenerated/claude/Project9.java",
                "aiGenerated/claude/Project10.java",
                //
                "aiGenerated/perplexityLabs/Project1.java", "aiGenerated/perplexityLabs/Project2.java", "aiGenerated/perplexityLabs/Project3.java",
                "aiGenerated/perplexityLabs/Project4.java",
                //
                "aiGenerated/geminiPlag/NetworkController.java", "aiGenerated/geminiPlag/ServerProcessManager.java",
                "aiGenerated/geminiPlag/GridOverseer.java",
                //
                "aiGenerated/grok/project1.java", "aiGenerated/grok/project2.java", "aiGenerated/grok/project3.java",
                "aiGenerated/grok/project4.java", "aiGenerated/grok/project5.java");
    }

    @NotNull
    private static Stream<Pair<String, String>> testPlagFiles() {
        return Stream.of(new Pair<>("aiGenerated/gemini/ProjectA.java", "aiGenerated/gemini/ProjectC.java"),
                new Pair<>("aiGenerated/gemini/ProjectB.java", "aiGenerated/gemini/ProjectE.java"),
                new Pair<>("aiGenerated/gemini/ProjectF.java", "aiGenerated/gemini/ProjectG.java"),
                new Pair<>("aiGenerated/gemini/ProjectH.java", "aiGenerated/gemini/ProjectI.java"),
                new Pair<>("aiGenerated/gemini/ProjectJ.java", "aiGenerated/gemini/ProjectK.java"),
                new Pair<>("aiGenerated/gemini/ProjectL.java", "aiGenerated/gemini/ProjectM.java"),
                new Pair<>("aiGenerated/gemini/ProjectN.java", "aiGenerated/gemini/ProjectO.java"),
                new Pair<>("aiGenerated/gemini/ProjectP.java", "aiGenerated/gemini/ProjectQ.java"),
                // new Pair<>("aiGenerated/gemini/ProjectR.java", "aiGenerated/gemini/ProjectS.java"), //anonymus class causes problems
                new Pair<>("aiGenerated/gemini/ProjectT.java", "aiGenerated/gemini/ProjectU.java"),
                new Pair<>("aiGenerated/gemini/Project1.java", "aiGenerated/gemini/Project2.java"),
                new Pair<>("aiGenerated/gemini/Project1.java", "aiGenerated/gemini/Project3.java"),
                // new Pair<>("aiGenerated/gemini/Project4.java", "aiGenerated/gemini/Project5.java"), //bug in GraphTransformations
                new Pair<>("aiGenerated/gemini/Project7.java", "aiGenerated/gemini/Project8.java"),
                new Pair<>("aiGenerated/gemini/Project4.java", "aiGenerated/gemini/Project7.java"),
                new Pair<>("aiGenerated/gemini/Project4.java", "aiGenerated/gemini/Project8.java"),
                //
                new Pair<>("aiGenerated/gemini/ProjectH.java", "aiGenerated/geminiPlag/NetworkController.java"),
                new Pair<>("aiGenerated/gemini/ProjectJ.java", "aiGenerated/geminiPlag/ServerProcessManager.java"),
                new Pair<>("aiGenerated/gemini/NetworkController.java", "aiGenerated/geminiPlag/GridOverseer.java"),
                //
                new Pair<>("aiGenerated/claude/Project1.java", "aiGenerated/claude/Project2.java"),
                new Pair<>("aiGenerated/claude/Project4.java", "aiGenerated/claude/Project1.java"),
                new Pair<>("aiGenerated/claude/Project4.java", "aiGenerated/claude/Project3.java"),
                new Pair<>("aiGenerated/claude/Project5.java", "aiGenerated/claude/Project6.java"),
                new Pair<>("aiGenerated/claude/Project7.java", "aiGenerated/claude/Project8.java"),
                new Pair<>("aiGenerated/claude/Project9.java", "aiGenerated/claude/Project10.java"),
                //
                new Pair<>("aiGenerated/perplexityLabs/Project1.java", "aiGenerated/perplexityLabs/Project3.java"),
                new Pair<>("aiGenerated/perplexityLabs/Project1.java", "aiGenerated/perplexityLabs/Project4.java"),
                //
                new Pair<>("aiGenerated/grok/project1.java", "aiGenerated/grok/project2.java"),
                new Pair<>("aiGenerated/grok/project3.java", "aiGenerated/grok/project4.java"),
                new Pair<>("aiGenerated/grok/project3.java", "aiGenerated/grok/project5.java"),
                new Pair<>("aiGenerated/grok/project4.java", "aiGenerated/grok/project5.java"));

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
    void AiGeneratedTestDataDeadCodeEvaluation(String fileName) throws ParsingException {
        List<Token> tokens = getTokensFromFile(fileName, false, false, false, false);
        List<Token> tokensWithoutSimpleDeadCode = getTokensFromFile(fileName, false, false, false, true);
        List<Token> tokensWithoutDeadCode = getTokensFromFile(fileName, true, true, false, true);
        List<Token> tokensWithoutDeadCodeManual = getTokensFromFileWithoutDeadCode(fileName, false);

        System.out.println("Similarity between manual and no dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokens) + "%");
        System.out.println("Similarity between manual and automatic simple dead code removal: "
                + similarity(tokensWithoutDeadCodeManual, tokensWithoutSimpleDeadCode) + "%");
        System.out.println(
                "Similarity between manual and automatic dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokensWithoutDeadCode) + "%");
        assertTrue(true);
    }

    @Test
    @Disabled
    void AiGeneratedTestDataDeadCodeEvaluationSingle() throws ParsingException {
        String fileName = "aiGenerated/grok/project5.java";
        List<Token> tokens = getTokensFromFile(fileName, false, false, false, false);
        List<Token> tokensWithoutSimpleDeadCode = getTokensFromFile(fileName, false, false, false, true);
        List<Token> tokensWithoutDeadCode = getTokensFromFile(fileName, true, true, false, true);
        List<Token> tokensWithoutDeadCodeManual = getTokensFromFileWithoutDeadCode(fileName, false);

        System.out.println("Similarity between manual and no dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokens) + "%");
        System.out.println("Similarity between manual and simple dead code removal: "
                + similarity(tokensWithoutDeadCodeManual, tokensWithoutSimpleDeadCode) + "%");
        System.out
                .println("Similarity between manual and dead code removal: " + similarity(tokensWithoutDeadCodeManual, tokensWithoutDeadCode) + "%");
        assertTrue(true);
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("testPlagFiles")
    void AiGeneratedTestDataPlagEvaluation(@NotNull Pair<String, String> fileNames) throws ExitException, IOException {
        String fileA = fileNames.getFirst();
        String fileB = fileNames.getSecond();
        double similarityJPlag = getJPlagPlagScore(fileA, fileB, false);
        double similarityMinimalCpg = getJPlagCpgPlagScore(fileA, fileB, false, false, false, false);
        double similarityStandardCpg = getJPlagCpgPlagScore(fileA, fileB, false, false, false, true);   // ToDo: enable reorder
        double similarityAi = getJPlagCpgPlagScore(fileA, fileB, true, true, false, true);

        System.out.println("Plagiarism scores for " + fileA + " and " + fileB + ":");
        System.out.println("JPlag standard: " + similarityJPlag);
        System.out.println("Cpg minimal transformations: " + similarityMinimalCpg);
        System.out.println("Cpg standard transformations: " + similarityStandardCpg);
        System.out.println("Cpg with AI dead code removal: " + similarityAi);
        assertTrue(true);
    }

    @Test
    @Disabled
    void AiGeneratedTestDataPlagEvaluationSingle() throws ExitException, IOException {
        String fileA = "aiGenerated/grok/project5.java";
        String fileB = "aiGenerated/grok/project4.java";
        double similarityJPlag = getJPlagPlagScore(fileA, fileB, false);
        double similarityMinimalCpg = getJPlagCpgPlagScore(fileA, fileB, false, false, false, false);
        double similarityStandardCpg = getJPlagCpgPlagScore(fileA, fileB, false, false, false, true);
        double similarityAi = getJPlagCpgPlagScore(fileA, fileB, true, true, false, true);

        System.out.println("Plagiarism scores for " + fileA + " and " + fileB + ":");
        System.out.println("JPlag standard: " + similarityJPlag);
        System.out.println("Cpg minimal transformations: " + similarityMinimalCpg);
        System.out.println("Cpg standard transformations: " + similarityStandardCpg);
        System.out.println("Cpg with AI dead code removal: " + similarityAi);
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

    private double getJPlagCpgPlagScore(@NotNull String fileNameA, @NotNull String fileNameB, boolean removeDeadCode, boolean detectDeadCode,
            boolean reorder, boolean normalize) throws ExitException, IOException {
        JavaCpgLanguage language = new JavaCpgLanguage(removeDeadCode, detectDeadCode, reorder);
        return getJPlagScore(fileNameA, fileNameB, normalize, language);
    }

    private double getJPlagPlagScore(@NotNull String fileNameA, @NotNull String fileNameB, boolean normalize) throws ExitException, IOException {
        de.jplag.java.JavaLanguage language = new de.jplag.java.JavaLanguage();
        return getJPlagScore(fileNameA, fileNameB, normalize, language);
    }

    private double getJPlagScore(@NotNull String fileNameA, @NotNull String fileNameB, boolean normalize, Language language)
            throws ExitException, IOException {
        File fileA = new File(BASE_PATH.toFile().getAbsolutePath(), fileNameA);
        File fileB = new File(BASE_PATH.toFile().getAbsolutePath(), fileNameB);
        // Create temporary directories for submissions
        File tempDirA = createTempDir();
        File tempDirB = createTempDir();
        // Copy files to temp directories
        File targetA = new File(tempDirA, "SubmissionA.java");
        File targetB = new File(tempDirB, "SubmissionB.java");
        java.nio.file.Files.copy(fileA.toPath(), targetA.toPath());
        java.nio.file.Files.copy(fileB.toPath(), targetB.toPath());
        Set<File> submissionDirectories = Set.of(tempDirA, tempDirB);
        JPlagOptions options = new JPlagOptions(language, null, submissionDirectories, Set.of(), null, null, null, null, DEFAULT_SIMILARITY_METRIC,
                DEFAULT_SIMILARITY_THRESHOLD, DEFAULT_SHOWN_COMPARISONS, new ClusteringOptions(), false, new MergingOptions(), normalize, false,
                new FrequencyAnalysisOptions());
        JPlagResult result = JPlag.run(options);
        assert result.getAllComparisons().size() == 1;
        JPlagComparison comparison = result.getAllComparisons().getFirst();
        double similarity = comparison.similarity();
        double maxSimilarity = comparison.maximalSimilarity();
        int matchedTokens = comparison.getNumberOfMatchedTokens();
        double frequencyWeightedSimilarity = comparison.frequencyWeightedSimilarity();
        return similarity;
    }

    private @NotNull File createTempDir() throws IOException {
        File tempDir = File.createTempFile("jplag_submission_", "");
        tempDir.delete();
        tempDir.mkdir();
        tempDir.deleteOnExit();
        return tempDir;
    }

}
