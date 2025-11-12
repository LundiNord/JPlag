package edu.kit.informatik;

/**
 * @author ujiqk
 * @version 1.0 */
public final class Main {

    private static final String ERROR_ARGUMENTS_NOT_SUPPORTED = "Error: Commandline arguments not supported";
    private static final String ERROR_ARGUMENTS_NOT_SUPPORTED2;

    private Main() {
        throw new IllegalStateException();
    }

    /**
     * @param args Kommandozeilenparameter mit Wert von x.
     */
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        x = Math.abs(x);
        int y = 100;

        if (x+y < 100) {
            System.out.print("1");
        } else {
            System.out.print("2");
        }
    }
}
