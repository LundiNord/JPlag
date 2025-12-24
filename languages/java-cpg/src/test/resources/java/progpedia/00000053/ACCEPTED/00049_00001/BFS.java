// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

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
    public int bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	int max = 1;
	
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	Collections.sort(q);
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    if (nodes[u].distance > max)
		max = nodes[u].distance;
	    for (int w : nodes[u].adj) {
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}
	    }
	    u++;
	}
	for(int i=1; i<=n; i++) {
	    nodes[i].visited = false;
	}
	return max;
    }
    public void print() {
	int vet [] = new int [n+1];
	int vet2 [] = new int [n+1];
	for (int i = 1; i<=n; i++) {
	    vet [i] = bfs(i);
	    vet2 [i] = bfs(i);
	}
	Arrays.sort(vet2);
	int d = vet2[n];
	int r = vet2[1];
	System.out.println(d);
	System.out.println(r);
	for(int i = 1; i<=n; i++) {
	    if(vet[i] == r)
		System.out.print(i + " ");
	}
	System.out.println();
	for(int i = 1; i<=n; i++) {
	    if(vet[i] == d)
		System.out.print(i + " ");
	}
	System.out.println();
	
	
    }
}

public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	Graph g = new Graph(in.nextInt());
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	g.print();		
    }
}
