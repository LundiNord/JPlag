package de.jplag.java_cpg.ai;

import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.string.StringCharInclValue;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.getMainObject;
import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.interpretFromResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadCodeDetectionStringTest {

    /**
     * simple test for character inclusion string analysis.
     */
    @Test
    void testString() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.DEFAULT);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        Value.setUsedStringAiType(StringAiType.CHAR_INCLUSION);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/string");
        JavaObject main = getMainObject(interpretation);
        assertEquals(new HashSet<>(Set.of(' ', 'D', 'e', 'h', 'J', 'l', ',', 'n', 'o')), ((StringCharInclValue) main.accessField("result")).getCertainContained());
        assertEquals(new HashSet<>(Set.of('!', 'c', 'W', 'H', 'm')), ((StringCharInclValue) main.accessField("result")).getMaybeContained());
        assertEquals(new HashSet<>(Set.of('J', 'O', 'H', 'N', ' ', 'D', 'E')), ((StringCharInclValue) main.accessField("result2")).getCertainContained());
        assertEquals(new HashSet<>(Set.of()), ((StringCharInclValue) main.accessField("result2")).getMaybeContained());
    }

}
