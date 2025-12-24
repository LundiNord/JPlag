import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa
	public int exc; 


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
    public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();

		for(int i=1; i<=n; i++)
			nodes[i].visited = false;	

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;
		nodes[v].exc = 0;
		
		//System.out.println("Testar o no " + v);
		
		while (q.size() > 0) {
			int u = q.removeFirst();
			
			if(nodes[u].distance > nodes[v].exc)
				nodes[v].exc = nodes[u].distance;
			
			//System.out.println(u + " [dist=" + nodes[u].distance + "]");
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1; 
				}  
		}		
		//System.out.println(nodes[v].exc);
		//System.out.println(diametro);	
    }
}

public class prob19 {
    public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		Graph g = new Graph(N);
		int   e = in.nextInt();
		
		for (int i=0; i<e; i++) 
			g.addLink(in.nextInt(), in.nextInt());

		//Construir array para excentricidade
		int exc[] = new int[N-1];
				
		// Pesquisa em largura a partir do no 1
		for(int i=1; i<=N; i++)
			g.bfs(i);
			
		int diametro = 0;	//Caminho minimo entre 2 nos, maxima excentricidade
		int raio = 10000;	//No com menor excentricidade
		//int raioIndex = N;	//Posição da menor excentricidade
		
		for(int i=1; i<=N; i++){
			if(g.nodes[i].exc >= diametro)
				diametro = g.nodes[i].exc;
				
			if(g.nodes[i].exc <= raio)
				raio = g.nodes[i].exc;
		}

		System.out.println(diametro);
		System.out.println(raio);
		
		for(int i=1; i<=N; i++)
			if(g.nodes[i].exc == raio)
				System.out.print(i + " ");
				
		System.out.println();
		
		for(int i=1; i<=N; i++)
			if(g.nodes[i].exc == diametro)
				System.out.print(i + " ");
		
		System.out.println();
    }
}
