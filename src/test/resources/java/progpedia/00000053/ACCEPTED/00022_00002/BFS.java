// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa
    public int excentricidade;

    Node() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
	excentricidade = -1;
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
    public void bfs(int v, int size) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    if(nodes[v].excentricidade < nodes[u].distance) 
		nodes[v].excentricidade = nodes[u].distance;
	    
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		    
		}
	    }

	for(int j=1; j<=size; j++)
	    nodes[j].visited=false;
    }

    public int excentricidade(int h){
	return nodes[h].excentricidade;
    }

    
}

public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	int num = in.nextInt();
	Graph g = new Graph(num);
	int   e = in.nextInt();

	
	for (int i=0; i<e; i++){ 
	    int a = in.nextInt();
	    int b = in.nextInt();
	    g.addLink(a, b);
	}

	int max    =  0;
	int maxpos[] = new int[num];
	int dracula=  0;

	int min    =  1501;
	int minpos[] = new int[num];
	int dooku  =  0;

	// Pesquisa em largura a partir do no 1
	for (int j=1; j<=num; j++){
	    g.bfs(j, num);
	    
	    if(max < g.excentricidade(j)){
		max = g.excentricidade(j);
		dracula = 0;
		maxpos[dracula] = j;
	    }

	    else if(max == g.excentricidade(j)){
		dracula++;
		maxpos[dracula] = j;
	    }

	    else if(min > g.excentricidade(j)){
		min = g.excentricidade(j);
		dooku = 0;
		minpos[dooku] = j;

	    }

	    else if(min == g.excentricidade(j)){
		dooku++;
		minpos[dooku] = j;
	    }
    
	}

	System.out.println( max );
	
	System.out.println( min );

	for(int i=0; i<=dooku; i++)
	    System.out.print( minpos[i] + " " );
	System.out.println();

	for(int i=0; i<=dracula; i++)
	    System.out.print( maxpos[i] + " ");
	System.out.println();

    }
}
