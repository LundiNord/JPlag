package de.jplag.java_cpg.ai;

import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.values.INumberValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.junit.jupiter.api.Test;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.getMainObject;
import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.interpretFromResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeadCodeDetectionIntervalTest {

    /**
     * A simple test with the main function only.
     * Using integer interval analysis.
     */
    @Test
    void testSimpleInterval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue());
    }

    /**
     * a simple test with the main function calling another function.
     */
    @Test
    void testSimple2Interval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple2");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
    }

}
