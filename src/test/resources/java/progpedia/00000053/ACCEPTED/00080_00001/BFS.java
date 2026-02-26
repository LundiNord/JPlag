// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

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
	exc = 0;
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    int d, r;
	
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
	this.d = 0;
	this.r = 1500;
	
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	int u = 0;
	while (q.size() > 0) {
	    u = q.removeFirst();
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
	int val = nodes[u].distance;
	if(val > d)
		d = val;
	if(val < r)
		r = val;
	nodes[v].exc = val;
    }
}

public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int d,		r;
	int h	=	0;
	int k 	=	0;
	int n 	= 	in.nextInt();
	Graph g = 	new Graph(n);
	int   e = 	in.nextInt();
	d 	= 	0;
	r 	= 	n;
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	for(int i = 1; i <= n; i++){
		g.bfs(i);
		for(int j=1; j<=n; j++)
			g.nodes[j].visited = false;	
	}
	System.out.println(g.d);
	System.out.println(g.r);
	for(int i=1; i<=n; i++)
		if(g.nodes[i].exc == g.r){
			if(h==1)
				System.out.print(" ");
			h = 1;
			System.out.print(i);
		}
	
	System.out.println();

	for(int j=1; j<=n; j++)
		if(g.nodes[j].exc == g.d){
			if(k==1)
				System.out.print(" ");
			k = 1;
			System.out.print(j);
		}

	System.out.println();
	
    }
}
