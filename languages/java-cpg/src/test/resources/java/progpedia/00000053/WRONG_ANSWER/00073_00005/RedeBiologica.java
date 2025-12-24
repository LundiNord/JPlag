import java.io.*;
import java.util.*;

// Classe que representa um no
class NodeRB {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa

    NodeRB() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
    }
}

// Classe que representa um grafo
class GraphRB {
    int n;           // Numero de nos do grafo
    NodeRB nodes[];    // Array para conter os nos

    GraphRB(int n) {
	this.n = n;
	nodes  = new NodeRB[n+1]; // +1 se os comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new NodeRB();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v,int[][] m,int i) {
		LinkedList<Integer> q = new LinkedList<Integer>();


		for(int j=1;j<n+1;j++)
			nodes[j].visited = false;

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
	    	int u = q.removeFirst();
	    	m[i][u-1] = nodes[u].distance;
	    	for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
		    		q.add(w);
		    		nodes[w].visited  = true;
		    		nodes[w].distance = nodes[u].distance + 1; 
				}	    
		}
    }
}

public class RedeBiologica {
    public static void main(String args[]) {
	Scanner input = new Scanner(System.in);

	int max = 0;
	int diam = 0;
	int raio = 7;
	int centrais,perif;
	boolean flag = false;

	int n = input.nextInt();
	GraphRB g = new GraphRB(n);
	int   e = input.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(input.nextInt(), input.nextInt());

	int[][] m = new int[n][n];

	for(int i=0;i<n;i++)
		g.bfs(i+1,m,i);	

	/*for(int i=0;i<n;i++){
		for(int j=0;j<n;j++)
			System.out.print(m[i][j] + " ");
		System.out.println();
	}*/

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			if(m[i][j]>max)
				max = m[i][j];
		}
		if(max>=diam)
			diam = max;
		else if(max<raio)
			raio = max;

		max = 0;
	}

	System.out.println(diam);
	System.out.println(raio);

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++)
			if(m[i][j]>max)
				max = m[i][j];

		if(max==raio){
			if(flag == true)
				System.out.print(" ");

			flag = true;

			System.out.print(i+1);
		}

		max = 0;
	}

	flag = false;
	System.out.println();

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++)
			if(m[i][j]>max)
				max = m[i][j];

		if(max==diam){

			if(flag == true)
				System.out.print(" ");

			flag = true;

			System.out.print(i+1);
		}
			
		max = 0;
	}
    }
}