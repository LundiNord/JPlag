import java.io.*;
import java.util.*;

//feito com ajuda

class Node {
	public LinkedList<Integer> adj; //adjacencias
	public boolean visited;						
	public int distance;

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	}
}


class Graph {
	int n;
	Node nodes[]; 
	int excentricidade[]; 

	Graph(int n) {
		this.n = n;
		nodes = new Node[n + 1];
		excentricidade = new int[n + 1];
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}


	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		excentricidade[v] = -1;
		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;
		while (q.size() > 0) {
			int u = q.removeFirst();
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited = true;
					nodes[w].distance = nodes[u].distance + 1;

				}
		}

		for (int w = 1; w <= n; w++)
			if (nodes[w].distance > excentricidade[v]){
				excentricidade[v] = nodes[w].distance;
				
			}

	}
}

public class Rede {

	static int diametro = 0;
	static int raio = Integer.MAX_VALUE;
	static int noCentral;
	static int noPeriferico;
	static int e;
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Graph g = new Graph(in.nextInt());
		e = in.nextInt();
		for (int i = 0; i < e; i++)
			g.addLink(in.nextInt(), in.nextInt());

		for (int i = 1; i <= g.n; i++) {
			g.bfs(i);
			for (int w = 1; w <= g.n; w++)
				g.nodes[w].visited = false;
		}

		for (int i = 1; i <= g.n; i++) {
			if (g.excentricidade[i] > diametro)
				diametro = g.excentricidade[i];
			if (g.excentricidade[i] < raio)
				raio = g.excentricidade[i];
			

		}
		System.out.println(diametro);
		System.out.println(raio);

		noCentral = 0;
		for (int i = 1; i <= g.n; i++) {
			if (g.excentricidade[i] == raio) {
				if (noCentral == 1)
					System.out.print(" ");
				noCentral = 1;
				System.out.print(i);
			}
		}
		System.out.println();

		for (int i = 1; i <= g.n; i++) {
			if (g.excentricidade[i] == diametro) {
				if (noPeriferico == 1)
					System.out.print(" ");
				noPeriferico = 1;
				System.out.print(i);
			}
		}
		System.out.println();
	}
}