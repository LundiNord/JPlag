import java.util.*;

public class Redes {

	static int N; 
	static int L;
	static boolean adj[][];
	static boolean visitados[];

	static void dfs(int v) {
		visitados[v] = true;
		for (int i = 1; i <= N; i++)
			if (adj[v][i] && !visitados[i])
				dfs(i);
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		adj = new boolean[N + 1][N + 1];
		visitados = new boolean[N + 1];
		L = in.nextInt();
		for (int i = 0; i < L; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		int count = 0;
		for (int i = 1; i < N + 1; i++) {
			if (!visitados[i]) {
				count++;
				dfs(i);
			}
		}
		System.out.println(count);
	}
}
