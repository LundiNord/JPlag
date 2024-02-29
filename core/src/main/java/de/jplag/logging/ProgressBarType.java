package de.jplag.logging;

/**
 * The available processes. Used as a hint for the ui, which step JPlag is currently performing.
 */
public enum ProgressBarType {
    LOADING("Loading Submissions  "),
    PARSING("Parsing Submissions  "),
    COMPARING("Comparing Submissions");

    private final String defaultText;

    ProgressBarType(String defaultText) {
        this.defaultText = defaultText;
    }

    /**
     * @return The default display text for the type
     */
    public String getDefaultText() {
        return defaultText;
    }
}
