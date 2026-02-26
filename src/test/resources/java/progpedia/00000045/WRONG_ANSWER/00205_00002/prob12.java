import java.util.*;
import java.io.*;

class prob12{
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int[][] piramide = new int[N][N];
	int D = in.nextInt();
	for(int i=0;i<N; i++) 
	    for(int j = 0; j <N; j++)  
		piramide[i][j] = 1;
	for(int i=0;i<D; i++){
	    int C=in.nextInt(), P=in.nextInt();
	    piramide[C-1][P-1]=0;

	}
	for(int i=1; i<N; i++) {
	    for(int j=0; j <N-i; j++) {
		if(piramide[i][j] == 0)
		    piramide[i][j] = 0;
		else
		    piramide[i][j] = piramide[i-1][j] + piramide[i-1][j+1];
	    }
	}
	System.out.println(piramide[N-1][0]);
    }
}
