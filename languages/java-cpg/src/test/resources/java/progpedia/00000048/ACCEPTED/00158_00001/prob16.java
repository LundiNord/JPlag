import java.util.*;
import java.io.*;

class ArrayofLists extends ArrayList<Integer> {}

class prob16 {
	static int vertices;
	static int arestas;
	static boolean vis[];
	static int total = 0;
	static boolean first;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		vertices = in.nextInt();
		arestas = in.nextInt();

		ArrayofLists[] graph = new ArrayofLists[vertices+1];
		boolean vis[] = new boolean[vertices+1];

		prob16.vis = vis;

		for(int i=0; i<vertices+1; i++) {
			graph[i] = new ArrayofLists();
		}

		for(int i=0; i<arestas; i++) {
			int p1 = in.nextInt();
			int p2 = in.nextInt();

			graph[p1].add(p2);
			graph[p2].add(p1);
		}
		for(int i=1; i<vertices+1; i++) {
			if(!vis[i]) {
				dfs(i, graph);
				total++;
			}
		}
		System.out.println(total);
	}

	static void dfs(int i, ArrayofLists[] graph) {
		vis[i] = true;
		for(Integer s : graph[i]) {
			if(!vis[s]) dfs(s, graph);
		}
	}
}