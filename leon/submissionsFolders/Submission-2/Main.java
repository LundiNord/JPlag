package edu.kit.informatik;

public final class Main {

    private int result;
    private int result2;

    private Main() {
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int z = 500;
        int y = 100;

        x = x + 50;
        z = z + 50;

        x = x + 50;
        z = z + 50;

        x = x + 50;
        z = z + 50;

        x = x + 50;
        z = z + 50;

        x = x + 50;
        z = z + 50;

        result = z;
        result2 = y;
    }

}
