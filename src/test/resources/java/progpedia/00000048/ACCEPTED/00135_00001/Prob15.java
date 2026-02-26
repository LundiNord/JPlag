import java.util.*;

public class Prob15 {
	/* exemplo de DFS num grafo nao dirigido */
	static int n; // numero de nos do grafo
	static int l;// numero de ligacoes existentes
	static boolean adj[][];// matriz de adjacnetes
	static boolean visited[];// nos visitados

	static void dfs(int v) {
		visited[v] = true;
		for (int i = 1; i <= n; i++)
			if (adj[v][i] && !visited[i])
				dfs(i);
	}

	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);

		n = stdin.nextInt();
		adj = new boolean[n + 1][n + 1];
		visited = new boolean[n + 1];
		l = stdin.nextInt();
		for (int i = 0; i < l; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		int count = 0;
		for (int i = 1; i < n + 1; i++) {
			if (!visited[i]) {
				count++;
				dfs(i); // Pesquisa em profundidade a partir do no 1
			}
		}
		System.out.println(count);
	}
}
