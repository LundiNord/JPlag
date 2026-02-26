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
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmax(int vec[], int n) {
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

    int extractMin() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
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


    void insert(int vertv, int key)
    { 
	if (sizeMax == size)
	    new Error("Heap is full\n");
	
	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	decreaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
    }

    void write_heap(){
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
    
    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
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


class Edge {
    int extremo;
    int comprimento,altura,largura;
    
    Edge(int fim, int c, int l, int a){
	extremo = fim;
	comprimento = c;
	altura = a;
	largura = l;
    }
}


class Node {
    //int label;
    LinkedList<Edge> edges = new LinkedList<Edge>();
}


class Graph {
    

    int comprimentoMax, comprimentoMin, larguraMax, larguraMin, alturaMin,origem, destino;
    Vector<Node> graph;
    Vector<Integer> bijecao;
    int nNodes;
  
    Graph(int cMax, int cMin, int lMax, int lMin, int aMin,int inicio, int fim) {
	graph = new Vector<Node>();
	graph.add(null);
	bijecao = new Vector<Integer>();
	bijecao.add(null);
	nNodes = 0;
	comprimentoMax = cMax;
	comprimentoMin = cMin;
	larguraMax = lMax;
	larguraMin = lMin;
	alturaMin = aMin;
	origem = inicio;
	destino = fim;
    }
    
    void addEdge(int origin, int dest, int l, int c, int a) {
	Edge e;
	Node o = new Node();
	int indOri, indDest;
	
	indOri = bijecao.indexOf(origin);
	if (indOri == -1) {
	    graph.add(o);
	    bijecao.add(origin);
	    nNodes++;
	    indOri = nNodes;
	}
	indDest = bijecao.indexOf(dest);
	if (indDest == -1) {
	    graph.add(o);
	    bijecao.add(dest);
	    nNodes++;
	    indDest = nNodes;
	}
	if (c>=comprimentoMin && l>=larguraMin && a>=alturaMin && c<=comprimentoMax && l<=larguraMax) {
	    e = new Edge(indDest, Math.min(c,comprimentoMax),Math.min(l,larguraMax),a);
	    graph.elementAt(indOri).edges.addFirst(e);
	}
    }
    
    void lerGrafo(Scanner in) {
	int inicio, fim, largura, comprimento, altura;
	inicio = in.nextInt();
	while (inicio != -1) {
	    fim = in.nextInt();
	    largura = in.nextInt();
	    comprimento = in.nextInt();
	    altura = in.nextInt();
	    addEdge(inicio,fim,largura,comprimento,altura);
	    addEdge(fim,inicio,largura,comprimento,altura);
	    inicio = in.nextInt();
	}
	origem = bijecao.indexOf(origem);
	destino = bijecao.indexOf(destino);
    }
    
    void mostrarGrafo() {
	for (int i = 1; i<=nNodes; i++) {
	    System.out.printf("No: %d\n",i);
	    for (Edge e: graph.elementAt(i).edges) 
		System.out.printf("%d ",e.extremo);
	    System.out.printf("\n");
	}
    }
    
    void calcularComprimentoMaximo() {
	int visitados[] = new int[nNodes+1];
	int aux;
	
	for (int i=1; i<nNodes; i++)
	    visitados[i] = 0;
	
	visitados[origem] = comprimentoMax;
	
	Heapmax heap = new Heapmax(visitados,nNodes);
	aux = heap.extractMin();
	
	while (aux != destino) {
	    for (Edge e: graph.elementAt(aux).edges) {
		if (visitados[e.extremo] < Math.min(visitados[aux], e.comprimento)) {
		    visitados[e.extremo] = Math.min(visitados[aux], e.comprimento);
		    heap.decreaseKey(e.extremo,visitados[e.extremo]);
		}
	    }
	    aux = heap.extractMin();
	}
	System.out.printf("%d\n",visitados[destino]);
    }
	    
}

class Encomenda {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String args[]) {
	int origem, dest;
	int cMin, cMax, lMin, lMax, aMin;
	
	lMin = in.nextInt();
	lMax = in.nextInt();
	cMin = in.nextInt();
	cMax = in.nextInt();
	aMin = in.nextInt();

	origem = in.nextInt();
	dest= in.nextInt();
	
	Graph g = new Graph(cMax, cMin, lMax, lMin, aMin, origem, dest);
	
	g.lerGrafo(in);	
	// g.mostrarGrafo();
	g.calcularComprimentoMaximo();
    }
}