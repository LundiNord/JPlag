import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class grafo{
	
	List<Integer> adj;
	boolean visited;
	
	grafo(){
		adj=new LinkedList<Integer>();
		visited=false;
	}
}

public class sixteen {

	
	public static void dfs(grafo[] g, int i){
		
		g[i].visited=true;
		while(!g[i].adj.isEmpty()){
			int temp=g[i].adj.remove(0);
			if(!g[temp-1].visited)	
				dfs(g,temp-1);
		}
	}
	
	public static void count(grafo[] g, int n){
		
		int c=0;
		
		for(int i=0;i<n;i++)
			if(!g[i].visited){
				c++;
				dfs(g,i);
			}
		
		System.out.println(c);
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		int l=s.nextInt();
		
		grafo g[]=new grafo[n];
		for(int i=0;i<n;i++)
			g[i]=new grafo();
		
		for(int i=0;i<l;i++){
			int one=s.nextInt();
			int two=s.nextInt();
			g[one-1].adj.add(two);
			g[two-1].adj.add(one);
		}
		
		count(g,n);
	}	
}
