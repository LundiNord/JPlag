import java.util.*;
import java.io.*;

public class ex15{
    static int n;
    static boolean adj[][];
    static boolean visited[];

    static void dfs(int v){
	visited[v]=true;
	for(int i=1;i<=n;i++){
	    if(adj[v][i] && !visited[i])
		dfs(i);
	}
    }
    
    static int redes(){
	int cont=0;
	for(int i=1;i<=n;i++)
	    visited[i]=false;
	for(int i=1;i<=n;i++){
	    if(visited[i]==false){
		cont++;
		dfs(i);
	    }
	}
	return cont;
    }
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	n=in.nextInt();
	adj=new boolean[n+1][n+1];
	visited=new boolean[n+1];
	int edges=in.nextInt();
	for(int i=0;i<edges;i++){
	    int a1=in.nextInt();
	    int a2=in.nextInt();
	    adj[a1][a2]=adj[a2][a1]=true;
	}
	System.out.println(redes());
    }
}


    