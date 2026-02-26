import java.util.*;
import java.io.*;

public class Prob14{
  static int n,edges;        // nos do grafo
  static int count = 0;
  static boolean adj[][];   //matriz de adjacencias
  static boolean visited[]; // visitados?



  static void conex(){
    for (int i = 1; i <= n; i++)
      if (!visited[i]){
        count += 1;
        dfs(i);
      }
  }


  static void dfs(int v){
    visited[v] = true;
    for( int i = 1; i <= n; i++)
      if(!visited[i] && adj[v][i] )
        dfs(i);

  }


  public static void main(String args[]){


    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    edges = in.nextInt();
    adj = new boolean[n+1][n+1];
    visited = new boolean[n+1];
    for (int i=0; i < edges; i++){
      int a = in.nextInt();
      int b = in.nextInt();
      adj[a][b] = adj[b][a]  = true;


    }

    conex();
    System.out.print(count);


  }
}
