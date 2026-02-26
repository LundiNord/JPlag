import java.util.Scanner;
import java.util.LinkedList;

class Arco {
    int no_final;
    int l,c,a;
    
    Arco(int fim, int l1, int c1, int a1){
	no_final = fim;
	l=l1;
	c=c1;
	a=a1;
    }

    int extremo_final() {
	return no_final;
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


class temp {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int lmin=in.nextInt(), lmax=in.nextInt(),cmin=in.nextInt(),cmax=in.nextInt(),amin=in.nextInt();
		int o=in.nextInt(), d=in.nextInt();
		Grafo g = new Grafo(n);
		constroiGrafo(g,in,lmin,cmin,amin);
		
	}
	
	static void constroiGrafo(Grafo g, Scanner in, int lmin, int cmin, int amin){
		int n1,n2,l,c,a, conta=0;
		while((n1=in.nextInt())!=-1){
			n2=in.nextInt();
			l=in.nextInt();
			c=in.nextInt();
			a=in.nextInt();
			if(lmin<=l && cmin<=c && amin<=a){
				conta++;
				g.insert_new_arc(n1, n2, l, c, a);
				g.insert_new_arc(n2, n1, l, c, a);
			}
		}
		System.out.println(conta);
	}
}








