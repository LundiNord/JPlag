import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class circuitosElectricos {
 
	static ArrayList<LinkedList<Integer>> grafo = new ArrayList<LinkedList<Integer>>();
	static boolean visited[];
	static int ne;
	static int nv;
	static int conexos;
	
	public static void dfs(Integer i){
		visited[i] = true;
		for (int j : grafo.get(i)){
			if (!visited[j]){
				dfs(j);
			}
		}
	}
	
	public static void main (String args[]){
		Scanner in = new Scanner(System.in);
		nv = in.nextInt()+1; // porque num grafo existem n-1 nos ?
		ne = in.nextInt();
		
		visited = new boolean[nv];
		
		for(int i = 0; i<nv ; i++){
			grafo.add(new LinkedList<Integer>());
		}
		
		// contrução do grafo
		for (int k = 0; k<ne; k++){
			int temp1 = in.nextInt();
			int temp2 = in.nextInt();
			
			grafo.get(temp1).add(temp2);
			grafo.get(temp2).add(temp1);
		}
		
		for (int i = 1; i<nv; i++){
			if (!visited[i]){
				dfs(i);
				conexos++;
			}
		}
		System.out.println(conexos);
	}
}

