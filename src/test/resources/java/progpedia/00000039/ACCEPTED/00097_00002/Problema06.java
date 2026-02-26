import java.util.*;
import java.io.*;

public class Problema06 {
  static int somasSize;

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);

    int n, p, pergunta, i, j, k=0;
    int s[];
    int somas[];
    //int somasSize;
    
    // Input
    n = stdin.nextInt();
    s = new int[n];

    for (i = 0; i < n; i++)
      s[i] = stdin.nextInt();

    somasSize = n*(n-1)/2;
    
    somas = new int[somasSize];

    for (i = 0; i < n; i++)
      for (j = i+1; j < n; j++)
	somas[k++] = s[i] + s[j];

    Arrays.sort(somas);
    
    p = stdin.nextInt();

    for (i = 0; i < p; i++) {
      pergunta = stdin.nextInt();
      System.out.println (bsearch(somas, 0, somasSize, pergunta));
    }
  }

  static String bsearch (int v[], int low, int high, int key) {
    int middle;
    int dist1, dist2;
    
    while (low < high) {
      middle = low + (high - low) / 2;

      if (key == v[middle])
	return v[middle] + "";

      else if (key < v[middle])
	high = middle;
      
      else
	low = middle + 1;
    }
    
    if (low > 0)
      low = low -1;
    
    dist1 = Math.abs(key-v[low]);

    if (low+1 >= somasSize)
      return v[low] + "";
    
    else {
      dist2 = Math.abs(key-v[low+1]);
    
      if (dist1 == dist2)
	return v[low] + " " + v[low+1];

      else if (dist1 < dist2)
	return v[low] + "";

      else
	return v[low+1] + "";

    }
  }
}
