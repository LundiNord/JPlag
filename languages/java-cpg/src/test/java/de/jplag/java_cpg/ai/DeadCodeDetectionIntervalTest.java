package de.jplag.java_cpg.ai;

import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.values.INumberValue;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import org.junit.jupiter.api.Test;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.getMainObject;
import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.interpretFromResource;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
    }

    /**
     * a slightly more complex test with the main function calling other functions.
     * with for loop and throw exception.
     */
    @Test
    void testSimple3() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple3");
        JavaObject main = getMainObject(interpretation);
        assertEquals(1, ((INumberValue) main.accessField("result")).getValue());
        assertEquals(2, ((INumberValue) main.accessField("result2")).getValue());
    }

    /**
     * test if without else
     */
    @Test
    void testIf() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/if");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  //z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); //y
    }

    /**
     * Test the programming course final project: QueensFarming
     */
    @Test
    void testQueensFarming() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/complex");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

}
