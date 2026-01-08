package de.jplag.java_cpg;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.jplag.ParsingException;
import de.jplag.Token;
import de.jplag.java_cpg.token.CpgTokenType;

/**
 * Test class for testing the interaction between AiPass and TokenizationPass.
 */
class CombinedPassTest extends AbstractJavaCpgLanguageTest {

    /**
     * @author Gemini 3 Flash
     */
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

    @Test
    void testDeadCodeRemovalInTokens2() throws ParsingException {
        String directoryName = "combined/One";
        JavaCpgLanguage language = new JavaCpgLanguage();
        File directory = new File(baseDirectory.getAbsolutePath(), directoryName);
        Set<File> files = Set.of(Objects.requireNonNull(directory.listFiles((dir, name) -> name.endsWith(".java"))));
        List<Token> parsedTokens = language.parse(files, true);
        for (Token token : parsedTokens) {
            System.out.println(token);
        }
        assertEquals(87, parsedTokens.size(), "Unexpected number of tokens after dead code removal");
        assertEquals(CpgTokenType.RECORD_DECL_BEGIN, parsedTokens.getFirst().getType());
        assertEquals(CpgTokenType.METHOD_CALL, parsedTokens.get(10).getType());
        assertEquals(CpgTokenType.IF_STATEMENT, parsedTokens.get(20).getType());
        assertEquals(CpgTokenType.FIELD_DECL, parsedTokens.get(30).getType());
        assertEquals(CpgTokenType.IF_BLOCK_END, parsedTokens.get(40).getType());
        assertEquals(CpgTokenType.METHOD_BODY_BEGIN, parsedTokens.get(50).getType());
        assertEquals(CpgTokenType.RECORD_DECL_BEGIN, parsedTokens.get(61).getType());
        assertEquals(CpgTokenType.ELSE_BLOCK_BEGIN, parsedTokens.get(70).getType());
        assertEquals(CpgTokenType.METHOD_CALL, parsedTokens.get(80).getType());
        assertEquals(CpgTokenType.RECORD_DECL_END, parsedTokens.get(85).getType());
    }

    @Test
    @Disabled
    void testDeadCodeRemovalInTokens3() throws ParsingException {
        String directoryName = "combined/Two";
        JavaCpgLanguage language = new JavaCpgLanguage();
        File directory = new File(baseDirectory.getAbsolutePath(), directoryName);
        Set<File> files = Set.of(Objects.requireNonNull(directory.listFiles((dir, name) -> name.endsWith(".java"))));
        List<Token> parsedTokens = language.parse(files, true);
        assertEquals(0, parsedTokens.size(), "Unexpected number of tokens after dead code removal");
    }

}
