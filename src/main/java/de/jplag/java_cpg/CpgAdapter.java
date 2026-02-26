package de.jplag.java_cpg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import de.fraunhofer.aisec.cpg.ConfigurationException;
import de.fraunhofer.aisec.cpg.InferenceConfiguration;
import de.fraunhofer.aisec.cpg.TranslationConfiguration;
import de.fraunhofer.aisec.cpg.TranslationManager;
import de.fraunhofer.aisec.cpg.TranslationResult;
import de.fraunhofer.aisec.cpg.frontends.java.JavaLanguage;
import de.fraunhofer.aisec.cpg.passes.ControlDependenceGraphPass;
import de.fraunhofer.aisec.cpg.passes.DynamicInvokeResolver;
import de.fraunhofer.aisec.cpg.passes.EvaluationOrderGraphPass;
import de.fraunhofer.aisec.cpg.passes.FilenameMapper;
import de.fraunhofer.aisec.cpg.passes.ImportResolver;
import de.fraunhofer.aisec.cpg.passes.JavaExternalTypeHierarchyResolver;
import de.fraunhofer.aisec.cpg.passes.JavaImportResolver;
import de.fraunhofer.aisec.cpg.passes.Pass;
import de.fraunhofer.aisec.cpg.passes.ProgramDependenceGraphPass;
import de.fraunhofer.aisec.cpg.passes.SymbolResolver;
import de.fraunhofer.aisec.cpg.passes.TypeHierarchyResolver;
import de.fraunhofer.aisec.cpg.passes.TypeResolver;
import de.jplag.java_cpg.ai.AiMethodPass;
import de.jplag.java_cpg.ai.AiPass;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

/**
 * This class handles the transformation of files of code to a token list.
 */
public class CpgAdapter {

    private final boolean removeDeadCode;
    private final boolean detectDeadCode;
    private final boolean methodAnalysisMode;
    private int deadLinesCount;
    private int deadCodeCount;

    /**
     * Sets if the analysis should try to continue if errors are encountered. The detected dead code will not be correct if
     * this is true.
     */
    private static final boolean continueOnError = false;

    public CpgAdapter(boolean removeDeadCode, boolean detectDeadCode, boolean methodAnalysisMode) {
        this.removeDeadCode = removeDeadCode;
        this.detectDeadCode = detectDeadCode;
        this.methodAnalysisMode = methodAnalysisMode;
    }

    void adapt(@NotNull Set<File> files) throws ParsingException, InterruptedException {
        assert !files.isEmpty();

        deadLinesCount = 0;
        // TokenizationPass sets tokenList
        translate(files);
    }

    @NotNull
    private <T extends Pass<?>> KClass<T> getKClass(Class<T> javaPassClass) {
        return JvmClassMappingKt.getKotlinClass(javaPassClass);
    }

    TranslationResult translate(@NotNull Set<File> files) throws ParsingException, InterruptedException {
        InferenceConfiguration inferenceConfiguration = InferenceConfiguration.builder().inferRecords(true).inferDfgForUnresolvedCalls(true).build();
        TranslationResult translationResult;
        AiPass.AiPassCompanion.setRemoveDeadCode(CpgAdapter.this.removeDeadCode);
        AiPass.AiPassCompanion.setContinueOnError(continueOnError);
        AiPass.AiPassCompanion.setDeadLinesCallback(CpgAdapter.this::setDeadLinesCount);
        AiPass.AiPassCompanion.setDeadCountCallback(CpgAdapter.this::setDeadCodeCount);
        AiMethodPass.AiMethodPassCompanion.setRemoveDeadCode(CpgAdapter.this.removeDeadCode);
        AiMethodPass.AiMethodPassCompanion.setContinueOnError(continueOnError);
        AiMethodPass.AiMethodPassCompanion.setDeadLinesCallback(CpgAdapter.this::setDeadLinesCount);
        AiMethodPass.AiMethodPassCompanion.setDeadCountCallback(CpgAdapter.this::setDeadCodeCount);
        try {
            TranslationConfiguration.Builder configBuilder = new TranslationConfiguration.Builder().inferenceConfiguration(inferenceConfiguration)
                    .sourceLocations(files.toArray(new File[] {})).registerLanguage(new JavaLanguage());

            List<Class<? extends Pass<?>>> passClasses = new ArrayList<>(
                    List.of(TypeResolver.class, TypeHierarchyResolver.class, JavaExternalTypeHierarchyResolver.class, JavaImportResolver.class,
                            ImportResolver.class, SymbolResolver.class, DynamicInvokeResolver.class, FilenameMapper.class,
                            EvaluationOrderGraphPass.class, ControlDependenceGraphPass.class, ProgramDependenceGraphPass.class));
            if (methodAnalysisMode) {
                passClasses.add(AiMethodPass.class);
            } else {
                passClasses.add(AiPass.class);
            }
            if (!detectDeadCode && !removeDeadCode) {
                passClasses.remove(AiPass.class);
                passClasses.remove(AiMethodPass.class);
            }
            for (Class<? extends Pass<?>> passClass : passClasses) {
                configBuilder.registerPass(getKClass(passClass));
            }
            translationResult = TranslationManager.builder().config(configBuilder.build()).build().analyze().get();
        } catch (ConfigurationException | ExecutionException e) {
            throw new ParsingException(List.copyOf(files).getFirst(), e);
        }
        return translationResult;
    }

    private void setDeadLinesCount(int count) {
        this.deadLinesCount = count;
    }

    int getDeadLinesCount() {
        return deadLinesCount;
    }

    private void setDeadCodeCount(int count) {
        this.deadCodeCount = count;
    }

    @TestOnly
    int getDeadCodeCount() {
        return deadCodeCount;
    }

}
