import java.util.*;
import java.io.*;
import java.lang.Math;

class Node{

    public float x;
    public float y;
    public float dist;
    public Node pai;
    public int index;
    
    Node(){
	
    }
    
    Node(float x, float y, int index){
        this.index = index;
	this.x=x;
	this.y=y;
	this.dist = Float.MAX_VALUE;
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

public class Sardas{

    public static Node r;
    public static LinkedList<Node> Q;
    
    public static Node extract(){

	float dist = Float.MAX_VALUE;
	int index=0;
	int current=-1;
	
	for(Node a : Q){
	    if(a.dist < dist){
		current = index;
		dist = a.dist;
	    }
	    index++;
	}
	Node rem = new Node();
	rem = Q.remove(current);
        return rem;
    }

    public static boolean belongsTo(Node v){
	for(Node a:Q){
	    if(Float.compare(a.x, v.x) == 0 && Float.compare(a.y, v.y) == 0 && a.index == v.index){
		return true;
	    }
	}
	return false;
    }

    public static float weight(Node a, Node b){
	return (float)Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
    }
    
    
    public static void main(String args[]){

	Scanner input = new Scanner(System.in);
	Graph g = new Graph(input.nextInt());
	for(int i=0; i<g.n; i++){
	    Node n = new Node(input.nextFloat(), input.nextFloat(), i);
	    g.addNode(n);
	}

	//vou considerar que todos os nodes sao adjacentes entre si
	//visto que nao foi explicitada nenhuma adjacencia
	g.nodes[0].dist = 0.0f;
	r = new Node();
	r = g.nodes[0];

        Q = new LinkedList<Node>();
	LinkedList<Node> A = new LinkedList<Node>();
	
	for(Node a : g.nodes){
	    Q.addLast(a);
	}
	
	while(Q.size()>0){
	    Node u = extract();

	    if(u.pai != null){
		A.addLast(u);
	    }
	    
	    for(Node v : g.nodes){	        
		
		if(belongsTo(v) && Float.compare(weight(u,v),v.dist) < 0){
		    v.pai = u;		    
		    v.dist = weight(u,v);		    
		}
	    }

	    
	}
	float result = 0.0f;
	
	for(Node v : g.nodes){
	    result += v.dist;	    
	}
	
	System.out.printf("%.2f\n",result);
	
    }

}
