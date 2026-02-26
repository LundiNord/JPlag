import java.io.*;
import java.util.*;

class Graph {
	int V;
	int E;
	//int[] vertices;
	//int nv;
	boolean[][] edges;
	boolean[] visited;
	//boolean sorted = false;
	
	Graph (int vert, int edg) {
		V = vert+1;
		E = edg;
		//nv = 0;
		edges = new boolean[V][V];
		visited = new boolean[V];
		//vertices = new int[V];
	}
	
	/*void addVertice(T n) {
		if (nv < V) {
			vertices[nv] = n;
			nv++;
		}
		else throw new IndexOutOfBoundsException("too many vertices");
	}*/

	void addEdge(int v1, int v2) {
		/*if (!sorted) {
			Arrays.sort(vertices);
			sorted = true;
		}
		int p1 = Arrays.binarySearch(vertices, v1);
		int p2 = Arrays.binarySearch(vertices, v2);
		if (p1 < 0) throw new IllegalArgumentException("Vertice " + v1 + " not found!");
		if (p2 < 0) throw new IllegalArgumentException("Vertice " + v2 + " not found!");*/
		if (v1 > V) throw new IllegalArgumentException("Vertice " + v1 + " not found!");
		if (v2 > V) throw new IllegalArgumentException("Vertice " + v2 + " not found!");
		if (!edges[v1][v2]) {
			edges[v1][v2] = true;
		}
		if (!edges[v2][v1]) {
			edges[v2][v1] = true;
		}
		//non-directed graph. adjacency matrix is symmetrical
	}

	public String toString() { // removed vertice array
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < V; i++) {
			s.append(i + ": ");
			for (int j = 0; j < V; j++) {
				if (edges[i][j]) s.append(j + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public void dfs(int node) {
		visited[node] = true;
		for (int i = 0; i < V; i++) {
			if (edges[node][i] && !visited[i]) {
				dfs(i);
			}
		}
	}
}

public class prat16 {
	public static void main (String[] args) {
		Scanner stdin = new Scanner(System.in);
		int nv = stdin.nextInt();
		int ne = stdin.nextInt();
		Graph grafo = new Graph(nv, ne);
		for (int i = 0; i < ne; i++) {
			grafo.addEdge(stdin.nextInt(), stdin.nextInt());
		}
		int contador = 0;
		for (int i = 0; i <= nv; i++) {
			if(!grafo.visited[i]) {
				contador++;
				grafo.dfs(i);
			}
		}
		System.out.println(contador-1);
		//System.out.println(grafo.toString());
	}
}