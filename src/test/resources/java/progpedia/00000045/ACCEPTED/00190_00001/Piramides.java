
import java.util.Scanner;

public class Piramides {

    public static long dinamic(int n, long pir[][]) {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (pir[i][j] != 0) {
                    pir[i][j] = pir[i + 1][j] + pir[i + 1][j + 1];
                }
            }
        }
        return pir[0][0];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        long pir[][] = new long[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                pir[i][j] = 1;
            }
        }

        for (int i = 0; i < d; i++) {
            int c = in.nextInt();
            int p = in.nextInt();
            pir[n - c][p - 1] = 0;
        }

        System.out.println(dinamic(n, pir));
    }
}
