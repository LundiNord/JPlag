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
    
    public void cleanvisited(){
        for(int i=1; i<=n; i++)
            nodes[i].visited = false;
    }

    // Algoritmo de pesquisa em largura
    public int[] bfs(int v) {
    int t[] = new int[n]; 
	LinkedList<Integer> q = new LinkedList<Integer>();
	q.add(v);
	nodes[v].distance = 0;
    nodes[v].visited = true;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " " + nodes[u].distance);
	    for (int w : nodes[u].adj){
            if (!nodes[w].visited) {
                q.add(w);
                nodes[w].visited  = true;
                nodes[w].distance = nodes[u].distance + 1; 
            }
        }
        t[u-1] = nodes[u].distance;  
	}
    
    return t;
    }
}

public class BFS {
    public static int n;
    
    public static int maxexcentricidade(int t[][]){
        int max=0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(t[i][j]>max) max = t[i][j];
                
       return max;
        
    }
    
    
    public static int raio(int t[][]){
        int raio=n;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(t[i][j]>1 && t[i][j]<raio) raio = t[i][j];
        
        return raio;    
    }
    
    public static String nos(int t[][], int raio, int dia){
        int max = 0;
        String noscentrais="";
        String nosperifericos="";
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(t[i][j]>max) max = t[i][j];
            }
            if(max==raio && noscentrais=="") noscentrais = Integer.toString(i+1);
            else if(max==raio && noscentrais!="") noscentrais = noscentrais + " " + Integer.toString(i+1);
            if(max==dia && nosperifericos=="") nosperifericos = Integer.toString(i+1);
            else if(max==dia && nosperifericos!="") nosperifericos = nosperifericos + " " + Integer.toString(i+1);
            
            max = 0;
       }
       
       return noscentrais + "\n" + nosperifericos;
        
    }
    
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
    
    n = in.nextInt();       
	Graph g = new Graph(n);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	g.addLink(in.nextInt(), in.nextInt());

	// Pesquisa em largura a partir do no 1
    int table[][] = new int[n][n];
	
    
    for(int i=0; i<n; i++){
        int t[] = g.bfs(i+1);
        for(int j=0; j<n; j++){
            table[i][j] = t[j];
        }
        g.cleanvisited();         
    } 
     int raio = raio(table);
     int diametro = maxexcentricidade(table);
     
    System.out.println(diametro);
    System.out.println(raio);
    System.out.println(nos(table, raio, diametro));
    
    
    /*imprimir tabela
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++)	
            System.out.print(table[i][j]+ " ");
        System.out.println();       
    }
    */
    
    
    }
}
