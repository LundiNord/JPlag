import java.util.Scanner;
 
public class problem16 {
     
    static Scanner in = new Scanner(System.in);
    static int n_vertices;
    static int n_arestas;
    static int[][] matrix;
    static boolean[] visited;
     
    public static void main(String[] args) {
         
        int contador=0;
        n_vertices = in.nextInt();
        n_arestas = in.nextInt();
         
        matrix = new int[n_vertices+1][n_vertices+1];
        visited = new boolean[n_vertices+1];
        clearVisited();
         
        for(int i=1; i<=n_arestas; i++){
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            matrix[v1][v2] = 1;
            matrix[v2][v1] = 1;
        }
         
        for(int i=1; i<=n_vertices; i++){
            if(!visited[i]){
                contador++;
                dfs(i);
            }
        }
        System.out.println(contador);
         
    } //end of main
     
    public static void dfs(int i){
        int j;
        visited[i] = true; // marcar o no como visitado
        for(j=1 ; j <= n_vertices;j++){
            if(matrix[i][j] > 0 && !visited[j])
                dfs(j);
        }
    }
     
    public static void clearVisited(){
        int i;
        for(i=0; i < visited.length; i++)
            visited[i] = false;
    }
}