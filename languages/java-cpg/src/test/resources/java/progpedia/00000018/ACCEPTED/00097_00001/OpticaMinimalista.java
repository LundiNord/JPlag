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
		if (a[i].vertkey == a[j].vertkey){
			if(a[i].vert < a[j].vert)
				return -1;
			if(a[i].vert == a[j].vert)		
				return 0;
			else
				return 1;
		}
		return 1;
	}


	private void heapify(int i) {
		int l, r, smallest;

		l = left(i);
		if (l > size) l = i;

		r = right(i);
		if (r > size) r = i;

		smallest = i;
		if (compare(l,smallest) > 0)
			smallest = l;
		if (compare(r,smallest) > 0)
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
	
	public boolean isEmpty(){
		return(size==0);
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
	//int label;
	LinkedList<Arco> adjs;
	boolean visitado;

	No() {
		adjs = new LinkedList<Arco>();
		visitado=false;
	}
}


class Grafo {
	No verts[];
	int nvs, narcos;

	public Grafo(int n) {
		nvs = n;
		narcos = 0;
		verts  = new No[n+1];
		for (int i = 0 ; i <= n ; i++)
			verts[i] = new No();
		// para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
	}

	public int num_vertices(){
		return nvs;
	}

	public int num_arcos(){
		return narcos;
	}

	public LinkedList<Arco> adjs_no(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int j, int valor_ij){
		verts[i].adjs.addFirst(new Arco(j,valor_ij));
		narcos++;
	}

	public Arco find_arc(int i, int j){
		for (Arco adj: adjs_no(i))
			if (adj.extremo_final() == j) return adj;
		return null;
	}
}

public class OpticaMinimalista {
	static Grafo g;

	public static int construirGrafo(){
		
		Scanner kb = new Scanner(System.in);
		int nnos= kb.nextInt();
		int ncaminhos = kb.nextInt();
		int manutencao = kb.nextInt();
		g = new Grafo(nnos);
		
		int inicio, fim, rendimentoBruto,s=0;
		
		for(int i=0; i<ncaminhos; i++){
			inicio=kb.nextInt();
			if(i==0)
				s=inicio;
			fim=kb.nextInt();
			rendimentoBruto=kb.nextInt();
			
			g.insert_new_arc(inicio, fim, rendimentoBruto-manutencao);
			g.insert_new_arc(fim, inicio, rendimentoBruto-manutencao);
		}
		
		return s;
	}
	
	public static void Prim(int s){
		
		int dist[] = new int [g.num_vertices()+1];
		
		for(int i=2; i<dist.length;i++)
			dist[i]=Integer.MIN_VALUE;
		
		dist[s]=0;
		
		Heapmax Q = new Heapmax(dist,g.num_vertices());
		int rendimento=0,v=0,w,dvw;
		
		while(!Q.isEmpty()){
			v = Q.extractMax();
			if(dist[v]!=Integer.MIN_VALUE){
				No no= g.verts[v];
				
				for(Arco arco: no.adjs){
					w = arco.extremo_final();
					dvw = arco.valor_arco();
					
					if(dist[w]<dvw && g.verts[w].visitado==false){
						dist[w]=dvw;
						Q.increaseKey(w, dist[w]);
					}
				}
			}
			g.verts[v].visitado=true;
			rendimento+=dist[v];			
		}
		
		if(dist[v]==Integer.MIN_VALUE)
			System.out.println("impossivel");
		else
			System.out.println("rendimento optimo: "+rendimento);
	}
	
	public static void main(String[] args) {
		
		int prim = construirGrafo();
		Prim(prim);
	}

}
