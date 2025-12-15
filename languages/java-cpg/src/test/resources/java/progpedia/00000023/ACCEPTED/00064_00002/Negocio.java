import java.util.*;
class Regiao{
	boolean visitado;
	int dist;
	int vizinhos[];
	int n_viz;
	
	Regiao(int n){
		visitado=false;
		dist=-1;
		n_viz=0;
		vizinhos=new int [n+1];
	}
	void add(int k,int d){
		vizinhos[k]=d;
		n_viz++;
	}
}

class Grapho{
	Regiao pontos[];
	int partida;
	int nos;
	int visitados;
	LinkedList<Integer> res;
	
	Grapho(int n,int p){
		nos=n;
		pontos=new Regiao[n+1];
		partida=p;
		visitados=0;
		res = new LinkedList<Integer>();
	}
	
	void scan(Scanner kb){
		int a=kb.nextInt();
		while(a!=-1){
			int b=kb.nextInt();
			int ab=kb.nextInt();
			if(pontos[a]==null)
				pontos[a]=new Regiao(nos);
			if(pontos[b]==null)
				pontos[b]=new Regiao(nos);
			pontos[a].add(b,ab);
			pontos[b].add(a,ab);
			a=kb.nextInt();
		}
	}
	
	void relax (int a, int b){
		if(pontos[b].dist==-1 || pontos[b].dist>pontos[a].dist+pontos[a].vizinhos[b])
			pontos[b].dist=pontos[a].dist+pontos[a].vizinhos[b];
			
	}
	int findMin(){
		int indice=-1;
		int min=999999999;
		for(int i=1;i<=nos;i++){
			if(pontos[i].dist!=-1 && pontos[i].dist<min && pontos[i].visitado==false){
				min=pontos[i].dist;
				indice=i;
			}
		}
		return indice;
	}
	void disjkstra(){
		pontos[partida].dist=0;
		while(visitados<nos){
			int actual=findMin();
			if(actual!=-1){
				int n=0;
			
				res.addLast(actual);
				Regiao r = pontos[actual];
				r.visitado=true;
				for(int i=0;i<nos||n<r.n_viz;i++){
					if(r.vizinhos[i]!=0){
						n++;
						relax(actual,i);
					}
				}
				visitados++;
			}
		}
	}
	void print(){
		while(!res.isEmpty()){
			int aux=res.getFirst();
			System.out.print(aux+" ");
			res.removeFirst();
		}
		//System.out.println();
	}
}
public class Negocio {
	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		int regioes=kb.nextInt();
		int partida=kb.nextInt();
		Grapho g=new Grapho(regioes,partida);
		g.scan(kb);
		g.disjkstra();
		g.print();
		
		
	}

}
