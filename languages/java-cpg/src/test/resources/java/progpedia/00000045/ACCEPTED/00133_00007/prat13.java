import java.io.*;
import java.util.*;

public class prat13 {
    static boolean[][] piramide;
    static int n;
    static int d;
    //static int caminhos = 0;
    static long[][] count;

    public static long sobe(int x, int y) {
	//System.out.println("posicao: " + x + " " + y);
	if (count[x][y] != -1)
	    return count[x][y];

	if (x == n-1)
	    return 1;
	else {
	    long c = 0;
	    if (piramide[x+1][y])
		c += sobe(x+1,y);
	    if (piramide[x+1][y+1])
		c += sobe(x+1,y+1);
	    count[x][y] = c;
	    return c;
	}
    }

    public static void main (String[] args) {
	Scanner reader = new Scanner(System.in);
	n = reader.nextInt();
	d = reader.nextInt();
	piramide = new boolean[n][n];
	count = new long[n][n];

	for (int i = 0; i < n; i++)
	    Arrays.fill(count[i], -1);

	for (int i = 0; i < n; i++) {
	    for (int j = 0; j <= i; j++) {
		piramide[i][j] = true;
	    }
	}
	for (int i = 0; i < d; i++) {
	    piramide[n-reader.nextInt()][reader.nextInt()-1] = false;
	}

	/*      for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++)
		System.out.print(piramide[i][j] + " ");
	    System.out.println();
	    }*/


	/*
	for (int i = 0; i < n; i++) {
	    if (piramide[n-1][n-i-1]) sobe(n-1, n-i-1);
	    }*/
	if (piramide[0][0])
	    System.out.println(sobe(0,0));
	else
	    System.out.println(0);
    }
}
