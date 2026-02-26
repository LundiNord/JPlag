import java.io.*;
import java.util.*;

public class prob15{
    static int n;
    static boolean adj[][];
    static boolean visited[];
    static int contador;
    

    static void dfs(int v){
	visited[v]= true;

	for(int i=1;i<=n;i++)
	    if(adj[v][i] && !visited[i])
	       dfs(i);
    }

    static void conex(int v){
	contador=0;
	visited[v]=false;
	for(int i=1;i<=n;i++)
	    if(!visited[i]){
		contador = contador + 1;
		dfs(i);
	    }
	System.out.println(contador);
    }


    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
	
        n = stdin.nextInt();
	int linhas = stdin.nextInt();
        
	adj = new boolean[n+1][n+1];
	visited = new boolean[n+1];
        
	for(int i=0;i<linhas;i++){
	    int a = stdin.nextInt();
	    int b = stdin.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	
	
	dfs(1);
	conex(1);
        
    }
}
