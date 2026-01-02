# JPlag-cpg Abstract Interpretation Engine

For debug, please run with `-ea` JVM flag.

**For now a lot of method have a default switch case that throws an exception.
They will later be replaced with a default case that sets the value to unknown and returns an unknown value.**

## Build

Maven: `mvn clean package`

## Code Structure

ToDo

## Supported language features

see [ToDos](./ToDo.md) for supported and unsupported features.

## Explicitly not supported language features

- exception flow is not modeled
- System.exit calls are not supported
- Continues and breaks in loops are not supported
- Iterators are not supported

see [ToDos](./ToDo.md) for supported and unsupported features.

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
