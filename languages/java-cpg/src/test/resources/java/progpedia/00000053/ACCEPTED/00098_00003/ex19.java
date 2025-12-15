import java.util.*;
import java.io.*;

class Node{
    public LinkedList<Integer> adj;
    public boolean visited;
    public int distance;
    Node(){
	adj= new LinkedList<Integer>();
	visited=false;
	distance=-1;
    }
}
class Tree{
    int num;
    Node nos[];
    int matrix[][];
    int diam;
    int raio;
    
    Tree(int n){
	num=n;
	matrix=new int[n+1][n+1];
	nos=new Node[n+1];
	for(int i=1;i<=num;i++){
	    nos[i]=new Node();
	}
    }
    public void addLink(int a, int b){
	nos[a].adj.add(b);
	nos[b].adj.add(a);
    }
    public void clean(){
	for(int i=1;i<=num;i++){
	    nos[i].visited=false;
	}
    }
    public void bfs(int v){
	LinkedList<Integer> t = new LinkedList<Integer>();
	t.add(v);
	nos[v].visited=true;
	nos[v].distance=0;
	while(t.size()>0){
	    int y=t.removeFirst();
	    matrix[v][y]=nos[y].distance;
	    for(int x: nos[y].adj){
		if(nos[x].visited==false){
		    t.add(x);
		    nos[x].visited=true;
		    nos[x].distance=nos[y].distance+1;
		}
	    }
	}
    }
    public void diam(){
	diam=0;
	for(int i=1;i<=num;i++){
	    for(int j=1;j<=num;j++){
		if(matrix[i][j]>diam){
		    diam=matrix[i][j];
		}
	    }	
	}
	System.out.println(diam);
    }
    public void raio(){
	raio=diam;
	for(int i=1;i<=num;i++){
	    int cont=0;
	    for(int j=1;j<=num;j++){
		if(matrix[i][j]>cont) {
		    cont=matrix[i][j];
		}
	    }
	    if(cont<raio) raio=cont;
	}
	System.out.println(raio);
    }
    public void noscentrais(){
	boolean vis=true;
	for(int i=1;i<=num;i++){
	    int cont=0;
	    for(int j = 1; j <= num; j++) {
		if(matrix[i][j] > cont)
		    cont = matrix[i][j];
	    }
	    if(cont==raio && vis){
		vis=false;
		System.out.print(i);
	    }
	    else if(cont==raio)
		System.out.print(" " + i);
	}
	System.out.println();
    }
    public void nosper(){
	boolean vis=true;
	for(int i=1;i<=num;i++) {
	    int cont = 0;
	    for(int j=1;j<=num;j++) {
		if(matrix[i][j] > cont)
		    cont=matrix[i][j];
	    }
	    if(cont==diam && vis) {
		vis=false;
		System.out.print(i);
	    }
	    else if(cont==diam)
		System.out.print(" " + i);
	}
	System.out.println();
    }
}
class ex19{
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int num=in.nextInt();
	int edges=in.nextInt();
	Tree tree= new Tree(num);
	for(int i=1;i<=edges;i++){
	    tree.addLink(in.nextInt(), in.nextInt());
	}
	for(int i=1;i<=num;i++){
	    tree.clean();
	    tree.bfs(i);
	}
	tree.diam();
	tree.raio();
	tree.noscentrais();
	tree.nosper();
    }
}
