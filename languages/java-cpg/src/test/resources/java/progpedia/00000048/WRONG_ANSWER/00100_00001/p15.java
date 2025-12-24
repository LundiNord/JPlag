import java.util.*;
 class p15{

	static int n;                 // Numero de nos do grafo
    static boolean adj[][]; // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?



 	public static void main(String [] args){
 		Scanner stdin = new Scanner(System.in);

		n = stdin.nextInt();

		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];	
		
		int edges = stdin.nextInt();	
		
		for (int i=0; i<edges; i++) {
		    int a = stdin.nextInt();
		    int b = stdin.nextInt();
		    adj[a][b] = adj[b][a] = true; // marca link
		}

		int count=0;

		for(int i=1; i<n; i++){
			if(!visited[i])
				count++;
				dfs(i);
		}

		System.out.println(count);
 	}

	static void dfs(int v) {
		visited[v] = true;
		
		for (int i=1; i<=n; i++)
	    	if (adj[v][i] && !visited[i])
				dfs(i);
    }

 }