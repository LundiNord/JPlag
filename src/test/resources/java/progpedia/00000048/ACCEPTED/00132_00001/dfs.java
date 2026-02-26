
//Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido

import java.util.*;
import java.io.*;

public class dfs{
 static int n;  // Numero de nos do grafo
 static boolean adj[][]; // Matriz de adjacencias
 static boolean visited[];  // Que nos ja foram visitados?
 static int conta;
 
 static void contar(){
	 for(int i=1;i<=n;i++){
		 if(!visited[i]){
			 Dfs(i);
			 conta++;
		 }
	 }
 }
 
 static void Dfs(int v) {
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
	    	Dfs(i);
    }
 
 public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();
	adj     = new boolean[n+1][n+1];
	visited = new boolean[n+1];	
	int edges = stdin.nextInt();	
	for (int i=0; i<edges; i++) {
	    int a = stdin.nextInt();
	    int b = stdin.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	
	
		contar();
	

	System.out.println(conta);       
 }
}
