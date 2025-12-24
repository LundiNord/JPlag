
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
    int[][] matrix;

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	matrix = new int[n+1][n+1];
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v, int s) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		LinkedList<Integer> q2 = new LinkedList<Integer>();

		q.add(v);
		q2.add(v);

		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
		    int u = q.removeFirst();
		    //System.out.println("i = " + v + " j = " + u + " val = " + nodes[u].distance);

		    matrix[v][u] = nodes[u].distance;
		   // System.out.println( matrix[v][u] + " na posicao i = " + v + " j = " + u);

		    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
		    for (int w : nodes[u].adj){
				if (!nodes[w].visited) {
			    q.add(w);
			    nodes[w].visited  = true;
			    nodes[w].distance = nodes[u].distance + 1; 
				}	   
			}
			
    	}
    	
    	nodes[v].visited = false;
		nodes[v].distance = 0;
		for(int i=1; i<=s; i++){
		    for (int w : nodes[i].adj){
				if (nodes[w].visited == true) {
			   		//q.addLast(w);
			   		nodes[w].visited  = false;
			    	nodes[w].distance = 0; 
				}	   
			}
	    }

	}
}

public class p19 {
	static int[] excen;

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

		int x = in.nextInt();
		Graph g = new Graph(x);
		int   e = in.nextInt();
		for (int i=0; i<e; i++) 
		    g.addLink(in.nextInt(), in.nextInt());

		// Pesquisa em largura a partir do no 1
		for(int i=1; i<=x ; i++){
			//System.out.println("Grafo para: " + i);
			g.bfs(i,x);		
		}

    	/*for(int i=1;i<=x;i++){
    		for(int j=1;j<=x;j++){
	    		System.out.print(g.matrix[i][j]);
	    	}
	    	System.out.println();
	    }*/

	   // System.out.println("-----Searching------");

	    diametro(g.matrix,x);
	    Arrays.sort(excen);
	    int diametro = excen[x];
	    System.out.println(diametro);

	    int raio = excen[1];
	    System.out.println(raio);

	    nosCentrais(g.matrix,raio,x);
	    System.out.println();
		nosPerifericos(g.matrix,diametro,x);
		System.out.println();	    

    }

    public static void diametro(int[][] matrix, int x){
    	
    	int max =0;
    	excen = new int[x+1];

    	for(int i =1; i<=x;i++){
    		for(int j=1; j<=x;j++){
    			if(matrix[i][j]>max)
    				max=matrix[i][j];
    		}
    		excen[i]=max;
    		max=0;
    	}
    }

    public static void nosCentrais(int[][] matrix,int raio,int x){
    	int cont=0;
    	boolean[] centrais = new boolean[x+1];

    	for(int i =1; i<=x;i++){
    		for(int j=1; j<=x;j++){
    			if(matrix[i][j]<=raio)
    				cont++;
    		}
    		if(cont==x){
    			centrais[i]=true;}
    		cont=0;
    	}
    	
    	for(int i=1;i<=x;i++){
    		if(centrais[i] == true)
    			System.out.print(i + " ");
    	}
    }

    public static void nosPerifericos(int[][] matrix,int diametro, int x){
    	boolean[] perifericos = new boolean[x+1];	

    	for(int i =1; i<=x;i++){
    		for(int j=1; j<=x;j++){
    			if(matrix[i][j]==diametro)
    				perifericos[i]=true;
    		}
    	}

    	for(int i=1;i<=x;i++){
    		if(perifericos[i] == true)
    			System.out.print(i + " ");
    	}
    }
}