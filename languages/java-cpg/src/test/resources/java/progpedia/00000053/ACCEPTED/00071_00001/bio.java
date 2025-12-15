
import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa

    Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    public int distances[][];

    Graph(int n) {
		this.n = n;
		distances = new int[n][n];
		
		for(int[] row : distances)
			Arrays.fill(row,0);
		
		nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
		
		for (int i=1; i<=n; i++)
		    nodes[i] = new Node();
	}

    public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

    public void makeFalse(){

    	for(int i=1; i<n+1;i++){
    		nodes[i].visited=false;
    	}

    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
		    int u = q.removeFirst();
		    distances[u-1][v-1] = distances[v-1][u-1] = nodes[u].distance;
			    for (int w : nodes[u].adj)
					if (!nodes[w].visited ) {
					    q.add(w);
					    nodes[w].visited  = true;
					    nodes[w].distance = nodes[u].distance + 1; 
					}	    
		}
	
	}
}

public class bio{
    public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int radius=Integer.MAX_VALUE;
    	int diameter=0;

    	LinkedList<Integer> central = new LinkedList<Integer>();
    	LinkedList<Integer> peripheral = new LinkedList<Integer>();
		Graph g = new Graph(in.nextInt());
		int   e = in.nextInt();
		for (int i=0; i<e; i++) 
		    g.addLink(in.nextInt(), in.nextInt());

		for (int j=1; j<g.n+1; j++){
			g.bfs(j);
			g.makeFalse();
			}	


	    for (int[] rows : g.distances) {
	    	Arrays.sort(rows);
	    	diameter = Math.max(diameter, rows[g.n-1]);
	    	radius = Math.min(radius, rows[g.n-1]);
	    }

	    System.out.println(diameter);
	    System.out.println(radius);
	    int aux=0;
	   	for (int[] rows : g.distances) {
	   		aux++;
	   		Arrays.sort(rows);
	   		if(rows[g.n-1]==radius) central.add(aux);
	   		if(rows[g.n-1]==diameter) peripheral.add(aux);
	   	}

	   	int k;
		while(!central.isEmpty()){
			k = central.removeFirst();
			if (central.isEmpty()) System.out.println(k);
			else System.out.print(k + " ");
		}
		
		while(!peripheral.isEmpty()){
			k = peripheral.removeFirst();
			if (peripheral.isEmpty()) System.out.println(k);
			else System.out.print(k + " ");
		}    
    }


}