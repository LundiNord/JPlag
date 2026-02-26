/*-----------------------------------------------------------------------*\
|  Exemplo de implementacao de fila de prioridade (por heap de minimo)    |
|                                                                         |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012        |
|   Last modified: 2013.01.02                                             |
\*-----------------------------------------------------------------------*/

import java.util.LinkedList;
import java.util.Scanner;

class Qnode {
	int vert;
	int vertkey;

	Qnode(int v, int key) {
		vert = v;
		vertkey = key;
	}
}

class Heapmin {
	private static int posinvalida = 0;
	int sizeMax, size;

	Qnode[] a;
	int[] pos_a;

	Heapmin(int vec[], int n) {
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

	int extractMin() {
		int vertv = a[1].vert;
		swap(1, size);
		pos_a[vertv] = posinvalida; // assinala vertv como removido
		size--;
		heapify(1);
		return vertv;
	}

	void decreaseKey(int vertv, int newkey) {

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
		decreaseKey(vertv, key); // diminui a chave e corrige posicao se
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
		if (a[i].vertkey < a[j].vertkey)
			return -1;
		if (a[i].vertkey == a[j].vertkey)
			if (a[i].vert > a[j].vert)
				return 1;
			else
				return -1;
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

	public boolean isEmpty() {
		return size == 0;
	}
}

class Arco {
	int no_final;
	int valor;

	Arco(int fim, int v) {
		no_final = fim;
		valor = v;
	}

	int extremo_final() {
		return no_final;
	}

	int valor_arco() {
		return valor;
	}
}

class No {
	int visitado = 0;
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

	public void insert_new_arc(int i, int j, int valor_ij) {
		verts[i].adjs.addFirst(new Arco(j, valor_ij));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : adjs_no(i))
			if (adj.extremo_final() == j)
				return adj;
		return null;
	}
}

public class main {

	public static void Dijkstra(Grafo g, int t) {
		int numVertices = g.num_vertices();
		int dist[] = new int[numVertices + 1];
		for (int i = 1; i <= numVertices; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[t] = 0;
		Heapmin q = new Heapmin(dist, numVertices-1);

		int v = 0, w = 0, dvw = 0;

		int index = 0;

		while (!q.isEmpty()) {
			v = q.extractMin();
			if (dist[v] != Integer.MAX_VALUE) {
				for (Arco a : g.adjs_no(v)) {
					w = a.extremo_final();
					dvw = a.valor_arco();
					if (dist[w] > dist[v] + dvw) {
						dist[w] = dist[v] + dvw;
						q.decreaseKey(w, dist[w]);
					}
				}
			}
			if (index == 0) {
				System.out.print(v);
			} else {
				System.out.print(" " + v);
			}
			index++;
		}
	}

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		int nRegioes = stdin.nextInt();
		int destino = stdin.nextInt();

		int reg1 = 0;
		int reg2 = 0;
		int dist = 0;

		Grafo g = new Grafo(nRegioes + 1);

		while ((reg1 = stdin.nextInt()) != -1) {
			reg2 = stdin.nextInt();
			dist = stdin.nextInt();

			g.insert_new_arc(reg1, reg2, dist);
			g.insert_new_arc(reg2, reg1, dist);
		}
		Dijkstra(g, destino);
		System.out.println();
	}
}
