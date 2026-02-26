//Joana Silva up200908475
//Feito em grupo em aula pratica



import java.util.*;
import java.io.*;


class Node {
	public LinkedList<Integer> adj;
	public boolean visited;
	public int distance;

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	}
}

class Graph {
	int n;
	Node nodes[];

	int grDistance;
	
	
	Graph(int n) {
		this.n = n;
		nodes  = new Node[n+1];
		for(int i=1; i<=n; i++){
			nodes[i] = new Node();
		}
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	//BFS
	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while(q.size() > 0){
			int u = q.removeFirst();
			for(int w : nodes[u].adj){
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1; 
				}
			}
		}
			
		//eccentricity calculation
		grDistance = -1;
		for(int i=1; i<=n; i++){
			if(nodes[i].distance > grDistance){
				grDistance = nodes[i].distance;
			}
		}
		
	}


	//reset
	public void resetVisits(){
		for(int i = 1; i<=n; i++){
			nodes[i].visited = false;
		}
	}

}

public class prob19{
	public static void main(String args[]){

		Scanner in = new Scanner(System.in);

		int nNodes = in.nextInt();
		int e = in.nextInt();
		int [] excentricidade = new int [nNodes+1];

		Graph g = new Graph(nNodes);

		for(int i=0; i<e; i++){ 
			g.addLink(in.nextInt(), in.nextInt());
		}


		//Eccentricity
		for(int i=1; i<=nNodes; i++){
			g.bfs(i);
			excentricidade[i] = g.grDistance;
			g.resetVisits();
		}


		int diameter = 0;
		int radius = Integer.MAX_VALUE;
		

		for(int i=1; i<=nNodes; i++){
			if(excentricidade[i] > diameter)
				diameter = excentricidade[i];

			if(excentricidade[i] < radius)
				radius = excentricidade[i];
		}
		
		
		System.out.println(diameter + "\n" + radius);

		String center = new String();
		String periferico = new String();
		
		for(int i=1; i<=nNodes; i++){
			if(excentricidade[i]==radius)
				center += i+" ";
		}

		if(center.endsWith(" ")){
			  center = center.substring(0, center.length()-1);
		}

		for(int i=1; i<=nNodes; i++){
			if(excentricidade[i]==diameter)
				periferico += i+" ";
		}
		if(periferico.endsWith(" ")){
			periferico = periferico.substring(0, periferico.length()-1);
		}

		System.out.println(center + "\n" + periferico);
		
		
	}
}
