import java.io.*;
import java.util.*;

class OptM{
	
	public static int G[][];
	public static int cont[];
	public static int W[][];
	public static int d[];
	
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		
		int nr_N = scn.nextInt();
		int nr_A = scn.nextInt();
		int custoF = scn.nextInt();
		G = new int [nr_N+1][nr_N-1];
		cont = new int[nr_N+1];
		W = new int[nr_N+1][nr_N+1];
		d = new int[nr_N+1];
		
		for (int i=0; i<nr_A; i++){
			int x = scn.nextInt();
			int y = scn.nextInt();
			
			G[x][cont[x]++] = y;
			G[y][cont[y]++] = x;
			W[x][y] = W[y][x] = scn.nextInt();
		}
		
		Prim(1);
		
		int soma = 0;
		boolean flag = false;
		for (int i=1; i<=nr_N; i++){
			if (d[i] == -1){
				flag = true;
				break;
			}
			else soma += d[i];
		}
		
		if (flag) System.out.println("impossivel");
		else
			System.out.println("rendimento optimo: " + (soma - custoF*(nr_N-1)));
	}
	
	public static void Prim(int s){
		Arrays.fill(d, -1);
		d[s] = 0;
		
		PriorityQueue<Integer> Q = new PriorityQueue<Integer>(G.length,
			new Comparator<Integer>(){
				public int compare(Integer x, Integer y){
					return d[y]-d[x];
				}
			});
		
		boolean color[] = new boolean[G.length];
		
		Q.add(s);
		while (!Q.isEmpty()){
			int x = Q.remove();
			color[x] = true;
			
			for (int i=0; i<cont[x]; i++){
				int k = G[x][i];
				if (!color[k]){
					if (d[k] < W[x][k]){
						d[k] = W[x][k];
						Q.remove(k);
						Q.add(k);
					}
				}
			}
		}
	}
}