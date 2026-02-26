import java.util.*;
import java.io.*;

public class testProb15 {
    static int n;                 // Numero de nos do grafo
    static boolean adj[][]; // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?

    static void dfs(int v) {
	//System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=n; i++){
	    if (adj[v][i] && !visited[i])
		dfs(i);
	}
    }

    static int conta(){
	int contador = 0;
	for(int i = 1; i<=n; i++)
	    visited[i] = false;

	for(int i = 1; i<=n; i++){
	    if(visited[i] == false){
		contador++;
		dfs(i);
	    }
	}
	return contador;
    }
    
    public static void main(String args[]) {
	Scanner input = new Scanner(System.in);
	
	n = input.nextInt();
	adj     = new boolean[n+1][n+1];
	visited = new boolean[n+1];	
	int edges = input.nextInt();	
	for (int i=0; i<edges; i++) {
	    int a = input.nextInt();
	    int b = input.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}

	System.out.println(conta());       
    }
}
