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
    int matriz[][];
    int diametro;
    int raio;

    Graph(int n) {
	this.n = n;
	matriz=new int[n+1][n+1];
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }
    public void fal(){
	for(int i=1;i<=n;i++)
	    nodes[i].visited=false;

}

    public void diametro(){
	diametro=0;
	
	for(int i=1;i<=n;i++){
	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>diametro)
		    diametro=matriz[i][j];

		}
		
	}
	System.out.println(diametro);


}

    public void raio(){
	raio=diametro;
	
	
	for(int i=1;i<=n;i++){
	int k=0;
	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>k)
		     k=matriz[i][j];
		

	}
		if(k<raio)
		   raio=k;
}
	
	System.out.println(raio);


}

    public void noscentrais(){
	boolean flag=true;

	for(int i=1;i<=n;i++){
	    int cent=0;

	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>cent)

		    cent= matriz[i][j];
	    }

		if(cent==raio && flag){
		    flag=false;

		    System.out.print(i);
		    
		}

		else if(cent==raio)

		    System.out.print(" " + i);
	}


}

    public void nosperifericos(){

	boolean flag1=true;

	for(int i=1;i<=n;i++){
	    int peri=0;

	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>peri)

		    peri= matriz[i][j];
	    }

	    if(peri==diametro && flag1){
		flag1 = false;

		System.out.print(i);
		
	    }
	    else if(peri==diametro)

		System.out.print(" " + i);
	}


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
	    matriz[v][u]=nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
    }
}

public class BFS {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n=in.nextInt();
	Graph g = new Graph(n);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
	for(int i=1;i<=n;i++){
	    g.fal();
	    g.bfs(i);	
	    
}
	g.diametro();
	g.raio();
	g.noscentrais();
	System.out.println();
	g.nosperifericos();
	System.out.println();
	
	/*for(int i=1;i<=n;i++){
	    for(int j=1;j<=n;j++){
		System.out.print(g.matriz[i][j] + " ");
			
	}
	System.out.println();
  }*/

	
	
	
    }
}
