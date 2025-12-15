import java.util.ListIterator;
import java.util.Scanner;
import java.util.LinkedList;
class HeapNode{
	int key;
	int indexKey;
	HeapNode(int pos,int d){
		key=d;
		indexKey=pos;
	}
	public int compareTo(HeapNode o){
		HeapNode aux = o; 
		if(this.key<aux.key || (this.indexKey<aux.indexKey && this.key==aux.key))
			return -1;
		else if(this.key>aux.key)
			return 1;
		return 0;
	}
}
class Heap {
	int max;
	int n;
	int pos[];
	HeapNode heap[];
	Heap(int size){
		max=size;
		n=0;
		pos=new int[max];
		heap= new HeapNode [max];

	}
	boolean isEmpty() {return n==0;}


	void updateNode(int no,int nkey){
		heap[pos[no]].key=nkey;
		upHeap(pos[no]);
	}

	void upHeap(int no){
		heap[0]=heap[no];
		int i=no;
		int j= i/2;
		while(heap[0].compareTo(heap[j]) < 0){
			pos[heap[j].indexKey]=i;
			heap[i]=heap[j];
			i=j;
			j=i/2;	
		}
		heap[i]=heap[0];
		pos[heap[i].indexKey]=i;

	}


	void addNode(int no, int d){
		n++;
		heap[n]=new HeapNode(no,d);
		pos[heap[n].indexKey]=n;
		upHeap(n);
	}

	HeapNode removeTop(){
		HeapNode aux = null;
		aux= heap[1];
		pos[heap[1].indexKey]=-1;
		heap[1]=heap[n];
		pos[heap[1].indexKey]=1;
		n--;
		downHeap(1);
		return aux;

	}

	void downHeap(int no){
		int min=no,l=no*2,r=no*2-1;

		if(r<=n && heap[r].compareTo(heap[min])<0)
			min=r;
		if(l<=n && heap[l].compareTo(heap[min])<0)
			min=l;
		if(min!=no){
			upHeap(min);
			downHeap(min);
		}
	}
}

class Grafo {

	int sizeV;
	int sizeA;	
	Vertex vertex[];
	boolean visitado[];
	int dia[];

	class Vertex{
		LinkedList <Arco> arestas;

		Vertex(){
			arestas= new LinkedList<Arco>();
		}
	}
	class Arco {
		int peso;
		int aponta;
		Arco(int p,int a){
			peso=p;
			aponta=a;
		}
	}
	Grafo(int tamanho){
		tamanho++;
		sizeV=tamanho;
		vertex = new Vertex[tamanho];
		visitado = new boolean[tamanho];
		dia = new int[tamanho];
		for(int i =0;i<tamanho;i++){
			vertex[i]=new Vertex();
			visitado[i]=true;
			dia[i]=0;
		}
	}

	public void AddArco(int were,int p,int a){
		vertex[were].arestas.add(new Arco(p,a));
		vertex[a].arestas.add(new Arco(p,were));
		sizeA++;
	}

	void xpto (){
		for(int x=1;x<sizeV;x++){

			ListIterator<Arco> it = vertex[x].arestas.listIterator();
			System.out.print(x+" ");
			while(it.hasNext()){
				System.out.print(it.next().aponta+ " " );
			}
			System.out.println();
		}
	}

	void reset(){
		for(int i =1;i<sizeV;i++){

			visitado[i]=true;
			dia[i]=0;
		}
	}

	void dijkstra(int source,LinkedList<Integer> Final) {

		int v, d;
		Heap heap= new Heap(sizeV);

		for (v=1; v<sizeV; v++) 
			if(v==source)
				heap.addNode(v,0);
			else
				heap.addNode(v,Integer.MAX_VALUE);



		while(!heap.isEmpty()) {
			HeapNode w= heap.removeTop();
			Final.addLast(w.indexKey);
			visitado[w.indexKey]=false;
			ListIterator<Arco> it = vertex[w.indexKey].arestas.listIterator();
			while(it.hasNext()){
				Arco aux =it.next();
				if(w.key<Integer.MAX_VALUE){
					if(visitado[aux.aponta]){
						d= aux.peso + w.key;
						if (d<heap.heap[heap.pos[aux.aponta]].key) 		    
							heap.updateNode(aux.aponta, d);		   
					}
				}
				else break;
			}
		}
	}


	public static void print_heap(	Heap lol){
		for(int x=1;x<=lol.n;x++)
			System.out.println(lol.heap[x].indexKey + " - " + lol.heap[x].key);
		System.out.println();
		System.out.println();
	}

	int bfs( int start,int finish){
		int count =0;
		LinkedList<Integer> fila = new LinkedList<Integer>();
		fila.addLast(start);
		while(!fila.isEmpty()){
			int temp = fila.removeFirst();
			ListIterator<Arco> it = vertex[temp].arestas.listIterator();
			while(it.hasNext()){
				int aux = it.next().aponta;
				if(visitado[aux]){
					visitado[aux]=false;
					fila.addLast(aux);
					if(aux!=finish){
						count++;
					}
				}
			}
		}
		return count;
	}
	
	
}

public class emcomenda {
	public static void main(String args[]){
		Scanner ler = new Scanner (System.in);
		int size = ler.nextInt();
		int lmin=ler.nextInt();
		int lmax=ler.nextInt();
		int cmin=ler.nextInt();
		int cmax=ler.nextInt();
		int amin=ler.nextInt();
		int start = ler.nextInt();
		int finish = ler.nextInt();
		
		Grafo g = new Grafo(size);
		int vertice= ler.nextInt();
		while(vertice!=-1)
		{
			int aresta=ler.nextInt();
			int laux=ler.nextInt();
			int caux=ler.nextInt();
			int aaux=ler.nextInt();
			if(laux>=lmin && caux>=cmin && aaux>=amin)
				g.AddArco(vertice, 0, aresta);
		
		vertice=ler.nextInt();
		}
		
		System.out.println(g.sizeA);
	}
}
