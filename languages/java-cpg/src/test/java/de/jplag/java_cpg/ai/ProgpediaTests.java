package de.jplag.java_cpg.ai;

import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.junit.jupiter.api.Test;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.getMainObject;
import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.interpretFromResource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProgpediaTests {

    /**
     * simple test for character inclusion string analysis.
     */
    @Test
    void test00000006Accepted() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/progpedia/00000006/ACCEPTED/");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

}
