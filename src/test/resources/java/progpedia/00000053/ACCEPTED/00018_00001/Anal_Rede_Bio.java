import java.util.*;

class grafo {
	List<Integer> adj;
	boolean Vis;
	int distancia;
	int ex;

	grafo() {
		adj = new LinkedList<Integer>();
		Vis = false;
		distancia = ex = 0;

	}
}

public class Anal_Rede_Bio {

	public static void bfs(grafo g[], int i) {
		LinkedList<grafo> q = new LinkedList<grafo>();
		q.add(g[i]);
		g[i].Vis = true;
		while (!q.isEmpty()) {
			grafo u = q.remove();
			for (int c : u.adj) {
				int aux = c;
				if (!g[aux].Vis) {
					q.add(g[aux]);
					g[aux].Vis = true;
					g[aux].distancia = u.distancia + 1;
					if (g[aux].ex < g[aux].distancia)
						g[aux].ex = g[aux].distancia;
				}
			}

		}
		for (int j = 0; j < g.length; j++) {
			g[j].Vis = false;
			g[j].distancia = 0;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int raio = n - 1;
		int diametro = 0;
		LinkedList<Integer> No_central = new LinkedList<Integer>();
		LinkedList<Integer> No_periferico = new LinkedList<Integer>();
		grafo g[] = new grafo[n];
		for (int i = 0; i < n; i++) {
			g[i] = new grafo();
		}
		int l = in.nextInt();
		for (int i = 0; i < l; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			g[a - 1].adj.add(b - 1);
			g[b - 1].adj.add(a - 1);
		}
		for (int i = 0; i < n; i++)
			bfs(g, i);
		for (int i = 0; i < n; i++) {
			if (g[i].ex < raio)
				raio = g[i].ex;
			if (g[i].ex > diametro)
				diametro = g[i].ex;
		}
		for (int i = 0; i < n; i++) {
			if (g[i].ex == raio)
				No_central.add((i + 1));
			if (g[i].ex == diametro)
				No_periferico.add((i + 1));
		}
		System.out.println(diametro);
		System.out.println(raio);
		while (!No_central.isEmpty()) {
			if (No_central.size() == 1)
				System.out.println(No_central.remove());
			else
				System.out.print(No_central.remove() + " ");
		}
		while (!No_periferico.isEmpty()) {
			if (No_periferico.size() == 1)
				System.out.println(No_periferico.remove());
			else
				System.out.print(No_periferico.remove() + " ");
		}

	}
}