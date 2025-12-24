import java.util.Scanner;

public class prob15 {

	static int n;   
	static boolean adj[][];
	static boolean visited[];
	static int count=0;
	
	static int nc ()
	{
		for (int i=1;i<=n;i++)
		{
			if (!visited[i])
			{
				count++;
				dfs(i);
			}
		}
		
		return count;
	}
	
	
	static void dfs(int v) 
	{   	
		visited[v] = true;
		for (int i=1; i<=n; i++)
			if (adj[v][i] && !visited[i])
			dfs(i);

	}
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		int edges = in.nextInt();	
		for (int i=0; i<edges; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
			
		}
		nc();
		System.out.println(count);    
	}
}