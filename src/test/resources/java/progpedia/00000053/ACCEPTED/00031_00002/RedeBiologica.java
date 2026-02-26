import java.util.*;

class No{
	int vertice;
	int distancia = 0;
	boolean visitado = false;
	Queue<Integer> vizinhos = new LinkedList<Integer>();

	No(int vert){
		vertice=vert;
		
	}
}

public class RedeBiologica {

	static int matriz[][];
	static No nos[];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nnos = in.nextInt();
		int narestas = in.nextInt();

		matriz = new int[nnos+1][nnos+1];
		
		nos = new No[nnos+1];

		for (int i = 1; i <= nnos; i++) {
			nos[i]=new No(i);
			for(int j=1; j<= nnos; j++){
				matriz[i][j] = 0;
			}
		}
		
		
		for (int j = 0; j < narestas; j++) {
			int first = in.nextInt();
			int second = in.nextInt();
			matriz[first][second] = 1;
			matriz[second][first] = 1;
			nos[first].vizinhos.add(second);
			nos[second].vizinhos.add(first);
		}
		
		
		
		for(int i=1;i<=nnos;i++){
			bfs(nnos, i);
		}
		
		int[] excentricidade = new int[nnos+1];
		for(int i=1;i<=nnos; i++){
			for(int j=1;j<=nnos;j++){
				if(excentricidade[i]<matriz[i][j]){
					excentricidade[i]=matriz[i][j];
				}
			}
		}
		
		//calc diametro
		int diametro=0;
		for(int i=1;i<=nnos; i++){
			if(excentricidade[i]>diametro){
				diametro=excentricidade[i];
			}
		}
		
		//calc raio
		int raio=1501;
		for(int i=1;i<=nnos; i++){
			if(excentricidade[i]<raio){
				raio=excentricidade[i];
			}
		}
		
		
		System.out.println(diametro);
		System.out.println(raio);
		boolean imp = false;
		for(int i=1;i<=nnos; i++){
			int no = raio;
			if(excentricidade[i]==no){
				if(!imp){
					System.out.print(i);
					imp = true;
				}
				else
					System.out.print(" "+i);
			}
		}
		System.out.println();
		imp = false;
		for(int i=1;i<=nnos; i++){
			int no = diametro;
			if(excentricidade[i]==no){
				if(imp==false){
					System.out.print(i);
					imp = true;
				}
				else
					System.out.print(" "+i);
			}
		}
		System.out.println();

	}
	
	
	static void bfs(int nnos, int no){
		Queue<No> nvisitados = new LinkedList<No>();
		nvisitados.add(nos[no]);
		nos[no].distancia = 0;
		nos[no].visitado = true;
		while(!nvisitados.isEmpty()){
			No temp = nvisitados.poll();
			int vizinhos = temp.vizinhos.size();
			for(int i=0; i<vizinhos; i++){
				int temp1 = temp.vizinhos.poll();
				if(!nos[temp1].visitado){
					nvisitados.add(nos[temp1]);
					nos[temp1].visitado=true;
					nos[temp1].distancia=temp.distancia+1;
					if(matriz[no][temp1]==0){
						matriz[no][temp1]=nos[temp1].distancia;
						matriz[temp1][no]=nos[temp1].distancia;
					}
				}
				temp.vizinhos.add(temp1);
			}
		}
		for(int i = 1; i<=nnos; i++)
			nos[i].visitado=false;
	}
}