
//package bfs;

// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

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
    int matriz_distancia[][];
    int diametro;
    int raio;
    int excentricidade[];
    
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1];
        diametro = 0;
        matriz_distancia = new int[n+1][n+1];
        excentricidade = new int[n+1];// +1 se os comecam em 1 ao inves de 0
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
        int max = 0;
	while (q.size() > 0) {
	    int u = q.removeFirst();
            if(nodes[u].distance > max) max = nodes[u].distance;
	    matriz_distancia[v][u] = matriz_distancia[u][v] = nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
        excentricidade[v] = max;
        if(max > diametro) diametro = max;
    }
    
    public void calc_raio(){
        raio = diametro;
        for(int i=1; i<=n; i++){
            if(raio > excentricidade[i]) raio = excentricidade[i];
        }
    }
    
    public void clean_nodes(){
        for(int i = 1; i<=n; i++)
            nodes[i].visited = false;
    }
    
    public void print_matriz(){
        boolean aux = false;
        for(int i=1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(!aux){
                    System.out.print(" ");
                    aux = true;
                }
                System.out.print(matriz_distancia[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void print_centrais(){
        boolean aux = false;
        for(int i= 1; i<=n; i++){
            
            if(excentricidade[i]==raio){
                if(aux){
                    System.out.print(" ");
                    aux = true;
                }
                System.out.print(i);
            }
        }
        System.out.println();
    }
    
    public void print_perifericos(){
        boolean aux = false;
        for(int i= 1; i<=n; i++){
            if(excentricidade[i]==diametro){
                if(aux){
                    System.out.print(" ");
                    aux = true;
                }
                System.out.print(i);
            }
        }
        System.out.println();
    }
}

public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	Graph g = new Graph(in.nextInt());
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	for(int i = 1; i<=g.n; i++){
            g.clean_nodes();
            g.bfs(i);
        }
        System.out.println(g.diametro);
        g.calc_raio();
        System.out.println(g.raio);
        g.print_centrais();
        g.print_perifericos();
    }
}