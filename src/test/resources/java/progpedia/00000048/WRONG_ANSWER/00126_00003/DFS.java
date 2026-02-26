// Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido

import java.util.*;
import java.io.*;

public class DFS {
    static int n;                 // Numero de nos do grafo
    static boolean adj[][]; // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?

    static void dfs(int v) {
	//System.out.print(v + " ");
	visited[v] = true;
	for (int i=1; i<=n; i++)
	    if (adj[v][i] && !visited[i])
	    {
            dfs(i);
        }

    }


    public static void main(String args[]) {
	Scanner input = new Scanner(System.in);

	n = input.nextInt();
	adj     = new boolean[n+1][n+1];
	visited = new boolean[n+1];
	int nligaçoes = input.nextInt();
	int edges=nligaçoes;
	int counter=0;
	for (int i=0; i<nligaçoes; i++) {
	    int a = input.nextInt();
	    int b = input.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}

    if(nligaçoes==0) counter=n;
    else
    {
        for(int i=1;i!=n;i++)
        {
            if(!visited[i]){
            dfs(i);
            counter++;
            }
        }
    }
	//dfs(1); // Pesquisa em profundidade a partir do no 1
	//System.out.println();
	System.out.println(counter);
    }
}
