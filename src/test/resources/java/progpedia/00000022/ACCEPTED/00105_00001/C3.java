import java.util.LinkedList;
import java.util.Scanner;

class Arco {
    int no_final;
    int c,l,a;
    
    Arco(int fim, int comp, int larg, int alt){
	no_final = fim;
	c = comp;
	l = larg;
	a = alt;
    }

    int extremo_final() {
	return no_final;
    }

    int comp_arco() {
	return c;
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
    
    public void insert_new_arc(int i, int j, int c, int l, int a){
	verts[i].adjs.addFirst(new Arco(j,c,l,a));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


class C3 {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int nnos = in.nextInt();
		int lmin = in.nextInt(), lmax = in.nextInt(), cmin = in.nextInt(), cmax = in.nextInt(), amin = in.nextInt();
		int origem = in.nextInt(), destino = in.nextInt();
		Grafo g = new Grafo(nnos);
		constroiGrafo(g, in, lmin,lmax,cmin,cmax,amin);
	}
	
	static void constroiGrafo(Grafo g, Scanner in, int lmin, int lmax, int cmin, int cmax, int amin){
		int n=0;
		int origem, destino;
		while((origem=in.nextInt())!=-1){
			destino=in.nextInt();
			int l=in.nextInt(),c=in.nextInt(),a=in.nextInt();
			g.insert_new_arc(origem, destino, c, l, a);
			g.insert_new_arc(destino, origem, c, l, a);
			if(l>=lmin && c>=cmin && a>=amin) n++;
		}
		System.out.println(n);
	}
}