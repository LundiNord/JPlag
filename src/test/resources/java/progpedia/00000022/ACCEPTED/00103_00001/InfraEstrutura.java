import java.util.Scanner;
import java.util.LinkedList;
class Arco {
    int no_final;
    int l;
    int c;
    int a;
    
    Arco(int fim, int l, int c, int a){
	no_final = fim;
	this.l=l;
	this.c=c;
	this.a=a;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_arco() {
	return l;
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
    
    public void insert_new_arc(int i, int j, int l, int c, int a){
	verts[i].adjs.addFirst(new Arco(j,l,c,a));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}
public class InfraEstrutura {
	public static void main(String[] args) {
		Scanner ex=new Scanner(System.in);
		int count=0;
		int nrNos=ex.nextInt();
		int larMin=ex.nextInt();
		int larMax=ex.nextInt();
		int compMin=ex.nextInt();
		int compMax=ex.nextInt();
		int altura=ex.nextInt();
		int noI=ex.nextInt();
		int noF=ex.nextInt();
		Grafo grafo = new Grafo(nrNos);
		int valorI=ex.nextInt();
		while(valorI!=-1){
			int valorF=ex.nextInt();
			int l=ex.nextInt();
			int c=ex.nextInt();
			int a=ex.nextInt();
			grafo.insert_new_arc(valorI, valorF, l,c,a);
			grafo.insert_new_arc(valorF, valorI, l,c,a);
			if(l>=larMin && c>=compMin && a>=altura)
				count++;
			valorI=ex.nextInt();	
		}
		System.out.println(count);
	}
}

