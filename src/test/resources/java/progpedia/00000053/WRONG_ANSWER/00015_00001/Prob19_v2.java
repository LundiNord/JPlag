import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
    public int distance;            // Distancia do no origem da pesquisa
    public int excentricidade;
    Node() {
	adj = new LinkedList<Integer>();
	visited = false;
	distance = -1;
	excentricidade = -1;
	
    }
}

// Classe que representa um grafo
class Graph {
	int diametro = 0;
	int raio = Integer.MAX_VALUE;
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
	nodes[v].excentricidade =0;

	while (!q.isEmpty()) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
			
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		    //System.out.println(w + " ///dist=" + nodes[w].distance + "]");
		  
		    if(nodes[v].excentricidade < nodes[w].distance)
		    	nodes[v].excentricidade = nodes[w].distance;
		    
		    
		    /*for (int i = 1; i <n+1; i++) {
	    	    if (nodes[i].distance > max) 
	    	    	max = nodes[i].distance;*/
		    
		}	
	    
	}
	if (nodes[v].excentricidade < raio && nodes[v].excentricidade != 0 )
		raio = nodes[v].excentricidade;
	if(nodes[v].excentricidade > diametro)
    	diametro = nodes[v].excentricidade;
	  //System.out.println(nodes[v].excentricidade);
		  

    
    }
	
    
    
    
   
    
}

public class Prob19_v2 {
	
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int num =in.nextInt();
	Graph g = new Graph(num);
	int   e = in.nextInt(); //arestas
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
	
	
	for(int i = 1; i <= num; i++){
		
		for(int j = 1; j < num ; j++)
			g.nodes[j].visited = false;
				
		g.bfs(i);
	
		
		
	}
	System.out.println(g.diametro);
	/*int raio=Integer.MAX_VALUE;
	for (int i = 0; i <= g.n+1; i++) {
	    if (g.nodes[i].excentricidade < raio)
	    	//&& g.nodes[i].excentricidade != 0
		raio = g.nodes[i].excentricidade;
	    
	}*/
	System.out.println(g.raio);
	
	int conta = 0;
	for (int i = 1; i <= num; i++) {
	    if (g.nodes[i].excentricidade == g.raio) { 
		if (conta != 0) System.out.print(" ");
		System.out.print(i);
		conta++;
	    }
	}
	System.out.println();
	conta = 0;
	for (int i = 1; i <= num; i++) {
	    if (g.nodes[i].excentricidade == g.diametro) { 
		if (conta != 0) System.out.print(" ");
		System.out.print(i);
		conta++;
	    }  
	}
	System.out.println();	
	
    }
}