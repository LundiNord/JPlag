import java.util.*;
import java.io.*;

public class P15{

    public static void dfs(int v, int n, boolean[] visited, boolean[][] adj) {
	//System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
		dfs(i, n, visited, adj);
    }
    
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		boolean[][] adj = new boolean[n+1][n+1];
		boolean[] visited = new boolean[n+1];
		
		for(int i=0; i<n; i++)
			visited[i]=false;
		
		int edges = stdin.nextInt();
		
		for (int i=0; i<edges; i++) {
		    int a = stdin.nextInt();
		    int b = stdin.nextInt();
		    adj[a][b] = adj[b][a] = true;
		}
		
		int contador=-1;
		for(int i=0; i<n; i++){
			if(visited[i]!=true)
				contador++;
			dfs(i, n, visited, adj);
		}
		
		System.out.println(contador);       
    }
}
