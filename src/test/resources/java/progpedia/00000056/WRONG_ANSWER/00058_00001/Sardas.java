import java.util.*;
import java.io.*;
import java.lang.Math;

class Node implements Comparable<Node>{
    int number;
    boolean visited;
    double distance;
    Node parent;
    int chosen;
    double chosenValue;
    double posx;
    double posy;

    Node(boolean visited, int number, double distance, Node parent, int chosen, double chosenValue, double posx, double posy){
	this.number = number;
	this.visited = visited;
	this.distance = distance;
	this.parent = parent;
	this.chosen = chosen;
	this.chosenValue = chosenValue;
	this.posx = posx;
	this.posy = posy;
    }
    @Override
	public int compareTo(Node x){
	if(this.distance<x.distance) return -1;
	else if(this.distance>x.distance) return 1;
	else{
	    if(this.number<x.number) return -1;
	    else if(this.number>x.number) return 1;
	    else return 0;
	}
    }
}

class Sardas{


    static Node[] array;
    static double[][] connected;
    static LinkedList<Node> list;
    static int Nnodes;
    static double total;


    public static void prim(Node nod){
	total = 0;
	int flag=0;
	for(int i=0; i<Nnodes; i++){
	    array[i].distance = 999999;
	    array[i].parent = null;
	}
	nod.distance=0;
	PriorityQueue<Node> heap = new PriorityQueue<Node>();
	for(int i=0; i<Nnodes; i++){
	    heap.add(array[i]);
	}
	while(!heap.isEmpty()){
	    Node u = heap.remove();
	    if(flag==1){

		total = total + u.chosenValue;
	    }
		flag =1;
	    for(int j=0; j<Nnodes; j++){
		if(connected[u.number-1][j]!=0){
		    if(heap.contains(array[j])==true && connected[u.number-1][j]<array[j].distance){
			array[j].parent = array[u.number-1];
			array[j].distance = connected[u.number-1][j];
			heap.remove(array[j]);
			heap.add(array[j]);
			array[j].chosen = u.number;
			array[j].chosenValue = connected[u.number-1][j];
		    }
		}
	    }
	}
    }



    public static void main(String[] args){
	Scanner hello = new Scanner(System.in);
	Nnodes = hello.nextInt();

	connected = new double[Nnodes][Nnodes];
	array = new Node[Nnodes];

	for(int i=0; i<Nnodes; i++){
	    array[i] = new Node(false, i+1, 9999999, null, 0, 0, 0, 0);
	}

	for(int i=0; i<Nnodes; i++){
	    double x = hello.nextDouble();
	    double y = hello.nextDouble();
	    array[i].posx = x;
	    array[i].posy =y;
	}

	for(int i=0; i<Nnodes; i++){ 
	    double x1 = array[i].posx;
	    double y1 = array[i].posy;
	    for(int j=0;j<Nnodes; j++){
		double x2 = array[j].posx;
		double y2 = array[j].posy;
		connected[i][j] = Math.sqrt(Math.pow(Math.abs(x1 - x2),2) + Math.pow(Math.abs(y1-y2),2));
	    }
	}



	prim(array[0]);
	System.out.printf("%.2f\n",total);
    }
}
