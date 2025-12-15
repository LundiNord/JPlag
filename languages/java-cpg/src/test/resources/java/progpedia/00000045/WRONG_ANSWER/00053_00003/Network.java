import java.io.*;
import java.util.*;

public class Network {
    static int n;                   // Número de nós da Árvore
    static boolean[] visited;       // Vetor que nos vai dizer que nós já foram visitados
    static boolean[][] matrix_adj;  // Matriz de adjacências

    static void readInput() {
        Scanner read = new Scanner( System.in );

        n = read.nextInt();
        int edges = read.nextInt();

        visited    = new boolean[n+1];
        matrix_adj = new boolean[n+1][n+1];

        for( int i = 0; i < edges; i++ ) {                          // Colocamos nas posições em que existem ligações
            int node1 = read.nextInt();                             // entre nós o valor booleano true
            int node2  = read.nextInt();

            matrix_adj[node1][node2] = matrix_adj[node2][node1] = true;
        }
    }

    static void depthFirstSearch( int v ) {
        visited[v] = true;                              // Nó v foi visitado, logo visted passa a true
        for( int i = 1; i <= n; i++ ) {                 // Usa o ciclo for para pesquisar os nós que ainda não foram
            if( matrix_adj[i][v] && !visited[i] )       // visitados e que têm ligação com o nó v
                depthFirstSearch(i);                                 // Se existirem ligações com o nó v, vai passar o nó v a ser o
        }                                               // nó i, isto é, vai imprimir o nó vizinho e fazer a procura
    }                                                   // a partir dele


    static int counter( int v ) {                       // Contador do número de componentes conexas do grafo disconexo
        int count = 0;
        for( int i = 1; i <= n; i++ )                   // Colocamos todos os nós do vetor como não visitados
            visited[i] = false;

        for( int i = 1; i <= n; i++ ) {                 // Percorremos todos os nós existentes
            if ( !visited[i]) {                         // Se o nó não foi visitado, adcionamos um ao contador e
                count = count + 1;                      // chamamos a função dfs (depthFirstSearch, definida na linha 28)
                depthFirstSearch(i);                    // A função dfs vai colocar os valores do vetor visited como
            }                                           // true para as posições em que passar até a componente acabar
        }                                               // Logo, quando percorremos os nós, só o primeiro nó da
        return count;                                   // próxima componente conexa é que vai ter o valor false em
    }                                                   // visited. Assim, contamos todas as componentes de um grafo
    public static void main( String[] args ) {          // disconexo
        readInput();
        int num = counter(1);
        System.out.println(num);
    }
}

