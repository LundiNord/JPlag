import java.util.*;
import java.io.*;

class p15{
  static int n;
  static boolean m[][];
  static boolean visited[];

  static void dfs(int v){
    visited[v] = true;
    for(int i = 1; i<=n;i++){
      if(m[v][i] && !visited[i]) dfs(i);
    }
  }
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
      n = stdin.nextInt();
      int k = stdin.nextInt();
      visited = new boolean[n+1];
      m = new boolean[n+1][n+1];
      int contador = 0;
      for(int i = 0; i<k; i++){
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        m[a][b] = m[b][a] = true;
      }
      for(int i = 1; i<=n; i++){
        if(!visited[i]){
          contador++;
          dfs(i);
        }
      }
      System.out.println(contador);

  }
}
