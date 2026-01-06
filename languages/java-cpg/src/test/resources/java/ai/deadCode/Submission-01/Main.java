package edu.kit.informatik;

public final class Main {
    private static int result;

    private Main() {
    }

    public static void main(String[] args) {
        int x = 10;
        if (x > 5) {
            result = 1;
        } else {
            result = 2; // Dead code
            result = 3; // Also Dead code
        }
    }
}
