//Professor, fiz este programa com a ajuda do Rui Filipe da Costa Carvalho

// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;
import java.lang.Object.*;

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

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    if(nodes[u].distance > max)
		max = nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		}	    
	}
	for(int i = 1; i < n+1 ; i++)
	    nodes[i].visited = false;
	return max;
    }

    public void print(){
	int[] v1 = new int[n+1];
	int[] v2 = new int[n+1];

	for(int i=1 ; i<=n ; i++){
	    v1[i] = bfs(i);
	    v2[i] = v1[i];
	}

	Arrays.sort(v2);
	int d = v2[n];
	int r = v2[1];
	System.out.println(d);
	System.out.println(r);

	boolean flag = true;
	for(int i=1 ; i<n+1 ; i++){
	    if(v1[i] == r){
		if(flag == true)
		    System.out.print(i);
		else
		    System.out.print(" " + i);
		flag = false;
	    }
	}
	System.out.println("");

	flag = true;
	for(int i=1 ; i<n+1 ; i++){
	    if(v1[i] == d){
		if(flag == true)
		    System.out.print(i);
		else
		    System.out.print(" " + i);
		flag = false;
	    }
	}
	System.out.println("");

	
	
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
