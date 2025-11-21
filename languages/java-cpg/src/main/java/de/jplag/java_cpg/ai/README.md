# JPlag-cpg Abstract Interpretation Engine

For debug, please run with `-ea` JVM flag.

## Build

Maven: `mvn clean package`

## Supported language features

## Explicitly not supported language features

## Usage Example

```java
import de.jplag.JPlag;
import de.jplag.JPlagResult;
import de.jplag.Language;
import de.jplag.exceptions.ExitException;
import de.jplag.java_cpg.JavaCpgLanguage;
import de.jplag.options.JPlagOptions;
import de.jplag.reporting.reportobject.ReportObjectFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public static void main(String[] args) {
    Language language = new JavaCpgLanguage();
    File submissionsRoot = new File(".../submissionsFolders");
    Set<File> submissionDirectories = Set.of(submissionsRoot);
    JPlagOptions options = new JPlagOptions(language, submissionDirectories, Set.of());
    try {
        JPlagResult result = JPlag.run(options);
        File outDir = new File(System.getProperty("user.home") + "/Downloads/");
        ReportObjectFactory reportObjectFactory = new ReportObjectFactory(outDir);
        reportObjectFactory.createAndSaveReport(result);
        System.out.println("JPlag analysis finished. Report written to: " + outDir.getAbsolutePath());
    } catch (ExitException e) {
        System.err.println("JPlag exited with an error: " + e.getMessage());
    } catch (FileNotFoundException e) {
        System.err.println("I/O error: " + e.getMessage());
    }
}
```
