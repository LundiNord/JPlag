import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
	int n;
	Node nodes[];

	Graph(int n) {
		this.n = n;
		nodes = new Node[n + 1]; // +1 se nos comecam em 1 ao inves de 0
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].dist = 0;

		int distMax = 0;
		int d[] = new int[n], i = 1;
		while (q.size() > 0) {
			int u = q.removeFirst();

			// diametro: maior distancia
			if (nodes[u].dist > distMax) 
				distMax = nodes[u].dist;
			
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited = true;
					nodes[w].dist = nodes[u].dist + 1;
				}

		}
		System.out.println(distMax);
	}
}

class Node {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public boolean visited; // Valor booleano que indica se foi visitao numa
							// pesquisa
	public int dist; // Distancia do no origem da pesquisa

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
		g.bfs(1);

	}
}
