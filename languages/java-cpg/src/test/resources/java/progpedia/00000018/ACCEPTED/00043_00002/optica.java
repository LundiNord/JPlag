import java.util.*;

class Ligacoes{
	int ponto;
	int peso;
	LinkedList<Ligacoes> adj;
	boolean visitado;
	
	Ligacoes(int p){
		ponto = p;
		peso=Integer.MIN_VALUE;
		adj=new LinkedList<Ligacoes>();
		visitado = false;
	}
	
	void addLigacao(Ligacoes l){
		adj.addLast(l);
	}
}

class Grafo{
	int nos;
	int nligacoes;
	int custo;
	int G[][];
	static Ligacoes x[];
	
	Grafo(int nnos, int nl, int c){
		nos = nnos;
		nligacoes = nl;
		custo = c;
		G= new int[nnos+1][nnos+1];
		x= new Ligacoes[nnos+1];
	}
	
	void createGrafo(Scanner in){
		for(int i = 1; i<=nos; i++){
			x[i]= new Ligacoes(i);
		}
		for(int i = 0; i<nligacoes; i++){
			int extremo1 = in.nextInt();
			int extremo2 = in.nextInt();
			int rendimento = in.nextInt();
			G[extremo1][extremo2] = rendimento;
			G[extremo2][extremo1] = rendimento;
			x[extremo1].addLigacao(x[extremo2]);
			x[extremo2].addLigacao(x[extremo1]);
		}
	}

	void Prim(){
		x[1].peso = 0;	
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux = new PriorityQueue<Integer>(nos, cmp);
		aux.add(1);
		
		while(!aux.isEmpty()){
			int u = aux.remove();
			if(!x[u].visitado){
				for(Ligacoes cursor : x[u].adj){
					int k = cursor.ponto;
					if(!x[k].visitado && cursor.peso < G[u][k]){ 
						cursor.peso = G[u][k];
						aux.add(k);
					}
				}
				x[u].visitado= true;
			}
		}
	}
	
	class Cmp implements Comparator<Integer>{
		public int compare(Integer z, Integer y){
			return x[y].peso - x[z].peso;
		}
	}
	
	void Output(){
		boolean conectado = true;
		int soma = 0;
		for(int i = 2; i<=nos; i++){
			if(x[i].peso == Integer.MIN_VALUE){
				conectado = false;
				break;
			}
			soma = soma + x[i].peso;
		}
		if(!conectado){
			System.out.println("impossivel");}
		else{
			int total = soma - (custo*(nos-1));
			System.out.println("rendimento optimo: " + total);
		}
	}
}

class optica {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nnos = in.nextInt();
		int nl = in.nextInt();
		int custo = in.nextInt();
		Grafo novo = new Grafo(nnos, nl, custo);
		novo.createGrafo(in);
		novo.Prim();
		novo.Output();
	}
}