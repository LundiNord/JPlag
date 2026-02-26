import java.util.*;
public class CFCs {
	public static void main(String argv[]){
		int n, m, resposta=0;
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		m=in.nextInt();
		
		boolean[][] matrix = new boolean[n+1][n+1];
		CFCs[] nodes = new CFCs[n+1];
		
		for(int i=0; i<m; i++){
			int a, b;
			a=in.nextInt();
			b=in.nextInt();
			
			matrix[a][b] = true;
			matrix[b][a] = true;
		}
		
		for(int i=1; i<=n;i++){
			nodes[i]=new CFCs(i);
		}
		
		for(int i=1; i<=n;i++){
			if(nodes[i].visited)continue;
			nodes[i].visited=true;
			resposta++;
			DFS(i,n,matrix,nodes);
		}
		
		System.out.println(resposta);
	}


	public static void DFS(int a, int n, boolean[][] matrix,CFCs[] nodes){
		for(int i=1; i<=n;i++){
			if(matrix[a][i] && !nodes[i].visited){
				nodes[i].visited=true;
				DFS(i,n,matrix,nodes);
			}
		}
	}
	
	private boolean visited;
	
	public CFCs(int x){
		this.visited = false;
	}
}
