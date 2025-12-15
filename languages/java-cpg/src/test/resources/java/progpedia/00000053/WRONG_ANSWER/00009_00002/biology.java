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
    public void bfs(int v, int[][] matrix) {

	LinkedList<Integer> q = new LinkedList<Integer>();

	for(int i=1; i<=n; i++){
	    nodes[i].visited = false;
	}
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
       
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    /*   System.out.println(u + " [dist=" + nodes[u].distance + "]"); */
	    matrix[v][u]=nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
    }
       

}

public class biology {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	LinkedList<Integer> perifericos= new LinkedList<Integer>();
	Graph g = new Graph(in.nextInt());
int[][]	 matrix= new int[g.n+1][g.n+1];
	int   e = in.nextInt();
	for (int i=0; i<e; i++){ 
	    g.addLink(in.nextInt(), in.nextInt());
	}
	// Pesquisa em largura a partir do no 1
	for(int i=1;i<g.n+1; i++){
	    g.bfs(i,matrix);
	}

  
	/*	for(int i=1; i<=g.n; i++){
	    for(int j=1; j<=g.n; j++){
		System.out.print(matrix[i][j]+ " ");
	    }
	    System.out.println();
	    }*/
	int max=0;
	int maxi=0;
	int min=1000;
	int nocentral=0;
	for(int i=1; i<=g.n; i++){
	    maxi=0;
	    for(int j=1; j<=g.n; j++){
		if(matrix[i][j]>=max){
		    max=matrix[i][j];
		}
		if(matrix[i][j]>=maxi){
		    maxi=matrix[i][j];
		}
	    }
	    if(maxi<=min){
		min=maxi;
		nocentral=i;
	    }
	}
	boolean tem;
	for(int i=1; i<=g.n; i++){
	    tem=false;
	    for(int j=1; j<=g.n; j++){
		if(matrix[i][j]==max){
		    tem=true;
		}
	    }
	    if(tem){
		perifericos.add(i);
	    }
	}


	System.out.println(max);
	System.out.println(min);
	System.out.println(nocentral);
	for(int peri : perifericos){
	    //int peri= perifericos.remove();
	    System.out.print(peri + " ");
	}
	System.out.println();


    }
}		
    

