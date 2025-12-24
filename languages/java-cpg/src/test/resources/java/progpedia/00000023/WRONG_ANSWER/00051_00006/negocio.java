import java.util.*;

class No{
	boolean visitado;
	int id;
	LinkedList<No> adj;
	int peso;
	
	No(int i){
		visitado = false;
		id=i;
		adj=new LinkedList<No>();
		peso=Integer.MAX_VALUE;
	}
	
	void addligacao(No x){
		adj.addLast(x);
	}
}

class grafo{
	int nnos;
	static No x[];
	int pesos[][];
	int destiny;
	
	grafo(int n, int d){
		nnos=n;
		x=new No[nnos+1];
		pesos=new int[nnos+1][nnos+1];
		destiny=d;
	}
	
	void inicializa(){
		for(int i=1; i<=nnos; i++){
			x[i]=new No(i);
		}
	}
	
	void creategrafo(Scanner in){
		inicializa();
		int p1=in.nextInt();
		while(p1!=-1){
			int p2=in.nextInt();
			int dist=in.nextInt();
			pesos[p1][p2]=dist;
			pesos[p2][p1]=dist;
			x[p1].addligacao(x[p2]);
			x[p2].addligacao(x[p1]);
			p1=in.nextInt();
		}
		
	}
	
	void DijKstras(){
		x[destiny].peso=0;
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux = new PriorityQueue<Integer>(nnos, cmp);
		
		aux.add(destiny);
		
		while(!aux.isEmpty()){
			int k=aux.remove();
			x[k].visitado = true;
			for(No y : x[k].adj){
				int j=y.id;
				if(!y.visitado && y.peso>x[k].peso+pesos[k][j]){
					y.peso=x[k].peso+pesos[k][j];
					aux.add(j);
				}
			}
		}	
	}
	
	void ordenar(){
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> order = new PriorityQueue<Integer>(nnos, cmp);
		for(int i=1; i<=nnos; i++){
			order.add(x[i].id);
		}
		while(!order.isEmpty()){
			int k=order.remove();
			System.out.print(k + " ");
		}
		System.out.println();
	}
	
	public static class Cmp implements Comparator<Integer>{
		public int compare(Integer z, Integer y){
			if(x[z].peso==x[y].peso){
				if(z>y) return y-z;
				else return z-y;
			}
				return x[z].peso - x[y].peso;
		}
	}

}

public class negocio {
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		int nnos=in.nextInt();
		int destino=in.nextInt();
		grafo G = new grafo(nnos, destino);
		G.creategrafo(in);
		G.DijKstras();
		G.ordenar();
	}
}