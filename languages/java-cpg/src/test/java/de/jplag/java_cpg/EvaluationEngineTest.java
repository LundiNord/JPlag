package de.jplag.java_cpg;

import static de.jplag.java_cpg.AbstractJavaCpgLanguageTest.BASE_PATH;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import de.jplag.ParsingException;
import de.jplag.Token;

class EvaluationEngineTest {
    // Test 1: amount of dead code detected
    // Test 2: plagiarism score accuracy
    // run with and without dead code removal

    @Test
    void AiGeneratedTestDataEvaluation() throws ParsingException {
        List<Token> tokens = getTokensFromFile("aiGenerated/gemini/ProjectA.java", false, false);
        List<Token> tokensWithDetected = getTokensFromFile("aiGenerated/gemini/ProjectA.java", false, true);
        List<Token> tokensWithoutDeadCode = getTokensFromFile("aiGenerated/gemini/ProjectA.java", true, true);

        System.out.println("Tokens without dead code: " + tokensWithoutDeadCode.size());
    }

    @NotNull
    private List<Token> getTokensFromFile(@NotNull String fileName, boolean removeDeadCode, boolean detectDeadCode) throws ParsingException {
        JavaCpgLanguage language = new JavaCpgLanguage(removeDeadCode, detectDeadCode);
        File file = new File(BASE_PATH.toFile().getAbsolutePath(), fileName);
        Set<File> files = Set.of(file);
        return language.parse(files, true);
    }

}
