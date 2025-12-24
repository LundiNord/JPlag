import java.util.HashMap;
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
	int sizeMax, size;

	Qnode[] a;
	int[] pos_a;

	Heapmax(int vec[], int n) {
		a = new Qnode[n + 1];
		pos_a = new int[n + 1];
		sizeMax = n;
		size = n;
		for (int i = 1; i <= n; i++) {
			a[i] = new Qnode(i, vec[i]);
			pos_a[i] = i;
		}

		for (int i = n / 2; i >= 1; i--)
			heapify(i);
	}

	int extractMax() {
		int vertv = a[1].vert;
		swap(1, size);
		pos_a[vertv] = posinvalida; // assinala vertv como removido
		size--;
		heapify(1);
		return vertv;
	}

	boolean contais(int v) {
		for (int i = 1; i <= size; i++) {
			if (a[i].vert == v)
				return true;
		}
		return false;
	}

	void increaseKey(int vertv, int newkey) {

		int i = pos_a[vertv];
		a[i].vertkey = newkey;

		while (i > 1 && compare(i, parent(i)) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	void insert(int vertv, int key) {
		if (sizeMax == size)
			new Error("Heap is full\n");

		size++;
		a[size].vert = vertv;
		pos_a[vertv] = size; // supondo 1 <= vertv <= n
		increaseKey(vertv, key); // diminui a chave e corrige posicao se
									// necessario
	}

	void write_heap() {
		System.out.printf("Max size: %d\n", sizeMax);
		System.out.printf("Current size: %d\n", size);
		System.out.printf("(Vert,Key)\n---------\n");
		for (int i = 1; i <= size; i++)
			System.out.printf("(%d,%d)\n", a[i].vert, a[i].vertkey);

		System.out.printf("-------\n(Vert,PosVert)\n---------\n");

		for (int i = 1; i <= sizeMax; i++)
			if (pos_valida(pos_a[i]))
				System.out.printf("(%d,%d)\n", i, pos_a[i]);
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
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
		if (l > size)
			l = i;

		r = right(i);
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
	int no_inicial;
	int no_final;
	int valor;

	Arco(int inicio,int fim, int v) {
		no_inicial=inicio;
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
	// int label;
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
		verts = new No[n + 1];
		for (int i = 0; i <= n; i++)
			verts[i] = new No();
		// para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
	}

	public int num_vertices() {
		return nvs;
	}

	public int num_arcos() {
		return narcos;
	}

	public LinkedList<Arco> adjs_no(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int j, int valor_ij) {
		verts[i].adjs.addFirst(new Arco(i,j, valor_ij));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : adjs_no(i))
			if (adj.extremo_final() == j)
				return adj;
		return null;
	}
}

public class Encomenda {

	
	public static void main(String[] args) {
		Scanner moo = new Scanner(System.in);
		
		int lMin=moo.nextInt();
		int lMax=moo.nextInt();
		int cMin=moo.nextInt();
		int cMax=moo.nextInt();
		int aMin=moo.nextInt();
		int inicio=moo.nextInt();
		int fim=moo.nextInt();

		LinkedList<Arco> arcos=new LinkedList<Arco>();
		HashMap<Integer,Integer> nos= new HashMap<Integer,Integer>();
		
		
		int vA=moo.nextInt();
		
		while(vA!=-1){
			int vB=moo.nextInt();
			
			int largura=moo.nextInt();
			int comprimento=moo.nextInt();
			int altura=moo.nextInt();
			if(largura>=lMin && largura<=lMax && comprimento>=cMin && comprimento<=cMax && altura>=aMin){
				if(!nos.containsKey(vA))
					nos.put(vA, nos.size()+1);
				vA=nos.get(vA);
				if(!nos.containsKey(vB))
					nos.put(vB, nos.size()+1);
				vB=nos.get(vB);
				
				//printHash(nos);
			//	System.out.println("Inserido:  "+vA+" "+vB+" "+comprimento);
				arcos.add(new Arco(vA,vB,comprimento));
				arcos.add(new Arco(vB,vA,comprimento));
		
			}
			vA=moo.nextInt();
			
			
		}
		if(nos.containsKey(inicio)){
			if(nos.containsKey(fim)){
				inicio=nos.get(inicio);
				fim=nos.get(fim);
				Grafo g=new Grafo(nos.size());
				for(Arco arc: arcos){
					g.insert_new_arc(arc.no_inicial, arc.no_final, arc.valor);
				}
				int[] caminho=new int [g.nvs+1];
				caminho=Prim(g, inicio,fim);
				
				
				if(caminho[fim]==Integer.MIN_VALUE)
					System.out.println(0);
				else
					System.out.println(caminho[fim]);
				
			}
			else{
				System.out.println(0);
			}
		}else{
			System.out.println(0);
		}
		
			
		//System.out.println(inicio+ " "+fim);
		

		
		
		
	}/*
	private static void printHash(HashMap<Integer, Integer> nos) {
		System.out.println("(key) (value)\n--------");
		for(Integer i:nos.keySet()){
			System.out.print(i+" ");
			System.out.println(nos.get(i));
		}
		
	}*/
	private static int[] Prim(Grafo g, int vA,int fim) {
		int dist[] = new int[g.nvs + 1];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MIN_VALUE;
		}
		dist[vA] = 0;
		Heapmax heap = new Heapmax(dist, g.nvs);
		while (heap.size != 0) {
			//System.out.println(dist[fim]);
			//heap.write_heap();
			int v = heap.extractMax();
			//System.out.print("No "+v+":");
			for (Arco w : g.adjs_no(v)) {
				//System.out.print(" "+w.no_final);
				 if (v==vA) {
					dist[w.no_final] = w.valor;
					heap.increaseKey(w.no_final, dist[w.no_final]);
				}
				else if (dist[w.no_final] < Math.min(dist[v],w.valor) && heap.contais(w.no_final)) {
					dist[w.no_final] = Math.min(dist[v],w.valor);
					heap.increaseKey(w.no_final, dist[w.no_final]);
				}
				
				
			}
			//System.out.println();

		}
		return dist;
	}
}
    


