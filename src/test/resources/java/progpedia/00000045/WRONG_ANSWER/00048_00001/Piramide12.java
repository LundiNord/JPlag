import java.util.*;
import java.io.*;

class Piramide12{

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int piramide[][] = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				piramide[i][j] = 1;

		int p = in.nextInt();
		for(int i=0; i<p; i++)
			piramide[in.nextInt()-1][in.nextInt()-1] = 0;

		for(int i=1; i<n; i++){
			for(int j=0; j<n-1; j++){
				if(piramide[i][j] == 0)
					piramide[i][j] = 0;
				else
					piramide[i][j] = piramide[i-1][j] + piramide[i-1][j+1];
			}
		}

		System.out.println(piramide[n-1][0]);
	}
}