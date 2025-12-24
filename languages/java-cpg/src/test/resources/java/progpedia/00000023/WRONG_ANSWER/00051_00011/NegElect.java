import java.util.LinkedList;
import java.util.Scanner;


class No{
	int valor;
	boolean visitado;
	int peso;
	
	No(int v){
		valor = v;
		visitado = false;
		peso = Integer.MAX_VALUE;
	}
}


class Grafo{
	int nreg;
	int dest;
	No n[];
	int pesos[][];
	
	
	Grafo(int nr, int d){
		nreg = nr;
		dest = d;
		n = new No[nreg+1];
		pesos = new int [nreg+1][nreg+1];
		
	}
	
	void inicializar(){
		for(int i=1; i<=nreg; i++){
			n[i] = new No(i);	
		}
		n[dest].peso = 0;
	}
	
	void criarGrafo(int p1, int p2, int dt){
		pesos[p1][p2] = dt;
		pesos[p2][p1] = dt;
	}
	
	void Dijkstra(){
		LinkedList<No> Q = new LinkedList<No>();
		for(int i=1; i<n.length; i++){
			Q.addFirst(n[i]);
		}
		
		while(!Q.isEmpty()){
			No u = extract_Min(Q);
			
			for(int j=1; j<n.length; j++){
				No v = n[j];
				if(pesos[u.valor][j] != 0)
					if(v.peso > (u.peso + pesos[u.valor][v.valor]))
						v.peso = (u.peso + pesos[u.valor][v.valor]);
			}
			u.visitado = true;
			
			if(Q.isEmpty())
				System.out.print(u.valor);
			else
				System.out.print(u.valor + " ");
		}
		
	}
	
	private No extract_Min(LinkedList<No> Q){
		 No min = new No(0);
		 
		 LinkedList<No> S = new LinkedList<No>();
		 while(!Q.isEmpty()){
			 No tmp = Q.removeFirst();
			 if(tmp.peso < min.peso)
				 min = tmp;
			 S.addLast(tmp);
		 }
		 while(!S.isEmpty()){
			 No tmp = S.removeFirst();
			 if(tmp != min)
				 Q.addFirst(tmp);
		 }
		 return min;
	}	
}


public class NegElect{
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		int nreg = stdin.nextInt();
		int dest = stdin.nextInt();
		Grafo G = new Grafo(nreg, dest);
		G.inicializar();
		
		int p1 = stdin.nextInt();
		while(p1 != -1){
			int p2 = stdin.nextInt();
			int dist = stdin.nextInt();
			G.criarGrafo(p1, p2, dist);
			p1 = stdin.nextInt();
		}
		G.Dijkstra();
	}
}