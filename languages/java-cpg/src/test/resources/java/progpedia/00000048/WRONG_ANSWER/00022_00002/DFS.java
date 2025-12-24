// Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido

import java.util.*;
import java.io.*;

public class DFS {
    static int n;                 // Numero de nos do grafo
    static boolean adj[][]; // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?
    static int contador;
	static int edges;

    static void dfs(int v) {
	contador=0;
	visited[v] = true;
	for (int i=1; i<=n; i++)		
	    if (adj[v][i] && !visited[i]){
		dfs(i);
		contador+=1;
		}
		
			

    }
    
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt();
	adj     = new boolean[n+1][n+1];
	visited = new boolean[n+1];	
	edges = stdin.nextInt();	
	for (int i=1; i<=edges; i++) {
	    int a = stdin.nextInt();
	    int b = stdin.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	
	
	dfs(1); // Pesquisa em profundidade a partir do no 1
		
	System.out.println(contador);
	    
    }
}
