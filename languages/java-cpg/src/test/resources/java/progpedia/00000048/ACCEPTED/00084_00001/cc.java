import java.util.*;
import java.io.*;


public class cc
{

    static boolean []vis;
    static boolean [][]save;
    static int n;

   public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       int l;
       int a,b;
       int c = 0;
       n = in.nextInt();
       l = in.nextInt();


       vis = new boolean[101];
       for(int i = 1;i < n;i++)
	   vis[i] = false;

       save = new boolean[101][101];
       for(int i = 1; i< n; i++)
	   for(int j = 1; j<n;j++)
	       save[i][j]= false;



       while(l-- != 0){
	   a = in.nextInt();
	   b = in.nextInt();
	   save[a][b] = true;
	   save[b][a] = true;
       }

       for(int i=1;i<=n;i++)
	   if(!(vis[i])) {
	       dfs(i);
	       c++;
	   }

       System.out.println(c);

   }

    public static void dfs(int v){
	vis[v] = true;
	for(int i = 1; i<=n ;i++) 
	    if(!(vis[i])  && save[v][i])
		dfs(i);
    }

}
