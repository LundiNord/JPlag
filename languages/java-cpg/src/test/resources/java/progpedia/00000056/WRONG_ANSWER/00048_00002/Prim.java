import java.util.*;
import java.lang.*;
import java.io.*;

class Graph{
    int n;
    Node[] nodes;

    public Graph(int n,double dist){
	this.n=n;
	nodes = new Node[n];
		
	for(int i =0;i<n;i++){
	    nodes[i] = new Node(i,dist);
	}
    }

    public void addLink(int n1,int n2,double weight){
	nodes[n1].adj.add(new Edge(nodes[n2],weight));
	nodes[n2].adj.add(new Edge(nodes[n1],weight));
    }

    public Node getChildn(Node node,int n){
	return node.adj.get(n).linked;
    }

    public void setAllNodesUnvisited(){
	for(int i=0;i<n;i++)
	    nodes[i].visited=false;
    }

    public void setAllDistancesAS(double dist){
	for(int i=0;i<n;i++)
	    nodes[i].dist=dist;
    }

    public void nullAllFathers(){
	for(int i=0;i<n;i++)
	    nodes[i].father=null;

    }


    public void prim(int s){
	
	setAllDistancesAS(1000000000);
	nodes[s].dist=0.0;
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	for (int i=0;i<n ;i++ ) {
	    pq.add(i);
	}
	while(!pq.isEmpty()){
	    int u= pq.poll();
	    for(Edge v : nodes[u].adj){
		if(pq.contains(v.linked.id) && v.weight < v.linked.dist  ){
		    
		    v.linked.dist=v.weight;
		}
	    }
	}
    }
}	

class Edge implements Comparable<Edge>{
    Node linked;
    Double weight;

    public Edge(Node linked,double weight){
	this.weight = weight;
	this.linked = linked;
    }

    public int compareTo(Edge other){
	return this.weight.compareTo(other.weight);
    }
}


class Node implements Comparable<Node>{
    LinkedList<Edge> adj;
    Integer id;
    Double dist;
    Integer father=null;
    boolean visited;


    public Node(int id,double dist){
	adj = new LinkedList<Edge>();
	boolean visited = false;
	this.dist=dist;
	this.id=id;
    }


    @Override 
	public int compareTo(Node other){
	return this.dist.compareTo(other.dist);
    }
}

class Par{
    double x,y;
    Par(double a, double b){
	x=a;
	y=b;
    }
}

class Prim{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int N=in.nextInt();
	Graph g = new Graph(N,1000);
	Par par[]=new Par[N];
	double [] peso=new double[N*(N+1)/2];
	for(int i=0;i<N;i++){
	    par[i]=new Par(in.nextDouble(),in.nextDouble());
	}
	for(int i=0;i<N;i++){
	    for(int j=i+1;j<N;j++){
		peso[i]=calcpeso(par[i],par[j]);
		g.addLink(i,j,peso[i]);
	    }
	}
	g.prim(0);
	double mst=0;
	for (int i=0;i<N;i++ ) {
	    mst+=g.nodes[i].dist;	
	}
	System.out.format("%.2f",mst);
	System.out.println();
    }

    static double calcpeso(Par a, Par b){
	return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }
}
