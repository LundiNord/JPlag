import java.io.*;
import java.util.*;

public class Pyramid {
    static int n_rocks;                     // Número de rochas na base da pirâmide
    static int broken_rocks;                // Rochas em falta ou deterioradas
    static int[][] pyramid;                 // Matriz da pirâmide


    static void readInput() {
        Scanner in = new Scanner( System.in );
        n_rocks = in.nextInt();
        broken_rocks = in.nextInt();
        pyramid = new int[n_rocks][n_rocks];

        // r = Variável auxiliar que evita percorrer posições desnecessárias na matriz
        int r = n_rocks;

        // Construção da pirâmide na matriz pyramid[i][j]
        for( int i = n_rocks-1; i >= 0; i-- ) {
            int j = 0;
            while( j < r ) {
                pyramid[i][j] = 1;                        // Se pyramid[i][j] == 1, então a pedra não está deteriorada
                j++;
            }
            r--;
        }

        int layer, pos;
        for( int i = 0; i < broken_rocks; i++ ) {
            layer = in.nextInt();                   // Camada da pedra deteriorada
            pos   = in.nextInt();                   // Posição na camada da pedra deteriorada
            pyramid[n_rocks-layer][pos-1] = 0;      // Identificar as pedras deterioradas na matriz
        }
    }

    // Função Solve() vai contar o número de caminhos possíveis
    // O número de caminhos possíveis até ao topo fica guardado no topo da matriz ( matrix[0][0] )
    static int Solve() {
        int[][] matrix = new int[n_rocks][n_rocks];             // Matrix auxiliar para contar o número de caminhos até
                                                                // ao topo

        // Copiamos a base da pirâmide para a matriz matrix[i][j] para podermos fazer a contagem
        for( int i = 0; i < n_rocks; i++ )
            matrix[n_rocks-1][i] = pyramid[n_rocks-1][i];

        // Somamos o número de caminhos possíveis para cada posição
        // Começamos pela camada a seguir à base da pirâmide( n_rocks-2 ) para podermos contar o número de caminhos
        // possíveis a partir da base
        // Se pyramid[i][j] == 0, não fazemos a soma pois a pedra está deteriorada nessa posição
        for( int i = n_rocks-2; i >= 0; i-- ) {
            for ( int j = 0; j <= i; j++ ) {
                if( pyramid[i][j] == 1 )
                    matrix[i][j] = matrix[i+1][j] + matrix[i+1][j+1];
            }
        }

        return matrix[0][0];                // Número de caminhos possíveis até ao topo da pirâmide

    }

    public static void main( String[] args ) {
        readInput();
        int answer = Solve();
        System.out.println( answer );
    }
}
