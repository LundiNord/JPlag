import java.util.*;
import java.io.*;

public class networks {
	static int n;
	static boolean adj[][];
	static boolean visited[];

	static void depth (int v) {
		visited[v] = true;
		for (int i=1; i<=n; i++)
		if (adj[v][i] && !visited[i])
			depth(i);
	}

	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int ctr = 0;

		n = stdin.nextInt();
		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];	
		int edges = stdin.nextInt();	
		for (int i=0; i<edges; i++) {
		    int a = stdin.nextInt();
		    int b = stdin.nextInt();
		    adj[a][b] = adj[b][a] = true;
		}

		for(int k=1; k<=n; k++){
			if(!visited[k]){
				ctr++;
				depth(k);
			}
		}


		System.out.println(ctr);
	}
}