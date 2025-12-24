package de.jplag.leon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

import de.jplag.JPlag;
import de.jplag.JPlagResult;
import de.jplag.Language;
import de.jplag.exceptions.ExitException;
import de.jplag.java_cpg.JavaCpgLanguage;
import de.jplag.options.JPlagOptions;
import de.jplag.reporting.reportobject.ReportObjectFactory;

/**
 *
 */
public class Main {

    static void main(String[] args) {
        System.out.println("Hello, JPlag Leon!");
        Language language = new JavaCpgLanguage();
        File submissionsRoot = new File("leon/submissionsFolders");
        Set<File> submissionDirectories = Set.of(submissionsRoot);
        JPlagOptions options = new JPlagOptions(language, submissionDirectories, Set.of());
        try {
            JPlagResult result = JPlag.run(options);
            File outDir = new File("leon/output.zip");
            // File outDir = new File(System.getProperty("user.home") + "/Downloads/");
            ReportObjectFactory reportObjectFactory = new ReportObjectFactory(outDir);
            reportObjectFactory.createAndSaveReport(result);
            System.out.println("JPlag analysis finished. Report written to: " + outDir.getAbsolutePath());
        } catch (ExitException e) {
            System.err.println("JPlag exited with an error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

}
