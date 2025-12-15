import java.util.*;
import java.lang.Math;
class Node{
    int num;
    double x;
    double y;
    double dist;
    boolean visited;
    LinkedList<Integer> adj;
    int pai;

    Node(double x, double y, int num){
	this.num = num;
	this.x=x;
	this.y=y;
	dist = -1;
	visited = false;
	adj = new LinkedList<Integer>();
    }
}
class Graph{
    int n;        
    Node nodes[]; 
    
    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1];
    }
    public static double func(Node a, Node b){
	return Math.sqrt( (a.x-b.x)*(a.x-b.x) +  (a.y-b.y)*(a.y-b.y) );
    }
    
    public void addLink(int a, int b){
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }
    
    public void Prim(Node r){
        PriorityQueue<Par> q = new PriorityQueue<Par>();
	for(int i=1;i<=n;i++){
	    nodes[i].dist=999;
	    nodes[i].pai=0;
	}
	
	q.add(new Par(r.num, 0));

	while (q.size() > 0){
	    Par p = q.poll();
	    nodes[p.no].visited = true;
	    nodes[p.no].dist = p.dist; 

	    for(int i=1;i<=n;i++)
		if (!nodes[i].visited)
		    q.add(new Par(i, func(nodes[p.no], nodes[i])));
	    

	    /*for(int w:nodes[p.no].adj){
		if(){
		    
		}
	    }*/
	}

	
	
	while (q.size() > 0){
	    Par p = q.remove();
	    System.out.println(p.no + " " + p.dist);
	}
    }
}
class Par implements Comparable<Par> {
    double dist;
    int no;
    
    Par (int a, double b){
	no   = a;
	dist = b;
    }
    
    public int compareTo(Par p){
	if (dist < p.dist)         return -1;
	else if (dist > p.dist) return +1;
	else                       return 0;
    }
}

class Prob21{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Graph g = new Graph(n);


	for(int i=1;i<=n;i++){
	    g.nodes[i]= new Node(in.nextDouble(), in.nextDouble(), i);
	}
	for(int i=1;i<=n;i++){
	    for(int j=1;j<=n;j++){
		g.addLink(i,j);
	    }
	}
       
	g.Prim(g.nodes[1]);
	
    }
}
