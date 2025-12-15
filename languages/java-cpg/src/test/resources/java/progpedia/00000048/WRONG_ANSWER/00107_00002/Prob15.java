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

	//dfs(n);
	System.out.println(dfs(n));
    }
    
    static int dfs(int v) {
	int cont = 0;
	visited[v] = true;
	for (int i=1; i<=n; i++) {
	    if (adj[v][i] && visited[i] != true) {
		
		cont += 1;
		dfs(i);
	    }
	}
	return cont;
    }

}

