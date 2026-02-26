import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;

public class problema21 {

	static Scanner in = new Scanner(System.in);
	//static boolean [][]matrix;
	static ArrayList<LinkedList<Integer>> listAdj = new ArrayList<LinkedList<Integer>>();
	static boolean []visited;
	static int [][]dist;
	static int n; //numero de nos/vertices do grafo
	static int e; //numero de arestas/ligacoes do grafo
	static int diam = 0;
	static int diamMax = 0;
	static int exc = 1;
	static int excMin = Integer.MAX_VALUE;
	static int []centralNodes;
	static int []peripheralNodes;

	
	public static void main(String[] args) {
		
		n = in.nextInt();  
		e = in.nextInt();  
		//matrix = new boolean[n+1][n+1]; 
		visited = new boolean[n+1];
		dist = new int[n+1][n+1];
		centralNodes = new int[n+1];
		peripheralNodes = new int[n+1];
		int count=0,count2=0;
		
		for(int i=0; i<=n; i++){
			listAdj.add(new LinkedList<Integer>());
		}
		for(int i=0; i<e; i++){
			int v1 = in.nextInt();
			int v2 = in.nextInt();
			listAdj.get(v1).add(v2);
			listAdj.get(v2).add(v1);
		}
		// perform a BFS on the graph
		bfs();
		System.out.println(diamMax);
		if(diamMax>2)
			System.out.println(excMin);
		if(diamMax<=2)
			System.out.println(1);
		
		for(int i=1;i<=n;i++){
			count=0;
			count2=0;
			for(int j=1;j<=n;j++){
				if(dist[i][j]<=excMin){
				  count++;	
				}
				if(dist[i][j]>=diamMax){
				  count2++;	
				}
			}
			if(count==n)
				centralNodes[i]=i;
			if(count2>0)
				peripheralNodes[i]=i;
		}
		count=0;
		for(int i=1; i<=n;i++){
			if(centralNodes[i]!=0){
			  count++;
			  if(count>=2)
				System.out.print(" ");  
			  System.out.print(centralNodes[i]);
			}
		}
		System.out.println();
		count=0;
		for(int i=1; i<=n;i++){
			if(peripheralNodes[i]!=0){
			  count++;
			  if(count>=2)
				System.out.print(" ");	
			  System.out.print(peripheralNodes[i]);
			}
		}
		System.out.println();
	}
	
	// perform a BFS on the graph G 
	public static void bfs(){
		for(int i = 1; i<=n;i++){
			bfs(i,visited);
		}
	}
	
	// perform a BFS starting at node start
	public static void bfs(int start, boolean[] v){
		Queue<Integer> q = new LinkedList<Integer>(); // a queue to process nodes
		
		// start with only the start node in the queue and mark it as visited
		q.offer(start);
		v[start]=true;
		dist[start][start] = 0;
		
		// continue searching the graph while still nodes in the queue
		while (!q.isEmpty()){
			int at = q.poll(); // get the head of the queue
			// add any unseen nodes to the queue to process, then mark them as visited so they don't get re-added
			for (int i=0; i<listAdj.get(at).size(); ++i){
				if (/*matrix[at][i] &&*/ !v[listAdj.get(at).get(i)]){
					q.offer(listAdj.get(at).get(i));
					v[listAdj.get(at).get(i)]=true;
					dist[start][listAdj.get(at).get(i)]=dist[start][at]+1;
					diam = dist[start][listAdj.get(at).get(i)];
					exc = dist[start][listAdj.get(at).get(i)]; 
				}
			}
		}
		if(diam>diamMax)
			diamMax=diam;
		
		if(exc<excMin)
			excMin=exc;
		
		clearVisited();	
	}
	
	public static void clearVisited(){
		int i;
		for(i=0; i < n+1; i++)
			visited[i] = false;
	}
}
