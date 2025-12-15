import java.util.LinkedList;
import java.util.Scanner;

class Arco {
	int no_final;
	int largura,comprimento,altura;

	Arco(int fim, int l, int c, int a){
		no_final = fim;
		largura = l;
		comprimento = c;
		altura = a;
	}

	int extremo_final() {
		return no_final;
	}

	int valor_largura() {
		return largura;
	}
	
	int valor_comprimento(){
		return comprimento;
	}
	
	int valor_altura(){
		return altura;
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
public class Infraestrutura {

	public static void construirGrafo(Grafo g, Scanner kb){
		
		int lMin = kb.nextInt();
		int lMax = kb.nextInt();
		int cMin = kb.nextInt();
		int cMax = kb.nextInt();
		int altura = kb.nextInt();
		
		int origem = kb.nextInt();
		int destino = kb.nextInt();
		
		int p1,p2,l,c,a,contador=0;
		
		p1=kb.nextInt();
		while(p1!=-1){
			p2=kb.nextInt();
			l=kb.nextInt();
			c=kb.nextInt();
			a=kb.nextInt();
		
			g.insert_new_arc(p1, p2, l, c, a);
			g.insert_new_arc(p2, p1, l, c, a);
			
			if(lMin<=l && cMin<=c && altura<=a)
				contador++;
			
			p1=kb.nextInt();
		}
		System.out.println(contador);		
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		int nnos = kb.nextInt();
		Grafo g = new Grafo(nnos);
		
		construirGrafo(g,kb);
	}

}
