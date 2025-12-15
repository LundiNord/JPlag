import java.util.*;
import java.io.*;

public class Problema12 {

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);

    int N;        // N pedras da primeira camada; N camadas.
    int D;        // Numero de pedras deterioradas/em falta.
    int cam, pos, maxpos; // Camada. Posicao.
    int i, j;

    N = stdin.nextInt();
    boolean piramide[][] = new boolean[N][N];
    int sp[][] = new int[N][N];

    // Encher piramide com 'true'.
    for (i = 0; i < N; i++)
      for (j = 0; j <= i; j++)
        piramide[i][j] = true;

    D = stdin.nextInt();

    // Trocar para 'false' as pedras deterioradas.
    for (i = 0; i < D; i++) {
      cam = stdin.nextInt();
      pos = stdin.nextInt();
      piramide[N - cam][pos - 1] = false;
    }

    // Subproblema da ultima camada.
    cam = N - 1;
    for (pos = 0; pos < N; pos++)
      if (piramide[cam][pos])
        sp[cam][pos] = 1;
      else
        sp[cam][pos] = 0;

    // Subproblemas das camadas restantes.
    maxpos = N;
    for (cam = N - 2; cam >= 0; cam--) {
      maxpos--;
      for (pos = 0; pos < maxpos; pos++) {
        if (piramide[cam][pos])
          sp[cam][pos] = sp[cam + 1][pos] + sp[cam + 1][pos + 1];
        else
          sp[cam][pos] = 0;
      }
    }

    // Output.
    System.out.println(sp[0][0]);
  }
}
