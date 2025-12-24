import java.util.LinkedList;
import java.util.Scanner;


class Node{
	int value;
	LinkedList<Node> adj;
	boolean visit;
	int key;
	
	Node(int n){
		value = n;
		adj = new LinkedList<Node>();
		visit = false;
		key = Integer.MAX_VALUE;
	}
	
	void addConnection(Node n){
		adj.addLast(n);
	}
}


class Graph{
	int nregion;
	int dest;
	Node n[];
	int matrix[][];
	LinkedList<Integer> S;
	
	
	Graph(int nr, int d){
		nregion = nr;
		dest = d;
		n = new Node[nr+1];
		matrix = new int[nr+1][nr+1];
		S = new LinkedList<Integer>();
	}
	
	void initialize(){
		for(int i=1; i<=nregion; i++){
			n[i] = new Node(i);
		}
	}
	
	void createGraph(int r1, int r2, int d){
		matrix[r1][r2] = d;
		matrix[r2][r1] = d;
		n[r1].addConnection(n[r2]);
		n[r2].addConnection(n[r1]);
	}
	
	
	void Dijkstra(){
		n[dest].key = 0;
		LinkedList<Integer> Q = new LinkedList<Integer>();
		
		Q.addLast(dest);
		while(!Q.isEmpty()){
			int u = extract_Min(Q);
			if(u != -1){
				int size = n[u].adj.size();
				for(int i=0; i<size; i++){
					int v = n[u].adj.get(i).value;
					if(!n[v].visit && n[v].key > n[u].key + matrix[u][v]){
						n[v].key = n[u].key + matrix[u][v];
						Q.addLast(v);
					}
				}
				n[u].visit = true;
				S.addLast(u);
			}
		}
	}
	
	private int extract_Min(LinkedList<Integer> Q){
		int value = -1;
		int min_key = Integer.MAX_VALUE;
		int index = 0;
		int size = Q.size();
		for(int i=0; i<size; i++){
			int tmp = Q.get(i); 
			if(!n[tmp].visit && n[tmp].key < min_key){
				min_key = n[tmp].key;
				value = tmp;
				index = i;
			}
			if(i != size-1){
				int tmp_next = Q.get(i+1);
				if(n[tmp].key == n[tmp_next].key){
					if(n[tmp_next].value < n[tmp].value){
						min_key = n[tmp_next].key;
						value = tmp_next;
						index = i+1;
					}
				}
			}
		}
		Q.remove(index);
		return value;
	}
	
	
	
	void output(){
		int size = S.size();
		for(int i=0; i<size; i++){
			System.out.print(S.get(i) + " ");
		}
	}
}



public class negocio {
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		
		int nregion = stdin.nextInt();
		int dest = stdin.nextInt();
		
		Graph G = new Graph(nregion, dest);
		G.initialize();
		
		int reg1, reg2, dist;
		reg1 = stdin.nextInt();	
		while(reg1 != -1){
			reg2 = stdin.nextInt();
			dist = stdin.nextInt();
			
			G.createGraph(reg1, reg2, dist);
			
			reg1 = stdin.nextInt();
		}
		G.Dijkstra();
		G.output();

	}
}