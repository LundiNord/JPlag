import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public double distance;            // Distancia do no origem da pesquisa
    public double y;
    public double x;
    public int pai;

    Node(double x, double y) {
	adj = new LinkedList<Integer>();
	distance = 0.0;
	this.x = x;
	this.y = y;
	pai = 0;
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

    public double peso(int x, int y){

    	double p = Math.sqrt(((nodes[x].x - nodes[y].x) * (nodes[x].x - nodes[y].x)) + ((nodes[x].y - nodes[y].y)*(nodes[x].y - nodes[y].y)));
	return p;

    }
    // Algoritmo de pesquisa em largura
    public void Prim(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	
	for(int i=1; i<=n; i++){
		nodes[i].distance = Double.MAX_VALUE;
		q.add(i);
	}
	nodes[v].distance = 0;
	double min = Double.MAX_VALUE;
	int u = 0;
	int menor = 0;
	while (q.size() > 0) {
		min = Double.MAX_VALUE;
	    for(int i=1; i<=n; i++)
		if(q.contains(i) && nodes[i].distance < min){
			min = nodes[i].distance;
			menor = i;
		}
	
	    q.removeFirstOccurrence(menor);
		u = menor;
		
	    for (int w : nodes[u].adj) {
		double p = peso(u, w);
		if (q.contains(w) && p < nodes[w].distance)  {
		    nodes[w].pai = u;
		    nodes[w].distance = p;
		}	
	
	    }
		    
	}
	
    }
}
public class Sardas {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Graph g = 	new Graph(n);
	for (int i=1; i<=n; i++) 
	    g.nodes[i]= new Node(in.nextDouble(), in.nextDouble());	

	for(int i=1; i<=n; i++)
		for(int j=1; j<=n; j++)
			g.nodes[i].adj.add(j);

	// Pesquisa em largura a partir do no 1
	g.Prim(1);
	double t=0.0;
	for(int i=1; i<=n; i++)
		t += g.nodes[i].distance;	
	
	System.out.printf("%.2f\n", t);
	
    }
}

