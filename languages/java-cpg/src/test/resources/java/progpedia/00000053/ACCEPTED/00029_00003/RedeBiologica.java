import java.util.*;

class No{
	int vertice;
	int dist;
	boolean visited = false;
	Queue<Integer> vizinhos = new LinkedList<Integer>();

	No(int vert){
		vertice=vert;
	}
}

public class RedeBiologica {

	static No[] nos;
	static int matriz[][]; 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nnos=in.nextInt();
		int narestas=in.nextInt();

		nos = new No[nnos+1];

		matriz = new int[nnos+1][nnos+1];


		for(int i=1; i<=nnos; i++){
			nos[i] = new No(i);
		}

		for(int i=0;i<narestas;i++){
			int prim=in.nextInt();
			int seg=in.nextInt();
			matriz[prim][seg]=1;
			matriz[seg][prim]=1;
			nos[prim].vizinhos.add(seg);
			nos[seg].vizinhos.add(prim);

		}
		for(int i=1; i<=nnos; i++){
			bfs(i);
		}

		int[] excentricidade = new int[nnos+1];
		for(int i=1;i<=nnos; i++){
			for(int j=1;j<=nnos;j++){
				if(excentricidade[i]<matriz[i][j]){
					excentricidade[i]=matriz[i][j];
				}
			}
		}


		// diametro & raio
		int diametro=0;
		int raio=1501;
		for(int i=1; i<=nnos; i++){
			if(diametro<excentricidade[i]){
				diametro=excentricidade[i];
			}
			if(raio>excentricidade[i]){
				raio=excentricidade[i];
			}
		}

		// imprimir
		System.out.println(diametro);
		System.out.println(raio);
		boolean flag = false;
		for(int i=1; i<=nnos; i++){
			if(raio==excentricidade[i]){
				if(flag==false){
					System.out.print(i);
					flag = true;
				}
				else
					System.out.print(" "+i);
			}
		}
		System.out.println();
		flag = false;
		for(int i=1; i<=nnos; i++){
			if(diametro==excentricidade[i]){
				if(flag==false){
					System.out.print(i);
					flag = true;
				}
				else
					System.out.print(" "+i);
			}
		}
		System.out.println();


	}
	static void bfs(int no){
		int dist = 0;
		Queue<No> nvisitados = new LinkedList<No>();
		nvisitados.add(nos[no]);
		nos[no].visited=true;
		nos[no].dist=0;
		while(!nvisitados.isEmpty()){
			No u = nvisitados.poll();
			int tam = u.vizinhos.size();
			for(int i = 0; i<tam;i++){
				int w = u.vizinhos.poll();
				if(nos[w].visited==false){
					nvisitados.add(nos[w]);
					nos[w].visited=true;
					nos[w].dist = u.dist + 1;
					if(matriz[no][w]==0){
						matriz[no][w]=nos[w].dist;
						matriz[w][no]=nos[w].dist;
					}
				}
				u.vizinhos.add(w);
			}
		}
		for(int i=1; i<nos.length; i++){
			nos[i].visited=false;
		}
	}

}
