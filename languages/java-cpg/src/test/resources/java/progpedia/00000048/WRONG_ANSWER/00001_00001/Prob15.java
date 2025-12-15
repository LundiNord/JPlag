import java.util.*;

class Prob15
{
    public static void main(String[] args) {
	
	Scanner input = new Scanner(System.in);
	
	int n = input.nextInt();
	int l = input.nextInt();
	Boolean [][] table = new Boolean[n+1][n+1];
	Boolean []visited  = new Boolean[n+1];
	
	for (int i=0;i<=n;i++) {
	    for (int j=0;j<=n;j++) {
		table[i][j]=false;
	    }
	}
	for (int i=0;i<=n;i++) 
	    visited[i]=false;
	
	for (int i=0;i<l;i++) {
	    int a= input.nextInt();
	    int b= input.nextInt();
	    table[a][b]=true;
	    table[b][a]=true;
	}
	int contador=0;
	for (int i=1;i<n;i++) {
	    if (!visited[i]) {
		dfs(i,visited,table,n);
		contador++;
	    }
	}
	System.out.println(contador);
    }
    static void dfs(int node, Boolean[] visited, Boolean[][] table, int n) {
	visited[node]=true;
	for (int i=1;i<=n;i++) 
	    if (table[node][i] && !visited[i])
		dfs(i, visited, table, n);
		
    }
}
