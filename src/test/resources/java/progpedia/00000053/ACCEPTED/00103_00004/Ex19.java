import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Ex19 {
	
	static int [][] matriz = new int [1501][1501];
	
	// Classe que representa um no
	static class Node {
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
	static class Graph {
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

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
		    int u = q.removeFirst();
		    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
		    
		    matriz[v][u] = nodes[u].distance;
		    //System.out.println("v= " + v + "   u= " + u + "   distancia =" + nodes[u].distance);
		    
		    for (int w : nodes[u].adj)
			if (!nodes[w].visited) {
			    q.add(w);
			    nodes[w].visited  = true;
			    nodes[w].distance = nodes[u].distance + 1; 
			}	    
		}
	    }
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		Graph g = new Graph(in.nextInt());
		
		
		int   e = in.nextInt();
		for (int i=0; i<e; i++) 
		    g.addLink(in.nextInt(), in.nextInt());

		
		int diametro = 0;
		int raio = 0;
		int [] noscentrais = new int [g.n+1];
		int [] nosperifericos = new int [g.n+1];
		
		
		// Pesquisa em largura
		for(int x = 1; x < g.n+1 ; x++){
			g.bfs(x);
			for(int y = 1; y < g.n+1 ; y++){
				g.nodes[y].visited = false;
			}
			
			
			
			int exc = 0;
			
			
			for(int y = 1; y < g.n+1 ; y++){
				//System.out.println(x + " " + y);
				if(matriz[x][y] > exc){
					exc = matriz[x][y];
				}
				
				//System.out.println(total + " " + exc + " " + x + " " + y);
			}
			
			
			if(exc > diametro){
				diametro = exc;
			}
			else if(exc < raio || raio == 0){
				raio = exc;
			}
			
		}
		
		int a = 1;
		int b = 1;
		
		for(int x = 1; x < g.n+1 ; x++){
			boolean periferico = false;
			boolean central = true;
			
			for(int y = 1; y < g.n+1 ; y++){
				if(matriz[x][y] == diametro){
					periferico = true;
				}
				else if(matriz[x][y] > raio){
					central = false;
				}
			}
			
			
			if(periferico){
				nosperifericos[a] = x;
				a++;
			}
			else if(central){
				noscentrais[b] = x;
				b++;
			}
			
		}
		
		
		System.out.println(diametro);
		System.out.println(raio);
		
		boolean first = true;
		for(int x = 1; x < g.n+1 ; x++){
			if(noscentrais[x] == 0){
				break;
			}
			else if(first){
				System.out.print(noscentrais[x]);
				first = false;
			}
			else{
				System.out.print(" " + noscentrais[x]);
			}
			
		}
		System.out.println();
		
		first = true;
		for(int x = 1; x < g.n+1 ; x++){
			if(nosperifericos[x] == 0){
				break;
			}
			else if(first){
				System.out.print(nosperifericos[x]);
				first = false;
			}
			else{
				System.out.print(" " + nosperifericos[x]);
			}
			
		}
		System.out.println();
		
		
		
	}
	
	

}
