import java.lang.*;
import java.util.*;
import java.io.*;

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

    int extractMax() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void increaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) > 0) { 
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
	increaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
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
	int l, r, largest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	largest = i;
	if (compare(l,largest) > 0)
	    largest = l;
	if (compare(r,largest) > 0)
	    largest = r;
	
	if (i != largest) {
	    swap(i, largest);
	    heapify(largest);
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

    boolean isEmpty(){
	return (size==0);
    }
}

class Arco {
    int no_final;
    int valor;
    
    Arco(int fim, int v){
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
    int label;
    LinkedList<Arco> adjs;

    No(int l) {
	adjs = new LinkedList<Arco>();
	label = l;
    }

    int indice(){
	return label;
    }
}


class Grafo {
    Hashtable<Integer,No> verts;
    Hashtable<Integer,Integer> vinv;
    int nvs, narcos;
			
    public Grafo() {
	nvs = 0;
	narcos = 0;
	verts  = new Hashtable <Integer, No>();
	vinv = new Hashtable <Integer,Integer>();
    }
    
    public int num_vertices(){
	return nvs;
    }

    public int num_arcos(){
	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
	return verts.get(i).adjs;
    }
    
    public void insert_new_arc(int i, int j, int valor_ij){
	if(!verts.containsKey(i)){No a=new No(++nvs); verts.put(i,a); vinv.put(nvs,i);}
	if(!verts.containsKey(j)){No b = new No(++nvs); verts.put(j,b); vinv.put(nvs,j);}
	verts.get(i).adjs.addFirst(new Arco(j,valor_ij));
	//verts.get(j).adjs.addFirst(new Arco(i,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}

class Encomenda {
    public static int min (int n, int m){
	if(n<=m) return n;
	return m;
    }

    public static void caminhos (Grafo g, int d, int cap[]){
	for(int i =1; i<=g.num_vertices(); i++){
	    cap[i]= 0;
	}
	cap[d]=Integer.MAX_VALUE;
	Heapmax hmax = new Heapmax(cap,g.num_vertices());
	while(hmax.size!=0){
	    int v = hmax.extractMax();
	    for(Arco a : g.adjs_no(g.vinv.get(v))){
		int w = g.verts.get(a.extremo_final()).label;
		int co = min(cap[v],a.valor_arco());
		if(co >cap[w]){
		    cap[w]=co;
		    hmax.increaseKey(w,cap[w]);
		}
	    }
	}
    }
    
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int largmin = in.nextInt();
	int largmax = in.nextInt();
	int compmin = in.nextInt();
	int compmax = in.nextInt();
	int altmin = in.nextInt();
	int origem = in.nextInt();
	int destino = in.nextInt();
	int pai = in.nextInt();
	Grafo g = new Grafo();
	while(pai!=-1){
	    int filho = in.nextInt();
	    int lmax = in.nextInt();
	    int cmax = in.nextInt();
	    int amax = in.nextInt();
	    if((lmax>=largmin) && (cmax>=compmin) && (amax>=altmin)){
		g.insert_new_arc(pai,filho,cmax);
		g.insert_new_arc(filho,pai,cmax);
	    }
	    pai = in.nextInt();
	}
	int cap[] = new int[g.num_vertices()+1];
	if(g.verts.containsKey(origem) && g.verts.containsKey(destino)){
	    caminhos(g,g.verts.get(origem).label,cap);
	    int lol = g.verts.get(destino).label;
	    System.out.println(cap[lol]);
	}
	else System.out.println("0");
    }
}
