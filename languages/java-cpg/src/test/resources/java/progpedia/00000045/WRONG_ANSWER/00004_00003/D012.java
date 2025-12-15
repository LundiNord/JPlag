import java.io.*;
import java.util.*;


public class D012 {
  static int n, d, c[], p[], count[][], pi[][];

  static void solve() {
    int aux;

    count = new int[n+1][n+1]; // *Criar espaco para  a matriz

  // * Prencher Matriz comecando com 1 na camada 1
    aux = n;
    for (int i=1; i<=n; i++) {
      for (int j=1; j<=aux; j++) {
        if (i==1 && pedras_falta(i,j))
          count[i][j] = 1;
        else if (i<n && pedras_falta(i,j))
          count[i][j] = count[i-1][j] + count [i-1][j+1];
        else if (i==n &&  pedras_falta(i,j))
          count[i][j] = count[i-1][j] + count[i-1][j+1];
        }
      aux--;
    }

  /*  // *Imprimir Matriz
    aux =1;
    for (int i=n; i>=1; i--) {
      for (int j=1; j<=aux; j++)
        System.out.print(count [i][j] + " ");
      System.out.println();
      aux++;
    }
  */


  }

  //------------------------------------------------------------

  // Ver se é uma pedra em falta ou deterioada
  static boolean pedras_falta(int camada, int pos) {


    for (int i=0; i<d; i++) {
      if (c[i] == camada && p[i] == pos)
        return false;
    }
    return true;
  }

  //-------------------------------------------------------

  static void readInput() {

    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();
    d = stdin.nextInt(); // Pedras em falta ou muito deterioradas

    c = new int[d];
    p = new int[d];

    for (int i=0; i<d; i++) {
      c[i] = stdin.nextInt();
      p[i] = stdin.nextInt();
    }

  }

  //------------------------------------------------

  public static void main(String[] args) {

    readInput();
    solve();
    System.out.println(count[n][1]);

  }

}
