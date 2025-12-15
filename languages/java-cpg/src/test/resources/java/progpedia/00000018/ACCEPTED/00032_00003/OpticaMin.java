import java.util.*;
import java.io.*;

class OpticaMin {
	
	public static int cMan;			// custos de manutencao
	public static int nNodes;
	public static int W[][];
	public static int colour[];
	public static int key[];		// usada no Prim
	public static ArrayList<ArrayList<Integer>> AdjList;
	
	public static void readGraph(Scanner scn) {
		nNodes = scn.nextInt();
		W = new int[nNodes][nNodes];
		colour = new int[nNodes];
		key = new int[nNodes];
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<nNodes; i++)
			AdjList.add(new ArrayList<Integer>());
		int nEdges = scn.nextInt();
		cMan = scn.nextInt();
		for (int i=0; i<nEdges; i++) {
			int x = scn.nextInt()-1;
			int y = scn.nextInt()-1;
			int w = scn.nextInt();
			AdjList.get(x).add(y);
			AdjList.get(y).add(x);
			W[x][y] = w;
			W[y][x] = w;
		}
	}
		
	public static void MST_Prim(int root) {
		for (int i=0; i<nNodes; i++)
			key[i] = -1;
		key[root] = 0;
		Queue<Integer> Q = new PriorityQueue<Integer>(nNodes, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return key[b]-key[a];
			}
		});
		for (int i=0; i<nNodes; i++)
			Q.add(i);
		while (!Q.isEmpty()) {
			int u = Q.poll();
			colour[u] = 2;
			for (int v: AdjList.get(u))
				if (colour[v] == 0 && W[u][v] > key[v]) {
					key[v] = W[u][v];
					Q.remove(v);		// update PriorityQueue
					Q.add(v);			// update PriorityQueue
				}
		}
	}
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		readGraph(scn);
		MST_Prim(0);
		boolean imp = false; 
		int ro = 0;			// rendimento optimo
		for (int i=0; i<nNodes; i++) {
			if (key[i] == -1) {imp = true; break; }
			ro += key[i];
		}
		ro -= cMan*(nNodes-1); 
		if (imp) 	System.out.println("impossivel");
		else 		System.out.println("rendimento optimo: " + ro);
	}
}