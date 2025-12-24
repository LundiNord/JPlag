//package Exercicios;
import java.util.*;
public class problem12 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int c,p;
		int n = in.nextInt();
		int d = in.nextInt();
		
		long[][] pyramid = new long[n][n];
		
		for(int i = n-1; i>=0; i--){
			for(int j = 0; j<=i; j++){
				pyramid[i][j]=1;
			}
		}
		
		for(int i = 0; i < d; i++){
			c = in.nextInt();
			p = in.nextInt();
			pyramid[n-c][p-1] = 0;
		}
		
		for(int i = n-2; i>=0; i--){
			for(int j = 0; j<=i; j++){
				if(pyramid[i][j]!=0)
					pyramid[i][j] = pyramid[i+1][j] + pyramid[i+1][j+1];
			}
		}
		
		System.out.println(pyramid[0][0]);
		in.close();
	}
}
