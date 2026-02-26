import java.util.*;
import java.io.*;

public class Problema10 {

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();

    int N;        // N encomendas.
    Sapato enc[]; // As encomendas.

    N = stdin.nextInt();

    enc = new Sapato[N];

    for (int i = 0; i < N; i++)
      enc[i] = new Sapato(stdin.nextInt(), stdin.nextInt(), i + 1);

    Arrays.sort(enc);

    // Output
    for (int i = 0; i < N; i++)
      sb.append(enc[i].id + " ");

    sb.setLength(sb.length() - 1);

    System.out.println(sb.toString());
  }
}

class Sapato implements Comparable<Sapato> {
  int dias, multa, id;
  float ratio;

  Sapato(int d, int m, int id) {
    this.dias = d;
    this.multa = m;
    this.id = id;

    this.ratio = m / d;
  }

  @Override
  public int compareTo(Sapato that) {
    Float is = this.ratio;
    Float at = that.ratio;

    int ret = at.compareTo(is);

    if (ret == 0) {
      Integer tis = this.id;
      Integer tat = that.id;

      return tis.compareTo(tat);
    }

    return ret;
  }

  @Override
  public String toString() {
    return "ID: " + id + " D: " + dias + " M: " + multa;
  }
}
