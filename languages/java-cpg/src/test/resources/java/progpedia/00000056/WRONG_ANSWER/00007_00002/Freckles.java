import java.util.*;

// class No{

// }


class Freckles{

	static double matrix[][];

	static boolean visited[][];
	
	static double dist[];

	static int pred[];

	static int q;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in).useLocale(Locale.US);

		int n = q = in.nextInt();

		matrix = new double[n][n];

		visited = new boolean[n][n];

		dist = new double[n];

		pred = new int[n];

		double coordinates[][] = new double[n][2];


		for(int i = 0; i<n; i++){
			double x = in.nextDouble();
			double y = in.nextDouble();
			coordinates[i][0] = x;
			coordinates[i][1] = y;
		}

		for(int i = 0; i<n; i++){
			for (int j=i; j<n; j++) {
				if(i!=j){
					matrix[j][i] = matrix[i][j] = calc(coordinates[i][0], coordinates[j][0], coordinates[i][1], coordinates[j][1]);
				}
				
			}
		}
		
		// System.out.println();
		// for(int i=0; i<n; i++){
		// 	for(int j=0; j<n; j++){
		// 		System.out.print(matrix[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();

		prim(matrix, 0);

		// for(int i=0; i<n; i++){
		// 	System.out.print(dist[i]+" ");
		// }
		// System.out.println();
		
		// for(int i=0; i<n; i++){
		// 	System.out.print(pred[i]+" ");
		// }
		// System.out.println();
		double sum=0;
		for(int i=0; i<n; i++){
			sum+=dist[i];
			// System.out.println(sum);
		}
		System.out.printf(Locale.US, "%.2f\n", sum);
	}

	private static double calc(double xi, double xj, double yi, double yj){
		return Math.sqrt((xi-xj)*(xi-xj) + (yi-yj)*(yi-yj));
	}

	private static void kruskall(){

	}

	//aplicaçao por matriz
	private static void prim(double graph[][], int no){
		int aux=q;

		for(int i=0; i<q; i++){
			pred[i]=0;
			dist[i]=Double.MAX_VALUE;
		}
		dist[no]=0;
		while(aux>=0){
			int u = min(graph);
			// System.out.println(u + " menor no");
			for(int i=0; i<q; i++){
				// System.out.println();
				if(graph[u][i]!=0 && graph[u][i]<dist[i]){
					pred[i]=u;
					// System.out.println("dentro do if " + graph[u][i]);
					dist[i]=graph[u][i];
				}
			}
			aux--;
		}

	}

	private static int min(double graph[][]){
		double min = Double.MAX_VALUE;
		int index =0;
		int indexj = 0;
		for(int i=0; i<q; i++){
			for(int j=0; j<q; j++){
				if(graph[i][j]<min && !visited[i][j]){
					index = i;
					indexj=j;
					min = graph[i][j];
				}
			}
		}
		visited[index][indexj]=true;
		return index;
	}
}