import java.util.*;

class Parte3_2
{
    static PriorityQueue<Node> list = new PriorityQueue<Node>();
    static LinkedList<Node>    aux  = new LinkedList<Node>();
    
    static float dist=0;
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	input.useLocale(Locale.US);
	
	int n = input.nextInt();
	
	for (int i=0;i<n;i++) {
	    Node node = new Node(input.nextFloat(), input.nextFloat());
	    list.add(node);
	    aux.add(node);
	}
	prim();
	
	System.out.format(Locale.ENGLISH,"%.2f",dist);
	System.out.println();
    }
    
    public static void prim() {
	boolean first=true;
	Node node = null;

	while(list.size()>0) {
	    
	    node = list.remove();
	    aux.remove(node);
	    
	    if (!first)
		dist+=node.dist;
	    else
		first=false;

	    for (Node adjNode : aux) {
		float weight = returnWeight(node, adjNode);
		
		if (weight<adjNode.dist){
		    list.remove(adjNode);
		    
		    adjNode.parent = node;
		    adjNode.dist   = weight;
		    
		    list.add(adjNode);
		}
	    }
	}
    }
    
    public static float returnWeight(Node node1, Node node2) {
	return (float) Math.sqrt((node1.x-node2.x)*(node1.x-node2.x) + (node1.y-node2.y)*(node1.y-node2.y));
    }
    
}

class Node implements Comparable<Node>{
    float dist   = Float.MAX_VALUE;
    Node  parent = null;
    
    public float x;
    public float y;
    
    Node(float x, float y) {
	this.x=x;
	this.y=y;
    }
    
    public int compareTo (Node n) {
	if (dist < n.dist)
	    return -1;
	else if (dist > n.dist)
	    return 1;
	else
	    return 0;
    }
}
