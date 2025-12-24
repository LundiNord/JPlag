import java.util.*;

class Grafo {
	Vertice verts [];
	int nverts, narcos;

	Grafo (int n) {
		verts = new Vertice[n+1];
		nverts = 0;
		narcos = 0;
	}

	void addVert (int n) {
		verts[n] = new Vertice(n);
		nverts++;
	}

	void addAdj (int a, int b, int l, int c, int al) {
		verts[a].adicionarAdj(b, l, c, al);
		narcos++;
	}
}

class Vertice {
	int val;
	Arco adjs;

	Vertice (int v) {
		val = v;
		adjs = null;
	}

	void adicionarAdj (int n, int l, int c, int a) {
		Arco aux;
		aux = new Arco(n, l, c, a, adjs);
		adjs = aux;
	}
}

class Arco {
	int proxVert, l, c, a;
	Arco prox;

	Arco (int n, int la, int co, int al, Arco ad) {
		proxVert = n;
		l = la;
		c = co;
		a = al;
		prox = ad;
	}
}

class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class HeapMax {
    private static int posinvalida = 0;
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    HeapMax (int n) {
    	a = new Qnode[n + 1];
    	pos_a = new int[n + 1];
		sizeMax = n;
		size = 0;
    }

    HeapMax(int vec[], int n) {
		a = new Qnode[n + 1];
		pos_a = new int[n + 1];
		sizeMax = n;
		size = n;
		for (int i = 1; i <= n; i++) {
		    a[i] = new Qnode(i,vec[i]);
		    pos_a[i] = i;
		}

		for (int i = n/2; i >= 1; i--)
		    heapify(i);
    }

    int extract_Max() {
		int vertv = a[1].vert;
		swap(1,size);
		pos_a[vertv] = posinvalida;  // assinala vertv como removido
		size--;
		heapify(1);
		return vertv;
    }

    boolean isEmpty() {
    	if (size == 0) return true;
    	return false;
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
		a[size] = new Qnode(vertv, key);
		pos_a[vertv] = size;   // supondo 1 <= vertv <= n
		decreaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
    }

    void write_heap() {
		System.out.printf("Max size: %d\n",sizeMax);
		System.out.printf("Current size: %d\n",size);
		System.out.printf("(Vert,Key)\n---------\n");
		for(int i=1; i <= size; i++)
		    System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
		
		System.out.printf("-------\n(Vert,PosVert)\n---------\n");

		for(int i=1; i <= sizeMax; i++)
		    if (pos_valida(pos_a[i]))
			System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }

    
    private int parent(int i) {
		return i/2;
    }
    private int left(int i) {
		return 2*i;
    }
    private int right(int i) {
		return 2*i+1;
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
		if (l > size) l = i;

		r = right(i);
		if (r > size) r = i;

		smallest = i;
		if (compare(l,smallest) < 0)
		    smallest = l;
		if (compare(r,smallest) < 0)
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

class encomenda {
	public static void main (String [] args) {
		int larMin, larMax, compMin, compMax, altMin, origem, destino, a, b, l, c, al, i;
		int d[], pred[];
		Arco adjs;
		Grafo g = new Grafo(999998);
		HeapMax queue;
		Scanner kb = new Scanner(System.in);

		larMin = kb.nextInt();
		larMax = kb.nextInt();
		compMin = kb.nextInt();
		compMax = kb.nextInt();
		altMin = kb.nextInt();

		origem = kb.nextInt();
		destino = kb.nextInt();

		a = kb.nextInt();
		while (a != -1) {
			b = kb.nextInt();
			l = kb.nextInt();
			c = kb.nextInt();
			al = kb.nextInt();

			if (g.verts[a] == null) g.addVert(a);
			if (g.verts[b] == null) g.addVert(b);
			g.addAdj(a, b, l, c, al);
			g.addAdj(b, a, l, c, al);

			a = kb.nextInt();
		}

		queue = new HeapMax(999998);
		d = new int[999999];
		pred = new int[999999];

		for (i=0; i <= 200; i++) {
			d[i] = 0;
			pred[i] = -1;
		}

		d[origem] = compMax;
		for (i=1; i < 999999; i++) {
			if (g.verts[i] != null) {
				queue.insert(i, d[i]);
			}
		}

		while (queue.isEmpty() == false) {
			a = queue.extract_Max();
			adjs = g.verts[a].adjs;
			while (adjs != null) {

				if (adjs.l >= larMin && adjs.l <= larMax && adjs.a >= altMin && adjs.c >= compMin && adjs.c <= compMax) {
					if (adjs.c >= d[a]) {
						d[adjs.proxVert] = d[a];
						if (queue.pos_a[adjs.proxVert] != 0) queue.decreaseKey(adjs.proxVert, d[adjs.proxVert]);
						pred[adjs.proxVert] = a;
					} else if (adjs.c < d[a]) {
						d[adjs.proxVert] = adjs.c;
						if (queue.pos_a[adjs.proxVert] != 0) queue.decreaseKey(adjs.proxVert, d[adjs.proxVert]);
						pred[adjs.proxVert] = a;
					}
				}

				adjs = adjs.prox;
			}
		}

		System.out.println(d[destino]);
	}
}