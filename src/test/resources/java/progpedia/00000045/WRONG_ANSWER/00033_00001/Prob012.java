//tive ajuda da Andreia Cunha(que fez a cadeira o ano passado) a resolver este problema 

import java.io.*;
import java.util.*;

public class Prob012{
	
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		int d = stdin.nextInt();
		
		int piramide[][] = new int[n][n];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<=i; j++){
				piramide[i][j]=1;
			}
		}
		
		for(int i=0; i<d; i++){
			int c = stdin.nextInt();
			int p = stdin.nextInt();
			piramide[n-c][p-1]=0;
		}
		
		int nproc = proc(piramide, n);
		System.out.println(nproc);
	}
	
	public static int proc(int piramide[][], int n){
		
		for(int i=n-2; i>=0; i--){
			for(int j=0; j<=i; j++){
				if(piramide[i][j]!=0)
					piramide[i][j]= piramide[i+1][j]+piramide[i+1][j+1];
			}
		}
		return piramide[0][0];
	}
			
}

