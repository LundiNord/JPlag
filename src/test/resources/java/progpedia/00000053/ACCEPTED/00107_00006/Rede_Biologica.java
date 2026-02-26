//package rede_biologia_19;

//Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
//(codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

//Classe que representa um no
class Node {
	public LinkedList<Integer> adj; // Lista de adjacencias
	public boolean visited;         // Valor booleano que indica se foi visitao numa pesquisa
	public int distance;            // Distancia do no origem da pesquisa
	public int excentricidade;
	public int index_max = 0;
	public int index_min = 0;

	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	}
}

//Classe que representa um grafo
class Graph {
	int n;           // Numero de nos do grafo
	Node nodes[];    // Array para conter os nos
	int tab[][];

	Graph(int n) {
		this.n = n;
		nodes  = new Node[n+1]; // +1 se os comecam em 1 ao inves de 0
		for (int i=1; i<=n; i++)
			nodes[i] = new Node();
		this.tab = new int [n+1][n+1];
	}

	public void addLink(int a, int b) {
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public void resetVisited(){
		for(int i = 1; i <= n ; i++){
			nodes[i].distance = -1;
			nodes[i].visited = false;
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
			tab[u][v]= nodes[u].distance;
			//System.out.println("No: "+ u + " [dist=" + nodes[u].distance + "]");
			for (int w : nodes[u].adj)
				if (!nodes[w].visited) {
					q.add(w);
					nodes[w].visited  = true;
					nodes[w].distance = nodes[u].distance + 1; 
				}
		}
	}

	public void calc_excentricidades(){
		for(int i = 1; i <= n; i++){
			int temp = 0;
			for(int j = 1; j <= n; j++){
				if(tab[i][j] > temp) temp = tab[i][j];
			}
			nodes[i].excentricidade = temp;
		}
	}

	public int diametro(){
		int temp = 0;
		for(int i = 1; i <= n; i++){
			if(nodes[i].excentricidade > temp){
				temp = nodes[i].excentricidade;
				nodes[i].index_max = i;
			}
		}
		return temp;
	}

	public int raio(){
		int temp = Integer.MAX_VALUE;
		for(int i = 1; i <= n; i++){
			if(nodes[i].excentricidade < temp){
				temp = nodes[i].excentricidade;
				nodes[i].index_min = i;
			}
		}
		return temp;
	}

	public void nos_centrais(){
		String centro = new String();
		int raio = this.raio();
		for(int i = 1; i <= n; i++){
			if(nodes[i].excentricidade == raio ){
				centro += i + " ";
			}
		}
		if(centro.endsWith(" ")){
			centro = centro.substring(0, centro.length()-1);
		}
		System.out.println(centro);
	}

	public void nos_perifericos(){
		String diam = new String();
		int diametro = this.diametro();
		for(int i = 1; i <= n; i++){
			if(nodes[i].excentricidade == diametro)
				diam += i + " ";
		}
		if(diam.endsWith(" "))
			diam = diam.substring(0, diam.length()-1);
		System.out.println(diam);
	}
}

public class Rede_Biologica {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		Graph g = new Graph(in.nextInt());
		int e = in.nextInt();
		for (int i=0; i<e; i++) 
			g.addLink(in.nextInt(), in.nextInt());
		// Pesquisa em largura a partir do no 1
		for(int i=1; i<=g.n; i++){
			g.bfs(i);
			g.resetVisited();
		}
		g.calc_excentricidades();
		System.out.println(g.diametro());
		System.out.println(g.raio());
		g.nos_centrais();
		g.nos_perifericos();
	}
}

