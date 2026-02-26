// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

/**
   Código feito por Pedro Ribeiro, adaptado por Fábio Queirós
   para exercício 19 
*/

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
    int exc[][];
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	exc = new int[n+1][n+1];
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }
    public void clean(){
	for(int i=1; i<nodes.length; i++){
	    nodes[i].visited = false;
	    nodes[i].distance = -1;
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
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    exc[v][u] = nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
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
	for(int i=1; i<=g.n; i++){
	    g.bfs(i);
	    g.clean();
	}

	int d = Integer.MIN_VALUE;
	int r = Integer.MAX_VALUE;
	
	for(int i=1; i<=g.n; i++){
	    int temp = Integer.MIN_VALUE;
	    
	    for(int j=1; j<=g.n; j++){
		if(g.exc[i][j] > temp){
		    temp = g.exc[i][j];
		}
		//System.out.print(g.exc[i][j]+" ");
	    }
	    if(temp > d){
		d = temp;		
	    }
	    if(temp < r){
		r = temp;
	    }
	    g.exc[i][0] = temp;
	    //System.out.println();
	}
	//diametro
	System.out.println(d);
	//raio
	System.out.println(r);

	LinkedList<Integer> central = new LinkedList<Integer>();
	LinkedList<Integer> perif = new LinkedList<Integer>();

	for(int i=1; i<=g.n; i++){
	    if(g.exc[i][0] == r){
		central.add(i);
	    }
	    for(int j=1; j<=g.n; j++){
		if(g.exc[i][j] == d){
		    perif.add(i);
		    break;
		}
	    }
	}
	
	for(int i=0; i<central.size(); i++){
	    if(i == central.size()-1){
		System.out.print(central.get(i));
		break;
	    }
	    System.out.print(central.get(i)+" ");
	}
	System.out.println();
	
	for(int i=0; i<perif.size(); i++){
	    if(i == perif.size()-1){
		System.out.print(perif.get(i));
		break;
	    }
	    System.out.print(perif.get(i)+" ");
	}
	System.out.println();
	
    }
}
