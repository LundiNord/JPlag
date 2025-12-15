import java.util.*;

// class Integer{ private final List<Integer>nodes;}
// public interface Integer extends List<Integer>{}

class Biology{

	static List<Integer> [] graph;
	static int solution[][];
	static boolean visited[];
	static int numbers;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = numbers = in.nextInt();

		solution = new int[n+1][n+1];
		visited = new boolean[n+1];

		// for(int i=1; i<=numbers; i++){
		// 	for(int j=1; j<=numbers; j++){
		// 		solution[i][j]=Integer.MAX_VALUE;
		// 	}
		// }

		int edges = in.nextInt();

		
		graph = new List[n+1];

		for(int i=1; i<=n; i++){
			graph[i]= new LinkedList<Integer>();
		}

		for(int i=1; i<=edges; i++){
			int a = in.nextInt();
			int b = in.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}
		for(int i=1; i<=n; i++){
			bfs(i);	
			clean();
			// System.out.println();
		}

		
		
		// for(int i=1; i<n+1; i++){
		// 	for (int j=1; j<n+1; j++) {
		// 		System.out.print(matrix[i][j]);
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();
		// for(int i=1; i<n+1; i++){
		// 	for (int j=1; j<n+1; j++) {
		// 		System.out.print(solution[i][j]);
		// 	}
		// 	System.out.println();
		// }
		// System.out.println();

		int diam = diameter(solution);
		int rad = radium(solution);
		central(solution, rad);
		periph(solution, diam);
	}

	private static void bfs(int v){
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(v);
		visited[v] = true;
		solution[v][v]=0;
		while(q.peek()!=null){
			int u =q.remove();
			// System.out.print(u + " ");
			List<Integer> w = graph[u];
			for(Integer i: w){
				if(!visited[i]){
					
					// System.out.println(matrix[u][i]);
					// calc(u, 1, u);

					solution[v][i]=solution[v][u] + 1;
					q.add(i);
					visited[i]=true;
				}
				
			}
		}

	}	

	private static void clean(){
		for(int i=1; i<=numbers; i++){
			visited[i]=false;
		}
	}

	private static int diameter(int solve[][]){
		int maximum=0;
		for(int i=1; i<=numbers; i++){
			for(int j=1; j <=numbers; j++){
				// System.out.println(solve[i][j]);
				if(solve[i][j]>maximum){
					maximum=solve[i][j];
				}
			}
		}
		System.out.println(maximum);
		return maximum;
	}

	private static int radium(int solve[][]){
		int minimum = Integer.MAX_VALUE;
		int maximum =0;
		for(int i=1; i<=numbers; i++){
			for(int j=1; j <=numbers; j++){
				// System.out.println(solve[i][j]);
				if(solve[i][j]>maximum){
					maximum=solve[i][j];
				}
			}
			if(maximum < minimum){
				minimum=maximum;
			}
			maximum=0;
		}
		
		System.out.println(minimum);
		return minimum;

	}

	private static void central(int solve[][], int rad){
		LinkedList<Integer> nmb = new LinkedList<Integer>();
		boolean visit[] = new boolean[numbers+1];
		int temp = 0;
		int index = 0;
		for(int i=1; i<=numbers; i++){
			for(int j=1; j<=numbers; j++){
				// System.out.println(solve[i][j]);
				if(solve[i][j]>=temp){
					temp=solve[i][j];
					index=i;
				}
			}
			// System.out.println("rad"+rad);
			if(temp==rad && !visit[index]){
				visit[i]=true;
				nmb.add(index);
				// System.out.println("entrei"+index);
			}
			else{
				temp=0;
			}
		}
		Collections.sort(nmb);
		System.out.print(nmb.removeFirst());
		for(Integer s: nmb){
			System.out.print(" "+s);
		}
		System.out.println();
	}

	private static void periph(int solve[][], int diam){
		LinkedList<Integer> nmb = new LinkedList<Integer>();
		boolean visit[] = new boolean[numbers+1];
		for(int i=1; i<=numbers; i++){
			for(int j=1; j<=numbers; j++){
				// System.out.println(solve[i][j]);
				if(solve[i][j]>=diam && !visit[i]){
					nmb.add(i);
					visit[i]=true;
				}
			}
		}
		Collections.sort(nmb);
		System.out.print(nmb.removeFirst());
		for(Integer s: nmb){
			System.out.print(" "+s);
		}	
		System.out.println();
	}
}