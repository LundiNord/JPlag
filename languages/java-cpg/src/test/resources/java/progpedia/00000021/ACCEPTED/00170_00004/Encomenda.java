import java.util.HashMap;
import java.util.LinkedList;

import java.util.Scanner;
import java.lang.*;
import java.util.*;

class Qnode {
	int vert;
	int vertkey;

	Qnode(int v, int key) {
		vert = v;
		vertkey = key;
	}
}

class Heapmax {
	private static int posinvalida = 0;
	int sizeMax, size;

	Qnode[] a;
	int[] pos_a;

	Heapmax(int vec[], int n) {
		a = new Qnode[n + 1];
		pos_a = new int[n + 1];
		sizeMax = n;
		size = n;
		for (int i = 1; i <= n; i++) {
			a[i] = new Qnode(i, vec[i]);
			pos_a[i] = i;
		}

		for (int i = n / 2; i >= 1; i--)
			heapify(i);
	}

	int extractMax() {
		int vertv = a[1].vert;
		swap(1, size);
		pos_a[vertv] = posinvalida; // assinala vertv como removido
		size--;
		heapify(1);
		return vertv;
	}

	void increaseKey(int vertv, int newkey) {

		int i = pos_a[vertv];
		a[i].vertkey = newkey;

		while (i > 1 && compare(i, parent(i)) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	void insert(int vertv, int key) {
		if (sizeMax == size)
			new Error("Heap is full\n");

		size++;
		a[size].vert = vertv;
		pos_a[vertv] = size; // supondo 1 <= vertv <= n
		increaseKey(vertv, key); // diminui a chave e corrige posicao se
									// necessario
	}

	void write_heap() {
		System.out.printf("Max size: %d\n", sizeMax);
		System.out.printf("Current size: %d\n", size);
		System.out.printf("(Vert,Key)\n---------\n");
		for (int i = 1; i <= size; i++)
			System.out.printf("(%d,%d)\n", a[i].vert, a[i].vertkey);

		System.out.printf("-------\n(Vert,PosVert)\n---------\n");

		for (int i = 1; i <= sizeMax; i++)
			if (pos_valida(pos_a[i]))
				System.out.printf("(%d,%d)\n", i, pos_a[i]);
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	private int compare(int i, int j) {
		if (a[i].vertkey > a[j].vertkey)
			return -1;
		if (a[i].vertkey == a[j].vertkey)
			return 0;
		return 1;
	}

	private void heapify(int i) {
		int l, r, smallest;

		l = left(i);
		if (l > size)
			l = i;

		r = right(i);
		if (r > size)
			r = i;

		smallest = i;
		if (compare(l, smallest) < 0)
			smallest = l;
		if (compare(r, smallest) < 0)
			smallest = r;

		if (i != smallest) {
			swap(i, smallest);
			heapify(smallest);
		}

	}

	private void swap(int i, int j) {
		Qnode aux;
		pos_a[a[i].vert] = j;
		pos_a[a[j].vert] = i;
		aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}

	private boolean pos_valida(int i) {
		return (i >= 1 && i <= size);
	}
}

class Arco {
	int no_inicial;
	int no_final;

	int custo;

	Arco(int inicio, int fim, int c) {
		no_inicial = inicio;
		no_final = fim;
		custo = c;
	}

	int extremo_final() {
		return no_final;
	}

}

class No {
	// int label;
	LinkedList<Arco> adjs;

	No() {
		adjs = new LinkedList<Arco>();
	}
}

class Grafo {
	No verts[];
	int nvs, narcos;

	public Grafo(int n) {
		nvs = n;
		narcos = 0;
		verts = new No[n + 1];
		for (int i = 0; i <= n; i++)
			verts[i] = new No();
		// para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
	}

	public int num_vertices() {
		return nvs;
	}

	public int num_arcos() {
		return narcos;
	}

	public LinkedList<Arco> adjs_no(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int j, int c) {
		int size = verts.length;
		if (size < i + 1) {
			verts = Arrays.copyOf(verts, i + 1);
			nvs = i;
			for (int k = size; k < nvs + 1; k++)
				verts[k] = new No();

		}
		
		verts[i].adjs.addFirst(new Arco(i, j, c));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : adjs_no(i))
			if (adj.extremo_final() == j)
				return adj;
		return null;
	}
}

public class Encomenda {

	public static void main(String[] args) {
		Scanner ex = new Scanner(System.in);

		int larMin = ex.nextInt();
		int larMax = ex.nextInt();
		int compMin = ex.nextInt();
		int compMax = ex.nextInt();
		int altura = ex.nextInt();
		HashMap<Integer,Integer> nos=new HashMap<Integer,Integer>();
		int noI = ex.nextInt();
		int noF = ex.nextInt();
		noI=hash(noI,nos);
		noF=hash(noF,nos);
		boolean origem = false;
		boolean destino = false;
		Grafo g = new Grafo(2);
		
		int valorI = ex.nextInt();
		
		
		while (valorI != -1) {
			valorI=hash(valorI,nos);
			int valorF = ex.nextInt();
			valorF=hash(valorF,nos);
			int l = ex.nextInt();
			int c = ex.nextInt();
			int a = ex.nextInt();
			if (l >= larMin && c >= compMin && a >= altura) {
				g.insert_new_arc(valorI, valorF, c);
				g.insert_new_arc(valorF, valorI, c);
				if (valorI == noI || valorF == noI)
					origem = true;
				if (valorI == noF || valorF == noF)
					destino = true;
			}
			valorI = ex.nextInt();
		}
		//printhash(nos);
		if (!origem || !destino) {
			System.out.println(0);
		} else {
			int dist[] = new int[g.nvs + 1];
			dist = Dijkstra(g, noI);
			if (dist[noF] == Integer.MIN_VALUE) {
				System.out.println(0);
			} else {
				if (compMax < dist[noF])
					System.out.println(compMax);
				else
					System.out.println(dist[noF]);
			}

		}
	}

	private static void printhash(HashMap<Integer, Integer> nos) {
		System.out.println("------HASH-----");
		for(Integer a:nos.keySet())
			System.out.println(a+" "+nos.get(a));
		
	}

	private static int hash(int valorI, HashMap<Integer, Integer> nos) {
		if(!nos.containsKey(valorI)){
			int value=nos.size()+1;
			nos.put(valorI, value);
		}
		valorI=nos.get(valorI);
		return valorI;
	}

	private static int[] Dijkstra(Grafo grafo, int s) {
		boolean visitado[] = new boolean[grafo.nvs + 1];
		int dist[] = new int[grafo.nvs + 1];

		for (int i = 0; i < grafo.nvs + 1; i++) {
			dist[i] = Integer.MIN_VALUE;
			visitado[i] = false;
		}
		dist[s] = Integer.MAX_VALUE;

		Heapmax heap = new Heapmax(dist, grafo.nvs);
		while (heap.size != 0) {

			int v = heap.extractMax();
			visitado[v] = true;

			for (Arco w : grafo.adjs_no(v)) {

				if (!visitado[w.no_final]) {

					if (dist[w.no_final] < Math.min(dist[v], w.custo)) {
						dist[w.no_final] = Math.min(dist[v], w.custo);
						heap.increaseKey(w.no_final, dist[w.no_final]);

					}
				}

			}

		}
		return dist;
	}
}
