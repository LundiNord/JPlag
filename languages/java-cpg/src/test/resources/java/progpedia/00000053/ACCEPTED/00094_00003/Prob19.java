import java.io.*;
import java.util.*;

//Classe que representa um no
class Node {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public boolean visited; // Valor booleano que indica se foi visitao numa
							// pesquisa
	public int distance; // Distancia do no origem da pesquisa

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	}
}

// Classe que representa um grafo
class Graph {
	int n; // Numero de nos do grafo
	Node nodes[]; // Array para conter os nos
	int excentricidade[]; // maior distancia de um vertice v a qualquer outo
							// vertice

	Graph(int n) {
		this.n = n;
		nodes = new Node[n + 1]; // +1 se os comecam em 1 ao inves de 0
		excentricidade = new int[n + 1];
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	// Algoritmo de pesquisa em largura
	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		excentricidade[v] = -1;
		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;
		//System.out.println(excentricidade[v]);
		while (q.size() > 0) {
			int u = q.removeFirst();
			// System.out.println(u + " [dist=" + nodes[u].distance + "]");
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited = true;
					nodes[w].distance = nodes[u].distance + 1;

				}
		}
		// calcular a distancia maior
		for (int w = 1; w <= n; w++)
			if (nodes[w].distance > excentricidade[v]){
				excentricidade[v] = nodes[w].distance;
				
			}
		//System.out.println(excentricidade[v] + " " + v);
	}
}

public class Prob19 {

	static int diametro = 0;
	static int raio = Integer.MAX_VALUE;
	static int noCentral;
	static int noPeriferico;
	static int e;
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		Graph g = new Graph(stdin.nextInt());
		e = stdin.nextInt();
		for (int i = 0; i < e; i++)
			g.addLink(stdin.nextInt(), stdin.nextInt());

		// Pesquisa em largura a partir do no 1
		for (int i = 1; i <= g.n; i++) {
			g.bfs(i);
			for (int w = 1; w <= g.n; w++)
				g.nodes[w].visited = false;
		}

		// calcular o raio e o diamentro
		// Diametro de um grafo: distancia maxima entre dois nos de um grafo
		for (int i = 1; i <= g.n; i++) {
			if (g.excentricidade[i] > diametro)
				diametro = g.excentricidade[i];
			if (g.excentricidade[i] < raio)
				raio = g.excentricidade[i];
			

		}
		System.out.println(diametro);
		System.out.println(raio);

		// calcular nos centrais
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

		// calcular nos perifericos
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
