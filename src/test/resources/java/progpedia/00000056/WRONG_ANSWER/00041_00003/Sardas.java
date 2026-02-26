// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node implements Comparable<Node>{
    public LinkedList<Integer> adj;       
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public double distance;          // Distancia do no origem da pesquisa
    public double x;
    public double y;
    public int pai;
    int index;
    
    Node(double x, double y, int index) {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
	this.x =x;
	this.y =y;
	this.index = index;
    }

    @Override
    public int compareTo(Node no) {
	if (distance < no.distance) return -1;
	if (distance > no.distance) return 1;
	return 0;

	
    }
}






// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	

    }
	public void addLink(int a, int b) {
	    nodes[a].adj.add(b);
	    nodes[b].adj.add(a);
	}

    
    // Algoritmo de Prim
    public double prim(Graph g, Node no, int n) {
	
	for (int i=2; i<=n; i++) {
	    nodes[i].pai = -1;
	    nodes[i].distance = 99999;
	}
	nodes[1].distance = 0;
	PriorityQueue<Node> q = new PriorityQueue<Node>();
	for (int i=1; i<=n; i++)
	    q.add(nodes[i]);
	
	while (q.size() > 0) {
	    Node n1 = q.poll();
	    n1.visited = true;
	    for (int w : n1.adj)
		if ( nodes[w].visited==false && q.contains(nodes[w]) && (Dist(n1,nodes[w]) < nodes[w].distance)) {
		    nodes[w].pai = n1.index;
		    nodes[w].distance = Dist(n1,nodes[w]);
		    q.add(nodes[w]);		    
		}
	}
	double s = 0;
	for (int i=1; i<=n; i++)
	    s += g.nodes[i].distance;
	

	return s;
    
    }
    
	public double Dist( Node no1, Node no2) {
	    return Math.sqrt(Math.pow(no1.x-no2.x, 2) + Math.pow(no1.y-no2.y,2));
	}
    
}  
public class Sardas {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();

	Graph g = new Graph(n);
	
	for (int i=1; i<=n; i++) {
	    double x = in.nextFloat();
	    double y = in.nextFloat();
	    g.nodes[i] = new Node(x, y, i);
	}
	
	for (int i=1; i<=n; i++)
	    for (int j=i+1; j<=n; j++) 
		if (i!= j)
		    g.addLink(i, j);
	
	    
	double s = g.prim(g, g.nodes[1], n);
	System.out.printf( "%.2f" , s);
	System.out.println();
	
	// Imprime as adjacencias de cada no
	/*	for (int i=1; i<=n; i++) {
	    System.out.print(i+" :");
	    for(int j : g.nodes[i].adj) 
		System.out.print(" " + j + " " + g.nodes[j].distance);
	    System.out.println();
	    }
	*/
	
    }
}
