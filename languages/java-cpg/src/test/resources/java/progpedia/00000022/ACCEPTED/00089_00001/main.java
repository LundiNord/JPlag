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

	boolean isEmpty() {
		return size == 0;
	}
}

class Arco {
	int no_final;
	int larg;
	int comp;
	int alt;

	Arco(int fim, int l, int c, int a) {
		no_final = fim;
		larg = l;
		comp = c;
		alt = a;
	}

	int extremo_final() {
		return no_final;
	}

	int valor_larg() {
		return larg;
	}

	int valor_comp() {
		return comp;
	}

	int valor_alt() {
		return alt;
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

	public void insert_new_arc(int i, int j, int valor_larg, int valor_comp,
			int valor_alt) {
		verts[i].adjs.addFirst(new Arco(j, valor_larg, valor_comp, valor_alt));
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

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		int nLocais = stdin.nextInt();
		int larguraMinima = stdin.nextInt();
		int larguraMaxima = stdin.nextInt();
		int compMinimo = stdin.nextInt();
		int compMaximo = stdin.nextInt();
		int altMinima = stdin.nextInt();

		int origem = stdin.nextInt();
		int destino = stdin.nextInt();

		int ext1 = 0;
		int ext2 = 0;
		int lMaxima = 0;
		int cMaximo = 0;
		int aMaxima = 0;

		Grafo g = new Grafo(nLocais + 1);

		while ((ext1 = stdin.nextInt()) != -1) {
			ext2 = stdin.nextInt();
			lMaxima = stdin.nextInt();
			cMaximo = stdin.nextInt();
			aMaxima = stdin.nextInt();

			if (lMaxima >= larguraMinima && cMaximo >= compMinimo
					&& aMaxima >= altMinima) {
				g.insert_new_arc(ext1, ext2, lMaxima, cMaximo, aMaxima);
				g.insert_new_arc(ext2, ext1, lMaxima, cMaximo, aMaxima);

			}
		}

		System.out.println(g.num_arcos() / 2);

	}

}
