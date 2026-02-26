import java.io.*;
import java.util.*;

public class Problema05 {
  public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);

    int N = stdin.nextInt();
    int i, j, count = 0;

    int v[] = new int[N];

    for (i = 0; i < N; i++)
      v[i] = stdin.nextInt();

    for (i = 0; i < N; i++)
      for (j = i + 1; j < N; j++)
        if (v[i] > v[j])
          count++;

    System.out.println(count);
  }
}
