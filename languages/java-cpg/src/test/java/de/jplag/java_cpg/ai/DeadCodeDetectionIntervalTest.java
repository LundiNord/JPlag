package de.jplag.java_cpg.ai;

import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.getMainObject;
import static de.jplag.java_cpg.ai.DeadCodeDetectionTest.interpretFromResource;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.jplag.ParsingException;
import de.jplag.java_cpg.ai.variables.values.JavaObject;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.ai.variables.values.numbers.FloatIntervalSetValue;
import de.jplag.java_cpg.ai.variables.values.numbers.INumberValue;
import de.jplag.java_cpg.ai.variables.values.numbers.IntIntervalSetValue;

/**
 * Test that only uses the CPG library. Specifically tests different integer interval analyses.
 * @author ujiqk
 * @version 1.0
 */
class DeadCodeDetectionIntervalTest {

    /**
     * A simple test with the main function only. Using integer interval analysis.
     */
    @Test
    void testSimpleInterval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue());
    }

    @Test
    void testSimpleSetInterval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.SET);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/simple");
        JavaObject main = getMainObject(interpretation);
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  // z
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
     * a slightly more complex test with the main function calling other functions. with for loop and throw exception.
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
        assertEquals(400, ((INumberValue) main.accessField("result")).getValue());  // z
        assertEquals(100, ((INumberValue) main.accessField("result2")).getValue()); // y
    }

    /**
     * Test the programming course final project: QueensFarming
     */
    @Test
    @Disabled("Disabled due to containing break statements not yet supported")
    void testQueensFarming() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/complex");
        JavaObject main = getMainObject(interpretation);
        assertNotNull(main);
    }

    /**
     * simplest loop test
     */
    @Test
    void testLoop() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/loop");
        JavaObject main = getMainObject(interpretation);
        assertEquals(500, ((INumberValue) main.accessField("result")).getValue()); // z
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
        assertFalse(((INumberValue) main.accessField("result3")).getInformation());
    }

    /**
     * simplest test of nested loop
     */
    @Test
    void testLoop2x() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.INTERVALS);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/loopx2");
        JavaObject main = getMainObject(interpretation);
        assertEquals(500, ((INumberValue) main.accessField("result")).getValue()); // z
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
        assertFalse(((INumberValue) main.accessField("result3")).getInformation());
    }

    @Test
    void testInterval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.SET);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/interval");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertEquals(110, ((IntIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getLowerBound());
        assertEquals(210, ((IntIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getUpperBound());
        assertEquals(161, ((INumberValue) main.accessField("result2")).getValue());
        assertFalse(((INumberValue) main.accessField("result3")).getInformation());
        assertEquals(150, ((IntIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getLowerBound());
        assertEquals(450, ((IntIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getUpperBound());
        assertFalse(((INumberValue) main.accessField("result4")).getInformation());
        assertEquals(501, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getLowerBound());
        assertEquals(2501, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getUpperBound());
    }

    @Test
    void testMultipleInterval() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.SET);
        Value.setUsedFloatAiType(FloatAiType.DEFAULT);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/intervalMulti");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertEquals(0, ((IntIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getLowerBound());
        assertEquals(Integer.MAX_VALUE, ((IntIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getUpperBound());
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
        assertEquals(2, ((IntIntervalSetValue) main.accessField("result2")).getIntervals().size());     // y
        assertEquals(10, ((IntIntervalSetValue) main.accessField("result2")).getIntervals().getFirst().getLowerBound());
        assertEquals(50, ((IntIntervalSetValue) main.accessField("result2")).getIntervals().getFirst().getUpperBound());
        assertEquals(200, ((IntIntervalSetValue) main.accessField("result2")).getIntervals().getLast().getLowerBound());
        assertEquals(300, ((IntIntervalSetValue) main.accessField("result2")).getIntervals().getLast().getUpperBound());
        assertFalse(((INumberValue) main.accessField("result3")).getInformation());
        assertEquals(11, ((IntIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getLowerBound());
        assertEquals(Integer.MAX_VALUE, ((IntIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getUpperBound());
        assertFalse(((INumberValue) main.accessField("result4")).getInformation());
        assertEquals(2, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().size());
        assertEquals(20, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getLowerBound());
        assertEquals(100, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getUpperBound());
        assertEquals(400, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getLast().getLowerBound());
        assertEquals(600, ((IntIntervalSetValue) main.accessField("result4")).getIntervals().getLast().getUpperBound());
    }

    @Test
    void testIntervalDouble() throws ParsingException, InterruptedException {
        Value.setUsedIntAiType(IntAiType.SET);
        Value.setUsedFloatAiType(FloatAiType.SET);
        AbstractInterpretation interpretation = interpretFromResource("java/ai/intervalDouble");
        JavaObject main = getMainObject(interpretation);
        assertFalse(((INumberValue) main.accessField("result")).getInformation());
        assertEquals(60.5f, ((FloatIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getLowerBound(), 0.0001);
        assertEquals(110.5, ((FloatIntervalSetValue) main.accessField("result")).getIntervals().getFirst().getUpperBound(), 0.0001);
        assertFalse(((INumberValue) main.accessField("result2")).getInformation());
        assertEquals(20.65f, ((FloatIntervalSetValue) main.accessField("result2")).getIntervals().getFirst().getLowerBound(), 0.0001);
        assertEquals(20.9f, ((FloatIntervalSetValue) main.accessField("result2")).getIntervals().getFirst().getUpperBound(), 0.0001);
        assertFalse(((INumberValue) main.accessField("result3")).getInformation());
        assertEquals(37.5f, ((FloatIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getLowerBound(), 0.0001);
        assertEquals(112.5f, ((FloatIntervalSetValue) main.accessField("result3")).getIntervals().getFirst().getUpperBound(), 0.0001);
        assertFalse(((INumberValue) main.accessField("result4")).getInformation());
        assertEquals(10.3f, ((FloatIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getLowerBound(), 0.0001);
        assertEquals(1010.3f, ((FloatIntervalSetValue) main.accessField("result4")).getIntervals().getFirst().getUpperBound(), 0.0001);
    }

}
