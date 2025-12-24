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
    public void bfs(int v, int[][] md) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	for(int i=1;i<=n;i++){
	    nodes[i].visited = false;
	}

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    md[v][u] = nodes[u].distance;
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj){
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}
	    }
	}
    } 
    
    public int excentricidade(int linha, int[][] md){
	int dist = 0;
	for(int i=1;i<this.n;i++){
	    if(md[linha][i]>dist){
		dist = md[linha][i];
	    }
	}
	return dist;
    }

    public int diametro(int[][] md){
	int maxD = 0;

	for(int i=1;i<=this.n;i++){
	   int distance =  excentricidade(i, md);
	   if(distance > maxD){
	       maxD = distance;
	   }
	}
	return maxD;
    }
    public int raio(int[][] md){
	int menorExc=100;
	for(int i=1;i<=this.n;i++){
	    int distance = excentricidade(i, md);
	    if(distance < menorExc){
		menorExc = distance;
	    }
	}
	return menorExc;
    }
    public void centralNodes(int[][] md){
	int raioD = raio(md);
	boolean space = true;
	for(int i=1;i<=this.n;i++){
	    int distance = excentricidade(i, md);
	    if(raioD == distance){
		if(space == true){
		    space = false;
		    System.out.print(i);
		}else if(space == false){
		    System.out.print(" " + i);
		}
		
	    }
	}
	System.out.println();
    }
    public void periferic(int[][] md, int d){
	boolean space = true;
	for(int i=1;i<this.n;i++){
	    int dist = excentricidade(i, md);
	    if(dist == d){
		if(space == true){
		    System.out.print(i);
		    space = false;
		}else if(space == false){
		    System.out.print(" " + i);
		}
			
	    }
	}
    }
}

public class BFS {

    static Graph g;
    public static void main(String args[]) {
	int[][] matrizDistancias;
	Scanner in = new Scanner(System.in);
	
	int nos = in.nextInt();
	g = new Graph(nos);
	matrizDistancias = new int[nos+1][nos+1];
	int   e = in.nextInt();
	for (int i=0; i<e; i++){ 
	    g.addLink(in.nextInt(), in.nextInt());
	}
	// Pesquisa em largura a partir do no 1
	for(int i=1;i<=nos;i++){
	    g.bfs(i, matrizDistancias);
	}
	int dia = g.diametro(matrizDistancias);
	int raio = g.raio(matrizDistancias);
	System.out.print(dia + " \n" + raio + "\n");
	g.centralNodes(matrizDistancias);
	g.periferic(matrizDistancias, dia);
    }
}
