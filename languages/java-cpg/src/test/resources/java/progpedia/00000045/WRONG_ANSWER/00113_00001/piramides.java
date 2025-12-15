import java.io.*;
import java.util.*;


class piramides{
    
    static int n_caminho(int[][] piramide, int n){
	//int counter=0;

	
	

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

	int[][] piramide = new int[n][n];
	
	//preencher a matriz com 1's
	for(int i = n-1; i>=0;i--){
	    for(int j = 0; j<=i; j++){
		piramide[i][j]=1;
	    }
	}

	System.out.println("1º print!!");
	for(int i = n-1; i>=0;i--)
	    for(int j = 0; j<=i; j++)
		System.out.print(piramide[i][j]+" ");
	
	System.out.println();
	

	for(int i=0; i < d; i++){
	    c = stdin.nextInt();
	    p = stdin.nextInt();
	    piramide[n-c][p-1]=0;
	}

	System.out.println("2º print!!");
	for(int i = n-1; i>=0;i--){
	    for(int j = 0; j<=i; j++){
		System.out.print(piramide[i][j]+" ");
	    }
		
	System.out.println();
    }
	System.out.println(n_caminho(piramide,n));
    }
}
