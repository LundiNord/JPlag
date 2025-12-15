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
    int[] exc;
    
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	exc = new int[n+1];
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	exc[v]= Integer.MIN_VALUE;
	
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		    if(nodes[w].distance>exc[v]) exc[v]=nodes[w].distance;
		}
	}

	
    }
}

public class redeBio {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int dia=Integer.MIN_VALUE;
	int raio=Integer.MAX_VALUE;

	int k=0;
	int h=0;

	Graph g = new Graph(in.nextInt());
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	for(int i=1; i<=g.n; i++){
	    g.bfs(i);
	    for(int u=1;u<=g.n; u++)
		g.nodes[u].visited = false;
	}

	for(int i=1; i<=g.n; i++){
	    if(g.exc[i]>dia) dia=g.exc[i];
	    if(g.exc[i]<raio) raio=g.exc[i];	  
	}

	System.out.println(dia);
	System.out.println(raio);

	//nos centrais
	for(int i=1; i<=g.n; i++){
	    if(raio == g.exc[i]) {
		if(h==1)
		    System.out.print(" ");
		h=1;
		System.out.print(i);
	    }
	}

	System.out.println();
	
	//nos perifericos
	for(int i=1; i<=g.n; i++){
	    if(dia == g.exc[i]){
		if(k==1)
		    System.out.print(" ");
		k=1;
		System.out.print(i);
	    }
	}
	
	System.out.println();
    }
}
