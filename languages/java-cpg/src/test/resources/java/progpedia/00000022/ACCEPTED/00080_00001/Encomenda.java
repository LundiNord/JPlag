import java.util.*;

class No{
	int peso, vertix;
	No(int v, int p){
	
		vertix = v;
		peso=p;
	}
}
class Rua{

	LinkedList<No> adj;
	Rua() {
		
		adj = new LinkedList<No>();
	
	}
	
}
class PQueue{
	int sizeMax, size;
	int pos_a[];
	No a[];
	
	PQueue(int V) {
		sizeMax=V;
		size=V;
		pos_a=new int[V+1];
		a=new No[V+1];
		
		
		
	}
	boolean BuildTree(int c[]) { // construir a Heap dados os custos
		a[0]=new No(0,0);
	
		for(int i = 1; i <= sizeMax;i++){
		
			a[i] = new No(i,c[i]);
			pos_a[i] = i;
	        }
		
		Maxheapify();
		return true;
	}
	void printQueue() {
		for(int i = 0; i <= size; i++) {
			System.out.println("Valor: " + a[i].vertix + " Prio: " + a[i].peso);
		}
		System.out.println();
	}
	

	
	void Maxheapify() {
			for(int i = sizeMax/ 2; i >= 1; i--)
				heapify(i);
	}
	
	void insert(int v, int key)
	{ 
	 
	  size++;
	  a[size].vertix = v;
	  pos_a[v] = size;   // supondo 0 <= v < n
	  a[size].peso = key;
	  decreaseKey(v,key);   // obriga corrigir posicao
	}
	
	
	int compare(int i, int j){
		  if (a[i].peso > a[j].peso)
		    return -1;
		  if (a[i].peso == a[j].peso)
		    return 0;
		  return 1;
	}
	
	void swap(int i,int j){
		No aux;
		pos_a[a[i].vertix] = j;
		pos_a[a[j].vertix] = i;
		aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
	void heapify(int i) {
		  
		  int l, r, smallest;
		  l = 2*i;
		  if (l > size) l = i;
		  r = 2*i+1;
		  if (r > size) r = i;
		  
		  smallest = i;
		  if (compare(l,smallest) < 0) 
		    smallest = l;
		  if (compare(r,smallest) < 0) 
		    smallest = r;
		  
		  if (i != smallest) {
		    swap(i,smallest);
		    heapify(smallest);
		  }
		}
	
	
	int extractMin() {
		  int v = a[1].vertix;
		  swap(1,size);
		  size--;
		  heapify(1);
		  return v;
		}
	
	void decreaseKey(int v, int newkey){
		  int i = pos_a[v];
		  a[i].peso = newkey;

		  while(i > 1 && compare(i,i/2) < 0){
		    swap(i,i/2);
		    i = i/2;
		  }
		}
	
}
class Graph {

	Rua[] ruas;
	
	Graph(int V) {
	
		ruas=(Rua[])new Rua[V];
		for (int i = 0; i < V; i++) 
			ruas[i] = new Rua();
	}
	
	
	void AddEdge(int vA, No d) {
		
		ruas[vA-1].adj.add(d);
	}
	
	int[] Dijkstra(Graph g, int V,int destino){
		int [] pred = new int [V+1];
		int [] dist = new int [V+1];
		boolean [] visited = new boolean [V+1];
	
		
		for (int i=0; i<dist.length; i++) {
			dist[i] = 0;
		}
		for (int i=0; i<visited.length; i++) {
			visited[i] = false;
		}
		
		dist[destino] = Integer.MAX_VALUE;
		
		PQueue Q= new PQueue(V);
		
		Q.BuildTree(dist);
	
		
		while(Q.size!=0){
			Q.printQueue();
			int next =Q.extractMin();
			System.out.println("+"+next);
			
			LinkedList<No> n = ruas[next-1].adj;		
				
			
			
			visited[next]=true;
			   for (No e: n) {
				   System.out.println("->>"+e.peso + " "+dist[next]);
				   int v = e.vertix;
				   if(e.peso>dist[next]){
					   e.peso=dist[next];
				   }
				
				   
				   if (dist[v] < (e.peso) && visited[v]==false) {
					   dist[v] = e.peso;
					   pred[v] = next;
					   Q.decreaseKey(v,e.peso);
				   }
			
			   }
		}
		
		return pred;
	
	}
}
    

public class Encomenda {

	
	public static void main(String[] args) {
		Scanner moo = new Scanner(System.in);
		int nr=moo.nextInt();
		int lMin=moo.nextInt();
		int lMax=moo.nextInt();
		int cMin=moo.nextInt();
		int cMax=moo.nextInt();
		int aMin=moo.nextInt();
		int inicio=moo.nextInt();
		int fim=moo.nextInt();
		int cont=0;
		int[] caminho=new int [nr];
		Graph G=new Graph(nr);
		
		int vA=moo.nextInt();
		while(vA!=-1){
			int vB=moo.nextInt();
			int largura=moo.nextInt();
			int comprimento=moo.nextInt();
			int altura=moo.nextInt();
			if(largura>=lMin && comprimento>=cMin && altura>=aMin){
				G.AddEdge(vA,new No(vB, comprimento));
				G.AddEdge(vB,new No(vA, comprimento));
				cont++;
			}
			vA=moo.nextInt();
		}
		System.out.println(cont);
		
	//	caminho=G.Dijkstra(G, nr, inicio);
//		for(int i=0; i<nr; i++){
//			System.out.println(caminho[i]);
//		}
//		if(caminho[fim]==0){
//			System.out.println("Impossivel");
//		}
	}
	

}
/*
20 30 25 40 15
3 4
1 2 25 30 50
3 2 30 27 20
2 4 15 35 25
5 6 29 20 35
1 5 30 25 20
4 6 23 25 15
3 5 22 30 15
1 4 20 30 23
6 3 30 40 16
-1

20 30 25 40 15
4 3
1 6 25 30 50
4 6 30 27 20
6 3 15 35 25
50 7 29 20 35
1 50 30 25 20
3 7 23 25 15
4 50 22 30 15
1 3 20 30 23
7 4 30 40 16
-1
*/
