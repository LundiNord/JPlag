import java.util.*;

class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmax{
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
	if (a[i].vertkey > a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }

  
    private void heapify(int i) {
	int l, r, biggest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	biggest = i;
	if (compare(l,biggest) < 0)
	    biggest = l;
	if (compare(r,biggest) < 0)
	    biggest = r;
	
	if (i != biggest) {
	    swap(i, biggest);
	    heapify(biggest);
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
    	 if(verts.length < i + 1){ 
             int lastSize = verts.length; 
             verts = Arrays.copyOf(verts, i + 1); 
             nvs = i; 
             for(int novo = lastSize; novo < nvs + 1; novo++) 
                 verts[novo] = new No(); 
         } 
         verts[i].adjs.addFirst(new Arco(j, valor_ij)); 
             narcos++; 
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
    
    public int dijkstra(int orig,int dest){
    	int dist[] = new int[nvs + 1];
		for (int i = 1; i <= nvs; i++) {
			dist[i] = 0;
		}
		dist[orig] = Integer.MAX_VALUE;
		Heapmax Q = new Heapmax(dist, nvs);
		while (Q.size != 0) {
			int v = Q.extractMax();
			ListIterator<Arco> li = verts[v].adjs.listIterator();
			for (Arco w: adjs_no(v)) { 
				int cCurrente; 
				if(w.valor < dist[v]) cCurrente = w.valor; 
                else{ 
                    cCurrente = dist[v]; 
                } 
                if(cCurrente > dist[w.no_final]){ 
                    dist[w.no_final] = cCurrente; 
                    Q.increaseKey(w.no_final, dist[w.no_final]); 
                } 
			}
		}
		return(dist[dest]);
		
    }
}

public class encomenda {
	public static void main(String[] args){
		int lmin,lmax,cmin,cmax,alt,orig,dest;
		Scanner sc= new Scanner(System.in);
		Grafo gr=new Grafo(2);
		int contagemOrigem = 0; 
        int contagemDestino = 0; 
		int b,plmax,pcmax,palt;		
		
		lmin=sc.nextInt();
		lmax=sc.nextInt();
		cmin=sc.nextInt();
		cmax=sc.nextInt();
		alt=sc.nextInt();
		
		orig=sc.nextInt();
		dest=sc.nextInt();
		
		int a=sc.nextInt();
		while(a!=-1){
			b=sc.nextInt();
			plmax=sc.nextInt();
			pcmax=sc.nextInt();
			palt=sc.nextInt();
			
			if((plmax>=lmin)&&(pcmax>=cmin)){
					if(palt>=alt){
						gr.insert_new_arc(a, b, pcmax);
						gr.insert_new_arc(b, a, pcmax);
						if(orig == a || orig == b) contagemOrigem++; 
		                if(dest == a || dest == b) contagemDestino++; 
					}
			}
			a=sc.nextInt();
			
		}
		 if(contagemOrigem == 0 || contagemDestino == 0) System.out.println("0"); 
	        else{ 
	            int compMax = gr.dijkstra(orig, dest); 
	            if(compMax > cmax)System.out.println(cmax); 
	            else{ 
	                System.out.println(compMax); 
	            } 
	        } 
	}
}
