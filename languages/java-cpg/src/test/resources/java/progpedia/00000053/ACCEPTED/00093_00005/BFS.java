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
    public void bfs(int v,int n,int distancia[][]) {
       	int i;
	LinkedList<Integer> q = new LinkedList<Integer>();
	for(i=1;i<=n;i++)
	    nodes[i].visited=false;
            q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    distancia[v][u]=nodes[u].distance;
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
	    int distancia[][]=new int[n+1][n+1];
	    Graph g = new Graph(n);
	    int e = in.nextInt();
	    int excentricidade[]=new int[n+1];
	    
	    for(int i=0;i<e;i++) 
		g.addLink(in.nextInt(),in.nextInt());
	    
	    for(int j=1;j<=n;j++){
		for(int k=1;k<=n;k++){
		    distancia [j][k]=0;
		}
	    }

	    for(int i=1;i<=n;i++){
		g.bfs(i,n,distancia);
	    }

	    int maximadistancia=0;
	    for(int j=1;j<=n;j++){
		for(int k=1;k<=n;k++)
		    if(distancia[j][k]>maximadistancia){
			maximadistancia=distancia[j][k];
		    }
		excentricidade[j]=maximadistancia;
		maximadistancia=0;
	    }
	    int raio=excentricidade[1];
	    int d=excentricidade[1];

	    for(int k=2;k<=n;k++){
		if(excentricidade[k]>d)
		    d=excentricidade[k];
		else if(excentricidade[k]<raio)
		    raio=excentricidade[k];
	    }

	    int noscentrais[]=new int[n+2];
	    int nosperifericos[]=new int[n+2];
	    int central=0;
	    int periferico=0;

	    for(int j=1;j<=n;j++){
		if(excentricidade[j]==raio){
			if(!Arrays.asList(noscentrais).contains(j)){
		   		noscentrais[central]=j;
		    	central++;
			}
		}
	    }
	    int f=0;
	    for(int i=1;i<=n;i++){
		f=0;
		for(int j=1;j<=n;j++){
		    if(distancia[i][j]==d & f==0){
		    	if(!Arrays.asList(nosperifericos).contains(i)){
			nosperifericos[periferico]=i;
			f=1;
			periferico++;
		}
		    }
		}
	    }
	    
	    System.out.println(d);
	    System.out.println(raio);
	    int m=0;
	    while(m<=central-1){
		System.out.print(noscentrais[m]+ " ");
		System.out.println(" ");
		m++;
	    }
    
	    for(int k=0;k<periferico-1;k++){
		if(k==periferico-1)
		    System.out.print(nosperifericos[k]);
		else 
	          System.out.print(nosperifericos[k] + " ");
	    }
	    System.out.println(nosperifericos[periferico-1]);
	
	        
	}
    }
    
