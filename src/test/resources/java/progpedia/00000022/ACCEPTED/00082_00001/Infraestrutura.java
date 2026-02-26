import java.util.*;

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

public class Infraestrutura{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int nnos = in.nextInt();
	Grafo g = new Grafo(nnos);
	int lmin=in.nextInt();
	int lmax=in.nextInt();
	int cmin=in.nextInt();
	int cmax=in.nextInt();
	int amin=in.nextInt();
	int or = in.nextInt();
	int dest = in.nextInt();
	int pai = in.nextInt();
	while(pai!=-1){
	    int filho = in.nextInt();
	    int larg = in.nextInt();
	    int comp = in.nextInt();
	    int alt = in.nextInt();
	    if(larg >= lmin && comp >= cmin && alt >= amin)
		g.insert_new_arc(pai,dest,1);
	    pai = in.nextInt();
	}
	System.out.println(g.num_arcos());
    }
}