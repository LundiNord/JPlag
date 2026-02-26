import java.util.*;

class No {
	int id;
	int peso;
	int pi;
	boolean visitado;
	LinkedList<No> adj;
	
	No (int identificacao) {
		id = identificacao;
		peso=Integer.MIN_VALUE;
		pi=-1;
		visitado=false;
		adj = new LinkedList<No>();
	}
	public void adiciona (No z) {
		adj.addLast(z);
	}
}
	
class Grafo {
	private int nNos, nLigacoes, custoGlobal;
	private int [][] pesos;
	static private No [] nos;
	
	
	Grafo (int n, int liga, int custo) {
		nNos=n;
		nLigacoes = liga;
		custoGlobal = custo;
		pesos= new int  [nNos+1][nNos+1];
		nos = new No [nNos+1];
	}
	private void inicializa(){
		for(int i = 1; i<=nNos; i++){
			nos[i]= new No(i);
			
		}
	}
	
	public void createGrafo (Scanner kb) {
		inicializa();
		
		for(int i=0; i<nLigacoes; i++) {
			int u = kb.nextInt();
			int v = kb.nextInt();
			int peso = kb.nextInt();
			pesos[u][v] = peso;
			pesos[v][u] = peso;
			nos[u].adiciona(nos[v]);
			nos[v].adiciona(nos[u]);
		}
	}
	void prim () {
		nos[1].peso=0;
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux = new PriorityQueue<Integer>(nNos, cmp);
		aux.add(1);
	
		while(!aux.isEmpty()) {
			int u = aux.remove();
			System.out.println(u);
			if(!nos[u].visitado) {
				for(No cursor: nos[u].adj) {
					int k = cursor.id;
					if(!nos[k].visitado && cursor.peso < pesos[u][k]) {
						nos[k].peso= pesos [u][k];
						nos[k].pi=u;
						aux.add(k);
											}
				}
				nos[u].visitado=true;
			}
		}
	}
		public static class Cmp implements Comparator<Integer>{
			public int compare(Integer z, Integer y){
				return nos[y].peso - nos[z].peso;
			}
	}
		
	
	public void rendimentoOptimo () {
		if(isImp()) 
			System.out.println("impossivel");
		else {
			int custo = custoGlobal * (nNos-1);
			int rendBruto = 0;
			for(int i=1;i<=nNos; i++) {
				rendBruto+= nos[i].peso;
			}
			int rendLiquido = rendBruto - custo;
			System.out.println("Rendimento Optimo:" + rendLiquido);
		}
				
		
	}
	
	private boolean isImp () {
		for(int i=1; i<=nNos; i++) {
			if(nos[i].visitado==false)
				return true;
		}
		return false;
	}
	
	
}

public class Optica_Minimalista {

	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		// nNos, nLigacoes, custo global
	Grafo rede = new Grafo(kb.nextInt(), kb.nextInt(), kb.nextInt());	
	rede.createGrafo(kb);
	rede.prim();
	rede.rendimentoOptimo();
		
		
	}

}
