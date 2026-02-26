import java.util.*;

public class Prim 
{
    static LinkedList<Node> nodes;
    static Node lastNode;
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	input.useLocale(Locale.US);
	
	int n = input.nextInt();
	nodes = new LinkedList<Node>();
	
	for (int i=0;i<n;i++) {
	    Node node = new Node(input.nextFloat(), input.nextFloat());
	    nodes.add(node);
	}
	
	
	double sumDist=0;
	
	boolean first = true;
	while (nodes.size()>0) {
	    double size;
	    Node u = takeMinimum();
	    if (!first)
		sumDist+=u.dist;
	    else
		first=false;
	    for (int i=0;i<nodes.size();i++) {
		size = returnDist(u,nodes.get(i));
		
		if (size<nodes.get(i).dist){
		    nodes.get(i).parent = u;
		    nodes.get(i).dist   = size;
		}
	    }
	}
	System.out.format(Locale.ENGLISH,"%.2f",sumDist);
	System.out.println();
    }
    
    public static Node takeMinimum() {
	int index = 0;
	double minDist = Double.MAX_VALUE;
	
	for (int i=0;i<nodes.size();i++) {
	    if (nodes.get(i).dist < minDist){
		minDist  = nodes.get(i).dist;
		index = i;
	    }
	}
	return nodes.remove(index);
    }
	
    public static double returnDist(Node a, Node b) {
	float  x1 = a.x;
	float  y1 = a.y;
	float  x2 = b.x;
	float  y2 = b.y;
	double  op = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
	return op;
    }
}

class Node
{
    float x;
    float y;
    Node parent;
    double dist;
    
    Node(float x, float y) {
	this.x=x;
	this.y=y;
	parent=null;
	dist=Double.MAX_VALUE;
    }
}
