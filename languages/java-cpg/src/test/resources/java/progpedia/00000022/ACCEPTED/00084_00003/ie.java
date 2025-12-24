/*-------------------------------------------------------------------*\
|  Definicao de grafos com pesos (int)                                |
|     Assume-se que os vertices sao numerados de 1 a |V|.             |
|                                                                     |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012    |
|   Last modified: 2013.01.03                                         |
\--------------------------------------------------------------------*/

import java.util.LinkedList;
import java.util.Scanner;

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

class ie {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int lmin = in.nextInt(), lmax = in.nextInt(), cmin = in.nextInt(), cmax = in.nextInt(), amin = in.nextInt();
		int s = in.nextInt(), t = in.nextInt();
		
		Grafo g = criaGrafo(in, n, lmin, lmax, cmin, cmax, amin);
		
		System.out.println(g.num_arcos()/2);
	}
	
	static Grafo criaGrafo(Scanner in, int n, int lmin, int lmax, int cmin, int cmax, int amin) {
		Grafo g = new Grafo(n);
		
		int v = in.nextInt();
		while (v!=-1) {
			int w = in.nextInt();
			int l = in.nextInt();
			int c = in.nextInt();
			int a = in.nextInt();
			
			if(l>=lmin && c>=cmin && a>=amin) {
				g.insert_new_arc(v, w, 0);
				g.insert_new_arc(w, v, 0);
			}
			
			v = in.nextInt();
		}
		
		return g;
	}
}