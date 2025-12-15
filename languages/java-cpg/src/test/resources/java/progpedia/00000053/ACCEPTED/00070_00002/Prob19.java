//Pesquisa em largura FIFO 
//Pesquisa em Profundidade LIFO
// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (codigo feito na teorica - inclui calculo de distancias)

import java.util.*;
import java.io.*;



class Node {
	public LinkedList<Integer> adj;
	public boolean visited;
	public int distance;
	Node(){
		adj = new LinkedList<Integer>();
		visited=false;
		distance=-1;
	}
}
class Graph {
	int n;
	Node nodes[];
	Graph(int n){
		this.n=n;
		nodes = new Node[n+1];
		for(int i=1;i<=n;i++)
			nodes[i]=new Node();
	}
	public void addLink(int a,int b){
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	//Algoritmo de BFS
	public int bfs(int v){
		int maxdis=0;
		LinkedList <Integer> q = new LinkedList<Integer>();
		q.add(v);
		nodes[v].visited=true;
		nodes[v].distance=0;
		while(!q.isEmpty()){
			int u=q.removeFirst(); 
			//System.out.println(u + "[dist = " + nodes[u].distance + " ]");
			for(int w : nodes[u].adj)
				if(!nodes[w].visited){
					q.add(w);
					nodes[w].visited=true;
					nodes[w].distance = nodes[u].distance +1; 
					if(nodes[w].distance > maxdis)
						maxdis=nodes[w].distance;
				}
		}
		for (int i = 1; i <= n; i++)
			nodes[i].visited = false;
		return maxdis;
	}
}
public class Prob19 {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		Graph g = new Graph(stdin.nextInt());
		int v[] = new int[g.n +1];
		int e = stdin.nextInt();
	
		for(int i=0;i<e;i++){
			g.addLink(stdin.nextInt(), stdin.nextInt());
		}
		for(int i=1;i<=g.n;i++)
				v[i]=g.bfs(i);
		int maxdis=0, min_maxdis=Integer.MAX_VALUE , indexmax = 0, indexmin =0;
		for(int i=1;i<=g.n;i++){
			if(maxdis <= v[i]){
				maxdis = v[i]; 
				indexmax=i;
			}//diametro
			else if(v[i] <= min_maxdis){
				min_maxdis = v[i]; //raio
				indexmin = i;
			}
		}
		System.out.println(maxdis + "\n" + min_maxdis);
		for(int i=1;i<=g.n;i++){
			if(v[i]==min_maxdis && indexmin!=i)
				System.out.print( i + " ");
			else if(v[i]==min_maxdis && indexmin==i)
				System.out.print(i);
		}
		System.out.println();
		for(int i=1;i<=g.n;i++){
			if(v[i]==maxdis && indexmax!=i)
				System.out.print(i + " ");
			else if(v[i]==maxdis && indexmax ==i){
				System.out.print(i);	
			}
			//System.out.println();
			//System.out.println(i + "[dist = " + g.nodes[i].distance + " ]");
			//System.out.println(i + "[dist = " + v[i] + " ]");
		}	
	}
}
