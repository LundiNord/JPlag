
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Herlander Silva
 */
public class P12_Piramides {

    static long[][] v;
    static int dim;
    static int broken;

    static void printArray(int[][] v) {
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    void readInput(int whereToRead) throws FileNotFoundException {
        Class c;
        c = this.getClass();
        Scanner stdin;
        switch (whereToRead) {
            default:
            case 1:
                stdin = new Scanner(System.in);
                break;
            case 2:
//                @SuppressWarnings("resource")
                stdin = new Scanner(new File("Problemas/" + c.getSimpleName() + ".in"));
                break;
        }

        dim = stdin.nextInt();
        broken = stdin.nextInt();

        v = new long[dim + 1][dim + 1];
        int idx = 1;
        while (idx <= broken && broken > 0) {
            v[(dim + 1) - stdin.nextInt()][stdin.nextInt()] = -1;
            idx++;
        }
    }

    static void markWays() {
        if (v[1][1] != -1) {
            v[1][1] = 1;
        }
        for (int i = 1; i < v.length; i++) {
            if (v[i - 1][1] != -1 && v[i][1] != -1) {
                v[i][1] += v[i - 1][1];
            }
            if (dim > 2) {
                int j;
                for (j = 2; j < i; j++) {
//                System.out.println(i+" "+(j)+" "+v[i-1][j]+" "+v[i-1][j-1]);
                    if (v[i][j] != -1) {
                        if (v[i - 1][j] != -1) {
                            v[i][j] += v[i - 1][j];
                        }
                        if (v[i - 1][j - 1] != -1) {
                            v[i][j] += v[i - 1][j - 1];
                        }
                    }
                }
//                System.out.println(j);
                if (v[i - 1][j - 1] != -1 && v[i][j] != -1) {
                    v[i][j] += v[i - 1][j - 1];
                }
            }
        }
    }

    static long countWays() {
        long ways = 0;
//        System.out.println(dim);
        for (int i = 1; i <= dim; i++) {
            if (v[dim][i] > 0) {
                ways += v[dim][i];
            }
        }
        return ways;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        P12_Piramides p12 = new P12_Piramides();
        p12.readInput(1);

//        printArray(v);
        markWays();
//        printArray(v);

        System.out.println(countWays());
    }
}
