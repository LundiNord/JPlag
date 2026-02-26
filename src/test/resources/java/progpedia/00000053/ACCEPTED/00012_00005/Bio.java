import java.io.*;
import java.util.*;

class Node {
	public LinkedList<Integer> adj;
	public boolean visited;
	public int distance;
	
	Node() {
		adj = new LinkedList<Integer>();
		visited = false;
		distance = -1;
	} 
}

class Graph { 
	int num_nodes;
	Node[] nodes;
	int[][] matrix_distance;
	
	Graph( int num_nodes ) {
		this.num_nodes = num_nodes;
		nodes = new Node[num_nodes+1];
		matrix_distance = new int[num_nodes+1][num_nodes+1];
		for( int i = 1; i <= num_nodes; i++ )
		nodes[i] = new Node();
	}
	
	public void restart() {
		for( int i = 1; i < nodes.length; i++ ) {
			nodes[i].distance = -1;
			nodes[i].visited = false;
		}
	}
	
	public void addToList( int x, int y ) {
		nodes[x].adj.add(y);
		nodes[y].adj.add(x);
	} 
	
	public void bfs( int value ) { 
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		list.add( value );
		nodes[value].visited = true;
		nodes[value].distance = 0;
		
		while( list.size() > 0 ) { 
			int k = list.removeFirst();
			matrix_distance[value][k] = nodes[k].distance;
			for( int w : nodes[k].adj ) {
				if( !nodes[w].visited ) {
					list.add(w);
					nodes[w].visited = true;
					nodes[w].distance = nodes[k].distance + 1;
				}
			}
		}
	}
}


class Bio {
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		
		Graph g = new Graph( in.nextInt() );
		int edges = in.nextInt();
		for( int i = 0; i < edges; i++ ) {
			int a = in.nextInt();
			int b = in.nextInt();
			g.addToList( a,b );
		}	
		
		for( int i = 1; i <= g.num_nodes; i++ ) {
			g.bfs(i);
			g.restart();
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		int[] diam = new int[g.num_nodes+1];
		int[] cent = new int[g.num_nodes+1];
		int[] perif = new int[g.num_nodes+1];
		
		for( int i = 1; i <= g.num_nodes; i++ ) {
			for( int j = 1; j <= g.num_nodes; j++ ) {
				if( g.matrix_distance[i][j] > max ) {
					diam[i] = g.matrix_distance[i][j];
				}
				max = diam[i];	
			}
		}
		max = 0;
		
		for(int i=1;i<=g.num_nodes;i++){
			
			if(diam[i]>max)
			max=diam[i];
			
			if(diam[i]<min)
			min=diam[i];
		}
		
		System.out.println(max);
		System.out.println(min);
		
		for(int i=1;i<=g.num_nodes;i++){
			
			if(diam[i]==min)
			cent[i]=i;
			
			else if(diam[i]==max)
			perif[i]=i;
		}
	
		Arrays.sort(cent);
		Arrays.sort(perif);
	
		for(int i=1;i<=g.num_nodes;i++){
			
			if(cent[i]>0 && i<g.num_nodes)
			System.out.print(cent[i] + " ");
			
			else if(cent[i]>0 && i==g.num_nodes)
			System.out.print(cent[i]);
		}
		
		System.out.println();
		
		for(int i=1;i<=g.num_nodes;i++){
			
			if(perif[i]>0 && i<g.num_nodes)
			System.out.print(perif[i] + " ");
			
			else if(perif[i]>0 && i==g.num_nodes)
			System.out.print(perif[i]);
		}
		System.out.println();
	}
}
