import java.io.*;
import java.util.*;


class Prob12{
	static long n_ways(long [][] pyramid, int n){
		for(int i = n-2; i>=0; i--){
			for(int j = 0; j<=i; j++){
				if(pyramid[i][j]!=0)
					pyramid[i][j] = pyramid[i+1][j] + pyramid[i+1][j+1];
			}
		}
		return pyramid[0][0];
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int c,p;
		int n = input.nextInt();
		int d = input.nextInt();
		
		long [][] pyramid = new long[n][n];
		
			for(int i = n-1; i>=0; i--){
			for(int j = 0; j<=i; j++){
				pyramid[i][j]=1;
			}
		}
	
	for(int i = 0; i < d; i++){
			c = input.nextInt();
			p = input.nextInt();
			pyramid[n-c][p-1] = 0;
		}
		
		System.out.println(n_ways(pyramid, n));
	}
}