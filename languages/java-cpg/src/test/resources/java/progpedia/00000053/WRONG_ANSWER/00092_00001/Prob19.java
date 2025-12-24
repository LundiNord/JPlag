import java.io.*;
import java.util.*;


class Node {
    
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;
    public int distance;            // Distancia do no origem da pesquisa
    public Node() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
    }
}


class Graph {
    int n;           
    Node nodes[];    
    
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os nós comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }
    
    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }
    
    // Algoritmo de pesquisa em largura
    public void bfs(int v, int n, int[][] dist) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	
	for(int i = 1; i <= n; i++)
	    nodes[i].visited = false;
	
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    dist[v][u] = nodes[u].distance;
	    for (int w : nodes[u].adj) 
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
    }
}

public class Prob19 {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	
	int[][] dist = new int[n + 1][n + 1];
	
	Graph g = new Graph(n);
	
	int   a = stdin.nextInt();
	
	int[] exc = new int[n + 1];
	
	for (int i=0; i<a; i++) 
	    g.addLink(stdin.nextInt(), stdin.nextInt());
	
	for(int j = 1; j <= n; j++)
	    for(int k = 1; k <= n; k++)
		dist[j][k] = 0;
	
	// Pesquisa em largura a partir do nó 1, até n
	for(int i = 1; i <= n; i++)
	    g.bfs(i,n,dist);
	
	int max_distance = 0;
	
	for(int j = 1; j <= n; j++){
	    for(int k = 1; k <= n; k++)
		if(dist[j][k] > max_distance)
		    max_distance = dist[j][k];
	    exc[j] = max_distance;
	    max_distance = 0;
	}
	int raio = exc[1];
	int diametro = exc[1];
	
	for(int j = 2; j <= n; j++)
	    if(exc[j] > diametro)
		diametro = exc[j];
	    else if(exc[j] < raio)
		raio = exc[j];

	int[] central = new int[n + 1];
	int[] periferal = new int[n + 1];
	int pos_central = 0;
	int pos_periferal = 0;
	
	for(int i = 1;i <= n; i++)
	    if(exc[i] == raio){
		central[pos_central] = i;
		pos_central++;
	    }

	
	for(int j = 1; j <= n; j++)
	    if(exc[j] == diametro){
		periferal[pos_periferal] = j;
		pos_periferal++;
	    }

	System.out.println(raio);
	System.out.println(diametro);
	
	for(int i = 0; i < pos_central - 1; i++)
	    System.out.print(central[i] +  " ");
	System.out.println(central[pos_central - 1]);
	
	for(int i = 0; i < pos_periferal - 1; i++)
	    System.out.print(periferal[i] + " ");
	System.out.println(periferal[pos_periferal - 1]);
    }
}
