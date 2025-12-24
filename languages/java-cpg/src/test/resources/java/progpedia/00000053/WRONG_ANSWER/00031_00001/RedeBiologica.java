import java.util.*;

class No{
	int vertice;
	int distancia;
	boolean visitado = false;
	Queue<Integer> vizinhos = new LinkedList<Integer>();
	
	No(int v){
		vertice = v;
	}
}


public class RedeBiologica {

	static int[][] matriz; 
	static No nnos[];

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		int nos = in.nextInt();
		int arestas = in.nextInt();
		
		matriz = new int[nos+1][nos+1];
		nnos = new No[nos+1];


		for (int i = 1; i <= nos; i++) {
			nnos[i] = new No(i);
			for(int j=1; j<= nos; j++){
				matriz[i][j] = 0;
			}
		}

		for (int j = 0; j < arestas; j++) {
			int first = in.nextInt();
			int second = in.nextInt();

			matriz[first][second] = 1;
			matriz[second][first] = 1;
			nnos[first].vizinhos.add(second);
			nnos[second].vizinhos.add(first);
			
			
		}
		
		for (int i = 1; i <= nos; i++) {
			bfs(nos, i);
		}
		
		int[] excentricidade = new int[nos+1];
		for(int i=1;i<=nos; i++){
			for(int j=1;j<=nos;j++){
				if(excentricidade[i]<matriz[i][j]){
					excentricidade[i]=matriz[i][j];
				}
			}
		}
		
		int diametro = 0;
		for (int i = 1; i <= nos; i++) {
			if (excentricidade[i]>diametro) {
				diametro = excentricidade[i];
			}
		}
		
		int raio = 20000;
		for (int i = 1; i <= nos; i++) {
			if (excentricidade[i]<raio) {
				raio = excentricidade[i];
			}
		}
		
		System.out.println(diametro);
		System.out.println(raio);
		
		boolean imp = false;
		
		for (int i = 1; i <= nos; i++) {
			if (excentricidade[i]==raio) {
				if(!imp){
					System.out.print(i);
				}
				else{
					System.out.print(" " + i);
				}
			}
		}
		System.out.println();
		
		imp = false;

		for (int i = 1; i <= nos; i++) {
			if (excentricidade[i]==diametro) {
				if(!imp){
					System.out.print(i);
				}
				else{
					System.out.print(" " + i);
				}
			}
		}
		
		System.out.println();
	}

	
	static void bfs(int nos, int i){
		Queue<No> nvisitados = new LinkedList<No>();
		
		nvisitados.add(nnos[i]);
		nnos[i].distancia = 0;
		nnos[i].visitado = true;
				
		while(!nvisitados.isEmpty()){
			No temp = nvisitados.remove();
			int vizinhos = temp.vizinhos.size();
			for(int j=0; j<vizinhos; j++){
				int temp1 = temp.vizinhos.poll();
				if(!nnos[temp1].visitado){
					nvisitados.add(nnos[temp1]);
					nnos[temp1].visitado = true;
					nnos[temp1].distancia = temp.distancia + 1;
					if (matriz[i][temp1]==0) {
						matriz[i][temp1] = nnos[temp1].distancia;
						matriz[temp1][i] = nnos[temp1].distancia;
					}
				}
				temp.vizinhos.add(temp1);
			}
			for(int j=0; j<nos; j++){
				nnos[i].visitado = false;
			}
		}
	}
}
