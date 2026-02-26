import java.util.*;
import java.io.*;

class Heapi {
    int index;
    Double value;

    Heapi(int index, Double value) {
	this.index = index;
	this.value = value;
    }
}

class Sardas {    
    static int n;
    static Double total = 0.0;

    public static void main(String args[]) throws IOException {
	BufferedReader scanf = new BufferedReader(new InputStreamReader(System.in));

	n = Integer.parseInt(scanf.readLine());
	int parente[] = new int[n];
	Double key[] = new Double[n];
	Double loc[][] = new Double[n][2], dis[][] = new Double[n][n];
	boolean vis[] = new boolean[n];

	PriorityQueue <Heapi> heap = new PriorityQueue<Heapi>(n, new Comparator<Heapi>() {
	    @Override
	    public int compare(Heapi x, Heapi y) {
	        return (x.value.compareTo(y.value));
	    }
        });

	for(int i=0; i<n; i++) {
	    String input[] = scanf.readLine().split(" ");
	    loc[i][0] = Double.parseDouble(input[0]);
	    loc[i][1] = Double.parseDouble(input[1]);
	}

	for(int i=0; i<n; i++)
	    for(int j=0; j<n; j++)
		dis[i][j] = Math.sqrt(Math.pow((loc[i][0] - loc[j][0]), 2) + Math.pow((loc[i][1] - loc[j][1]), 2));
 
	prim(heap, parente, key, dis, vis, 0);

	String resultado = String.format("%.2f", total);
	System.out.println(resultado.replaceAll(",","."));
    }
    
    static void prim(PriorityQueue <Heapi> heap, int parente[], Double key[], Double dis[][], boolean vis[], int v) {

	Arrays.fill(key, Double.POSITIVE_INFINITY);
	Arrays.fill(parente, -1);

	key[v] = 0.0;
	heap.add(new Heapi(v, key[v]));

	while(!heap.isEmpty()) {
	    Heapi out = heap.poll();
	    int u = out.index;
	    if(!vis[u]) { vis[u] = true; total += key[u]; }

	    for(int i=0; i<n; i++) {
		if(!vis[i] && dis[u][i] < key[i]) {
		    parente[i] = u;
		    key[i] = dis[u][i];
		    heap.add(new Heapi(i, key[i]));
		}
	    }
	}
    }
}
