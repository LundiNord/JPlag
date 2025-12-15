import java.util.Scanner;
import java.util.LinkedList;
import java.util.Scanner;

class Arco {
    int no_final;
    int lar;
    int com;
    int alt;
    
    Arco(int fim, int l, int c, int a){
	no_final = fim;
	lar = l;
	com = c;
	alt = a;
    }

    int extremo_final() {
	return no_final;
    }

    int lar_arco() {
	return lar;
    }
    
    int com_arco(){
    	return com;
    }
    
    int alt_arco(){
    	return alt;
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
	verts[i].adjs.addFirst(new Arco(j, l, c, a));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}

public class Infra {

	/**
	 * @param args
	 */
	public static Scanner inp = new Scanner (System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nnos, lmin, lmax, cmin, cmax, alt;
		int origem, destino;
		int sol=0;
		
		nnos = inp.nextInt(); 		//line 1
		lmin = inp.nextInt();		//line 2
		lmax = inp.nextInt();
		cmin = inp.nextInt();
		cmax = inp.nextInt();
		alt = inp.nextInt();		//end line 2
		origem = inp.nextInt();		//line 3
		destino = inp.nextInt();	//3

		Grafo rede = new Grafo(nnos);

	
		int e1, e2, larg, comp, altu;
		
		while((e1=inp.nextInt())!=-1){
			e2 = inp.nextInt();
			larg = inp.nextInt();
			comp = inp.nextInt();
			altu = inp.nextInt();
			if(lmin<=larg && cmin<=comp && alt<=altu){
				sol++;
			}
		}
			System.out.println(sol);
		}
	
	}	

