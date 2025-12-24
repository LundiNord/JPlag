// Em conjunto com Alberto Pinheira

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

class Grafo {
	int n;           
	Node nodes[];    
	int eccent; 
	
	Grafo(int n) {
		this.n = n;
		nodes  = new Node[n+1]; 
		for (int i=1; i<=n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}


	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
			int u = q.removeFirst();
			
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1; 
				}	    
		}
		
		eccent = -1; 
		for(int i=1; i<=n; i++) {
			if(nodes[i].distance  > eccent)
				eccent = nodes[i].distance;
		}
		
	}
	
	public int excentriCIdade() {
		return eccent;
	}

	
	public void unvisited() {
		for(int i = 1; i<=n; i++) {
			nodes[i].visited = false;
		}
	}
	
}

public class prob19{
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);

		Grafo g = new Grafo(stdin.nextInt());
		int   e = stdin.nextInt();
		
		for (int i=0; i<e; i++) 
			g.addLink(stdin.nextInt(), stdin.nextInt());

	
		int excentricidade[] = new int[g.n+1];
		
		for(int i = 1; i<=g.n;i++) {
			g.bfs(i);
			excentricidade[i] = g.excentriCIdade();
			g.unvisited();
		}
		
		int diametro = 0, raio = Integer.MAX_VALUE;
		

		for(int i = 1; i<=g.n;i++) {
			if (diametro < excentricidade[i]) {
				diametro = excentricidade[i];
			}
			if (raio > excentricidade[i]) {
				raio = excentricidade[i];
			}
		}
		
		System.out.println(diametro);
		System.out.println(raio);
		
		String centro = new String();
		
		for(int i = 1; i<=g.n; i++) {
			if(excentricidade[i] == raio) centro += i + " ";
		}
		
		if (centro.endsWith(" ")) {
			  centro = centro.substring(0, centro.length() - 1);
		}
		
		System.out.println(centro);
		String periferico = new String();
		
		for(int i = 1; i<=g.n; i++) {
			if(excentricidade[i] == diametro) periferico += i + " ";
		}
		
		if (periferico.endsWith(" ")) {
			periferico = periferico.substring(0, periferico.length() - 1);
		}
		
		System.out.println(periferico);

	}
}