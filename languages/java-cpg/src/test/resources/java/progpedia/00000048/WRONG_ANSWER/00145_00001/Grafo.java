import java.util.*;
//import java.io.*;

class Node
{
	ArrayList<Integer> adj;
	int value;
	boolean visited;
}

class Graph
{
	private int V;
	Node g[];
	public Graph(int V)
	{
		this.V = V;
		this.g = new Node[V];
		for (int i = 0; i < V; i++)
		{
			this.g[i] = new Node();
			this.g[i].value = i;
			this.g[i].adj = new ArrayList<Integer>();
			this.g[i].visited = false;
		}
	}
	
	public void addConnection(int V1, int V2)
	{
		this.g[V1].adj.add(V2);
	}
	
	public void outGraph()
	{
		for(int i = 0; i < this.V; i++)
		{
			System.out.print("Node "+i+1+":");
			for(int j = 0; j < this.g[i].adj.size(); j++) 
			{
				System.out.print((this.g[i].adj.get(j)).toString());
			}  
			System.out.println();
			
		}
	}

	public void dfs(Node v)
	{
		v.visited = true;
		for (int i = 0; i < v.adj.size(); i++)
		{
			if(this.g[v.adj.get(i)-1].visited == false)
				dfs(this.g[v.adj.get(i)-1]);
		}
	}
	
	public int components()
	{
		int count = 0;
		for (int i=0; i < this.V; i++)
		{
			this.g[i].visited = false;
		}
		for (int i=0; i < this.V; i++)
		{
			if (this.g[i].visited == false)
			{
				count++;
				dfs(this.g[i]);
			}
		}
		return count;
	}
}

class Grafo
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		Graph g = new Graph(v);
		int e = sc.nextInt();
		for (int i = 0; i < e; i++)
		{
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			g.addConnection(v1-1, v2);
		}
		System.out.println(g.components());
		//g.outGraph();
		sc.close();
	}
}