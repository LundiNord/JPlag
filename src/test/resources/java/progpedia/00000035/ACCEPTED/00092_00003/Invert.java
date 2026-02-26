import java.util.*;
import java.io.*;

public class Invert {
    static int n;               // Número de posições
    static int[] v;             // Vetor que guarda a sequência

    // Função que calcula o número de inversões
    static int Invertions( int[] array ) {
        int invert = 0;

        // Ciclos for que vão selecionando uma posição do vetor e comparando com as restantes posições
        for( int i = 0; i < n; i++ ) {
            for (int j = i + 1; j < n; j++ ) {
                if( array[i] > array[j])
                    invert++;
            }
        }
        return invert;
    }

    static void readInput() {
        Scanner in = new Scanner( System.in );
        n = in.nextInt();
        v = new int[n];

        for( int i = 0; i < n; i++ )
            v[i] = in.nextInt();
    }

    public static void main( String[] args ) {
        readInput();
        int num_inv = Invertions( v );
        System.out.println( num_inv );
    }
}
