   import java.io.*;
    import java.util.*;
     
public class redes {
       
        static boolean[][] adj;
        static boolean[] visited;
        static int nv, ne;
     
        static void dfs (int node) {
            visited[node] = true;
	
	            for (int i = 1; i < nv; i++) {
                if (adj[node][i] && !visited[i]) {
			
				                    
			dfs(i);
                }
            }
        }
     
        public static void main (String[] args) {
            Scanner stdin = new Scanner (System.in);
            nv = stdin.nextInt()+1;
            ne = stdin.nextInt();
            adj = new boolean[nv][nv];
            visited = new boolean[nv];
            for (int i = 0; i < ne; i++) {
                int a = stdin.nextInt();
                int b = stdin.nextInt();
                adj[a][b] = adj[b][a] = true; //grafo nao dirigido, ligacao de a para b e b para a
            }
//		System.out.println("vértice de valor: ");
	int contador=0;            
	for (int i = 1; i < nv; i++) {
                if (!visited[i]){
                	dfs(i);
			contador++;
		
		}
            }

		System.out.println(contador);
        } // fecha o main
	
    }

