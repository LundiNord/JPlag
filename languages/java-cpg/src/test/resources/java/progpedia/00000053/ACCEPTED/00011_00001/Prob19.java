// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa
    public Node() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    
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
	    for (int w : nodes[u].adj) //w percorre todos os nos de nodes[].adj(que é booleano)
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
	int   e = stdin.nextInt();
	int[] exc = new int[n + 1];
	for (int i=0; i<e; i++) 
	    g.addLink(stdin.nextInt(), stdin.nextInt());
	
	for(int j = 1; j <= n; j++)
	    for(int k = 1; k <= n; k++)
		dist[j][k] = 0;
	
	// Pesquisa em largura a partir do nó 1, até n
	for(int i = 1; i <= n; i++) g.bfs(i,n,dist);
	
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

	int[] central = new int[n + 2];
	int[] periferal = new int[n + 2];
	int pos_central = 0;
	int pos_periferal = 0;
	
	for(int i = 1;i <= n; i++)
	    if(exc[i] == raio){
		central[pos_central] = i;
		pos_central++;
	    }

	
	int flag = 0;
	for(int j = 1; j <= n; j++){
	    flag = 0;
	    for(int k = 1; k <= n; k++)
		if(dist[j][k] == diametro & flag == 0){
		    periferal[pos_periferal] = j;
		    flag = 1;
		    pos_periferal++;
		}
	}
	
	System.out.println(diametro);
	System.out.println(raio);
	for(int i = 0; i < pos_central - 1; i++)
	    System.out.print(central[i] +  " ");
	System.out.println(central[pos_central - 1]);
	
	for(int i = 0; i < pos_periferal - 1; i++)
	    System.out.print(periferal[i] + " ");
	System.out.println(periferal[pos_periferal - 1]);
    }
}
