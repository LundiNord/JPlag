/*-------------------------------------------------------------------*\
|  Definicao de grafos com pesos (int)                                |
|     Assume-se que os vertices sao numerados de 1 a |V|.             |
|                                                                     |
|   A.P.Tomas, CC211 (material para prova pratica), DCC-FCUP, 2012    |
|   Last modified: 2013.01.03                                         |
\--------------------------------------------------------------------*/

import java.util.Iterator;
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
    
    public int bfs(int inicio, int fim){
    	LinkedList<Integer> arvore= new LinkedList<Integer>();
    	int dista[]= new int[nvs+1];
    	boolean visitados[] = new boolean[nvs+1];
    	for (int x=1;x<=nvs;x++){
    		dista[x]=Integer.MAX_VALUE;
    		visitados[x]=false;
    	}
    	
    	dista[inicio]=0;
    	visitados[inicio]=false;
    	
    	arvore.addLast(inicio);
    	while(!(arvore.isEmpty())){
    		int no= arvore.removeFirst();
    		visitados[no]=true;
    		Iterator<Arco> adj= verts[no].adjs.iterator();
    		while (adj.hasNext()){
    			System.out.println("entrou");
    			int ex= (adj.next()).no_final;
    			if (visitados[ex]==false){
    				dista[ex]= dista[no] +1;
    				arvore.addLast(ex);
    			}
    			
    		}
    	}
    	return dista[fim];
    	
    }
}

public class Infra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin = new Scanner(System.in);
		int nos=stdin.nextInt();
		int lar_min=stdin.nextInt() ,  lar_max=stdin.nextInt(),  compr_min=stdin.nextInt(),  compr_max=stdin.nextInt(),  al_min = stdin.nextInt();
		int inicio=stdin.nextInt() , fim=stdin.nextInt();
		Grafo exemplo= new Grafo(nos);
		while(true){
			int in;
			in=stdin.nextInt();
			if (in==-1){
				break;
			}
			int out=stdin.nextInt(), largura=stdin.nextInt(), comprimento=stdin.nextInt() , altura =stdin.nextInt();
			if ((largura>lar_min && largura<lar_max) && (comprimento>compr_min && comprimento<compr_max) && (altura>al_min)){
				exemplo.insert_new_arc(in, out, 0);
				exemplo.insert_new_arc(out, in, 0);
			}
			
			
		}
		int c= exemplo.bfs(inicio, fim);
		if (c==Integer.MAX_VALUE){
			System.out.println("Impossivel");
		}
		else{
			System.out.println(c);
		}
	}

}
