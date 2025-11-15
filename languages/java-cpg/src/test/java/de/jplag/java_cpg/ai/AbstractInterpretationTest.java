package de.jplag.java_cpg.ai;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

class AbstractInterpretationTest {

    @Test
    void runMain() {
        setup();
    }


    void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        File submissionsRoot = new File(classLoader.getResource("java/ai/simple").getFile());
        Set<File> submissionDirectories = Set.of(submissionsRoot);
    }

    @Test
    void testSimple() {
        ClassLoader classLoader = getClass().getClassLoader();
        File submissionsRoot = new File(classLoader.getResource("java/ai/simple").getFile());
        Set<File> submissionDirectories = Set.of(submissionsRoot);


    }


}
