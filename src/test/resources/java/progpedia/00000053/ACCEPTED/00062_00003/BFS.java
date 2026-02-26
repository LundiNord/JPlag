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
    int mdist[][];
    Graph(int n) {
	this.n = n;
	mdist = new int[n+1][n+1];
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }
    public void reset(){
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
	    mdist[v][u] = nodes[u].distance;
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
	for (int i=0; i<e; i++){ 
	    g.addLink(in.nextInt(), in.nextInt());
	}
	for(int i=1;i<=g.n;i++){
	    g.bfs(i);
	    g.reset();
	}
	int max= Integer.MIN_VALUE;
	int min= Integer.MAX_VALUE;
	int diam[] = new int[g.n+1];
	int cent[] = new int[g.n+1];
	int perif[] = new int[g.n+1];
	for(int i=1; i<=g.n; i++){
	    for(int j=1; j<=g.n; j++){
		if(g.mdist[i][j] > max){
		    diam[i] = g.mdist[i][j];
		}
		max = diam[i];
	    }
	}
	max=0;
	for(int i=1;i<=g.n;i++){
	    if(diam[i]>max)
		max=diam[i];
	    if(diam[i]<min)
		min=diam[i];
	}
	System.out.println(max);
	System.out.println(min);
	
	for(int i=1;i<=g.n;i++){
	    if(diam[i]==min)
		cent[i]=i;
	    else if(diam[i]==max)
		perif[i]=i;
	}

	Arrays.sort(cent);
	Arrays.sort(perif);

	for(int i=1;i<=g.n;i++){
	    if(cent[i]>0)
		System.out.print(cent[i] + " ");
	}
	System.out.println();
	for(int i=1;i<=g.n;i++){
	    if(perif[i]>0)
		System.out.print(perif[i] + " ");
	}
	System.out.println();
    }
}


