import java.util.*;

//Encomenda runtime error why?
// ASK APT
class Edgenova {

	int outro;
	int largura;
	int comprimento;
	int altura;

	Edgenova(int w, int l, int c, int h) {
		outro = w;
		largura = l;
		comprimento = c;
		altura = h;
	}
}

class Node {
	LinkedList<Edgenova> adj;
	boolean visitado;

	Node() {
		visitado = false;
		adj = new LinkedList<Edgenova>();
	}

}

class InputNode {
	int side1;
	int side2;
	int largura;
	int comprimento;
	int altura;

	InputNode(int a, int b, int l, int c, int h) {
		side1 = a;
		side2 = b;
		largura = l;
		comprimento = c;
		altura = h;
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

class Graph { // Graph
	private final int V;
	private int E;
	private Node[] nos;

	/**
	 * Create an empty graph with V vertices.
	 */
	public Graph(int V) {
		if (V < 0)
			throw new RuntimeException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		nos = new Node[V + 1];

		for (int i = 1; i < V + 1; i++) {
			nos[i] = new Node();
		}

	}

	/**
	 * Copy constructor.
	 * 
	 * public EdgeWeightedGraph(EdgeWeightedGraph G) { this(G.V()); this.E =
	 * G.E(); for (int v = 1; v < G.V(); v++) { // reverse so that adjacency
	 * list is in same order as original Stack<Edge> reverse = new
	 * Stack<Edge>(); for (Edge e : G.adj[v]) { reverse.push(e); } for (Edge e :
	 * reverse) { adj[v].add(e); } } }
	 */

	/**
	 * Return the number of vertices in this graph.
	 */
	public int V() {
		return V;
	}

	/**
	 * Return the number of edges in this graph.
	 */
	public int E() {
		return E;
	}

	/**
	 * Add the undirected edge e to this graph.
	 */
	public void addEdge(int v, int w, int l, int c, int h) {

		nos[v].adj.addFirst(new Edgenova(w, l, c, h));
		nos[w].adj.addFirst(new Edgenova(v, l, c, h));
		E++;
	}

	/**
	 * Return a string representation of this graph.
	 */
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V - 1 + " " + E + NEWLINE);
		for (int v = 1; v < V; v++) {
			s.append(v + ": ");
			for (Edgenova e : nos[v].adj) {
				s.append(e.outro + " l: " + e.largura + " c: " + e.comprimento
						+ " h: " + e.altura);
				s.append(" | ");
			}

			s.append(NEWLINE);
		}
		return s.toString();
	}

	/*
	 * public void dosmth(int aux[], int lmax) {
	 * 
	 * boolean existe=false; int sol = lmax; for (int i = 0; i < aux.length - 1;
	 * i++) { existe = false; for (Edgenova e : nos[aux[i]].adj) {
	 * 
	 * if (e.outro == aux[i + 1]) {
	 * 
	 * existe = true; if (sol > e.largura) sol = e.largura; } }
	 * if(existe==false) break; } if (existe == false)
	 * System.out.println("Nao"); else System.out.println(sol);
	 * 
	 * }
	 */

	public void dijsk(int s, int f, int cmax) {
		int dist[] = new int[V];
		int pred[] = new int[V];

		for (int i = 1; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = 0;
		}

		dist[s] = -cmax;

		Heapmin H = new Heapmin(dist, V - 1);

		int u;

		while (!H.isvazia()) {
			u = H.extramin();
			// System.out.print(u+" ");
			if (dist[u] < Integer.MAX_VALUE)
				for (Edgenova e : nos[u].adj) {
					if (dist[e.outro] > Math.max(dist[u], e.comprimento)) { // relax
						dist[e.outro] = Math.max(dist[u], e.comprimento);
						pred[e.outro] = u;
						H.decreKey(e.outro, dist[e.outro]);

					}

				}
			// for (int i = 1; i < V; i++)
			// System.out.println(i+" "+dist[i]);
			// System.out.println(".............");
		}

		if (dist[f] == Integer.MAX_VALUE)
			System.out.println("0");
		else
			System.out.println(-dist[f]);

	}

	

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int nv;
		
		
		
		int lmin, lmax, cmin, cmax, altura;
		Vector<Integer> myalgo = new Vector<Integer>();

		lmin = in.nextInt();
		lmax = in.nextInt();
		cmin = in.nextInt();
		cmax = in.nextInt();
		altura = in.nextInt();
		int start = in.nextInt();
		int fim = in.nextInt();
				
		int a = in.nextInt();
		
		myalgo.add(a);
		
		LinkedList<InputNode> mylist = new LinkedList<InputNode>();
		int b, lar, com, alt;
		// Graph G = new Graph(nv + 1);
		while (a != -1) {

			b = in.nextInt();
			if (!myalgo.contains(b))
				myalgo.add(b);
			lar = in.nextInt();
			com = in.nextInt();
			alt = in.nextInt();
			if (lar >= lmin && com >= cmin && alt >= altura)
				mylist.addLast(new InputNode(a, b, lar, com, alt));
			// G.addEdge(a, b, -lar, -com, -alt);
			a = in.nextInt();
			if (!myalgo.contains(a))
				myalgo.add(a);
		}
		
		
		nv = myalgo.size();
		//System.out.println(myalgo.indexOf(1));
		Graph G = new Graph(nv + 1);
		
		for (InputNode inode : mylist){
			G.addEdge(myalgo.indexOf(inode.side1)+1, myalgo.indexOf(inode.side2)+1, -inode.largura, -inode.comprimento,-inode.altura);
		}
		
		//System.out.println(nv);
	
		//System.out.println(myalgo.toString());
		
	//	System.out.println(G);
		
		
		
		G.dijsk(myalgo.indexOf(start)+1, myalgo.indexOf(fim)+1, cmax);
	}

}