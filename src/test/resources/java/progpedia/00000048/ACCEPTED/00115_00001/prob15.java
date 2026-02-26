import java.util.*;

class prob15 {

    private static boolean visited[];
    private static int [][] adj;
    private static int N;
    private static void dfs(int v){
	visited[v]=true;
	for(int i=0;i<N;i++){
	    if(adj[v][i]==1 && !visited[i])
		dfs(i);
	}
    }
  

    public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	N= in.nextInt();
	int L= in.nextInt();
	adj = new int[N][N];
	visited = new boolean[N];
	
	int count=0;
	for(int i=0;i<N;i++){
	    visited[i] =false;
	    for(int j=0;j<N;j++)
		adj[i][j]=0;
	}

	for(int i=0;i<L;i++){
	    int a=in.nextInt() ,b=in.nextInt();
	    adj[a-1][b-1]=1;
	    adj[b-1][a-1]=1;
	}

	    
	for(int i=0;i<N;i++){
      
	    if(!visited[i]){
		count++;
		dfs(i);
	    }
	}
	System.out.println(count);
    }
}	



