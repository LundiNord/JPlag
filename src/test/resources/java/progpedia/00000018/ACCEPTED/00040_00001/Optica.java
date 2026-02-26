import java.util.LinkedList;
import java.util.Scanner;


class Connections{
	
	int point;
	
	LinkedList<Connections> adj;
	
	int key;
	
	int parent;
	
	boolean visit;
		
	
	Connections(int p){
		this.point = p;
		adj = new LinkedList<Connections>();
		this.key = Integer.MIN_VALUE;
		this.parent = Integer.MIN_VALUE;
		this.visit = false;
	}
	
	
	void addConnection(Connections c){
		adj.addLast(c);
	}
}

class Graph{
	
	int nnodes;
	int nconn;
	int cost;
	
	int matrix[][];
	
	Connections c[];
	
	boolean imp;
	
	
	Graph(int nnodes, int nconn, int ct){
		this.nnodes = nnodes;
		this.nconn = nconn;
		this.cost = ct;
		matrix = new int[nnodes+1][nnodes+1];
		c = new Connections[nnodes+1];
		this.imp = false;
	}
	
	
	void initialize(){
		for(int i=0; i<=nnodes; i++){
			c[i] = new Connections(i);
		}
	}
	
	
	
	void createRelation(int p1, int p2, int ct){
		matrix[p1][p2] = ct;
		matrix[p2][p1] = ct;
		c[p1].addConnection(c[p2]);
		c[p2].addConnection(c[p1]);
	}
	
	
	void Prim(){
		
		LinkedList<Integer> Q = new LinkedList<Integer>();		
		
		int size = c.length-1;
		for(int i=0; i<size; i++){
			Q.addLast(i+1);
		}
		
		c[1].key = 0;
		c[1].parent = 0;	
		while(!Q.isEmpty()){
			int u = extract_Max(Q);
			if(u != -1){
				c[u].visit = true;
				int size_adj_u = c[u].adj.size();
				for(int i=0; i<size_adj_u; i++){
					int v = c[u].adj.get(i).point;
					if(!c[v].visit && matrix[u][v] > c[v].key){
						c[v].key = matrix[u][v];
						c[v].parent = u;
					}
				}
			}
			else{
				imp = true;
				break;
			}
		}
	}
	
	int extract_Max(LinkedList<Integer> Q){
		int value = -1;
		int max_key = Integer.MIN_VALUE;
		int index = 0;
		int size = Q.size();
		for(int i=0; i<size; i++){
			int tmp = Q.get(i);
			if(!c[tmp].visit && c[tmp].key > max_key){
				value = tmp;
				max_key = c[tmp].key;
				index = i;
			}
		}
		Q.remove(index);
		return value;
	}
	
	
	void output(){
		if(imp == true){
			System.out.println("impossivel");
		}
		else{
			int x = c.length;
			int[] node_key = new int[x];
			int sum = 0;
			for(int i=1; i<x; i++){
				node_key[i] = c[i].key;
			}
			for(int i=1; i<x; i++){
				sum += node_key[i];
			}
			int total = sum-(cost*(nnodes-1));
			System.out.println("rendimento optimo: " + total);
		}
	}
}



public class Optica {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		
		int nnodes = stdin.nextInt();
		
		int nconn = stdin.nextInt();
		
		int cost = stdin.nextInt();
		
		
		Graph G = new Graph(nnodes, nconn, cost);
		G.initialize();
		
		
		for(int i=0; i<nconn; i++){
		int p1 = stdin.nextInt();
			int p2 = stdin.nextInt();
			int rend = stdin.nextInt();
			
			G.createRelation(p1, p2, rend);
		}
		

		G.Prim();
		

		G.output();
	}
}
