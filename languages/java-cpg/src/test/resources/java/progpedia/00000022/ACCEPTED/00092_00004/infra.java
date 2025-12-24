import java.util.*;
import java.lang.*;
 
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


public class infra {
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int locais= in.nextInt();
		int larg_min=in.nextInt();
		int larg_max=in.nextInt();
		int comp_min=in.nextInt();
		int comp_max=in.nextInt();
		int altura=in.nextInt();
		int origem=in.nextInt();
		int destino=in.nextInt();
		int n1=0;//apanha o primeiro no da linha
		int count=0;
		while(n1!=-1) {
			n1=in.nextInt();
			if(n1==-1) break;
			int n2=in.nextInt();
			int larg=in.nextInt();
			int	comp=in.nextInt();
			int alt=in.nextInt();
			if(comp>=comp_min && larg>=larg_min && alt>=altura)
						count++;
		}
		System.out.println(count);
	}
}

