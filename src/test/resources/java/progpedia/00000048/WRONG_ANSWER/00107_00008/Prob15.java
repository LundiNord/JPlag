import java.util.*;
import java.io.*;

public class Prob15 {
    static int n;
    static int l;
    static boolean adj[][];
    static boolean visited[];
    
    public static void main (String args[]) {
	Scanner stdin = new Scanner(System.in);

	n       = stdin.nextInt();  //num de pontos
	l       = stdin.nextInt();  //num de lig
	adj     = new boolean [n+1][n+1]; 
	visited = new boolean [n+1];

	for (int i=0; i<l; i++) {
	    int a = stdin.nextInt();
	    int b = stdin.nextInt();
	    adj[a][b] = adj[b][a] = true;
	}
	
	if (l == 0) 
	    System.out.println(n);
	else
	//dfs(n);
	System.out.println(dfs(1));
    }
    
    static int dfs(int v) {
	int cont = 0;
	for (int i=1; i<=n; i++) {
	    if (adj[v][i] && visited[v] != true) {
		visited[i] = true;
		visited[v] = true;
		cont += 1;
		dfs(i);
	    }
	}
	return cont;
    }

}

