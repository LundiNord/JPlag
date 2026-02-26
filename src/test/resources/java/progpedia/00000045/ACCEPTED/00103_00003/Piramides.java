import java.util.*;
import java.io.*;

class Piramides{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);

	int n, d;
	n = in.nextInt();
	d = in.nextInt();
	long[][] count = new long[n][n];

	for(int i = 0; i<n ; i++)
	    for(int j=0 ; j < n ; j++)
		count[i][j]=1;
	
	for(int i=0 ; i<d ; i++){
	    int c = in.nextInt();
	    int p = in.nextInt();

	    count[n-c][p-1]=0;
	}

	for(int i=n-2; i>=0 ; i--){
	    for(int j = 0 ; j < n ;j++){
		if(count[i][j]==1){
		    if(j==n-1)
			count[i][j]=count[i+1][j];
		    else
			count[i][j]=count[i+1][j] + count[i+1][j+1];
		}
	    }
	}
	
	System.out.println(count[0][0]);
    }
}
