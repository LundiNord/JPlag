// Exemplo de pesquisa em profundidade (DFS) num grafo nao dirigido
import java.util.*;
import java.io.*;

public class Prob15{
    static int n;               // Numero de nos do grafo
    static boolean adj[][]; 	// Matriz de adjacencias
    static boolean visited[];  	// Que nos ja foram visitados?
    
    static void dfs(int v){
		visited[v] = true;
		for (int i=1; i<=n; i++)
		    if (adj[v][i] && !visited[i])
				dfs(i);
    }
    
    static int conta(){
    	int contador = 0;
  
    	for(int i=1; i<=n; i++){
    		visited[i]=false;
    	}

    	for(int i=1; i<=n; i++){
    		if(!visited[i]){
    			contador++;
    			dfs(i);
    		}
    	}

    	return contador;
    }


    public static void main(String args[]) {
		Scanner inp = new Scanner(System.in);
		
		n = inp.nextInt();
		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];	
		int nligacoes = inp.nextInt();	
		
		for (int i=0; i<nligacoes; i++) {
		    int a = inp.nextInt();
		    int b = inp.nextInt();
		    adj[a][b] = adj[b][a] = true;
		}

		System.out.println(conta());       
    }
}