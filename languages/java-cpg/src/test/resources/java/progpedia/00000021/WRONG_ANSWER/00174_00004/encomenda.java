import java.util.*;


import java.util.Arrays;
import java.util.LinkedList;
/*-----------------------------------------------------------------------*\
|  Exemplo de implementacao de fila de prioridade (por heap de minimo)    |
|                                                                         |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012        |
|   Last modified: 2013.01.02                                             |
\*-----------------------------------------------------------------------*/

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
	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


 class Medidas{
		int Lmin ;
		int Lmax ;
		int Cmin ;
		int Cmax ;
		int Amin ;	
	Medidas(int L1,int L2,int C1,int C2,int A1){
		Lmin = L1;
		Lmax = L2;
		Cmin = C1;
		Cmax = C2;
		Amin = A1;
	}
	
}

public class encomenda {
	
	public static void main(String args[]){
		Scanner inp = new Scanner(System.in);
		Medidas medidas = new Medidas(inp.nextInt(),inp.nextInt(),inp.nextInt(),inp.nextInt(),inp.nextInt());
		int origem = inp.nextInt();
		int dest = inp.nextInt();
		Grafo graf = new Grafo(2);
		int count1 = 0;
		int count2 = 0;
		int i = inp.nextInt();
		while(i !=-1){
			int j = inp.nextInt();
            int lMaxArco = inp.nextInt();
            int cMaxArco = inp.nextInt();
            int aMaxArco = inp.nextInt(); 
			if(checkmedidas(medidas,lMaxArco,cMaxArco,aMaxArco)){
				graf.insert_new_arc(i, j, cMaxArco);
				graf.insert_new_arc(j, i, cMaxArco);
				if(origem ==  i || origem == j)
					count1++;			
				if ( dest == i || dest ==j) 
					count2++;		
				}
			i = inp.nextInt();
		}
		if(count1 == 0 || count2 == 0){
			System.out.println("0");
		}else{
		int compMax = Dijkstra(origem, dest,graf);
		System.out.println(compMax+ " "+ medidas.Cmax);
        if(compMax > medidas.Cmax)System.out.println(medidas.Cmax);
        else{
            System.out.println(compMax);
        }
        } 
		
		
	}

	private static boolean checkmedidas(Medidas medidas, int lMaxArco,
			int cMaxArco, int aMaxArco) {
		if(medidas.Amin <= aMaxArco && medidas.Lmin <= lMaxArco && medidas.Cmin <= cMaxArco)
			return true;
		return false;
	}
	
	private static int Dijkstra( int s, int dest, Grafo graf){
		int dist [] = new int [graf.nvs+1];
		for(int i =1; i<=graf.nvs;i++) dist[i]=0;
		
		dist[s] = Integer.MAX_VALUE;
		Heapmax heap = new Heapmax(dist,graf.nvs);

		while(heap.size != 0){
			int v = heap.extractMax();

			for( Arco w : graf.adjs_no(v)){
				int cCurrent;
				if(w.valor < dist[v]) cCurrent = w.valor;
				else
					cCurrent = dist[v];
				if(cCurrent > dist[w.no_final]){
					dist[w.no_final] = cCurrent;
					heap.increaseKey(w.no_final, dist[w.no_final]);
				}
			}
		
		}
		return dist[dest];	
	}
	
	
}
