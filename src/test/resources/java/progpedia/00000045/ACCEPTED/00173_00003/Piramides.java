import java.io.*;
import java.util.*;


class Piramides{
	static long caminhos(long [][] piramide, int n){
		for(int i = n-2; i>=0; i--){
			for(int j = 0; j<=i; j++){
				if(piramide[i][j]!=0)
					piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1]; 
			}
		}
		return piramide[0][0];
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int c,p;
		int n = in.nextInt(); 
		int d = in.nextInt(); 
		
		long [][] piramide = new long[n][n]; 
		
		
		
		for(int i = n-1; i>=0; i--){ 
			for(int j = 0; j<=i; j++){ 
				piramide[i][j]=1; 
			}
		}
		
		
		
		for(int i = 0; i < d; i++){
			c = in.nextInt(); 
			p = in.nextInt(); 
			piramide[n-c][p-1] = 0; 
		}
		
		System.out.println(caminhos(piramide, n));
	}
}
