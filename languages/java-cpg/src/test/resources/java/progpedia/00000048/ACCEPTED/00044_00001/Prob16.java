import java.util.LinkedList;
import java.util.Scanner;


public class Prob16 {
	

	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int L = in.nextInt();
		int a,b;
		Grafo rede = new Grafo(N);
		
		for(int i=0; i<L; i++){
			a = in.nextInt();
			b= in.nextInt();
			rede.insert_new_arc(a, b, 0);
			rede.insert_new_arc(b, a, 0);
		}
		
		System.out.println(rede.compConexas());
	}
	

	
	
}
class Arco {
	int no_final;
	int valor;

	Arco(int fim, int v) {
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
	// int label;
	LinkedList<Arco> adjs;
	boolean visited;
	
	No() {
		adjs = new LinkedList<Arco>();
		visited=false;
	}
}

class Grafo {
	No verts[];
	int nvs, narcos;

	public Grafo(int n) {
		nvs = n;
		narcos = 0;
		verts = new No[n + 1];
		for (int i = 0; i <= n; i++)
			verts[i] = new No();
		// para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
	}

	public int num_vertices() {
		return nvs;
	}

	public int num_arcos() {
		return narcos;
	}

	public LinkedList<Arco> adjs_no(int i) {
		return verts[i].adjs;
	}

	public void insert_new_arc(int i, int j, int valor_ij) {
		verts[i].adjs.addFirst(new Arco(j, valor_ij));
		narcos++;
	}

	public Arco find_arc(int i, int j) {
		for (Arco adj : adjs_no(i))
			if (adj.extremo_final() == j)
				return adj;
		return null;
	}
	
	public void resetVisited(){
		for(No a : verts){
			a.visited=false;
		}
	}
	
	public void dfs(No v){
		v.visited = true;
		
		for(Arco a : v.adjs){
			No no = verts[a.no_final];
			
			if(!no.visited)
				dfs(no);
		}
				
	}
	
	public int compConexas(){
		int cnt=0;
		
		for(int i=1; i<=nvs;i++){
			No a = verts[i];
			
			if(!a.visited){
				cnt++;
				dfs(a);
			}
		}
		
		return cnt;
	}

}