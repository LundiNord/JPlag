import java.util.*;

class Edge {
	int outro;
	int rt;// rendimento

	Edge(int o, int r) {
		outro = o;
		rt = r;

	}
}

class Node {
	LinkedList<Edge> adj;
	boolean visitado;

	Node() {
		visitado = false;
		adj = new LinkedList<Edge>();
	}
}

class Qnode {
	int ver;
	int verkey;

	Qnode(int v, int k) {
		ver = v;
		verkey = k;
	}
}

class Heapmin {
	int size;
	int sizemax;
	Qnode[] conj;
	int[] pos;

	Heapmin(int vec[], int n) {

		size = n;
		sizemax = n;
		pos = new int[n + 1];
		conj = new Qnode[n + 1];

		for (int i = 1; i < n + 1; i++) {
			conj[i] = new Qnode(i, vec[i]);
			pos[i] = i;

		}
		/*
		 * for (int i = 1; i < n + 1; i++) { System.out.println(i + " " +
		 * conj[i].ver + " " + conj[i].verkey); } System.out.println("------");
		 * for (int i = 0; i < n; i++) { System.out.println(i + " " + pos[i]); }
		 * System.out.println("------");
		 */
		for (int i = n / 2; i >= 1; i--)
			heapify(i);

	}

	public boolean isvazia() {
		if (size == 0)
			return true;
		return false;
	}

	void decreKey(int v, int key) {

		int i = pos[v];
		conj[i].verkey = key;

		while (i > 1 && compare(i, i / 2) < 0) { // i/2 pai
			swap(i, i / 2);
			i = i / 2;
		}

		/*
		 * System.out.println("::::"); for(int j=1;j<size+1;j++){
		 * System.out.println(j+" "+conj[j].ver+" "+conj[j].verkey);
		 * 
		 * } for(int j=1;j<pos.length;j++){
		 * 
		 * System.out.println("v:"+j+"p:"+pos[j]); } System.out.println("::::");
		 */
	}

	public int compare(int i, int j) {
		// System.out.println(i + " " + j);
		if (conj[i].verkey < conj[j].verkey)
			return -1;
		if (conj[i].verkey == conj[j].verkey)
			return 0;
		return 1;
	}

	void swap(int i, int j) {
		Qnode aux;
		pos[conj[i].ver] = j;
		pos[conj[j].ver] = i;
		aux = conj[i];
		conj[i] = conj[j];
		conj[j] = aux;

	}

	public int extramin() {
		// System.out.println("::"+conj[1].ver+" "+conj[1].verkey);
		// System.out.println("::"+conj[2].ver+" "+conj[2].verkey);

		int v = conj[1].ver;
		swap(1, size);
		size--;
		heapify(1);
		return v;
	}

	public void heapify(int i) {
		int l, r, smallest;

		l = 2 * i;
		if (l > size)
			l = i;

		r = (2 * i) + 1;

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

}

class myGraph {
	final int V;
	int E;
	Node[] nos;

	myGraph(int v) {
		V = v;
		E = 0;
		nos = new Node[V + 1];

		for (int i = 0; i < V + 1; i++) {
			nos[i] = new Node();
		}

	}

	public void addEdge(int v, int w, int r) {
		nos[v].adj.addFirst(new Edge(w, r));
		nos[w].adj.addFirst(new Edge(v, r));
		E++;
	}

	public void txtgraph() {
		System.out.println("vert: " + V + " " + "edge: " + E);
		for (int v = 1; v < V; v++) {
			System.out.print(v + ": ");
			for (Edge e : nos[v].adj) {
				System.out.print(e.outro + " c: " + e.rt + "|");
			}
			System.out.println();
		}
	}

	public void Prim() {
		int key[] = new int[V + 1];
		int color[] = new int[V + 1];
		int root;
		int aux = 0;
		int aux2 = 0;
		int rendimento = 0;
		int result = Integer.MIN_VALUE;
		//outerloop: for (int j = 1; j < V + 1; j++) {
			root = 1;
			aux = 0;
			for (int i = 1; i < V + 1; i++) {
				key[i] = Integer.MAX_VALUE;
				color[i] = 1; // 1 white
			}
			key[root] = 0;
			Heapmin H = new Heapmin(key, V);
			int u;
			rendimento = 0;
			while (!H.isvazia()) {
				u = H.extramin();
				for (Edge e : nos[u].adj) {
					if ((color[e.outro] == 1) && (e.rt < key[e.outro])) {
						key[e.outro] = e.rt;
						H.decreKey(e.outro, key[e.outro]);
						rendimento = rendimento + e.rt;
					}
				}
				color[u] = 2;
			}
			//if (aux2 == 0)
				
				for (int i = 1; i < key.length; i++) {
					if (key[i] == Integer.MAX_VALUE) {
						aux = 1;
						//break outerloop;

					}
					//aux2 = 1;
				}
			if (rendimento > result){
				result = rendimento;

			}

		//}
		if (aux == 0)
			System.out.println("rendimento optimo: " + rendimento);
		else
			System.out.println("impossivel");

		/*
		 * int aux = 0; for (int i = 1; i < key.length; i++) if (key[i] ==
		 * Integer.MAX_VALUE) { aux = 1;
		 * 
		 * }
		 * 
		 * 
		 * 
		 * if (aux == 0) System.out.println("rendimento optimo: " + rendimento);
		 * else System.out.println("impossivel");
		 */

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n, nl, custo, a, b, rend;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		nl = in.nextInt();
		custo = in.nextInt();
		myGraph G = new myGraph(n);
		for (int i = 0; i < nl; i++) {
			a = in.nextInt();
			b = in.nextInt();
			rend = in.nextInt();
			rend = rend - custo;
			G.addEdge(a, b, rend);
			// inserir no
		}
		// G.txtgraph();

		G.Prim();

	}
}