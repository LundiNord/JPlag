import java.io.*;
import java.util.*;

class Piramide{
    public static void main(String[] args) {
	Scanner stdin = new Scanner(System.in);
	int c = stdin.nextInt();
	long[][] piramide = new long[c][c];
	int d = stdin.nextInt();
	for(int i = 0; i < c; i++) 
	    for(int j = 0; j < c; j++)  
		piramide[i][j] = 1;
	for(int i = 0; i < d; i++)
	    piramide[stdin.nextInt() - 1][stdin.nextInt() - 1] = 0;
	for(int i = 1; i < c; i++) {
	    for(int j = 0; j < c - i; j++) {
		if(piramide[i][j] == 0)
		    piramide[i][j] = 0;
		else
		    piramide[i][j] = piramide[i - 1][j] + piramide[i - 1][j + 1];
	    }
	}
	System.out.println(piramide[c - 1][0]);
    }
}
