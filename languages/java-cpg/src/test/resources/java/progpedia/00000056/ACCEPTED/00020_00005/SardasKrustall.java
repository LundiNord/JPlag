import java.util.*;
import java.io.*;
import java.lang.Math;

class Node{

    public double x;
    public double y;
    public Node pai;
    public int index;
    
    Node(){
    }
    
    Node(double x, double y, int index){
        this.index = index;
	this.x=x;
	this.y=y;
	this.pai = null;
    }
    
}

class Graph{

    public int n;
    public int c;
    public Node nodes[];
    
    Graph(int n){
	this.n = n;
	nodes = new Node[n];
	this.c=0;
	for(int i=0; i<n; i++){
	    nodes[i] = new Node();
	}
    }

    public void addNode(Node n){
	nodes[c] = n;
	this.c++;
    }

}

class Set{

    public LinkedList<Node> nodes;
    public int id;
    
    Set(Node a, int id){
	nodes = new LinkedList<Node>();	
	nodes.addLast(a);
	this.id = id;
    }
    
}

class Edge implements Comparable<Edge>{

    public Node a;
    public Node b;
    public double dist;
    
    
    Edge(Node a, Node b){
	this.a=a;
	this.b=b;
	this.dist=Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
    }

    @Override
    public int compareTo(Edge e){
	if(Double.compare(this.dist, e.dist) < 0){
	    return -1;
	}else if(Double.compare(this.dist, e.dist) > 0){
	    return 1;
	}
	return 0;
    }
       
}

public class SardasKrustall{

    public static LinkedList<Set> sets;
    public static LinkedList<Edge> A;
    public static LinkedList<Edge> edges;
    
    public static int find(Node node){
	for(int i=0; i<sets.size(); i++){
	    for(int j=0; j<sets.get(i).nodes.size(); j++){
		if(sets.get(i).nodes.get(j).x == node.x && sets.get(i).nodes.get(j).y == node.y){
		    return i;
		}
	    }
	}
	return -1;
    }

    
    public static void merge(int a, int b){
	
       	for(int i=0; i<sets.get(b).nodes.size(); i++){
	    sets.get(a).nodes.add(sets.get(b).nodes.get(i));
	}
	sets.remove(b);
	
    }

    public static void resetId(){
	for(int i=0; i<sets.size(); i++){
	    sets.get(i).id = i;
	}
    }
    
    public static void main(String args[]){

	Scanner input = new Scanner(System.in);
	Graph g = new Graph(input.nextInt());
	for(int i=0; i<g.n; i++){
	    Node n = new Node(input.nextFloat(), input.nextFloat(), i);
	    g.addNode(n);
	}

        A = new LinkedList<Edge>();
	edges = new LinkedList<Edge>();
        sets = new LinkedList<Set>();

	for(int i=0; i<g.n; i++){
	    sets.add(new Set(g.nodes[i],i));
	}

	for(int i=0; i<g.n; i++){
	    for(int j=i+1; j<g.n; j++){
		edges.add(new Edge(g.nodes[i], g.nodes[j]));
	    }
	}
	
	Collections.sort(edges);
	
	for(Edge e : edges){			
	    int a = find(e.a);
	    int b = find(e.b); // -1
	    if(a != b){
		A.addLast(e);			        
	        merge(a, b);     
	    }	    
	}

	double result = 0.0f;
	
	for(Edge E:A){
	    result += E.dist;
	}
	System.out.printf("%.2f\n",result);
    }

}
