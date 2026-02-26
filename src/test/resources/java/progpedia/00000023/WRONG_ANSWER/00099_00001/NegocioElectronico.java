import java.util.*;
import java.lang.*;

class Graph {
	LinkedList<Integer>[] adj;
	int[] parent, dist;
	int[][] distance;
	boolean[] visited;
	
	@SuppressWarnings("unchecked")
	Graph(int dim) {
		adj = new LinkedList[dim];
		parent = new int[dim];
		dist = new int[dim];
		distance = new int[dim][dim];
		visited = new boolean[dim];
		Arrays.fill(visited, false);
	}
	
	void printGraph() {
		for (int i = 0; i < adj.length; i++) {
			Iterator<Integer> it = adj[i].iterator();
			System.out.printf("%d ", i+1);
			while(it.hasNext()) {
				System.out.printf("%d ", it.next()+1);
			}
			System.out.println();
		}
	}
	
	void printMatrix() {
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++)
				System.out.printf("%d ", distance[i][j]);
			System.out.printf("\n");
		}
	}
	
	int extractMin(PriorityQueue<Integer> q) {
		Iterator<Integer> it = q.iterator();
		int min = Integer.MAX_VALUE;
		int indMin = -1;
		while (it.hasNext()) {
			int index = it.next();
			if (dist[index] < min) {
				min = dist[index];
				indMin = index;
			}
		}
		if (indMin == -1 && !q.isEmpty())
			indMin = q.peek();
		q.remove(indMin);
		return indMin;
	}
	
	PriorityQueue<Integer> dijkstra(int source) {
		PriorityQueue<Integer> s = new PriorityQueue<Integer>();
		Arrays.fill(parent, -1);
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		PriorityQueue<Integer> vertices = new PriorityQueue<Integer>();
		for (int i = 0; i < adj.length; i++)
			vertices.add(i);
		while (!vertices.isEmpty()) {
			int u = extractMin(vertices);
			if (dist[u] == Integer.MAX_VALUE) break;
			s.add(u);
			Iterator<Integer> it = adj[u].iterator();
			while (it.hasNext()) {
				int v = it.next();
				if (dist[v] > dist[u] + distance[u][v] && !visited[v]) {
					dist[v] = dist[u] + distance[u][v];
					parent[v] = u;
				}
			}
			visited[u] = true;
		}
		return s;
	}
	
	void output(PriorityQueue<Integer> s) {
		while (!s.isEmpty())
			System.out.printf("%d ", extractMin(s) + 1);
		System.out.printf("\n");
	}
}

class NegocioElectronico {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int nLojas = inp.nextInt();
		int lojaDest = inp.nextInt();
		String str;
		inp.nextLine();
		Graph mapa = new Graph(nLojas);
		for (int i = 0; i < mapa.adj.length; i++)
			mapa.adj[i] = new LinkedList<Integer>();
		while(!(str = inp.nextLine()).equals("-1")) {
			Scanner sc = new Scanner(str);
			int value1 = sc.nextInt()-1;
			int value2 = sc.nextInt()-1;
			int dist = sc.nextInt();
			mapa.distance[value1][value2] = mapa.distance[value2][value1] = dist;
			mapa.adj[value1].addLast(value2);
			mapa.adj[value2].addLast(value1);
		}
		PriorityQueue<Integer> lojas = new PriorityQueue<Integer>();
		lojas = mapa.dijkstra(lojaDest-1);
		/*Iterator<Integer> it = lojas.iterator();
		while (it.hasNext()) {
			int v = it.next();
			System.out.printf("%d ", v+1);
			System.out.printf("%d\n", mapa.dist[v]);
			
		}
		System.out.printf("\n");*/
		mapa.output(lojas);
	}
}