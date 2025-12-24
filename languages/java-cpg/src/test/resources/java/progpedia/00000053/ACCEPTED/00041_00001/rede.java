// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)
// trabalho realizado com Diogo Ribeiro 201305327
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
    public int bfs(int v) {
	int maxdis = 0;
	LinkedList<Integer> q = new LinkedList<Integer>();

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
		    if(nodes[w].distance > maxdis){
			maxdis = nodes[w].distance;
		    }
		}	    
	}
	//System.out.println(maxdis + "\n" + minmaxdis);
	for(int i = 1; i <= n; i++)
	    nodes[i].visited = false;
	return maxdis;
    }
     
}

public class rede {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	Graph g = new Graph(in.nextInt());
	int v[] = new int[g.n+1];
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
	for(int i = 1; i <= g.n; i++)
	    v[i] = g.bfs(i);
	int maxdis = 0, minmaxdis= Integer.MAX_VALUE,indexmax=0,indexmin=0;
	for(int i = 1; i <= g.n; i++){
	    if(v[i] >= maxdis){
		maxdis = v[i];
		indexmax = i;
	    }
	    else if(v[i] <= minmaxdis){
		minmaxdis = v[i];
		indexmin = i;
	    }
	}
	//	System.out.println(indexmax + " " + indexmin);
	System.out.println(maxdis+ "\n" +minmaxdis);
	for(int i = 1; i<=g.n;i++)
	    if(v[i] == minmaxdis && indexmin != i)
		System.out.print(i + " ");
	    else if(v[i] == minmaxdis && indexmin == i)
		System.out.println(i);    
	for(int i = 1; i<=g.n;i++)
	    if(v[i] == maxdis && indexmax != i)
		System.out.print(i + " ");
	    else if(v[i] == maxdis && indexmax == i)
		System.out.println(i);
    }
}
