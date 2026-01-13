package de.jplag.java_cpg;

import static de.jplag.java_cpg.transformation.TransformationRepository.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.MetaInfServices;

import de.jplag.Language;
import de.jplag.ParsingException;
import de.jplag.Token;
import de.jplag.java_cpg.ai.*;
import de.jplag.java_cpg.ai.variables.values.Value;
import de.jplag.java_cpg.transformation.GraphTransformation;

/**
 * This class represents the front end of the CPG module of JPlag.
 */
@MetaInfServices(de.jplag.Language.class)
public class JavaCpgLanguage implements Language {
    private static final int DEFAULT_MINIMUM_TOKEN_MATCH = 9;
    private static final String[] FILE_EXTENSIONS = {".java"};
    private static final String NAME = "Java Code Property Graph module";
    private static final String IDENTIFIER = "java-cpg";
    private final CpgAdapter cpgAdapter;

    /**
     * Creates a new {@link JavaCpgLanguage}.
     */
    public JavaCpgLanguage() {
        this.cpgAdapter = new CpgAdapter(true, true, true, allTransformations());
    }

    public JavaCpgLanguage(boolean removeDeadCode, boolean detectDeadCode, boolean reorder) {
        this.cpgAdapter = new CpgAdapter(removeDeadCode, detectDeadCode, reorder, allTransformations());
    }

    public JavaCpgLanguage(boolean removeDeadCode, boolean detectDeadCode, boolean reorder, GraphTransformation[] transformations) {
        this.cpgAdapter = new CpgAdapter(removeDeadCode, detectDeadCode, reorder, transformations);
    }

    public JavaCpgLanguage(boolean removeDeadCode, boolean detectDeadCode, boolean reorder, GraphTransformation[] transformations,
            IntAiType intAiType, FloatAiType floatAiType, StringAiType stringAiType, CharAiType charAiType, ArrayAiType arrayAiType) {
        this(removeDeadCode, detectDeadCode, reorder, transformations);
        Value.setUsedIntAiType(intAiType);
        Value.setUsedFloatAiType(floatAiType);
        Value.setUsedStringAiType(stringAiType);
        Value.setUsedCharAiType(charAiType);
        Value.setUsedArrayAiType(arrayAiType);
    }

    /**
     * @return array with only the minimal set of transformations needed for a standard tokenization
     */
    @NotNull
    public static GraphTransformation[] minimalTransformations() {
        return new GraphTransformation[] {removeImplicitStandardConstructor, removeLibraryRecord, removeLibraryField,};
    }

    /**
     * @return array with only the set of transformations needed for dead code removal
     */
    @NotNull
    public static GraphTransformation[] deadCodeRemovalTransformations() {
        return new GraphTransformation[] {removeEmptyDeclarationStatement, removeImplicitStandardConstructor, removeLibraryRecord, removeLibraryField,
                removeEmptyConstructor, removeUnsupportedConstructor, removeUnsupportedMethod, removeEmptyRecord};
    }

    /**
     * Adds the given {@link GraphTransformation} to the list to apply to the submissions.
     * @param transformation the transformation
     */
    public void addTransformation(GraphTransformation transformation) {
        this.cpgAdapter.addTransformation(transformation);
    }

    /**
     * Adds the given {@link GraphTransformation}s to the list to apply to the submissions.
     * @param transformations the transformations
     */
    public void addTransformations(GraphTransformation[] transformations) {
        this.cpgAdapter.addTransformations(transformations);
    }

    /**
     * Resets the set of transformations to the obligatory transformations only.
     */
    public void resetTransformations() {
        this.cpgAdapter.clearTransformations();
        this.cpgAdapter.addTransformations(this.obligatoryTransformations());
        this.cpgAdapter.addTransformations(this.standardTransformations());
    }

    /**
     * Returns the set of transformations required to ensure that the tokenization works properly.
     * @return the array of obligatory transformations
     */
    private GraphTransformation[] obligatoryTransformations() {
        return new GraphTransformation[] {wrapThenStatement, wrapElseStatement, wrapForStatement, wrapWhileStatement, wrapDoStatement};
    }

    /**
     * Returns a set of transformations suggested for use.
     * @return the array of recommended transformations
     */
    public GraphTransformation[] standardTransformations() {
        return new GraphTransformation[] {removeOptionalOfCall, removeOptionalGetCall, moveConstantToOnlyUsingClass, inlineSingleUseVariable,
                removeLibraryRecord, removeEmptyRecord,};
    }

    /**
     * Returns a set of all transformations.
     * @return the array of all transformations
     */
    public GraphTransformation[] allTransformations() {
        return new GraphTransformation[] {ifWithNegatedConditionResolution, forStatementToWhileStatement, removeOptionalOfCall, removeOptionalGetCall,
                removeGetterMethod, moveConstantToOnlyUsingClass, inlineSingleUseConstant, inlineSingleUseVariable, removeEmptyDeclarationStatement,
                removeImplicitStandardConstructor, removeLibraryRecord, removeLibraryField, removeEmptyConstructor, removeUnsupportedConstructor,
                removeUnsupportedMethod, removeEmptyRecord,};
    }

    @Override
    public List<String> fileExtensions() {
        return Arrays.asList(FILE_EXTENSIONS);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public int minimumTokenMatch() {
        return DEFAULT_MINIMUM_TOKEN_MATCH;
    }

    @Override
    public List<Token> parse(Set<File> files, boolean normalize) throws ParsingException {
        try {
            return cpgAdapter.adapt(files, normalize);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return List.of();
        }
    }

    @Override
    public boolean expectsSubmissionOrder() {   // FixMe: parallelimus seems to only sometimes work correctly
        return true;
    }

    @Override
    public boolean supportsNormalization() {
        return true;
    }

    @Override
    public boolean requiresCoreNormalization() {
        return false;
    }
}
