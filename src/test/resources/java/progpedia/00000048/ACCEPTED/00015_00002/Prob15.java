import java.util.*;
import java.io.*;

public class Prob15
{
	static int n;
	static boolean adj[][];
	static boolean visited[];
	static int contador = 0;
	
	static void dfs(int v)
	{
		visited[v] = true;
		for(int i = 1; i <= n; i++)
		{
			if(adj[v][i] && !visited[i])
			{
				dfs(i);
			}
			
		}
		
	}	


	public static void main (String [] args)
	{
		Scanner input = new Scanner(System.in);
	
		n = input.nextInt();
	
		adj = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		
		int edges = input.nextInt();
		
		for( int i = 0; i < edges; i++)
		{
			int a = input.nextInt();
			int b = input.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		dfs(1);
		contador=1;
		for(int i = 2; i <= n; i++)
		{
			if(!visited[i])
			{
				dfs(i);
				contador++;
			}
		}
		System.out.println(contador);
	}
}