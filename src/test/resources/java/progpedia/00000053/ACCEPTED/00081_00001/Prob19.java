// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Arrays;

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
    int matrix[][];		//matriz que guarda as distancia minimas
    
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
	matrix = new int[n+1][n+1];
	
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
	    matrix[v][u]= nodes[u].distance;
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
    }
    public void emptyNode(){
    	for(int i=1; i<=n;i++)
    		nodes[i].visited=false;
    }
    
    public void printMatrix(int[][] m){
    	for(int i=1; i<=n; i++){
    		System.out.println();
    		for(int j=1; j<=n;j++)
    			System.out.print(m[i][j] + " ");
    	}
    }
    
    public int excentricidade(int a){
    	int max=0;
    	for(int i=1; i<=n;i++){
    		if(matrix[a][i]>max)
    			max=matrix[a][i];
    	}
    	return max;
    }
}
public class Prob19 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	int diametro=0, raio=1000, nc=0, np=0, n;
	int[] noCentral, noPeriferico, temp1, temp2;
	
	n = in.nextInt();
	Graph g = new Graph(n);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
	temp1 = new int[e];
	temp2 = new int[e];
	// Pesquisa em largura a partir do no 1
	for(int i=1; i<=n; i++){
		//System.out.println("no inicial" + i);
		g.bfs(i);
		g.emptyNode();
		//g.printMatrix(g.matrix);
    }
	
	for(int i=1; i<=n; i++){
		int exce = g.excentricidade(i);
		if(diametro<exce)
			diametro = exce;
		if(raio>exce)
			raio=exce;
	}
    
	for(int i=1;i<=n;i++){
		int exce = g.excentricidade(i);
		if(exce==diametro){
			temp1[np]=i;
			//System.out.println("temp1[" + np + "]= "  + temp1[np]);
			np++;
		}
		if(exce==raio){
			temp2[nc]=i;
			//System.out.println("temp2[" + nc + "] = " + temp2[nc]);
			nc++;
		}
	}
	//printMatrix(temp1);
	//printMatrix(temp2);
	//System.out.println("nc = " + nc + "np = " + np);
	noPeriferico = new int[np];
	noCentral = new int[nc];
	for(int i=0;i<np; i++){
		noPeriferico[i]=temp1[i];
		//System.out.println("no periferico " + noPeriferico[i] + "temp1 " + temp1[i] + " i " + i);
	}
	//System.out.println(noPeriferico.length);
	for(int i=0;i<nc; i++)
		noCentral[i]=temp2[i];
	
	Arrays.sort(noCentral);
	Arrays.sort(noPeriferico);
	
	System.out.println(diametro);
	System.out.println(raio);
	System.out.print(noCentral[0]);
	for(int i=1; i<nc; i++)
		System.out.print(" " + noCentral[i]);
	System.out.println();
	System.out.print(noPeriferico[0]);
	for(int i=1; i<np; i++)
		System.out.print(" " + noPeriferico[i]);
    }
    
    static void printMatrix(int[] m){
    	for(int i=0;i<m.length;i++)
    		System.out.print(m[i] + " ");
    	System.out.println();
    }
}