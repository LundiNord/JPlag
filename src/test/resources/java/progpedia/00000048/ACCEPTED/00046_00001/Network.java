import java.util.*;

class Network{

	static int graph[][];
	static boolean visited[];
	static int numbers=0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		numbers=n;
		int lines = in.nextInt();

		graph=new int[n+1][n+1];
		visited = new boolean[n+1];

		for(int i=0; i<n+1; i++)
			visited[i]=false;

		for(int i=1; i<=lines; i++){
			int node = in.nextInt();
			int neighbor = in.nextInt();
			graph[neighbor][node]=1;
			graph[node][neighbor]=1;
			
		}

		int counter=0;

		for(int i=1; i<n+1; i++){
			if(!visited[i]){
				// System.out.println(visited[i]+" "+i);
				dfs(i);	
				counter++;
			}
		}
		System.out.println(counter);

	// 	for(int i=1; i<n+1;i++){
	// 		for(int j=1; j<n+1; j++){
	// 			System.out.print(graph[i][j]+" ");
	// 		}
	// 		System.out.println();
	// 	}
	// 	System.out.println();
	// 	System.out.println(Arrays.toString(graph));
	}

	private static void dfs(int n){
		visited[n]=true;
		// System.out.println(visited[n]);
		for(int i=1; i<numbers+1; i++){
			// System.out.println(graph[n][i]+" "+visited[i]);
			if(graph[n][i]==1 && !visited[i]){
				// System.out.println(n+" "+i);
				dfs(i);
			}
		}
		// for(int i=1; i<n+1;i++){
		// 	for(int j=1; j<n+1; j++){
		// 		graph[i][j]=n;
		// 	}
			
		// }
	}
}