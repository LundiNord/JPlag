import java.io.*;
import java.util.*;


class piramides{   
    static long n_caminho(long[][] piramide, int n){
  
	for(int i = n-2; i>=0;i--){
	    for(int j = 0; j<=i; j++){
		if(piramide[i][j] != 0)
		    piramide[i][j] = piramide[i+1][j]+piramide[i+1][j+1];
				
	    }
	}
	return piramide[0][0];
    }
    
    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	int c, p;
	
	int n = stdin.nextInt();
	int d = stdin.nextInt();

	long[][] piramide = new long[n][n];
	
	//preencher a matriz com 1's
	for(int i = n-1; i>=0;i--){
	    for(int j = 0; j<=i; j++){
		piramide[i][j]=1;
	    }
	}

	
	for(int i=0; i < d; i++){
	    c = stdin.nextInt();
	    p = stdin.nextInt();
	    piramide[n-c][p-1]=0;
	}

	
	System.out.println(n_caminho(piramide,n));
    }
}
