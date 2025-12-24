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
    int mx[][];
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
	mx= new int[n+1][n+1];
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    public void start(){
	for(int i=1; i<=n;i++){
	    nodes[i].visited=false;
	    nodes[i].distance=-1;
	}
	
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    mx[v][u]=nodes[u].distance;
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    for (int w : nodes[u].adj)//
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	    
	}
    }
}

public class p19 {

    static int t;

    
    
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	t=in.nextInt();
	Graph g = new Graph(t);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
	
	
	
	for(int i=1; i<=t;i++){  
	    g.bfs(i);
	    g.start();
	    //mx[i][j]=g.nodes[j].distance;
	    
	}

	
	
        
	int di=0;
        for(int i=1;i<=t;i++)
	    for(int j=1;j<=t;j++)
		if(g.mx[i][j]>di)
		    di=g.mx[i][j];
	

	
	
	System.out.println(di);// diametro

	int vet[]=new int[t+1];
	
	for(int i=1; i<=t;i++){
	    int v2=0;
	    for(int j=1; j<=t;j++){
		if(g.mx[i][j]>v2)
		    v2=g.mx[i][j];
	    }
	    vet[i]=v2;
	}
	
	int v1=vet[1];
	for(int i=2;i<=t;i++)
	    if(vet[i]<v1)
		v1=vet[i];

	int raio=v1;
	System.out.println(raio);// raio


	
	boolean flag=true;

	for(int i=1;i<=t;i++){
	    if(vet[i]==raio && flag){
		System.out.print(i);
		flag=false;}
	    else if(vet[i]==raio)
		System.out.print(" " +i);
	}

	System.out.println();

	boolean flag2=true;

	for(int i=1;i<=t;i++){
	    if(vet[i]==di && flag2){
		System.out.print(i);
		flag2=false;}
	    else if(vet[i]==di)
		System.out.print(" " +i);
	}

	

	System.out.println();
	
	
    }
}
