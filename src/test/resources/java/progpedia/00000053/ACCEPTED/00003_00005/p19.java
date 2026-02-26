import java.io.*;
import java.util.*;


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


class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];// Array para conter os nos
    int mat[][];

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	mat = new int[n+1][n+1];
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    public void putf(){
	for(int i=1;i<=n;i++)
	    nodes[i].visited = false;
	
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	
	while (q.size() > 0) {
	    int u = q.removeFirst();
	    mat[v][u]= nodes[u].distance;
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1;
		}	    
	}
    }
}

public class p19{
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
        
	int n = stdin.nextInt();
	Graph g = new Graph(n);
	int e = stdin.nextInt();
	for(int i=0;i<e;i++)
	    g.addLink(stdin.nextInt(), stdin.nextInt());

	for(int i=1;i<=n;i++){
	    g.putf();
	    g.bfs(i);
	    
	}
	        
	       
        
	boolean flag = true;
	boolean flag2 = true;
	
	int maxd =0;

	int array [] = new int[n+1];
	for(int i=1;i<=n;i++){
	    
	    for(int j=1;j<=n;j++){
		if(g.mat[i][j]>maxd)
		    maxd= g.mat[i][j];
	    }
	    
	}
        
        
	
	int mind= maxd;

	for(int i=1;i<=n;i++){
	    int excen=0;
	    for(int j=1;j<=n;j++){
		if(g.mat[i][j]> excen)
		    excen= g.mat[i][j];
		
	    }
	    if(excen<mind)
		mind=excen;
	}
	int raio = mind;



	System.out.println(maxd);
	System.out.println(raio);


	for(int i=1;i<=n;i++){
	    int nmax=0;
	    for(int j=1;j<=n;j++){
		if(g.mat[i][j]>nmax)
		    nmax= g.mat[i][j];
	    }
		if(nmax==raio && flag){
		    System.out.print(i);
		    flag=false;
		}
		else if(nmax==raio)
		    System.out.print(" " + i);
	}
	System.out.println();
	
	for(int i=1;i<=n;i++){
	    int nmax=0;
	    for(int j=1;j<=n;j++){
		if(g.mat[i][j]>nmax)
		    nmax= g.mat[i][j];
	    }
	    if(nmax==maxd && flag2){
		System.out.print(i);
		flag2 = false;
	    }
	    else if(nmax==maxd)
		System.out.print(" " + i);
	}
	System.out.println();
	    
	
	
    }

}
