import java.util.*;
import java.io.*;

public class circuitos{
    static int n;  
    static boolean adj[][]; 
    static boolean visitado[]; 

    static void dfs(int v) {
    	visitado[v] = true;
    	for (int i=1; i<=n; i++){
    		if (adj[v][i] && !visitado[i]){
    			dfs(i);
    		}
    	}
    }

    public static void main(String args[]) {
	Scanner ler = new Scanner(System.in);
	
	n = ler.nextInt();
	adj     = new boolean[n+1][n+1];
	visitado = new boolean[n+1];	
	int count = 0;
	int edges = ler.nextInt();	
	for (int i=0; i<edges; i++) {
	    int a = ler.nextInt();
	    int b = ler.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	for(int i = 1; i<=n;i++){
		if (!visitado[i]) {
			dfs(i);
			count++;
		}
		
	}
	if(edges == 0){
		count = n;
	}
	System.out.println(count);     
    }
}
