import java.io.*;
import java.util.*;

public class D010 {
  static int n, d[], m[], seq[];


  static void solve() {

    for (int i=0; i<n; i++) {
      seq[i] = (m[i]-d[i]);
    }


    for (int i=0; i<n; i++) {
      int maior =0;
      for (int j=0; j<n; j++) {
        if (seq[j] > seq[maior] && seq[j] != -1)
          maior = j;
      }
      seq [maior] = -1;
      System.out.print(maior+1 + " ");
    }
    System.out.println();
  }

 //------------------------------------------------------------

  static void readInput() {

    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();

    d = new int[n];
    m = new int[n];
    seq = new int[n];

    for (int i=0; i<n; i++) {
      d[i] = stdin.nextInt();
      m[i] = stdin.nextInt();
    }



  }

  //------------------------------------------------------------

  public static void main(String[] args) {

    readInput();
    solve();

  }
}
