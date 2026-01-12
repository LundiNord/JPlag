package de.jplag.java_cpg.ai;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import de.fraunhofer.aisec.cpg.graph.Node;
import de.fraunhofer.aisec.cpg.sarif.PhysicalLocation;

/**
 * Records visited code lines in source files.
 * @author ujiqk
 * @version 1.0
 */
public class VisitedLinesRecorder {

    private final Map<URI, Set<Integer>> visitedLines;
    private final Map<URI, Set<Integer>> possibleLines;
    private final Map<URI, Set<Integer>> detectedDeadLines;

    public VisitedLinesRecorder() {
        visitedLines = new HashMap<>();
        possibleLines = new HashMap<>();
        detectedDeadLines = new HashMap<>();
    }

    public void recordLinesVisited(@NotNull Node node) {
        PhysicalLocation location = node.getLocation();
        if (location == null) {
            return;
        }
        URI uri = location.getArtifactLocation().getUri();
        Set<Integer> alreadyVisitedLines = visitedLines.computeIfAbsent(uri, _ -> new HashSet<>());
        // Only line numbers and not full regions are stored
        int startLine = location.getRegion().startLine;
        int endLine = location.getRegion().getEndLine();
        for (int line = startLine; line <= endLine; line++) {
            alreadyVisitedLines.add(line);
        }
        // check if file information is already present
        possibleLines.computeIfAbsent(uri, this::countLinesInFile);
    }

    public void recordFirstLineVisited(@NotNull Node node) {
        PhysicalLocation location = node.getLocation();
        if (location == null) {
            return;
        }
        URI uri = location.getArtifactLocation().getUri();
        Set<Integer> alreadyVisitedLines = visitedLines.computeIfAbsent(uri, _ -> new HashSet<>());
        // Only line numbers and not full regions are stored
        int startLine = location.getRegion().startLine;
        alreadyVisitedLines.add(startLine);
        // check if file information is already present
        possibleLines.computeIfAbsent(uri, this::countLinesInFile);
    }

    public void recordDetectedDeadLines(@NotNull URI uri, int startLine, int endLine) {
        assert startLine <= endLine;
        assert startLine >= 0;
        Set<Integer> deadLines = new HashSet<>();
        for (int line = startLine; line <= endLine; line++) {
            deadLines.add(line);
        }
        Set<Integer> alreadyDeadLines = detectedDeadLines.computeIfAbsent(uri, _ -> new HashSet<>());
        alreadyDeadLines.addAll(deadLines);
    }

    public void recordDetectedDeadLines(@NotNull Node node) {
        PhysicalLocation location = node.getLocation();
        if (location == null) {
            return;
        }
        URI uri = location.getArtifactLocation().getUri();
        int startLine = location.getRegion().startLine;
        int endLine = location.getRegion().getEndLine();
        recordDetectedDeadLines(uri, startLine, endLine);
    }

    @NotNull
    @Pure
    @TestOnly
    public Map<URI, Set<Integer>> getNonVisitedLines() {
        Map<URI, Set<Integer>> nonVisitedLines = new HashMap<>();
        for (Map.Entry<URI, Set<Integer>> entry : possibleLines.entrySet()) {
            URI uri = entry.getKey();
            Set<Integer> possible = entry.getValue();
            Set<Integer> visited = visitedLines.getOrDefault(uri, new HashSet<>());
            Set<Integer> nonVisited = new HashSet<>(possible);
            nonVisited.removeAll(visited);
            nonVisitedLines.put(uri, nonVisited);
        }
        return nonVisitedLines;
    }

    @Pure
    public boolean checkIfCompletelyDead(@NotNull URI uri, int startLine, int endLine) {
        if (startLine == -1 || endLine == -1) {
            return false;
        }
        assert startLine <= endLine;
        assert startLine >= 0;
        Set<Integer> visited = visitedLines.getOrDefault(uri, new HashSet<>());
        for (int line = startLine; line <= endLine; line++) {
            if (visited.contains(line)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    private Set<Integer> countLinesInFile(@NotNull URI uri) {
        Set<Integer> lines = new HashSet<>();
        try (var reader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File(uri)))) {
            int lineNumber = 1;
            while (reader.readLine() != null) {
                lines.add(lineNumber++);
            }
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Could not read file: " + uri, e);
        }
        return lines;
    }

    @TestOnly
    public Map<URI, Set<Integer>> getDetectedDeadLines() {
        return detectedDeadLines;
    }

    @TestOnly
    public Map<URI, Set<Integer>> getVisitedLines() {
        return visitedLines;
    }

}
