import java.io.*;
import java.util.*;


class Node {
	public LinkedList<Integer> adj ;
	public boolean visited;
	public int distance;
	public int excentricidade;
	Node() {
		adj = new LinkedList<Integer>();
		visited  = false;
		distance = -1;
		excentricidade = 0; 
	}
}

class Graph {
	int n;										// numero de nos
	Node[] nodes;
	int diam = 0;
	int raio = 0;
	
	Graph( int n ) {
		this.n  =n;
		nodes = new Node[n+1];   		 			// +1 porque começa em 1
		for( int i = 1; i <= n; i++ )			 // Cria o numero de nós necessários para o grafo
			nodes[i] = new Node();
	}
	
	public void addLink( int a, int b ) {	// Cria uma nova aresta
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}
	
	public void bfs( int v ) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.add( v );
		nodes[v].visited = true;
		nodes[v].distance = 0;
		
		while( l.size() > 0 ) {
			int u = l.removeFirst();
			for( int w : nodes[u].adj ) {
				if( !nodes[w].visited ) {
					l.add(w);
					nodes[w].visited = true;
					nodes[w].distance = nodes[u].distance +1;								// nodes[u].distance aumenta porque visitou um novo valor a partir de nodes[u]
					
					if( nodes[w].excentricidade < nodes[w].distance )
						nodes[w].excentricidade = nodes[w].distance;
				}
			}
			if( nodes[u].excentricidade > diam )														// comparação para descobrir o diâmetro
				diam = nodes[u].excentricidade;
			if( raio == 0 )
				raio = nodes[u].excentricidade;
			else if( raio != 0 && nodes[u].excentricidade < raio )							// comparacão para descobrir o raio
				raio = nodes[u].excentricidade;
		}
	}
}

class RedeBiologica {
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		int num = in.nextInt();
		
		Graph g = new Graph( num );
		int arestas = in.nextInt();
		for( int i = 0; i < arestas; i++ )
			g.addLink( in.nextInt(),  in.nextInt() );
		
		for( int i = 1; i <= num; i++ ) {
			g.bfs( i );
			
			for( int j = 1; j <= num; j++ )						// passa o visited a false, porque já estão todas as posições a true devido ao bfs anterior
				g.nodes[j].visited = false;
			
		}
		
		System.out.println( g.diam );
		System.out.println( g.raio );
		for( int j = 1; j < num; j++ ) {
			if( g.nodes[j].excentricidade == g.raio ) {
			System.out.print( j+" ");
			}
		}
		if( g.nodes[num].excentricidade == g.raio )
			System.out.print( num );
		System.out.println();
		for( int j = 1; j < num; j++ ) {
			if( g.nodes[j].excentricidade == g.diam ) {
				System.out.print( j+" " );
			}
		}
		if( g.nodes[num].excentricidade == g.diam )
			System.out.print( num );
		System.out.println();
	}
}
