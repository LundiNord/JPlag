import java.io.*;
import java.util.*;

class Encomenda {
	
	public static int nNodes;
	public static int D[];		// melhor comprimento
	public static int color[];
	public static int W[][];	// comprimentos maximos 
	public static ArrayList<ArrayList<Integer>> AdjList;
	public static int lmin, lmax, cmin, cmax, amin;
	public static int src, dest;
	
	public static TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
	public static LinkedList<Integer> list = new LinkedList<Integer>();
	
	/*
	 * usa uma TreeMap para criar indices a partir de 0 de forma a poucar memoria 
	 * como nao temos conhecimento do limite dos nos (no maximo o maximo inteiro)
	 */
	public static int getIndex(int key) {
		if (map.containsKey(key)) return map.get(key);
		map.put(key, map.size());
		return map.size()-1;
	}
	
	/*
	 * processa o input para determinar o numero de nos do grafo
	 * armazena a informacao necessaria para criar o grafo na lista list
	 * elimina arcos em que nao respeitam as dimensoes minimas do caixote
	 */
	public static int getNnodes(Scanner scn) {
		int x;
		while ((x = scn.nextInt()) != -1) {
			int y = scn.nextInt();
			int l = scn.nextInt();
			int c = scn.nextInt();
			int a = scn.nextInt();
			if (l >= lmin && c >= cmin && a >= amin) {
				list.add(getIndex(x));
				list.add(getIndex(y));
				list.add(c);
			}
		}
		return map.size();
	}
	
	public static void input(Scanner scn) {
		lmin = scn.nextInt();
		lmax = scn.nextInt();
		cmin = scn.nextInt();
		cmax = scn.nextInt();
		amin = scn.nextInt();
		src = getIndex(scn.nextInt());
		dest = getIndex(scn.nextInt());
		nNodes = getNnodes(scn);
		D = new int[nNodes];
		color = new int[nNodes];
		W = new int[nNodes][nNodes];
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<nNodes; i++)
			AdjList.add(new ArrayList<Integer>());
		while(!list.isEmpty()) {
			int x = list.removeFirst();
			int y = list.removeFirst();
			int c = list.removeFirst();
			AdjList.get(x).add(y);
			AdjList.get(y).add(x);
			W[x][y] = c;
			W[y][x] = c;
		}
	}
	
	public static void Dijkstra() {
		for (int i=0; i<nNodes; i++) 
			D[i] = -1;
		D[src] = Integer.MAX_VALUE;
		Queue<Integer> Q = new PriorityQueue<Integer>(nNodes, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				if (D[b] > D[a]) return 1;
				else if(D[b] < D[a]) return -1;
				else return 0;
				//return D[b]-D[a]; // overflow causa problemas
			}
		});
		for (int i=0; i<nNodes; i++)
			Q.add(i);
		while(!Q.isEmpty()) {
			int u = Q.poll();
			for (int v: AdjList.get(u)) 
				if (color[v] == 0) {
					int min = Math.min(D[u], W[u][v]);
					if (D[v] < min) {
						D[v] = min;
						Q.remove(v);
						Q.add(v);
					}
				}
			color[u] = 2;
		}
	}

	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		input(scn);
		Dijkstra();
		System.out.println((D[dest] != -1) ? D[dest] : 0 );
	}
}