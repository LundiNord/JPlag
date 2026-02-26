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

public class InfraEstruturas {
	
	public static void main (String args[]){
		
		Scanner in = new Scanner(System.in);
		
		int nnos = in.nextInt();
		
		Grafo g = new Grafo(nnos);
		
		int largMin = in.nextInt();
		int largMax = in.nextInt();
		int compMin = in.nextInt();
		int compMax = in.nextInt();
		int altMin = in.nextInt();
		int origem = in.nextInt();
		int destino = in.nextInt();
		
		int p1, p2, l, c, a, count = 0;
		
		p1 = in.nextInt();
		
		while (p1 != -1){
			p2 = in.nextInt();
			l = in.nextInt();
			c = in.nextInt();
			a = in.nextInt();
			
			if(l >= largMin && c >= compMin && a >= altMin){
				count++;
			}
			
			p1 = in.nextInt();
		}
		System.out.println(count);
	}
}
