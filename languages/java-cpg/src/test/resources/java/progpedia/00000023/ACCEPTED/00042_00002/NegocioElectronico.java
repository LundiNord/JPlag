import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

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


/***************************************************************************************************************/


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
    	if (a[i].vertkey == a[j].vertkey) {
	    	if (a[i].vert< a[j].vert)
	    		return -1;
	    	if (a[i].vert> a[j].vert)
	    		return 1;
	    	return 0;
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


/***************************************************************************************************************/


public class NegocioElectronico {
	static int nNos;
	static int nInicial;
	static int dist[];
	static Grafo g;
	static LinkedList<Integer> fila;
	static Grafo construir_Grafo(Scanner in){
	
		 g =new Grafo(nNos);
		
		int nI = in.nextInt();
		
		
		while(nI!=-1){
			int nF = in.nextInt();
			int custo = in.nextInt();
			g.insert_new_arc(nI, nF, custo);
			g.insert_new_arc(nF, nI, custo);
			nI = in.nextInt();	
		}
		
		return g;
	}
	
	static void caminho(Grafo g, int s) {
		 dist = new int [g.nvs+1];
		fila = new LinkedList<Integer>();
			 
		 for (int u = 1; u <= g.nvs ; u++){  
             dist [u] = Integer.MAX_VALUE;              
         } 
		 dist[s]=0;
		 Heapmin heap = new Heapmin(dist,g.nvs);
	
		 while(heap.size!=0){
			 
			 int v = heap.extractMin();
			 for(Arco arc: g.adjs_no(v)){ 
				 int w = arc.extremo_final();
				 int dvw = arc.valor_arco();
				// System.out.println( v + " " + w + " " + dvw );
				 if( dist[w] > dist[v]+dvw) {
					 dist[w] = dist[v]+dvw;
					 heap.decreaseKey(w,dist[w]); 
				 }
			 }
			
			fila.addLast(v);
		 }		 
		 }
		
		static void escrever(){
			while(!fila.isEmpty()){
				System.out.print(fila.removeFirst());
				if(fila.size()!=0)
					System.out.print(" ");
				else
					System.out.println();
				
			}
		}

	public static void main(String[] args) {
					
		Scanner in = new Scanner(System.in);
		nNos = in.nextInt();
		nInicial = in.nextInt();

		g=construir_Grafo(in);
		caminho( g, nInicial);
		escrever();
	
		
	}

}
