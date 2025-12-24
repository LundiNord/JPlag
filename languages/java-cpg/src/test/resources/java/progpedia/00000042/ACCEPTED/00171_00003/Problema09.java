import java.util.*;
import java.io.*;

public class Problema09 {
  static Segmento segmts[];
  static int N;        // N segmentos.
  static int M;        // Cobrir até M.
  static int END = 0;  // Preenchido até.
  static int LAST = 0; // Procurar a partir de.

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int MIN = 0;

    // Input
    M = stdin.nextInt();
    N = stdin.nextInt();

    segmts = new Segmento[N];

    for (int i = 0; i < N; i++)
      segmts[i] = new Segmento(stdin.nextInt(), stdin.nextInt());

    // Ordenar os segmentos.
    Arrays.sort(segmts);

    //Enquanto não estiver preenchido até M.
    while (END < M) {
      chooseNext(END);
      MIN++;
    }

    // Output
    System.out.println(MIN);
  }

  static public void chooseNext(int start) {
    for (int i = LAST; i < N && segmts[i].inicio <= start; i++) {
      if (segmts[i].fim > END) {
        END = segmts[i].fim;
        LAST = i;
      }
    }
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

    return is.compareTo(at);
  }

  @Override
  public String toString() {
    return ("I:" + this.inicio + " F:" + this.fim);
  }
}
