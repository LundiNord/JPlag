import java.lang.*;
import java.util.*;

/*-----------------------------------------------------------------------*\
|  Exemplo de implementacao de fila de prioridade (por heap de minimo)    |
|                                                                         |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012        |
|   Last modified: 2013.01.02                                             |
\*-----------------------------------------------------------------------*/

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
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmin(int vec[], int n) {
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
	if (a[i].vertkey < a[j].vertkey)
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

/****************
 *	CÓDIGO MEU  *
 ****************/

class edge{
	int to,weight;

	edge(int to, int weight){
		this.to = to;
		this.weight = weight;
	}
}

class node{
	LinkedList<edge> adj;

	node(){
		adj = new LinkedList<edge>();
	}
}

class graph{
	node[] vertices;
	int nnode;

	graph(int len){
		vertices = new node[len+1];
		nnode = len;
		for(int i=1;i<=nnode;i++)
			vertices[i] = new node();
	}

	public void addEdge(int from, int to, int weight){
		vertices[from].adj.addLast(new edge(to,weight));
		vertices[to].adj.addLast(new edge(from,weight));
	}

	public int[] prim(){
		int[] key = new int[nnode+1];
		int[] pred = new int[nnode+1];
		boolean[] visited = new boolean[nnode+1];

		for(int i=1;i<=nnode;i++){
			key[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		key[1] = 0;
		Heapmin hm = new Heapmin(key,nnode);
		pred[1] = 0;
		
		int u;
		edge e;
		while(hm.size != 0){
			u = hm.extractMin();
			ListIterator<edge> li = vertices[u].adj.listIterator();
			while(li.hasNext()){
				e = li.next();
				if(!visited[e.to] && e.weight < key[e.to]){
					key[e.to] = e. weight;
					hm.decreaseKey(e.to,key[e.to]);
					pred[e.to] = u;
				}
			}
			visited[u] = true;
		}

		return key;
	}
}

class optica{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int nnode,nedge,expenses;
		nnode = sc.nextInt();
		nedge = sc.nextInt();
		expenses = sc.nextInt();

		graph gr = new graph(nnode);

		for(int i=0;i<nedge;i++)
			gr.addEdge(sc.nextInt(),sc.nextInt(),(sc.nextInt()*(-1)));


		int[] sol = gr.prim();
		int total = 0;
		for(int i=1;i<=nnode;i++)
			if(sol[i] != Integer.MAX_VALUE)
				total += sol[i];
			else{
				System.out.println("Impossível");
				return;
			}

		total = (total * -1) - (2 * (nnode-1));
		System.out.println(total);
	}
}