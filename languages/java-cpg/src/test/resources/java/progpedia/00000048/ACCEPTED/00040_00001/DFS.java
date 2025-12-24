// Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido

import java.util.*;
import java.io.*;

public class DFS {
    static int numNos; // Numero de nos do grafo
    static boolean adj[][]; // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?

    static void dfs(int v) {
	//System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=numNos; i++)
	    if (adj[v][i] && !visited[i])
		dfs(i);
    }
    
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	
	numNos = stdin.nextInt(); // num de pontos de contacto
	adj     = new boolean[numNos+1][numNos+1];
	int edges = stdin.nextInt();	//num ligaçoes existentes
	for (int i=0; i<edges; i++) {
	    int a = stdin.nextInt();
	    int b = stdin.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	int contador = 0;
	visited = new boolean[numNos+1];	
	for(int i=1;i<=numNos;i++){
		if(visited[i]==false){
			contador = contador +1;
			dfs(i); // Pesquisa em profundidade a partir do no 1
		}
	}
	System.out.println(contador);       
    }
}