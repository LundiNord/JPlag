import java.util.*;

class Prob21
{
    static LinkedList<Node> list;

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	input.useLocale(Locale.US);
	
	int n = input.nextInt();
	list = new LinkedList<Node>();
	
	for (int i=0;i<n;i++) {
	    Node node = new Node(input.nextFloat(), input.nextFloat());
	    input.nextLine();
	    list.add(node);
	}
	
	Node node = list.get(0);
	node.visited=true;
	double sum=0;
	
	for (int i=0;i<n;i++) {
	    Edge edge = returnEdge(node);
	    sum+=edge.dist;
	    node = edge.b;
	    node.visited=true;
	}
	System.out.format(Locale.ENGLISH,"%.2f",sum);
	System.out.println();
    }
    
    public static Edge returnEdge(Node node) {
	
	float   bestDist=Float.MAX_VALUE;
	int     bestIndex=0;
	boolean flag=false;
	
	int i=0;
	for (i=0;i<list.size();i++) {
	    if (!list.get(i).visited){
		flag=true;
		float  x1 = node.a;
		float  y1 = node.b;
		float  x2 = list.get(i).a;
		float  y2 = list.get(i).b;
		double op = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
		
		if (op<bestDist){
		    bestDist=(float) op;
		    bestIndex=i;
		}
	    }
	}
	if (!flag)
	    bestDist=0;
	
	Edge edge = new Edge(node, list.get(bestIndex), bestDist);
	return edge;
    }
}

class Node
{
    float a;
    float b;
    boolean visited;
    
    Node(float a, float b) {
	this.a=a;
	this.b=b;
	this.visited=false;
    }
    
}

class Edge
{
    float dist;
    Node a;
    Node b;

    Edge(Node a, Node b, float dist){
	this.a=a;
	this.b=b;
	this.dist =dist;
    }
}
