import java.util.*;
import java.io.*;

class Node{
    int number;
    boolean visited;

    Node(boolean visited, int number){
	this.number = number;
	this.visited = visited;
    }
}

class Prob19{


    static Node[] array;
    static int[][] connected;
    static LinkedList<Node> list;
    static int Nnodes;
    static int[][] distances;


    public static void bfs(Node x){
	list = new LinkedList<Node>();
	list.addLast(x);
	array[x.number-1].visited = true;
	distances[x.number-1][x.number-1] = 0;
	while(!list.isEmpty()){
	    Node u = list.removeFirst();
	    for(int j=0;j<Nnodes;j++){
		if(connected[u.number-1][j]==1 && array[j].visited==false){
		    list.addLast(array[j]);
		    array[j].visited=true;
		    if(distances[x.number-1][u.number-1] + 1 < distances[x.number-1][j])
  distances[x.number-1][j] = distances[x.number-1][u.number-1] + 1;
		    distances[j][x.number-1] = distances[x.number-1][j];
		}
	    }
	}
    }

    public static void main(String[] args){
	Scanner hello = new Scanner(System.in);
	Nnodes = hello.nextInt();
	int Nedges = hello.nextInt();

	connected = new int[Nnodes][Nnodes];
	distances = new int[Nnodes][Nnodes];


	for(int i=0; i<Nnodes; i++){
	    Arrays.fill(distances[i], Nnodes+1);
	}


	array = new Node[Nnodes];
	for(int i=0; i<Nnodes; i++){
	    array[i] = new Node(false, i+1);
	}

	for(int i=0; i<Nedges; i++){
	    int x = hello.nextInt();
	    int y = hello.nextInt();
	    connected[x-1][y-1]=1;
	    connected[y-1][x-1]=1;
	}
	for(int i=0; i<Nnodes; i++){
	    for(int j=0; j<Nnodes; j++) array[j].visited=false;
	    bfs(array[i]);
	}
	/*
	for(int i=0; i<Nnodes; i++){
	    for(int j=0; j<Nnodes; j++){
		if(distances[i][j]<Nnodes)	System.out.print(distances[i][j] + " ");
		else System.out.print("_ ");
	    }
	    System.out.println();
	    }*/

	int maxtotal = 0;
	int mintotal = Nnodes+1;
	int[] noscentrais = new int[Nnodes];
	int[] nosperi = new int[Nnodes];

	for(int i=0; i<Nnodes; i++){
	    int max = 0;
	    for(int j=0; j<Nnodes; j++){
		if(distances[i][j]>max) max = distances[i][j];
        
	    }
	    if(max>maxtotal) maxtotal = max;
	    if(max<mintotal) mintotal = max;
	}

	int z=0;
       for(int i=0; i<Nnodes; i++){
	    int max = 0;
	    for(int j=0; j<Nnodes; j++){
		if(distances[i][j]>max) max = distances[i][j];
        
	    }
	    if(max==mintotal){
		noscentrais[z]=i+1;
		z++;
	    }
       }

       int w=0;
   for(int i=0; i<Nnodes; i++){
	    int max = 0;
	    for(int j=0; j<Nnodes; j++){
		if(distances[i][j]>max) max = distances[i][j];
        
	    }
	    if(max==maxtotal){
		nosperi[w]=i+1;
		w++;
	    }
       }




	System.out.println(maxtotal);

	System.out.println(mintotal);

	Arrays.sort(noscentrais);
	for(int i=0; i<Nnodes; i++){
	    if(i!=Nnodes-1 && noscentrais[i]!=0)
		System.out.print(noscentrais[i] + " ");
	    else 
		if(noscentrais[i]!=0) System.out.print(noscentrais[i]);
	}

	System.out.println();

	Arrays.sort(nosperi);
	for(int i=0; i<Nnodes; i++){
	    if(i!=Nnodes-1 && nosperi[i]!=0)
		System.out.print(nosperi[i] + " ");
	    else 
		if(nosperi[i]!=0) System.out.print(nosperi[i]);
	}

	System.out.println();


    }
}
