import java.util.*;
import java.io.*;

// END = 0;
// verificar segmentos com INICIO menor ou igual a END
// o que termina mais tarde

public class Problema09 {
  static Segmento segmts[];

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int N;       // N segmentos
    int M;       // Cobrir até M
    int END = 0; // Preenchido até.

    // Input
    M = stdin.nextInt();
    N = stdin.nextInt();

    segmts = new Segmento[N];

    for (int i = 0; i < N; i++) {
      segmts[i] = new Segmento(stdin.nextInt(), stdin.nextInt());
    }

    // Ordenar os segmentos.
    Arrays.sort(segmts);

    /*
    for (int j = 0; j < N; j++)
      System.out.println(segmts[j].toString());
    */

    int MIN = 0;

    /*
     Enquanto não estiver preenchido até M.
     Se vector está ordenado e há sempre maneira
    de cobrir. Ez.
    */
    for (int i = 0; i < M; i++) {
      if (segmts[i].fim >= M) {
        MIN = i + 1;
        break;
      }
    }

    // Output
    System.out.println(MIN);
  }
}

class Segmento implements Comparable<Segmento> {
  public int inicio, fim;

  Segmento(int s, int f) {
    inicio = s;
    fim = f;
  }

  @Override
  public int compareTo(Segmento that) {
    if (this.inicio < that.inicio)
      return -1;

    if (this.inicio > that.inicio)
      return 1;

    Integer is = this.fim;
    Integer at = that.fim;

    return at.compareTo(is);
  }

  @Override
  public String toString() {
    return ("I:" + this.inicio + " F:" + this.fim);
  }
}
