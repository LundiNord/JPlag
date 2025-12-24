import java.util.*;
class teste{
    static int n;
    static boolean adj[][];
    static boolean visited[];

    static void conexo(){
	int cont=0;
	for(int i=1;i<=n;i++){
	    visited[i]=false;
	}
	for(int i=1;i<=n;i++){
	    if(!visited[i])
		cont++;
	    dfs(i);
	}
	System.out.println(cont);
    }

    static void dfs(int v){
	visited[v]=true;
	for(int i=1;i<=n;i++){
	    if(adj[v][i] && !visited[i])
		dfs(i);
	}
    }
    
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	n=in.nextInt();
	adj = new boolean[n+1][n+1];
	visited = new boolean[n+1];
	int edges = in.nextInt();
	for(int i=0;i<edges;i++){
	    int a= in.nextInt();
	    int b= in.nextInt();
	    adj[a][b]=adj[b][a]=true;

	}
	conexo();
    }
}
