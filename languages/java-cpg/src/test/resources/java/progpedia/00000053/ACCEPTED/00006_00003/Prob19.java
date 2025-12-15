import java.util.*;

public class Prob19 {
    public static int smallestDistance=Integer.MAX_VALUE;
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int n = input.nextInt();
	Graph g = new Graph(n);
	LinkedList<Integer> centralNodes = new LinkedList<Integer>();
	int e = input.nextInt();
	
	for (int i=0;i<e;i++) {
	    g.addLink(input.nextInt(),input.nextInt());
	}
	for (int i=1;i<=n;i++){
	    g.bfs(i);
	    for (int j=0;j<=n;j++) {
		g.nodes[j].visited=false;
	    }
	    
	    if (g.distance<smallestDistance){
		smallestDistance=g.distance;
		g.smallestDistance=smallestDistance;
		
		while(centralNodes.size()>0)
		    centralNodes.remove();
		centralNodes.add(i);
	    }
	    
	    else if (g.distance==g.smallestDistance){
		centralNodes.add(i);
	    }
	    g.distance=0;
	}
	
	System.out.println(g.biggestDistance);
	System.out.println(g.smallestDistance);
	
	for (int i=0;i<centralNodes.size();i++)
	    if (i<centralNodes.size()-1)
		System.out.print(centralNodes.get(i)+ " ");
	    else
		System.out.println(centralNodes.get(i));
	
	int i=1;
	boolean first=true;
	
	for (i=1;i<=n;i++) {
	    if (g.nodes[i].biggestDistance == g.biggestDistance){
		
		if (first) {
		    first=false;
		    System.out.print(i);
		}
		else
		    System.out.print(" " + i);
	    }
	}
	System.out.println();
    }
}


class Node
{
    public LinkedList<Integer> adj;
    public boolean visited;
    public int distance;
    public int biggestDistance;
    
    Node() {
	adj= new LinkedList<Integer>();
	visited=false;
	distance=-1;
    }
}

class Graph
{
    static int biggestDistance=-1;
    static int smallestDistance=Integer.MAX_VALUE;
    static int distance=0;
    
    int n;
    Node[] nodes;
    
    Graph(int n) {
	this.n=n;
	nodes = new Node[n+1];
	for (int i=0;i<=n;i++)
	    nodes[i]= new Node();
    }
    
    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }
    
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();
	
	q.add(v);
	nodes[v].visited=true;
	nodes[v].distance=0;

	while(q.size()>0) {
	    int u = q.removeFirst();
	    
	    for (int w : nodes[u].adj){
		if (!nodes[w].visited) {
		    q.add(w);
		    nodes[w].visited=true;
		    nodes[w].distance=nodes[u].distance+1;
		    
		    if (nodes[w].distance>nodes[w].biggestDistance)
			nodes[w].biggestDistance=nodes[w].distance;
		    
		    if (nodes[w].distance>biggestDistance){
			biggestDistance=nodes[w].distance;
		    }
		    if (nodes[w].distance>distance)
			distance=nodes[w].distance;
		}
	    }
	}
    }
}
