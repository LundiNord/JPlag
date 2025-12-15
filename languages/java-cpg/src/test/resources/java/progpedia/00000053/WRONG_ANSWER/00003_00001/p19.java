import java.io.*;
import java.util.*;


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
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //   System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		}	    
	}
    }
}

public class p19{
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
        
	int n = stdin.nextInt();
	Graph g = new Graph(n);
	int e = stdin.nextInt();
	for(int i=0;i<e;i++)
	    g.addLink(stdin.nextInt(), stdin.nextInt());


	g.bfs(1);
	int maxd=0;
	for(int i=1;i<=n;i++)
	    if(g.nodes[i].distance > maxd)
		maxd = g.nodes[i].distance;
	
	System.out.println(maxd);

	



    }

}
