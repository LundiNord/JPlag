import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
	int n;
	Node nodes[];
	int excen[][];
	
	Graph(int n) {
		this.n = n;
		
		nodes = new Node[n + 1]; // +1 se nos comecam em 1 ao inves de 0
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node();
		
		excen = new int[n+1][n+1];
		for(int i = 1; i <=n; i++)
			for(int j = 1; j <= n; j++)
				excen[i][j] = 0;
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public void setNotVisited() {
		for (int i = 1; i <= n; i++)
			nodes[i].visited = false;
	}

	public int getRadius() {
		int distMin = n;
		for (int i = 1; i <= n; i++)
			if (nodes[i].excen < distMin)
				distMin = nodes[i].excen;

		return distMin;
	}

	public int getDiameter() {
		int distMax = -1;
		for (int i = 1; i <= n; i++)
			if (nodes[i].excen > distMax)
				distMax = nodes[i].excen;

		return distMax;
	}

	public void printCentralNodes() {
		int radius = getRadius();
		boolean first = true;

		for (int i = 1; i <= n; i++) {
			if (nodes[i].excen == radius && first) {
				System.out.print(i);
				first = false;
			} else if (nodes[i].excen == radius && !first)
				System.out.print(" " + i);
		}
		System.out.println();
	}

	public void printPerifericalNodes() {
		int diameter = getDiameter();
		boolean first = true;
		int e = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (excen[i][j] == diameter && first && e!=i) {
					System.out.print(i);
					e = i;
					first = false;
				} else if (excen[i][j] == diameter && !first && e!=i){
					System.out.print(" " + i);
					e = i;
				}
			}
		}
		System.out.println();
	}

	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].dist = 0;

		int distMax = 0;
		int  k = v;
		while (q.size() > 0) {
			int u = q.removeFirst();
			//System.out.println(u + " [dist=" + nodes[u].dist + "]");
			
			excen[k][u]= nodes[u].dist;
			
			if (nodes[u].dist > distMax)
				distMax = nodes[u].dist;

			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited = true;
					nodes[w].dist = nodes[u].dist + 1;
				}
		
		}
		
		nodes[v].excen = distMax;
		/*imprimir matriz com excentricidades
		 * for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				System.out.print(excen[i][j]);
			}
			System.out.println();
		}*/
	}
}

class Node {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public boolean visited; // indica se foi visitado numa pesquisa
	public int dist; // Distancia do no origem da pesquisa
	public int excen; // Excentricidade do no (maior dist min)

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		dist = -1;
	}
}

public class Prob19 {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		Graph g = new Graph(n);

		int e = stdin.nextInt();

		for (int i = 1; i <= e; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();

			g.addLink(a, b);
		}

		for (int i = 1; i <= n; i++) {
			g.bfs(i);
			g.setNotVisited();
		}

		System.out.println(g.getDiameter());
		System.out.println(g.getRadius());
		g.printCentralNodes();
		g.printPerifericalNodes();
	}
}