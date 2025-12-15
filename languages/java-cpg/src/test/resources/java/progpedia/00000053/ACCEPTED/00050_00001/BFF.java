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
    public static int diam, raio;
    public LinkedList<Integer> centrais;
    public LinkedList<Integer> perif;

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
	centrais = new LinkedList<Integer>();
	perif = new LinkedList<Integer>();
	diam = 0;
	raio = n;
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public int bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	for (int z=1; z<=n; z++)
	    nodes[z].visited = false;

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	int maior = 0;
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    if (nodes[u].distance>maior)
		maior = nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
	return maior;
    }
}

public class BFF {

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int vertices = in.nextInt();
	Graph g = new Graph(vertices);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	int diam = 0;
	int raio = vertices;
	LinkedList<Integer> centrais = new LinkedList<Integer>();
	LinkedList<Integer> perif = new LinkedList<Integer>();
	for (int i=1; i<=vertices; i++){
	    int temp = g.bfs(i);
	    if (temp>=diam){
		if (temp > diam){
		    while (!perif.isEmpty())
			perif.removeFirst();
		    diam = temp;
		}
		perif.add(i);
	    }
	    if (temp <= raio){
		if (temp < raio){
		    while(!centrais.isEmpty())
			centrais.removeFirst();
		    raio = temp;
		}
		centrais.add(i);
	    }

	}
	System.out.println (diam);
	System.out.println (raio);
	Collections.sort(centrais);
	Collections.sort(perif);
	while (centrais.size() > 0){
	    System.out.print(centrais.removeFirst());
	    if (centrais.size() > 0)
		System.out.print(" ");
	}
	System.out.println();
	while (perif.size() > 0){
	    System.out.print(perif.removeFirst());
	    if (perif.size() > 0)
		System.out.print(" ");
	}
	System.out.println();

    }
}
