import java.io.*;
import java.util.*;


class RC {
	static int pins;
	static boolean[] visited;
	static boolean[][] adj;
	
	static void dfs( int value ) {
		visited[value] = true;
		for( int i = 1; i <= pins; i++ ) {								// Tem de começar em 1 por causa do dfs(i)
			if( adj[value][i] && !visited[i] )
			dfs(i);
		}
	}
	
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		
		pins  = in.nextInt();
		visited = new boolean[pins+1];
		adj = new boolean[pins+1][pins+1];
		int edges = in.nextInt();
		
		
		for( int i = 0; i < edges; i++ ) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		
		int count = 0;
		
		for( int i = 1; i <= pins; i++ ) {								// Tem de começar em 1 por causa do dfs(i)
			if( !visited[i] ) {
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
	}
}
