import java.util.*;
import java.io.*;


class Piramide {
    public static void main (String args[]) {
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt(); //numero de camadas && numero blocos da base
	int d = in.nextInt(); //numero de pedras em falta

	long [][] mat = new long [n][n];
	int aux = n;
	int aux1 = n;
	for(int l=0; l<n; l++) {
	    for(int c=0; c<aux; c++) {
	        mat[l][c] = 1;
	    }
	    aux--;
	}
	for(int r=0; r<d; r++) {
	    int l1 = in.nextInt();
	    int c1 = in.nextInt();
	    mat[l1-1][c1-1] = 0;
	}
	for(int l=1; l<n; l++) {
	    for(int c=0; c<aux1; c++) {
		if(mat[l][c] != 0) {
		    mat[l][c] = mat[l-1][c] + mat[l-1][c+1];
		}
		
	    }
	    aux1--;
	}
	System.out.println(mat[n-1][0]);
    }
}

	
	


