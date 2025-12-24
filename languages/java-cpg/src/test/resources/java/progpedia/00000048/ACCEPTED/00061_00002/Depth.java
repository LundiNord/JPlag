import java.util.*;
import java.io.*;



class Depth{
    
    static boolean a[][]=new boolean[101][101];
    static boolean v[]=new boolean[101];
    static int c=0;
    
    public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	in.nextLine();
	int l=in.nextInt();
	in.nextLine();
	int x=0,y=0;
	for(int i=0;i<l;i++){
	    x=in.nextInt();
	    y=in.nextInt();
	    a[x][y]=a[y][x]=true;
	    in.nextLine();
	}
	dfs(n);
	System.out.println(n-c);
    }
    public static void dfs(int n){
	for(int i=1;i<=n;i++)
	    if(!v[i])
		dfs(i,n);
    }
    private static void dfs(int x,int n){
	v[x]=true;
	for(int i=1;i<=n;i++)
	    if(a[x][i] && !v[i]){
		dfs(i,n);
		//System.out.println("DFS("+i+")");
		c++;
	    }
    }
}	
