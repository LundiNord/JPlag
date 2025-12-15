// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj;       
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public double distance;          // Distancia do no origem da pesquisa
    public double x;
    public double y;
    public int pai;
    public int index;
    
    Node(double x, double y, int index) {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = 999999;
	this.x =x;
	this.y =y;
	this.index = index;
    }
}

class Par implements Comparable<Par> {
    int no;
    double dist;

    Par(int n, double d) {
	no = n;
	dist = d;
    }

    public int compareTo(Par p) {
	if (dist < p.dist) return -1;
	if (dist > p.dist) return 1;
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
    public double prim(int n) {
	
	PriorityQueue<Par> q = new PriorityQueue<Par>();
	q.add(new Par(1, 0));

	double cost = 0;
	while (q.size() > 0) {
	    Par p = q.poll();
	    
	    if (!nodes[p.no].visited) {
		cost += p.dist;
		//System.out.println(p.no + " " + p.dist);
		nodes[p.no].visited = true;
		for (int w : nodes[p.no].adj) {
		    //System.out.println("!" + w + " " + Dist(nodes[p.no],nodes[w]));
		    if ( !nodes[w].visited && (Dist(nodes[p.no],nodes[w]) < nodes[w].distance)) {
			nodes[w].distance = Dist(nodes[p.no],nodes[w]);			
			q.add(new Par(w, nodes[w].distance));
		    }
		}

	    }
	}
	
	return cost;    
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
	
	    
	double s = g.prim(n);
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
