// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

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
    int M[][];
    
    Graph(int n) {
	this.n = n;
	M = new int[n+1][n+1];
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
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		    M[v][w] = nodes[w].distance;
		    M[w][v] = nodes[w].distance;

		    /*for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++)
			    System.out.print(M[i][j] + " ");
			System.out.println(); 
		    }
		    System.out.println(); */
		}	    
	}
    }
}

public class Rede_B {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int e  = in.nextInt();
	Graph g = new Graph(n);
	for (int i=0; i<e; i++) 
	    g.addLink( in.nextInt(), in.nextInt());
	   
	for (int i=1; i<=n; i++) {
	    g.bfs(i);
	    for (int j=1;j<=n; j++)
		for (int w : g.nodes[j].adj)
		    g.nodes[w].visited=false;
	}
	    
	//Imprime as adjacencias de cada No
	/*for (int i=1; i<=n; i++) {
	    System.out.print("Nos adjacentes de " + i + ": ");
	    for (int w : g.nodes[i].adj)
		System.out.print(w + " ");
	    System.out.println();
	    }*/

	//Imprime a Matriz das distâncias mínimas entre dois nós do grafo 
	/*for (int i=1; i<=n; i++) {
	    for (int j=1; j<=n; j++)
		System.out.print(g.M[i][j] + " ");
	    System.out.println(); 
	    }*/

	
	int max_total = 0;
	int max_linha = 999;
	for (int i=1; i<=n; i++) {
	    int max_L_atual = 0;
	    for (int j=1; j<=n; j++) {
		if (g.M[i][j] > max_total)
		    max_total = g.M[i][j];
		if (g.M[i][j] > max_L_atual)
		    max_L_atual = g.M[i][j];
	    }
	    if (max_linha > max_L_atual)
		max_linha = max_L_atual;
	}

	System.out.println(max_total);
	System.out.println(max_linha);


	//Nos centrais ---tem excentricidade igual ao raio
	int ind = 0;
	for (int i=1; i<=n; i++) {
	    int max_L_atual = 0;
	    for (int j=1; j<=n; j++) {
		if (g.M[i][j] > max_L_atual)
		    max_L_atual = g.M[i][j];
	    }
	    if (max_L_atual == max_linha) {
		System.out.println(i);
		ind = i;
		break;
	    }
	}
	for (int i=ind+1; i<=n; i++) {
	    int max_L_atual = 0;
	    for (int j=1; j<=n; j++) {
		if (g.M[i][j] > max_L_atual)
		    max_L_atual = g.M[i][j];
	    }
	    if (max_L_atual == max_linha)
		System.out.print(" "+ i );
	}


	//Nos Perifericos ---tem excentricidade igual ao diametro
	ind = 0;
	for (int i=1; i<=n; i++) {
	    int max_L_atual = 0;
	    for (int j=1; j<=n; j++) {
		if (g.M[i][j] > max_L_atual)
		    max_L_atual = g.M[i][j];
	    }
	    if (max_L_atual == max_total) {
		System.out.print(i);
		ind = i;
		break;
	    }
	}
	for (int i=ind+1; i<=n; i++) {
	    int max_L_atual = 0;
	    for (int j=1; j<=n; j++) {
		if (g.M[i][j] > max_L_atual)
		    max_L_atual = g.M[i][j];
	    }
	    if (max_L_atual == max_total)
		System.out.print(" " + i);
	}
	System.out.println();
	
    }
}
