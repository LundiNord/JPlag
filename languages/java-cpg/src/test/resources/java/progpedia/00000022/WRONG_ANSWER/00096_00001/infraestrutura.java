import java.util.*;

class Grafo {
	Vert verts[];

	Grafo (int n) {
		int i;
		verts = new Vert[n+1];
		for (i=0; i <= n; i++) verts[i] = new Vert(i);
	}

	void addAdj (int x, int y, int l, int c, int a) {
		verts[x].adicionarAdj(y, l, c, a);
	} 
}

class Vert {
	int label;
	Arco adjs;

	Vert (int n) {
		label = n;
		adjs = null;
	}

	void adicionarAdj (int x, int l, int c, int a) {
		Arco aux;
		aux = new Arco(adjs, x, l, c, a);
		adjs = aux;
	}
}

class Arco {
	int proxVert, l, c, a;
	Arco prox;

	Arco (Arco x, int p, int la, int co, int al) {
		proxVert = p;
		l = la;
		c = co;
		a = al;
		prox = x;
	}
}

class Lista {
	Node first;
	int length;

	Lista () {
		length = 0;
		first = null;
	}

	boolean isEmpty () {
		if (length == 0) return true;
		return false;
	} 

	void addNode (int n) {
		Node aux;
		if (length == 0) {
			first = new Node(n, null);
		} else {
			aux = new Node(n, first);
			first = aux;
		}
		length++;
	}

	int removeFirst () {
		int n;
		n = first.val;
		first = first.prox;
		length--;
		return n;
	}

	class Node {
		int val;
		Node prox;

		Node (int v, Node p) {
			val = v;
			prox = p;
		}
	}
}

class infraestrutura {
	static int depthFirstSearch (Grafo g, int origem, int destino, int nverts, int larMin, int larMax, int compMin, int compMax, int altMin) {
		Lista stack = new Lista();
		Arco adjs;
		int a, i;
		int lidos[] = new int[nverts+1];

		for (i=0; i <= nverts; i++) lidos[i] = 0;

		a = 0;
		for (i=1; i <= nverts; i++) {
			adjs = g.verts[i].adjs;
			while (adjs != null) {
				if (adjs.l >= larMin && adjs.l <= larMax && adjs.c >= compMin && adjs.c <= compMax && adjs.a >= altMin) a++;
				adjs = adjs.prox;
			}
		}

		return a;
	}

	public static void main (String [] args) {
		int nverts, larMin, larMax, compMin, compMax, altMin, origem, destino, a, b, l, c, al, cont;
		Scanner kb = new Scanner(System.in);
		Grafo g;

		nverts = kb.nextInt();
		g = new Grafo(nverts);

		larMin = kb.nextInt();
		larMax = kb.nextInt();
		compMin = kb.nextInt();
		compMax = kb.nextInt();
		altMin = kb.nextInt();

		origem = kb.nextInt();
		destino = kb.nextInt();

		cont = 0;
		a = kb.nextInt();
		while (a != -1) {
			b = kb.nextInt();
			l = kb.nextInt();
			c = kb.nextInt();
			al = kb.nextInt();

			g.addAdj(a, b, l, c, al);
			g.addAdj(b, a, l, c, al);

			if (larMin <= l && larMax >= l && compMin <= c && compMax >= c && altMin <= al) {
				cont += 2;
				System.out.println("(" + l + ", " + c + ", " + al + ")");
			}

			a = kb.nextInt();
		}

		//cont = depthFirstSearch(g, origem, destino, nverts, larMin, larMax, compMin, compMax, altMin);
		System.out.println(cont);
	}
}