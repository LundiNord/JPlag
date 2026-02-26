import java.util.LinkedList;
import java.util.Scanner;

class Arc {
    int no_final;     
    Arc(int fim){
	no_final = fim;
    }
    int final_node() {
	return no_final;
    }
}

class Node {
    LinkedList<Arc> adjs;
    Node() {
	adjs = new LinkedList<Arc>();
    }
}

class Graph {
    Node verts[];
    int nvs, narcos;		
    public Graph(int n) {
	nvs = n;
	narcos = 0;
	verts  = new Node[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new Node();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
	return nvs;
    }
    
    public int num_arcos(){
	return narcos;
    }
    
    public LinkedList<Arc> adjs_no(int i) {
	return verts[i].adjs;
    }
    
    public void insert_new_arc(int a, int b){
	verts[a].adjs.addFirst(new Arc(b));
        narcos++;
    }

    public Arc find_arc(int a, int b){
	for (Arc adj: adjs_no(a))
	    if (adj.final_node() == b) return adj;
	return null;
    }
}

public class RedeBiologica {    
    static Graph read_graph(Scanner in){
	Graph g;
	int nNos = in.nextInt(); 
	int ramos = in.nextInt(); 
	int inicio, end;
	g = new Graph(nNos);		
	for(int i=0; i<ramos; i++){	
	    inicio = in.nextInt();
	    end = in.nextInt();
	    g.insert_new_arc(inicio, end);
	    g.insert_new_arc(end, inicio);
	}
	return g;
    }
    	
    static int[] distmin(Graph g,int origem) {
	int d[] = new int[g.nvs+1];
	boolean visited[] = new boolean[g.nvs+1];
	for (int u = 0; u < g.nvs+1 ; u++){ 
	    visited[u] = false; 
	}   
	visited[origem] = true; 
	d[origem] = 0;
	LinkedList<Integer>q = new LinkedList<Integer>(); 
	q.add(new Integer (origem));
	while (!q.isEmpty()) {
	    Integer aux = (Integer)q.removeFirst() ;
	    int u = aux.intValue(); 
	    for(Arc a: g.adjs_no(u)){
		int v = a.final_node();
		if(!visited[v]){
		    visited[v] = true; 
		    d[v] = d[u] + 1;
		    q.addLast(new Integer (v));
		}
	    }
	}
	return d;
	
    }
	  	
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	Graph g= read_graph(in);
	int v[]=new int[g.nvs+1];
	int m[][] = new int[g.nvs+1][g.nvs+1];
	for(int i=1;i<=g.nvs;i++){
	    m[i][i]=0;
	    v=distmin(g,i);
	    for(int j=1;j<=g.nvs;j++)
		m[i][j]=v[j];
	}
	



	int exc[]=new int[g.nvs+1];
	for(int i=1;i<=g.nvs;i++){
	    for(int j=1;j<=g.nvs;j++){
		if(m[i][j]>exc[i])
		    exc[i]=m[i][j];
	    }
	}
	int diam=exc[1];
	for(int k=2;k<=g.nvs;k++){
	    if(exc[k]>diam)
		diam=exc[k];
	}
	int raio=exc[1];
	for(int k=2;k<=g.nvs;k++){
	    if(exc[k]<raio)
		raio=exc[k];
	}
	System.out.println(diam);
	System.out.println(raio);
	int count=0;
	int countaux=0;
	for(int k=1;k<=g.nvs;k++){
	    if(exc[k]==raio)
		count++;
	}
	for(int k=1;k<=g.nvs;k++){
	    if(exc[k]==raio){
		countaux++;
		if(countaux==count)
		    System.out.println(k);
		else System.out.print(k+" ");
	    }
	}
	count=0;
	countaux=0;
	for(int k=1;k<=g.nvs;k++){
	    if(exc[k]>=diam)
		count++;
	}
	for(int k=1;k<=g.nvs;k++){
	    if(exc[k]>=diam){
		countaux++;
		if(countaux==count)
		    System.out.println(k);
		else System.out.print(k+" ");
	    }
	}
    }
}
