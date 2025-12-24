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
    public int bfs(int v) {

	LinkedList<Integer> q = new LinkedList<Integer>();
	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;
	int excentricidade = 1;

	while (q.size() > 0) {
	    int u = q.removeFirst();
		
	    if(excentricidade < nodes[u].distance)
		excentricidade =  nodes[u].distance;
	    
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited  = true;
		    nodes[w].distance = nodes[u].distance + 1; 
		}	   
	}  
	for(int x=1; x<=n; x++)
	    nodes[x].visited = false;
	
	return excentricidade;	  
    }
    public void print(){

	int c=1;
	int[] excent = new int[n+1]; //array com maior excentricidade de cada nó

      	while(c<=n){
	    excent[c] = bfs(c);
	    c++;    
	}
	int[] SortedExcent = new int[n+1];   //SortedExcent = Sort( excent )
	for(int i=1; i<=n; i++)
	    SortedExcent[i] = excent[i];
	Arrays.sort(SortedExcent);

	System.out.println(SortedExcent[n]);   // 1ª posicao é o raio
	System.out.println(SortedExcent[1]);   // ultima posicao é o diametro

	int flag=0;
	if(excent[1] == SortedExcent[1]){
	    flag=1;
	    System.out.println("1");    }
	for(int i=2; i<=n; i++)                
	    if(excent[i]==SortedExcent[1])   //nos com excentricidad igual ao raio (igual a 1ª posicao do SortedExcent)
		if(flag==0)
		    System.out.print(i);
		else
		    System.out.print(" "+i);
	System.out.println();
	
	int flag2=0;
	if(excent[1] == SortedExcent[n]){
	    flag=1;
	    System.out.print("1");      }
	for(int i=2; i<=n; i++)                
	    if(excent[i] == SortedExcent[n])   
		if(flag==0)
		    System.out.print(i);
		else
		    System.out.print(" " +i);
	System.out.println();
    }
}

public class Rede {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int nodes = in.nextInt();
	Graph g = new Graph(nodes);
	int   e = in.nextInt();  //numero de arestas
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1 até ao n-ésino nó
	g.print();   

    }
}
