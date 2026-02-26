import java.io.*;
import java.util.*;

class Grafo{
    int nv;
    int ne;
    boolean adj[][];
    int dis[][];
    boolean visi[];
    Queue<Integer> Q=new LinkedList<Integer>();
    Grafo(int nv, int ne){
	this.nv=nv;
	this.ne=ne;
	adj=new boolean[nv+1][nv+1];
	visi=new boolean[nv+1];
	dis=new int[nv+1][nv+1];
    }
    void bfs(int n){
	int ele, aux;
	Arrays.fill(visi,false);	
	Q.add(n);
	dis[n][n]=0;
	visi[n]=true;
	while(!Q.isEmpty()){
	    ele=Q.remove();
	    aux=1;
	    while(aux<=nv){ 
		if(adj[ele][aux] && !visi[aux]){
		    Q.add(aux);
		    visi[aux]=true;
		    dis[n][aux]=dis[n][ele]+1;
		}
		aux++;
	    }
	}
    }

}	    
class prob21{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	int E=sc.nextInt();
	Grafo g=new Grafo(N,E);
	int k,j;
	int raio=99999999, ra=0, rb=0;
	int exc=0, excc=0;
	int cent[]=new int[N];
	int per[]=new int[N];
	for(int i=0;i<E;i++){
	    k=sc.nextInt();
	    j=sc.nextInt();
	    g.adj[k][j]=true;
	    g.adj[j][k]=true;
	}
	for(int y=1;y<=N;y++){
	    g.bfs(y);
	}
	//raio
	for(int o=1;o<=N;o++){
	    rb=0;
	    for(int u=1;u<=N;u++){
		ra=g.dis[o][u];
		if(rb<ra &&ra!=0)
		    rb=ra;
		if(excc<ra)
		    excc=ra;
	    }
	    if(raio>rb)
		raio=rb;
	    if(exc<excc)
		exc=excc;
	}
		int maxr, minr;
	int a=0, b=0;
	System.out.println(exc);
	System.out.println(raio);
	for(int r=1;r<=N;r++){
	    maxr=0;
	    minr=0;
	    for(int e=1;e<=N;e++){
		if(maxr<g.dis[r][e]){
		    maxr=g.dis[r][e];
		}
		if(minr<g.dis[r][e])
		    minr=g.dis[r][e];
	    }if(raio==maxr){
		 cent[a++]=r;
	    }
	    if(exc==minr)
		per[b++]=r;
	}
	    Arrays.sort(per);
	    Arrays.sort(cent);
	    a=N-1;
	    b=N-1;
	    while(a>=0 && cent[a]!=0) a--;
	    System.out.print(cent[++a]);
	    while (++a<N)
		System.out.print(" "+cent[a]);
	    System.out.println();
	    while(b>=0 && per[b]!=0) b--;
	    System.out.print(per[++b]);
	    while(++b<N)
		System.out.print(" "+per[b]);
	    System.out.println();
	    
	

    }
}
