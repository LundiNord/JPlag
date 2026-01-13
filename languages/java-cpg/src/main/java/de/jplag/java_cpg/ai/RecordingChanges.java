package de.jplag.java_cpg.ai;

/**
 * Class to keep track of whether changes are being recorded between multiple ai Instances.
 * @author ujiqk
 * @version 1.0
 */
public class RecordingChanges {

    private boolean recording;

    public RecordingChanges(boolean recordingChanges) {
        this.recording = recordingChanges;
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean recordingChanges) {
        this.recording = recordingChanges;
    }

}
