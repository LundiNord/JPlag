import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node{
	int val;
	int custo;
	LinkedList<Node> adj;
	boolean visitado;
	Node(int v){
		val=v;
		custo=Integer.MIN_VALUE;
		adj=new LinkedList<Node>();
		visitado=false;
	}
	void addLigacao(Node l){
		adj.addLast(l);
	}
}

class Grafo{
	static Node Ligacoes[];
	int G[][];
	int nos,ligacoes,custo1;
	Grafo(int n,int l,int c){
		nos=n;
		ligacoes=l;
		custo1=c;
		Ligacoes=new Node[nos+1];
		G=new int[nos+1][nos+1];
	}

	void inicializa(){
		for(int i=1; i <= nos;i++){
			Ligacoes[i]=new Node(i);
		}
	}

	void creatGrafo(Scanner in){
		inicializa();
		for(int i=0; i < ligacoes;i++){
			int p1=in.nextInt();
			int p2=in.nextInt();
			int rend=in.nextInt();
			G[p1][p2]=rend;
			G[p2][p1]=rend;
			Ligacoes[p1].adj.addLast(Ligacoes[p2]);
			Ligacoes[p2].adj.addLast(Ligacoes[p1]);
		}
	}
	
	void Prim(){
		Ligacoes[1].custo=0;
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux=new PriorityQueue<Integer>(nos,cmp);
		aux.add(1);
		while(!aux.isEmpty()){
			int u=aux.remove();
			if(!Ligacoes[u].visitado){
				for(Node a:Ligacoes[u].adj){
					int k=a.val;
					if (!Ligacoes[k].visitado  && a.custo < G[u][k]){
						a.custo=G[u][k];
						aux.add(k);
					}
				}
				Ligacoes[u].visitado=true;		
			}
		}
	}
	
	public static class Cmp implements Comparator<Integer>{
		public int compare(Integer z, Integer y){
			return Ligacoes[y].custo - Ligacoes[z].custo;
		}
	}
	
	void output(){
		int soma=0;
		boolean conectado=true;
			for(int i=2;i <= nos;i++){
					if(Ligacoes[i].custo == Integer.MIN_VALUE){
						conectado=false;
						break;			
					}
					soma += Ligacoes[i].custo;				
			}
			
			if(!conectado ){
				System.out.println("impossivel");
			}
			else{
				int total = soma - (custo1*(nos-1));
				System.out.println("rendimento optimo: " + total);
			}
	}
}

public class Optica {

	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		int nos=inp.nextInt();
		int ligacoes=inp.nextInt();
		int custo=inp.nextInt();
		Grafo novo=new Grafo(nos,ligacoes,custo);
		novo.creatGrafo(inp);
		novo.Prim();
		novo.output();
	}

}
