import java.io.*;
import java.util.*;
/*class Node{
    int i;
    boolean visi;
    Node adja;
    int v;
    Node(int v,Node a){
	this.v=v;
	adja=a;
	visi=false;
    }
   void dfs(Node v){
	v.visi=true;
	if(adja.visi!=true)
	    dfs(adja);
   }
   }*/
    
    
    
class Grafo {
    int nv;
    int ne;
    boolean adj[][];
    boolean[] visi;
    Grafo(int nv, int ne){
	this.nv=nv;
	this.ne=ne;
	adj=new boolean[nv+1][nv+1];
	visi=new boolean[nv+1];
    }
    void dfs(int n){
	visi[n]=true;
	for(int i=1;i<=nv;i++)
	    if(!visi[i] && adj[n][i])
		dfs(i);
    }
}

class prob16{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	int L=sc.nextInt();
	Grafo g=new Grafo(N,L);
	int k,j, count=0;
	for(int i=0;i<L;i++){
	    k=sc.nextInt();
	    j=sc.nextInt();
	    g.adj[k][j]=true;
	    g.adj[j][k]=true;
	}
	for(int y=1;y<=N;y++){
	    if(!g.visi[y]){
		count++;
		g.dfs(y);
	    }
	}
	System.out.println(count);
    }	
}
