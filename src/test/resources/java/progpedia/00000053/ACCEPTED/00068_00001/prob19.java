import java.util.*;
import java.io.*;
import java.lang.*;

class Node{
    public LinkedList<Integer> adj;
    public boolean visited;
    public int distance;

    Node(){
	adj=new LinkedList<Integer>();
	visited=false;
	distance=-1;
    }
}

class Graph{
    public int N;
    public Node nodes[];
    Graph(int N){
	this.N=N;
	nodes=new Node[N+1];
	for(int i=1;i<N+1;i++){
	    nodes[i]=new Node();
	}
    }
    public void addLink(int a, int b){
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    public void bfs(int v){
	LinkedList<Integer> q =new LinkedList<Integer>();
	q.add(v);
	nodes[v].visited=true;
	nodes[v].distance=0;
	while(q.size()>0){
	    int u=q.removeFirst();
	    for(int w : nodes[u].adj){
		if(!nodes[w].visited){
		    q.add(w);
		    nodes[w].visited=true;
		    nodes[w].distance=nodes[u].distance+1;
		}
	    }
	}
    }
}

class prob19{

    public static void main(String[] args){
	Scanner in= new Scanner(System.in);
	int N=in.nextInt();
	int E=in.nextInt();
	int[][] distancias = new int[N+1][N+1];
	Graph g = new Graph(N);
	for(int i=0;i<E;i++){
	    g.addLink(in.nextInt(),in.nextInt());	    
	}
	for(int i=1;i<N+1;i++){
	    g.bfs(i);
	    for(int j=1;j<N+1;j++){
		distancias[i][j]=g.nodes[j].distance;
		g.nodes[j].visited=false;
		g.nodes[j].distance=-1;
	    }
	}
	int diam=0;
	int raio=1000;
	int ncentral[]=new int[N+1];
	int nperi[]=new int[N+1];
	int [] ex =new int[N+1];
	for(int i=1;i<N+1;i++){
	    for(int j=1;j<N+1;j++){
		if(distancias[i][j]!=0){
		    ex[i]=Math.max(ex[i],distancias[i][j]);
		    diam=Math.max(ex[i],diam);
		}
	    }
	    raio=Math.min(ex[i],raio);
	}

	System.out.println(diam);
	System.out.println(raio);
	for(int i=1;i<N+1;i++){
	    for(int j=1;j<N+1;j++){
		if(ex[i]==diam){
		    nperi[i]=i; 
		}
	    }
	}

	for(int i=1;i<N+1;i++){
	    for(int j=1;j<N+1;j++){
		if(ex[i]==raio){
		    ncentral[i]=i;
		}
	    }
	}
	boolean first=true;
	for(int i=0;i<ncentral.length;i++){
	    if(ncentral[i]!=0)
		if(first){
		    System.out.print(ncentral[i]);
		    first=false;
		} 
		else{
		    System.out.print(" "+ncentral[i]);
		}
	}
	first =true;
	System.out.println();
	for(int i=0;i<nperi.length;i++){
	    if(nperi[i]!=0)
		if(first){
		    System.out.print(nperi[i]);
		    first=false;
		}
		else{
		    System.out.print(" "+nperi[i]);
		}
	}
	System.out.println();
    }
}
    
	      

