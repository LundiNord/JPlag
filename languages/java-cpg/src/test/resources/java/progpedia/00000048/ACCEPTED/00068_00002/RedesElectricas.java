import java.util.*;

class No{
	int vertice;
	boolean visited=false;
	Queue<Integer> vizinhos = new LinkedList<Integer>();

	No(int vert){
		vertice=vert ;
	}

} 

public class RedesElectricas {
	static No nos[];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nnos = in.nextInt();
		int narestas = in.nextInt();
		
		nos = new No[nnos+1];
		
		for(int i=1; i<=nnos;i++){
			nos[i]=new No(i);
		}
		
		for(int i=0; i<narestas; i++){
			int prim = in.nextInt();
			int seg = in.nextInt();
			nos[prim].vizinhos.add(seg);
			nos[seg].vizinhos.add(prim);
		}
		int resultado = 0;
		resultado = conexos();
		System.out.println(resultado);


	}
	
	static void dfs(int v){
		nos[v].visited=true;
		while(!nos[v].vizinhos.isEmpty()){	
			int w = nos[v].vizinhos.poll();
			if(nos[w].visited==false){
				dfs(w); 
			}
		}
		
	}
	
	static int conexos(){
		int contador = 0;
		for(int i=1; i<nos.length;i++){
			nos[i].visited=false;
		}
		for(int i = 1; i<nos.length;i++){
			if(nos[i].visited==false){
				contador +=1;
				dfs(i);
			}
		}
		return contador;
	}

}
