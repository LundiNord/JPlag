import java.util.*;
import java.io.*;
import java.text.*;

class heapi {
	int index;
	Double valor;

	heapi(int index, Double valor) {
		this.index = index;
		this.valor = valor;
	}
}

class prob24 {
	static int n;
	static int c;
	static Double total=0.00;
	static Double key[];
	static int parent[];
	static boolean[] vis;
	static boolean[] vis2;

	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		Double[][] a = new Double[n][2];
		Double[][] dist = new Double[n][n];
		Double[] key = new Double[n];
		int[] parent = new int[n];
		boolean[] vis = new boolean[n];
		boolean[] vis2 = new boolean[n];

		prob24.key = key;
		prob24.parent = parent;
		prob24.vis = vis;
		prob24.vis2 = vis2;

		PriorityQueue<heapi> heap = new PriorityQueue<heapi>(n, new Comparator<heapi>() {
    		@Override
    		public int compare(heapi o1, heapi o2) {
        		return (o1.valor.compareTo(o2.valor));
    		}
		});

		for(int i=0; i<n; i++) {
			String input[] = in.readLine().split(" ");
			a[i][0] = Double.parseDouble(input[0]);
			a[i][1] = Double.parseDouble(input[1]);
		}

		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				Double primeiro = (a[j][0] - a[i][0])*(a[j][0] - a[i][0]);
				Double segundo = (a[j][1] - a[i][1])*(a[j][1] - a[i][1]);
				dist[i][j] = Math.sqrt(primeiro + segundo);
				dist[j][i] = Math.sqrt(primeiro + segundo);
			}
		}
		prim(a, dist, heap, 0);
		String result = String.format("%.2f", total);
		System.out.println(result.replaceAll(",", "."));
	}

	static void prim(Double[][] a, Double[][] dist, PriorityQueue<heapi> heap, int x) {
		for(int i=0; i<n; i++) {
			key[i] = Double.POSITIVE_INFINITY;
			parent[i] = -1;
		}
		key[x] = 0.0;
		heap.add(new heapi(x, key[x]));
		while(!heap.isEmpty()) {
			heapi s = heap.poll();
			int u = s.index;

			if(!vis[u]) { 
				vis[u] = true; 
				total += key[u];
			}

			for(int i=0; i<n; i++) {
				if(!vis[i] && dist[u][i] < key[i]) {

					parent[i] = u;
					key[i] = dist[u][i];
					heap.add(new heapi(i, key[i]));
				}
			}
		}
	}
}