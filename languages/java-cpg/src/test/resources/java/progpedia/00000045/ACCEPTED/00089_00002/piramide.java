// Neste problema fui ajudado pela colega Frederica Murillo
import java.io.*;
import java.util.*;

class piramide{
    
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();	
		long[][] piramide = new long[n][n];

		int p = stdin.nextInt();

		for(int i = 0; i < n; i++) 
		    for(int j = 0; j < n; j++)  
			piramide[i][j] = 1;
	
		for(int i = 0; i < p; i++)
		    piramide[stdin.nextInt() - 1][stdin.nextInt() - 1] = 0;
	
	for(int i = 1; i < n; i++) {
		for(int j = 0; j < n - i; j++) {
			if(piramide[i][j] == 0)
			    piramide[i][j] = 0;
		else
		    piramide[i][j] = piramide[i - 1][j] + piramide[i - 1][j + 1];
		    }
		}
	System.out.println(piramide[n - 1][0]);
    }
}
