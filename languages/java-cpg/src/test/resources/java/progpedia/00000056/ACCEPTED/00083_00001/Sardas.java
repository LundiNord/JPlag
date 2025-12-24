// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)


//Tive ajude neste exercicio

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

	double custo = 0;
	while (q.size() > 0) {
	    Par par = q.poll();
	    
	    if (!nodes[par.no].visited) {
		custo = custo + par.dist;
		nodes[par.no].visited = true;
		for (int w : nodes[par.no].adj) {
		    if ( !nodes[w].visited && (Dist(nodes[par.no],nodes[w]) < nodes[w].distance)) {
			nodes[w].distance = Dist(nodes[par.no],nodes[w]);			
			q.add(new Par(w, nodes[w].distance));
		    }
		}

	    }
	}
	
	return custo;    
    }
    
	public double Dist( Node no1, Node no2) {
	    return Math.sqrt(Math.pow(no1.x-no2.x, 2) + Math.pow(no1.y-no2.y,2));
	}
    
}  
public class Sardas {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int numSardas = in.nextInt();

	Graph g = new Graph(numSardas);
	
	for (int i=1; i<=numSardas; i++) {
	    double x = in.nextFloat();
	    double y = in.nextFloat();
	    g.nodes[i] = new Node(x, y, i);
	}
	
	for (int i=1; i<=numSardas; i++)
	    for (int j=i+1; j<=numSardas; j++) 
		if (i!= j)
		    g.addLink(i, j);
	
	    
	double s = g.prim(numSardas);
	System.out.printf( "%.2f" , s);
	System.out.println();
	
    }
}
