import java.io.*;
import java.util.*;

class piramide{
    public static void main(String args[]){
	int n , d , l ,o;
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	int P[][] = new int[n][n];
	d = stdin.nextInt();

	for(int i = 0; i < d ; i++){
	    l = stdin.nextInt();
	    o = stdin.nextInt();
	    P[n-l][o-1] = 1;
	    
	}


	for(int i = 0; i < n ; i++){
	    for(int j = 0; j <=i ; j++)
		System.out.print(P[i][j]);
	    System.out.println();
	}
    }
    static int caminhos(int n , int P[][] , int d){
	
	return 0;
	
    }
}
