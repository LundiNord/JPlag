import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa
	public int exc;

    Node() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
	exc =0;
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }


    // Algoritmo de pesquisa em largura
    public void bfs(int v, int n) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	for(int i=1; i <= n; i++)
		nodes[i].visited = false;

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	 
	   //System.out.println(u + " [dist=" + nodes[u].distance + "]");
		nodes[v].exc = nodes[u].distance;

	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
	//System.out.println("////////");
    }
}

public class p19 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	int n=in.nextInt();
	
	Graph g = new Graph(n);
	int   e = in.nextInt();

	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1

	int exc [] = new int [n+1];
	int exc1 [] = new int [n+1];

	for( int i=1; i<=n; i++ ){
		g.bfs(i, n);		
		exc[i] = g.nodes[i].exc;
		exc1[i] = g.nodes[i].exc;
	}

	Arrays.sort(exc1);

	System.out.println(exc1[exc1.length-1]);

	System.out.println(exc1[1]);

	for( int i=1; i<=n; i++)
		if( exc[i] == exc1[1] )
			System.out.print(i+" ");

	System.out.println();
	
	for (int i=1;i<=n ;i++ )
		if( exc[i] == exc1[exc1.length-1] )
			System.out.print(i+" ");
	
	System.out.println();	
	}
}