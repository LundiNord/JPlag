
//package prob19;
  
  
import java.util.*;


class No {
    public LinkedList<Integer> adj;
    public boolean visited;        
    public int dist;           

    No() {
	adj = new LinkedList<Integer>();
	visited = false;
	dist = -1;
    }
}


class Graph {
    int n;          
    No nos[];    
    int matriz[][];
    int diametro;
    int r;

    Graph(int n) {
	this.n = n;
	matriz=new int[n+1][n+1];
	nos  = new No[n+1]; 
	for (int i=1; i<=n; i++)
	    nos[i] = new No();
    }
    public void fal(){
	for(int i=1;i<=n;i++)
	    nos[i].visited=false;

}

    public void diametro(){
	diametro=0;
	
	for(int i=1;i<=n;i++){
	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>diametro)
		    diametro=matriz[i][j];

		}
		
	}
	System.out.println(diametro);


}

    public void r(){
	r=diametro;
	
	
	for(int i=1;i<=n;i++){
	int count=0;
	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>count)
		     count=matriz[i][j];
		

	}
		if(count<r)
		   r=count;
}
	
	System.out.println(r);


}

    public void nosc(){
	boolean a1=true;

	for(int i=1;i<=n;i++){
	    int count1=0;

	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>count1)

		    count1= matriz[i][j];
	    }

		if(count1==r && a1){
		    a1=false;

		    System.out.print(i);
		    
		}

		else if(count1==r)

		    System.out.print(" " + i);
	}


}

    public void nosp(){

	boolean a2=true;

	for(int i=1;i<=n;i++){
	    int count2=0;

	    for(int j=1;j<=n;j++){
		if(matriz[i][j]>count2)

		    count2= matriz[i][j];
	    }

	    if(count2==diametro && a2){
		a2 = false;

		System.out.print(i);
		
	    }
	    else if(count2==diametro)

		System.out.print(" " + i);
	}


}

    public void addLink(int a, int b) {
	nos[a].adj.add(b);
	nos[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
	LinkedList<Integer> q = new LinkedList<Integer>();

	q.add(v);
	nos[v].visited = true;
	nos[v].dist = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
	    //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    matriz[v][u]=nos[u].dist;
	    for (int w : nos[u].adj)
		if (!nos[w].visited) {
		    q.add(w);
		    nos[w].visited  = true;
		    nos[w].dist = nos[u].dist + 1; 
		}	    
	}
    }
}

public class Prob19 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n=in.nextInt();
	Graph g = new Graph(n);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());

	
	for(int i=1;i<=n;i++){
	    g.fal();
	    g.bfs(i);	
	    
}
	g.diametro();
	g.r();
	g.nosc();
	System.out.println();
	g.nosp();
	System.out.println();
    }
}
    

