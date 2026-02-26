import java.io.*;
import java.util.*;

class NegE{
	
	public static int G[][];
	public static int cont[];
	public static int W[][];
	
	public static int d[];
	
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		
		int nr_nos = scn.nextInt();
		int destino = scn.nextInt();
		G = new int[nr_nos+1][nr_nos-1];
		cont = new int[nr_nos+1];
		W = new int[nr_nos+1][nr_nos+1];
		
		int x = scn.nextInt();
		while (x != -1){
			int y = scn.nextInt();
			G[x][cont[x]++] = y;
			G[y][cont[y]++] = x;
			W[x][y] = W[y][x] = scn.nextInt();
			x = scn.nextInt();
		}
		
		d = new int[nr_nos+1];
		Dijkstra(destino);
	}
	
	public static void Dijkstra(int s){
		for (int i=1; i<G.length; i++)
			d[i] = Integer.MAX_VALUE;
		d[s] = 0;
		
		PriorityQueue<Integer> Q = new PriorityQueue<Integer>(G.length,
			new Comparator<Integer>(){
				public int compare(Integer x, Integer y){
					if (d[x] != d[y])
						return d[x]-d[y];
					return x-y;
				}
			});
		
		boolean color[] = new boolean[G.length];
		
		Q.add(s);
		while (!Q.isEmpty()){
			int x = Q.remove();
			System.out.print(x);
			color[x] = true;
			
			for (int i=0; i<cont[x]; i++){
				int k = G[x][i];
				if (!color[k]){
					if (d[k] > d[x]+W[x][k]){
						d[k] = d[x]+W[x][k];
						Q.remove(k);
						Q.add(k);
					}
				}
			}
			
			if (!Q.isEmpty())
				System.out.print(" ");
		}
		System.out.println();
	}
}