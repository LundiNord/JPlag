import java.util.*;

class prob21{
	//variables
	static boolean visitados[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int n,e,dists[][];

	//bfs
	static void bfs(int node){
		// nova fila
		Queue<Integer> fila = new ArrayDeque<Integer>();
		fila.add(node);
		visitados[node]=true;
		dists[node][node]=0;
		while(!fila.isEmpty()){
			int u = fila.remove();
			for(int i : graph.get(u)) {
				if(!visitados[i]){
					visitados[i]=true;
					fila.add(i);
					dists[node][i]=dists[i][node]=dists[node][u]+1;
				} 
			}
		}
	
	}
	//main
	public static void main (String Args[]){
		Scanner in = new Scanner (System.in);
		n=in.nextInt();
		e=in.nextInt();
		visitados= new boolean[n];
		// inicializar grafo como lista de listas
		for (int i  = 0; i < n; i++)
			graph.add(new ArrayList<Integer>());
		dists=new int [n][n];
		
		// construir grafo
		for (int i=0;i<e;i++){
			int a =in.nextInt();
			int b =in.nextInt();
			graph.get(a-1).add(b-1); graph.get(b-1).add(a-1);
			//dists[a][b]=dists[b][a]=1;
		}
		// inicializar array de visitados a false
		for(int i=0;i<n;i++){
			Arrays.fill(visitados, false);
			bfs(i);
		}
		// array com excentricidade de cada no
		int excent[] = new int[n];
		for (int i = 0; i < n; i++) {
			int mx = 0;
			for (int j = 0; j < n; j++)
				mx = Math.max(mx, dists[i][j]);
			excent[i] = mx;
		}
		//calcular diametro
		int diametro = 0;
		for (int j = 0; j < n; j++)
			diametro = Math.max(diametro, excent[j]);
		
		System.out.println(diametro);
		//calcular excentricidade minima
		int raio = Integer.MAX_VALUE;
		for(int i=0;i<n;i++)
			raio = Math.min(raio, excent[i]);
		System.out.println(raio);
	
		// calcular nos centrais
		// numero de nos centrais
		int nc=0;
		for(int i=0;i<n;i++){
			if(excent[i]==raio)nc++;
		}
		//imprimir
		for(int i=0;i<n;i++){
			if(excent[i]== raio){
				if(nc>1){
					System.out.print((i+1)+" ");
					nc--;
				}
				else if(nc == 1)System.out.println(i+1);
			}
		}
		//calcular a nos perifericos
		int np=0;
		for(int i=0;i<n;i++){
			if(excent[i]==diametro)np++;
		}
		for(int i=0;i<n;i++){
			if(excent[i]==diametro){
				if(np>1){
					System.out.print((i+1)+" ");
					np--;
				}
				else if (np==1)System.out.println(i+1);
				}
			}
		}//end of main
}// end of class
	

 