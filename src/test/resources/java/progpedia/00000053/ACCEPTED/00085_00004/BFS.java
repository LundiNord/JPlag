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
    int max,n;// Numero de nos do grafo
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
	max = 1;
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
		
		if(nodes[u].distance > max)
			max= nodes[u].distance;
		
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
	for(int i = 1 ;i<=n;i++)
		nodes[i].visited =false;
		return max;
	}
}	

public class BFS {
    public static void main(String args[]) {
	int r=1,d=1,raio,diametro;
	Scanner in = new Scanner(System.in);
	Graph g = new Graph(in.nextInt());
	int   e = in.nextInt();
	int[] exc = new int[g.n+1];
	int[] centrais = new int[g.n+1];
	int[] perif = new int[g.n+1];
	for (int i=1; i<=e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
		for(int i=1;i<=g.n;i++)
			exc[i]=g.bfs(i);
		
		/*verificacao
		for(int i=1;i<=g.n;i++)
			System.out.print(exc[i]+" ");
		System.out.println();
		end of verificacao*/
		
		//raio e diametro
		raio=exc[1];
		diametro=exc[1];
		
		for(int i=1;i<=g.n;i++){
			if(exc[i]<raio)
				raio = exc[i];
			if(exc[i]>diametro)
				diametro=exc[i];
		}
		for(int i=1;i<=g.n;i++){
			if(exc[i]==raio)
				centrais[r++]=i;
				
			if(exc[i]==diametro)
				perif[d++]=i;
		}
		
		System.out.println(diametro);
		System.out.println(raio);
		//centrais
		for(int i=1;centrais[i]!=0;i++){
			if(centrais[i+1]==0)
				System.out.println(centrais[i]);
			else
				System.out.print(centrais[i] + " ");
		}
		//perifericos
		for(int i=1;perif[i]!=0;i++){
			if(perif[i+1]==0)
				System.out.println(perif[i]);
			else
				System.out.print(perif[i] + " ");
				
		}
    }
}