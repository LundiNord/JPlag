import java.util.*;
import java.io.*;
import java.lang.*;

class ArrayofLists extends ArrayList<Integer> {}

class prob21 {
	static List<Integer> nonvisited = new ArrayList<Integer>();
	static int nos;
	static int are;
	static int max_aux;
	static int min;
	static int maxi = 0;

	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		nos = Integer.parseInt(in.readLine());
		are = Integer.parseInt(in.readLine());
		ArrayofLists[] graph = new ArrayofLists[nos+1];
		boolean vis[][] = new boolean[nos+1][nos+1];
		int exc[][] = new int[nos+1][nos+1];
		int max[] = new int[nos+1];

		for(int i=1; i< nos+1;i++)
    		graph[i] = new ArrayofLists();

		for(int i=0; i<are; i++) {
			String aux[] = in.readLine().split(" ");
			graph[Integer.parseInt(aux[0])].add(Integer.parseInt(aux[1]));
			if (!graph[Integer.parseInt(aux[1])].contains(Integer.parseInt(aux[0]))) {
				graph[Integer.parseInt(aux[1])].add(Integer.parseInt(aux[0]));
			}
		}
		//VETOR DE MAXIMOS
		for(int i=1; i<nos+1; i++) {
			dfs_art(graph, exc, i);
			if (max_aux > max[i]) max[i] = max_aux;
		}

		//DIAMETRO
		for(int i=1; i<nos+1; i++) {
			if (max[i] > maxi) maxi = max[i];
		}
		System.out.println(maxi);

		//RAIO
		min = max[1];
		for(int i=1; i<nos+1; i++) {
			if (max[i] < min) min = max[i];  //Pode ser 0?
		}
		System.out.println(min);

		//CENTRAIS E PERIFERICOS
		String c = "";
		String p = "";
		for(int i=1; i<nos+1; i++) {
			if (max[i] == min) c = c + i + " ";
			if (max[i] == maxi) p = p + i + " ";
		}
		System.out.println(c.trim());
		System.out.println(p.trim());
	}

	static void dfs_art(ArrayofLists graph[], int exc[][], int v) {
		max_aux=0;
		boolean vis[] = new boolean[nos+1];
		nonvisited.clear();
		nonvisited.add(v);
		exc[v][v] = 0;
		vis[v] = true;
		while (!nonvisited.isEmpty()) {
			int u = nonvisited.get(0);
			nonvisited.remove(0);
			for (Integer s : graph[u]) {
				if (!vis[s]) {
					nonvisited.add(s);
					vis[s] = true;
					exc[v][s] = exc[v][u] + 1;
				}
				if (exc[v][s] > max_aux && exc[v][s] != 0) max_aux = exc[v][s];
			}
		}
	}
}