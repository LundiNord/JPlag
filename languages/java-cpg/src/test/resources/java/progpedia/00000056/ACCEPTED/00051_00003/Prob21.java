import java.io.*;
import java.util.*;
//Codigo feito em conjunto com Carlos Ribeiro

//Classe que representa um no
class Node1 {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public double distance; // Distancia de um ponto(x,y) a outro
	public double x;
	public double y;
	public int noPai;

	Node1(double x, double y) {
		adj = new LinkedList<Integer>();
		distance = 0.0;
		noPai = 0;
		this.x = x;
		this.y = y;
	}
}

// Classe que representa um grafo
class Grap {
	int n; // Numero de nos do grafo
	Node1 nodes[]; // Array para conter os nos

	Grap(int n) {
		this.n = n;
		nodes = new Node1[n + 1]; // +1 se os comecam em 1 ao inves de 0

	}

	// calcular distancia
	public double distancia(int x, int y) {
		return Math
				.sqrt(((nodes[x].x - nodes[y].x) * (nodes[x].x - nodes[y].x))
						+ ((nodes[x].y - nodes[y].y) * (nodes[x].y - nodes[y].y)));
	}

	// algoritmo de Prim
	public void Prim(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			nodes[i].distance = Double.MAX_VALUE;
			q.add(i);
		}
		nodes[v].distance = 0;
		double minimo = Double.MAX_VALUE;
		int z = 0;
		int menor = 0;
		while (q.size() > 0) {
			minimo = Double.MAX_VALUE;
			for (int i = 1; i <= n; i++)
				if (q.contains(i) && nodes[i].distance < minimo) {
					minimo = nodes[i].distance;
					menor = i;
				}

			q.removeFirstOccurrence(menor);
			z = menor;

			for (int w : nodes[z].adj) {
				double dist = distancia(z, w);
				if (q.contains(w) && dist < nodes[w].distance) {
					nodes[w].noPai = z;
					nodes[w].distance = dist;
				}

			}

		}

	}
}

public class Prob21 {
	static int n;// numero de sardas
	static double dis = 0.0;
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		Grap g = new Grap(n);
		for (int i = 1; i <= n; i++) {
			g.nodes[i] = new Node1(stdin.nextDouble(), stdin.nextDouble());
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				g.nodes[i].adj.add(j);//adiciono o no a lista de adjacentes
			}
		}
		//fazer a pesquisa em lasrgura a partir do primeiro no
		g.Prim(1);
		for(int i = 1; i <= n; i++)
			dis += g.nodes[i].distance;
		System.out.printf( "%.2f", dis );
	}
}
