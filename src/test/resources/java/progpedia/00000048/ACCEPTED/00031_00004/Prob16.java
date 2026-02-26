import java.io.*;
import java.util.*;


class Prob16{
	static int nvertices, narestas;
	static ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
	static boolean [] visitados;
	
	static void dfs(int node){
		visitados[node]=true;
		for(int i : graph.get(node)){
			if(!visitados[i]) dfs(i);
		}
	}
	
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		nvertices= in.nextInt()+1;
		narestas=in.nextInt();
		visitados= new boolean[nvertices];
		for(int i = 0; i < nvertices; i++){
			graph.add(new LinkedList<Integer>());
		}
		for(int i = 0; i < narestas; i++){
			int a,b;
			a=in.nextInt();
			b=in.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		int conexos = 0;
		for(int i = 1; i < nvertices; i++){
			if(!visitados[i]){
				dfs(i);
				conexos++;
			}
		}
		System.out.println(conexos);
	}
	
	
	
	
	
	
}
