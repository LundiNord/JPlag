import java.util.*;
class ligacao{
	int ponto;
	int peso;
	boolean visitado;
	LinkedList<ligacao>adj;
	
	ligacao(int po){
		ponto=po;
		visitado=false;
		adj= new LinkedList<ligacao>();
		peso= Integer.MIN_VALUE;
				
			}
	void addligacao(ligacao l){
	adj.addLast(l);
	}
}
class grafo {
	int nn;
	int nl;
	int c;
	static ligacao x[];
	int g[][];
	
	grafo (int nnr, int nlp, int cm){
		nn=nnr;
		nl=nlp;
		c=cm;
		g=new int [nnr+1][nnr+1];
		x= new ligacao [nnr+1];
		
	}
	void inicializar(){
		for (int i=1; i<=nn ;i++){
			x[i]=new ligacao (i);
		}
	}
	void criar_grafo ( Scanner in ){
		inicializar();
		for (int i=0;i <nl;i++){
			int nn1=in.nextInt();
			int nn2=in.nextInt();
			int cs=in.nextInt();
			g[nn1][nn2]=cs;
			g[nn2][nn1]=cs;
			x[nn1].addligacao(x[nn2]);
			x[nn2].addligacao(x[nn1]);
		}
	}
	void prim(){
		x[1].peso = 0;
		
		Comparator<Integer> cmp = new Cmp();
		PriorityQueue<Integer> aux = new PriorityQueue<Integer>(nn,cmp);
		aux.add(1);
		while(!aux.isEmpty()){
			int u =aux.remove();
			if(!x[u].visitado){
				for( ligacao cursor: x[u].adj){
					int k = cursor.ponto;
					if(!x[k].visitado && cursor.peso<g[u][k]){
						cursor.peso=g[u][k];
						aux.add(k);
					}
				}
				x[u].visitado=true;
			}
		}
	}
	public static class Cmp implements Comparator <Integer>{
		public int compare (Integer z, Integer y ){
			return x[y].peso-x[z].peso;
		}
		
	}
	void print(){
		boolean ligado= true;
		int soma =0;
		for (int i=2;i<=nn;i++){
			if (x[i].peso ==Integer.MIN_VALUE){
				ligado=false;
				break;
			}
			soma+=x[i].peso;
			
		}
if(!ligado){
	System.out.println("impossivel");
	
}
else {
	int total = soma - (c*(nn-1) );
	System.out.println("rendimento optimo: "+total);
}
	}
}

public class optica_minimalista {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	Scanner in = new Scanner (System.in);
	int nos = in.nextInt();
	int n=in.nextInt();
	int custo = in.nextInt();
	grafo novo = new grafo(nos,n,custo);
	novo.criar_grafo(in);
	novo.prim();
	novo.print();
	}

}
