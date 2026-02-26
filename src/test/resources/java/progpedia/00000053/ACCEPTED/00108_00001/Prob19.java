import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitado numa pesquisa
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
    int matriz [][]; // matriz com os resultados
    int exct [];     //excentricidade
    
    Graph(int n) {
	matriz = new int [n+1][n+1];   
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void fls () {
	for (int i=1; i<=n; i++)
	    nodes[i].visited = false;
    }
    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	
	q.add(v);
	nodes[v].visited  = true;
	nodes[v].distance = 0;
	//	matriz[v][v]      = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    matriz[v][u] = nodes[u].distance;
	    for (int w : nodes[u].adj) {
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		    //matriz[w][u] = matriz[u][w] = nodes[w].distance;	    
		}
	
	    }
	}
    }

    public void exct () {
	exct     = new int[n+1];
	int dim  = 0;

	for (int i=1; i<=n; i++) {
	    exct[i] = 0;
	    for (int j=1; j<=n;j++)
		if (exct[i] < matriz[i][j])
		    exct[i] = matriz[i][j];
	}
	//Diametro
	for (int i=1; i<=n; i++) {
	    if (dim < exct[i])
		dim = exct[i];
	}

	System.out.println(dim);

	//Raio
	int raio = dim;

	for (int i=1; i<=n; i++) {
	    if (raio > exct[i])
		raio = exct[i];
	    // System.out.println(exct[i]);
	}
	
	System.out.println(raio);
	
	//Centrais
	boolean aux = true;
	for (int i=1; i<=n; i++) {
	    if (exct[i] == raio && aux){
		aux = false;
		System.out.print(i);
	    }
	    else if (exct[i] == raio)
		System.out.print(" " + i);
	}
	System.out.println();
	    
	//Perifericos
	aux = true;
	for (int i=1; i<=n; i++) {
	    if (exct[i] == dim && aux) {
		System.out.print(i);
		aux = false;
	    }
	    else if (exct[i] == dim)
		System.out.print(" " + i);
	}
	System.out.println();
	
    }
}

public class Prob19 {

    static int n;
    static int e;
    
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	
	n        = stdin.nextInt();
	Graph g  = new Graph(n);
	e        = stdin.nextInt();
	
	for (int i=0; i<e; i++) 
	    g.addLink( stdin.nextInt(), stdin.nextInt());
	
	for (int i=1; i<=n; i++) {
	    g.fls();
	    g.bfs(i);	
	}
	g.exct();
	
    }
}