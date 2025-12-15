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
    public Node[] bfs(int v, int M_dist[][]) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	
	int no = v ;
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    		 
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	  
	    for (int w : nodes[u].adj)
	    	
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		}
	
	}
	
	for (int j=1; j<=n; j++){
		M_dist[no][j] = nodes[j].distance;
		M_dist[j][no] = nodes[j].distance;
		
    }
	for (int j=1; j<=n; j++) {
		nodes[j].distance = 0;
		nodes[j].visited = false;
	}
	return nodes;
    }
}
public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	Graph g = new Graph(n);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	int M_dist[][]= new int[n+1][n+1];
	// Pesquisa em largura a partir do no 1
	
	Node nos[]= new Node[n+1]; 
	for (int i=1; i<=n; i++) 
		nos = g.bfs(i, M_dist);
	
	int max;
	for (int i=1; i<=n; i++){
		max = 0;
		for (int j=1; j<=n; j++)
			if (max < M_dist[i][j])
				max = M_dist[i][j];
		nos[i].exc = max;
	}
	int max_total=0;
	int min_total=999;
	for (int i=1; i<=n; i++){
		if (max_total < nos[i].exc)
			max_total = nos[i].exc;
		if (min_total > nos[i].exc)
			min_total = nos[i].exc;
	}
	System.out.println(max_total);
	System.out.println(min_total);
	
	int s=0;
	for (int i=1; i<=n; i++)
		if (min_total == nos[i].exc){
			s++;
			if (s==1)
				System.out.print(i);
			else
				System.out.print(" " + i);
		}
	System.out.println();
	s=0;
	for (int i=1; i<=n; i++)
	if (max_total == nos[i].exc) {
		s++;
		if (s==1)
			System.out.print(i);
		else
			System.out.print(" " + i);
	}
	System.out.println();
	}
}








