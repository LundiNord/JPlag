import java.util.*;
import java.lang.*;

class Graph {
	LinkedList<Integer>[] adj;
	int[] parent, key;
	int[][] cost;
	boolean[] visited;
	
	@SuppressWarnings("unchecked")
	Graph(int dim) {
		adj = new LinkedList[dim];
		parent = new int[dim];
		key = new int[dim];
		cost = new int[dim][dim];
		visited = new boolean[dim];
		Arrays.fill(visited, false);
	}
	
	boolean findValue(PriorityQueue<Integer> q, int value) {
		if (q.isEmpty()) return false;
		Iterator<Integer> it = q.iterator();
		while(it.hasNext()) {
			int val = it.next();
			if(val == value)
				return true;
		}
		return false;
	}
	
	int extractMax(PriorityQueue<Integer> q) {
		Iterator<Integer> it = q.iterator();
		int max = Integer.MIN_VALUE;
		int indMax = -1;
		while (it.hasNext()) {
			int index = it.next();
			if (key[index] > max){
				max = key[index];
				indMax = index;
			}
		}
		if (indMax == -1 && !q.isEmpty()) 
			indMax = q.peek();
		q.remove(indMax);
		return indMax;
	}
	
	String primMST() {
		Arrays.fill(parent, -1);
		Arrays.fill(key, Integer.MIN_VALUE);
		key[0] = 0;
		PriorityQueue<Integer> vertices = new PriorityQueue<Integer>();
		for (int i = 0; i < adj.length; i++)
			vertices.add(i);
		while(!vertices.isEmpty()) {
			int u = extractMax(vertices);
			Iterator<Integer> it = adj[u].iterator();
			while (it.hasNext()) {
				int v = it.next();
				if (!visited[v] && findValue(vertices, v) && key[v] < cost[u][v]) {
					parent[v] = u;
					key[v] = cost[u][v];
				}
			}
			visited[u] = true;
		}
		int count = 0;
		for (int i = 0; i < key.length; i++) {
			if (i != 0 && key[i] == Integer.MIN_VALUE)
				return "impossivel";
			count += key[i];
		}
		return "rendimento optimo: " + count;
	}
	
}

class OpticaMinimalista {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int nNodes = inp.nextInt();
		int nConnections = inp.nextInt();
		int costMan = inp.nextInt();
		Graph optica = new Graph(nNodes);	
		for (int i = 0; i < nNodes; i++) 
			optica.adj[i] = new LinkedList<Integer>();
		for (int i = 0; i < nConnections; i++) {
			int value1 = inp.nextInt()-1;
			int value2 = inp.nextInt()-1;
			int prof = inp.nextInt() - costMan;
			optica.cost[value1][value2] = prof;
			optica.cost[value2][value1] = prof;
			optica.adj[value1].addLast(value2);
			optica.adj[value2].addLast(value1);
		}
		System.out.printf("%s\n", optica.primMST());
	}
}

