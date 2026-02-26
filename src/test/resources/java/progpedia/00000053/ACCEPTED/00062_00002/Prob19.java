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
    int mdist[][];
   
    Graph(int n) {
		this.n = n;
		nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
		mdist = new int[n+1][n+1];
		for (int i=1; i<=n; i++)
			nodes[i] = new Node();
	}
 
    public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
    }
    public void reset(){
		for(int i=1; i<nodes.length; i++){
			nodes[i].visited = false;
			nodes[i].distance = -1;
		}
    }

    public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();
 
		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;
 
		while (q.size() > 0) {
			int u = q.removeFirst();
			mdist[v][u] = nodes[u].distance;
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1;
				}      
			}
		}
}
 
public class Prob19 {
    public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
	Graph g = new Graph(n);
    int   e = in.nextInt();
   
    for (int i=0; i<e; i++)
        g.addLink(in.nextInt(), in.nextInt());
 

    for(int i=1; i<=n; i++){
        g.bfs(i);
        g.reset();
    }
 
   int diam[] = new int[n+1];  
   int cent[] = new int[n+1];
   int perif[] = new int[n+1];
 
   int max = Integer.MIN_VALUE;
   int min = Integer.MAX_VALUE;
   
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(g.mdist[i][j] > max)
               diam[i] = g.mdist[i][j];
               max = diam[i];
        }
    }
 
    max = 0;
   
    for(int i=1; i<=n; i++){
        if(diam[i]>max){
           max = diam[i];
        }
        if(diam[i]<min){
           min = diam[i];
        }  
    }
 
    System.out.println(max);
    System.out.println(min);
 
    for(int i=1; i<=n; i++){
        if(diam[i]==min){
           cent[i]=i;
        }
        if(diam[i]==max){
           perif[i]=i;
        }
    }
    Arrays.sort(cent);
    Arrays.sort(perif);
 
    for(int i=1; i<=n; i++){
        if(i == n && cent[i]>0){
           System.out.print(cent[i]);
        }  
           
        if(i< n && cent[i]>0){
           System.out.print(cent[i] + " ");
        }
    }    
           System.out.println();
   
 
    for(int i=1; i<=n; i++){
        if(i == n && perif[i]>0){
           System.out.print(perif[i]);
        }
 
        if(i< n && perif[i]>0){
           System.out.print(perif[i] + " ");
        }       
    }    
    System.out.println();
  }
}