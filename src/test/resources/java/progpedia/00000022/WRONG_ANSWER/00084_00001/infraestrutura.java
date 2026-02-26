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

class infraestrutura {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		int nnos = in.nextInt();
		int lmin = in.nextInt();
		int lmax = in.nextInt();
		int cmin = in.nextInt();
		int cmax = in.nextInt();
		int alt = in.nextInt();
		
		int s = in.nextInt();
		int t = in.nextInt();
		
		Grafo g = criaGrafo(nnos, lmin, lmax, cmin, cmax, alt, in);
	}
	
	static Grafo criaGrafo(int nnos, int lmin, int lmax, int cmin, int cmax, int alt, Scanner in) {
		Grafo g = new Grafo(nnos);
		
		int narcos = 0;
		int s = in.nextInt();
		
		while(s!=-1){
			int t = in.nextInt();
			int l = in.nextInt();
			int c = in.nextInt();
			int a = in.nextInt();
			
			if(l>=lmin && l<=lmax && c>=cmin && c<=cmax && a>=alt) {
				g.insert_new_arc(s, t, 0);
				g.insert_new_arc(t, s, 0);
				narcos++;
			}
			
			s = in.nextInt();
		}
		
		System.out.println(narcos);
		return g;
	}
}