import java.util.*;
import java.io.*;

class Node {
	public boolean visited;
	public double x;
	public double y;
	public LinkedList<Edge> adj;

	Node(double x, double y){
		visited = false;
		this.x = x;
		this.y = y;
		adj = new LinkedList<Edge>();
	}

	public void addEdge (Edge a){
		adj.add(a);

	}
}

class Graph {
	public LinkedList<Node> nodes; 
	public double ink;

	Graph() {
		nodes = new LinkedList<Node>();
		ink = 0.0;
	}

	public void addNode (Node a) {
		nodes.add(a);
	}

	public void populate() {
		LinkedList<Node> temp = new LinkedList<Node>();
		temp.addAll(nodes);
		Node parent;
		Node aux;
		parent = temp.removeFirst();
		while(!temp.isEmpty()){
			for (Node a : temp) {	
				parent.addEdge(makeEdge(parent,a));
				a.addEdge(makeEdge(a,parent));
			}
			parent = temp.removeFirst();
		}
		
	}

	public Edge makeEdge(Node a, Node b) {
		return new Edge(a,b);
	}


	public double prim(){
		double aux = Double.POSITIVE_INFINITY;
		LinkedList<Node> stuff = new LinkedList<Node>();
		stuff.addAll(nodes);
		LinkedList<Edge> edges = new LinkedList<Edge>();
		Node node = null;
		Edge auxedge = null;
		node = stuff.removeFirst();
		node.visited = true;
			
		while(!stuff.isEmpty()){
			aux = Double.POSITIVE_INFINITY;
			edges.addAll(node.adj);
			for (Edge a : edges) {
				if(!(a.target.visited)){
					if(aux > a.weight) { 
						aux = a.weight;
						auxedge = a;
					}

				}
				

			}
			if (aux != Double.POSITIVE_INFINITY) {
					ink += aux;
					auxedge.target.visited = true;
					node = stuff.remove(stuff.indexOf(auxedge.target));
			}
		}

		return ink;

	}
}

class Edge {

	public double weight;
	public Node target;
	public Node from;

	Edge (Node from, Node target) {
		this.target = target;
		this.from = from;
		weight = Math.sqrt(Math.pow(from.x - target.x, 2) + Math.pow(from.y - target.y, 2));
	}



}


public class sardas {

	public static void main (String args[]) {
		Scanner input = new Scanner(System.in);
		Graph g = new Graph();
		double x, y, ink;
		
		int n = input.nextInt();
		
		while (n!=0){
			x = input.nextDouble();
			y = input.nextDouble();
			Node toAdd = new Node(x,y);
			g.addNode(toAdd);
			n--;
		}

		g.populate();

		ink = g.prim();

		System.out.printf("%.2f\n", ink);

	}

}