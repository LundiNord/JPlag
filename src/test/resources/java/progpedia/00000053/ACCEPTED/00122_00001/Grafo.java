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
	private int distancias[][];
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
		if (!this.g[V1-1].adj.contains(V2))
			this.g[V1-1].adj.add(V2);
		if (!this.g[V2-1].adj.contains(V1))
			this.g[V2-1].adj.add(V1);
	}
	
	public void outGraph()
	{
		for(int i = 0; i < this.V; i++)
		{
			System.out.print("Node "+(i+1)+":");
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
	
	public void bfs(Node v)
	{
		for (int i=0; i < this.V; i++)
			this.g[i].visited = false;
		
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(v);
		v.visited = true;
		while (q.size() != 0)
		{
			Node u = q.remove(0);
			for (int i = 0; i < u.adj.size(); i++)
			{
				if(this.g[u.adj.get(i)-1].visited == false)
				{
					q.add(this.g[u.adj.get(i)-1]);
					this.g[u.adj.get(i)-1].visited = true;
				}
			}			
		}
	}
	
	public void distancias()
	{
		int d[][] = new int[this.V][this.V];
		for (int i = 0; i<this.V; i++)
		{
			for (int j = 0; j<this.V; j++)
				d[i][j] = 0;
		}
		for (int i = 0; i<this.V; i++)
		{
			for (int j=0; j < this.V; j++)
				this.g[j].visited = false;
			
			ArrayList<Node> q = new ArrayList<Node>();
			q.add(g[i]);
			d[g[i].value][g[i].value] = 0;
			g[i].visited = true;
			while (q.size() != 0)
			{
				Node u = q.remove(0);
				for (int j = 0; j < u.adj.size(); j++)
				{
					if(this.g[u.adj.get(j)-1].visited == false)
					{
						q.add(this.g[u.adj.get(j)-1]);
						this.g[u.adj.get(j)-1].visited = true;
						d[i][this.g[u.adj.get(j)-1].value] = d[i][u.value] + 1;
					}
				}			
			}
			
		}
//		for (int k = 0; k<this.V; k++)
//		{
//			for (int j = 0; j<this.V; j++)
//				System.out.print(d[k][j] + " ");
//			System.out.println();
//		}
		
		this.distancias = d;
	}
	
	public int excentricidade(int n)
	{
		int x = 0;
		for (int i = 0; i<this.V; i++)
		{
			if (distancias[n][i] > x)
				x = distancias[n][i];
		}
		return x;
	}
	
	public int diametro()
	{
		int d = 0;
		for (int i = 0; i<this.V; i++)
		{
			int e = excentricidade(i);
			if (e > d)
				d = e;
		}
		return d;
	}
	
	public int raio()
	{
		int r = 5001;
		for (int i = 0; i<this.V; i++)
		{
			int e = excentricidade(i);
			if (e < r)
				r = e;
		}
		return r;
	}
	
	public ArrayList<Integer> centrais()
	{
		ArrayList<Integer> c = new ArrayList<Integer>();
		int r = raio();
		for (int i = 0; i<this.V; i++)
		{
			int e = excentricidade(i);
				if(e == r)
				{
					c.add(i+1);
				}
			
		}
		
		return c;
	}
	
	public ArrayList<Integer> perifericos()
	{
		ArrayList<Integer> p = new ArrayList<Integer>();
		int d = diametro();
		for (int i = 0; i<this.V; i++)
		{
			for (int j = 0; j<this.V; j++)
			{
				if(distancias[i][j] == d)
				{
					p.add(i+1);
					break;
				}
			}
		}
		
		return p;
	}
	
	public int components()
	{
		int count = 0;
		for (int i=0; i < this.V; i++)
			this.g[i].visited = false;
		
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
			g.addConnection(v1, v2);
		}
		//System.out.println(g.components());
		g.distancias();
		System.out.println(g.diametro());
		System.out.println(g.raio());
		ArrayList<Integer> c = g.centrais();
		ArrayList<Integer> p = g.perifericos();
		for (int i = 0; i<c.size(); i++)
		{
			System.out.print(c.get(i));
			if (i!=c.size()-1)
			{
				System.out.print(" ");
			}
		}
		System.out.println();
		for (int i = 0; i<p.size(); i++)
		{
			System.out.print(p.get(i));
			if (i!=p.size()-1)
			{
				System.out.print(" ");
			}
		}
		System.out.println();
		//g.outGraph();
		sc.close();
	}
}