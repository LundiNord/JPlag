import java.util.*;
import java.io.*;

class Piramide{


	static long caminho(long [][] piramide, int cam){
		for(int i = cam-1; i >= 0; i--)
			for (int j = i; j >= 0; j--)
				if(i < cam-1 && piramide[i][j] == 1)
					piramide[i][j] += piramide[i+1][j];
				

		return piramide[0][0];
	

	}

	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		
		int n = stdin.nextInt();

		long pir[][] = new long[n][n];

		for(int i = n-1; i >= 0; i--)
			for (int j = 0; j <= i; j++)
				pir[i][j] = 1;


		int d = stdin.nextInt();
		int c, p;
		
		for(int i=0; i<d; i++){
			c = stdin.nextInt();
			p = stdin.nextInt();
			pir[n-c][p-1]=0;

		}
		
		System.out.println(caminho(pir, n));
		
			
				

	}
}
