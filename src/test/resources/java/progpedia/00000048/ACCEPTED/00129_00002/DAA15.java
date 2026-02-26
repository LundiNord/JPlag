import java.util.*;

public class DAA15 {

	static int n;                 // Numero de nos do grafo
	static boolean adj[][]; 	  // Matriz de adjacencias
	static boolean visited[];     // Que nos ja foram visitados?
	static int count=0;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		adj = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		int edges = scan.nextInt();	
		for (int i=0; i<edges; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		count_non_visited();
		System.out.println(count);    
		scan.close();
	}
	
	static int count_non_visited (){
		for (int i=1;i<=n;i++){
			if (!visited[i]){
				count++;
				dfs(i);
			}
		}
		return count;
	}
	
	
	static void dfs(int v) {   	
		visited[v] = true;
		for (int i=1; i<=n; i++)
			if (adj[v][i] && !visited[i])
				dfs(i);
		}
}