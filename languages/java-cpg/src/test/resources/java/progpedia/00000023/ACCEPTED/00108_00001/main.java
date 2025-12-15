import java.util.Scanner;
import java.io.ObjectInputStream.GetField;
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
		if (a[i].vertkey == a[j].vertkey) {
			if (a[i].vert < a[j].vert)
				return -1;
			if (a[i].vert == a[j].vert)
				return 1;
			return 0;
		}
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
		return (size == 0);
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
	// int label;
	LinkedList<Arco> adjs;
	boolean visited;
	int pred;
	int distancia;

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

/*
 * FILA DE PRIORIDADES {n1:v1, n2:v2,...} INSERE(F,o:o_melhor) fn!=0
 * INSERE(F,n:o_pior) while(F!=0) n<-retira_o_melhor(F) para todo a € Adj(n) , a
 * € F RELAX(n,a);
 * 
 * 
 * 
 * RELAX(n,a) v = EST(n); v'=arco(n,a).valor HIP=COMP(v,v') H_MELHOR(HIP,EST(a))
 * ALTERA_EST(a,HIP)
 */
public class main {
	static void dijkstra(Grafo g, int s) {
		int numVertices = g.num_vertices();
		int dist[] = new int[numVertices + 1];
		for (int i = 1; i <= numVertices; i++) {
			dist[i] = Integer.MAX_VALUE;
			g.verts[i].visited = false;
		}

		int v = 0, w = 0, dvw = 0, index = 0;
		dist[s] = 0;

		Heapmin Q = new Heapmin(dist, numVertices);
		while (!Q.isEmpty()) {
			v = Q.extractMin();
			if (dist[v] != Integer.MAX_VALUE) {
				for (Arco arc : g.adjs_no(v)) {
					w = arc.extremo_final();
					dvw = arc.valor_arco();
					if (dist[v] + dvw < dist[w]) {
						dist[w] = dist[v] + dvw;
						Q.decreaseKey(w, dist[w]);
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

		System.out.println();

	}

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int nRegioes;
		int regiaoDestino;
		int reg1, reg2 = 0, distReg = 0;
		nRegioes = stdin.nextInt();

		Grafo g = new Grafo(nRegioes);
		regiaoDestino = stdin.nextInt();

		while ((reg1 = stdin.nextInt()) != -1) {

			reg2 = stdin.nextInt();
			distReg = stdin.nextInt();
			g.insert_new_arc(reg1, reg2, distReg);
			g.insert_new_arc(reg2, reg1, distReg);

		}

		dijkstra(g, regiaoDestino);

	}

}
