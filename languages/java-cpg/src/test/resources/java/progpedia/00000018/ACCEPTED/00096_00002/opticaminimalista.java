import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
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
    
    public void Prim(){
    	boolean a=false;
    	int dist[] = new int[nvs+1];
    	boolean op[] = new boolean[nvs+1];
    	int pai[]=new int[nvs+1];
    	
    	for(int i=1;i<=nvs;i++){
    		dist[i]=Integer.MAX_VALUE;
    		op[i]=false;
    	}
    	dist[1]=0;
    	pai[1]=0;
    	Heapmin Q = new Heapmin(dist,nvs);
    	int u;
    	Arco n;
    	while(Q.size!=0){
    		u=Q.extractMin();
    			ListIterator<Arco> li = verts[u].adjs.listIterator();
				while(li.hasNext()){
					n=li.next();
					if((op[n.no_final]==false)&&(n.valor<dist[n.no_final])){
						dist[n.no_final]=n.valor;
						Q.decreaseKey(n.no_final, dist[n.no_final]);
						pai[n.no_final]=u;
					}
					op[u]=true;
				
    		}    			
    	}
    	int max=0;
    	for(int i=1;i<=nvs;i++){
    		if(dist[i]!=Integer.MAX_VALUE){
    			max=max+(dist[i]);
    		}
    		else{
    			System.out.println("impossivel");	
    			a=true;
    			break;
    		}
    		
    		
    	}
    	if(a!=true){
    		System.out.println("rendimento optimo: "+max*(-1));
    	}
    		
    }
}

public class opticaminimalista {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n,m,c,a,b,r;
		n=sc.nextInt();
		m=sc.nextInt();
		c=sc.nextInt();
		Grafo gr=new Grafo(n);
		for(int i=1;i<=m;i++){
			a=sc.nextInt();
			b=sc.nextInt();
			r=sc.nextInt();
			r=r-c;
			gr.insert_new_arc(a, b, r*(-1));
			gr.insert_new_arc(b, a, r*(-1));
		}
		gr.Prim();
	}	
}
