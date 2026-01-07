package de.jplag.java_cpg;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import de.jplag.ParsingException;
import de.jplag.Token;

/**
 * Test class for testing the interaction between AiPass and TokenizationPass.
 * @author Gemini 3 Flash
 */
public class CombinedPassTest extends AbstractJavaCpgLanguageTest {

    @Test
    void testDeadCodeRemovalInTokens() throws ParsingException {
        String fileName = "ai/deadCode5/Submission-01/Main.java";
        JavaCpgLanguage language = new JavaCpgLanguage();

        // Parse the file with normalization enabled (which triggers AiPass)
        List<Token> parsedTokens = language.parse(Set.of(new File(baseDirectory.getAbsolutePath(), fileName)), true);

        // Check if any token belongs to DeadClass (lines 11-15)
        boolean foundDeadClassToken = false;
        for (Token token : parsedTokens) {
            if (token.getLine() >= 11 && token.getLine() <= 15) {
                foundDeadClassToken = true;
                break;
            }
        }

        assertFalse(foundDeadClassToken, "Tokens from DeadClass should have been removed by AiPass");

        // Also verify that we still have tokens from Main class (lines 3-9)
        boolean foundMainClassToken = false;
        for (Token token : parsedTokens) {
            if (token.getLine() >= 3 && token.getLine() <= 9) {
                foundMainClassToken = true;
                break;
            }
        }
        assertTrue(foundMainClassToken, "Tokens from Main class should be present");
    }
}
