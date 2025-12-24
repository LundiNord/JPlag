import java.io.*;
import java.util.*;

class Queue {
	int queue[];
	int size;
	int first;
	int last;
	int cap;

	Queue (int c) {
		cap = c;
		queue = new int[c];
		size = first = last = 0;
	}

	boolean isEmpty() {
		return (size == 0);
	}

	int remove() {
		int r = queue[first];
		first = (first+1) % cap;
		size--;
		return r;
	}

	void add (int node) {
		queue[last] = node;
		last = (last+1) % cap;
		size++;
	}
}


class Graph {
	int V;
	int E;
	ArrayList<LinkedList<Integer>> nodeMap = new ArrayList<LinkedList<Integer>>();
	boolean visited[];
	boolean temCiclo = false;
	int ordem[];
	int cursor_ord = 0;
	int color[]; //0 = branco, 1 = cinzento, 2 = preto
	int dist[][];

	Graph (int vert, int edges, Scanner s) {
		V = vert+1;
		E = edges;
		visited = new boolean[V];
		ordem = new int[V];
		color = new int[V];
		dist = new int[V][V];
		for (int i = 0; i < V; i++) 
			nodeMap.add(new LinkedList<Integer>());
		for (int i = 0; i < E; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			addEdge(a,b);
			addEdge(b,a); //retirar esta linha para termos um grafo dirigido
		}
	}

	void addEdge (int v1, int v2) {
		nodeMap.get(v1).add(v2);
	}

	void dfs (int node) {
		visited[node] = true;
		for (int i : nodeMap.get(node))
			if (!visited[i])
				dfs(i);
	}

	void resetVisited () {
		for (int i = 0; i < V; i++) 
			visited[i] = false;
	}

	int conexos () {
		int count = 0;
		resetVisited();
		for (int i = 1; i < V; i++) {
			if (!visited[i]){
				count++;
				dfs(i);
			}
		}
		return count;
	}

	int[] ordem () {
		for (int i = 1; i < V; i++) 
			ordem[i] = -1;
		cursor_ord = 0;
		resetVisited();
		for (int i = 1; i < V; i++)
			if (!visited[i])
				dfs_ord(i);
		return ordem;
	}

	void dfs_ord (int node) {
		visited[node] = true;
		for (int i : nodeMap.get(node))
			if (!visited[i])
				dfs(i);
		ordem[cursor_ord] = node;
		cursor_ord++;
	}

	boolean ciclo () {
		for (int i = 1; i < V; i++)
			if (color[i] == 0)
				dfs_ciclo(i);
		return temCiclo;
	}

	void dfs_ciclo (int node) {
		color[node] = 1; //cinzento
		for (int i : nodeMap.get(node)) {
			if (color[i] == 1)
				temCiclo = true;
			else if (color[i] == 0) //branco
				dfs(i);
		}
		color[node] = 2; //preto
	}

	//todo: tarjan

	void bfs (int node) {
		resetVisited();
		Queue q = new Queue(V);
		q.add(node);
		visited[node] = true;
		while (!q.isEmpty()) {
			int u = q.remove();
			for (int i : nodeMap.get(u)) {
				if (!visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}

	void bfs_dist (int v) {
		resetVisited();
		Queue q = new Queue(V);
		q.add(v);
		dist[v][v] = 0;
		visited[v] = true;
		while (!q.isEmpty()) {
			int u = q.remove();
			for (int w : nodeMap.get(u)) {
				if (!visited[w]) {
					q.add(w);
					visited[w] = true;
					dist[v][w] = dist[v][u] + 1;
				}
			}
		}
	}
}

public class testGraph {
	public static void main(String[] args) {
		Scanner stdin = new Scanner (System.in);
		int nv = stdin.nextInt();
		int ne = stdin.nextInt();
		Graph g = new Graph (nv, ne, stdin);
		System.out.println(g.conexos());
	}
}