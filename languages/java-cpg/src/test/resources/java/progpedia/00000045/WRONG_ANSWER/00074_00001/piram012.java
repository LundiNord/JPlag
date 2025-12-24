import java.io.*;
import java.util.*;

public class piram012 {

    static int resolve(int n, int m[][]){

	m[0][0] = 1;

	for(int i=1; i<n; i++){
	    for(int j=0; j<i+1; j++){
		if(m[i][j] != -1){
		    if(j==0){
			if(m[i-1][j]>0)
			    m[i][j] += m[i-1][j];
			else
			    m[i][j] = -1;
		    }
		    else{
			if(m[i-1][j]<0 && m[i-1][j-1]<0)
			    m[i][j] = -1;
			else{
			    if(m[i-1][j]<0)
				m[i][j] = m[i-1][j-1];
			    else if(m[i-1][j-1]<0)
				m[i][j] = m[i-1][j];
			    else
				m[i][j] += m[i-1][j] + m[i-1][j-1];
			}
		    }
		}
	    }
	}
	int res = 0;
	for(int i=0; i<n; i++)
	    if(m[n-1][i] != -1)
		res += m[n-1][i];
	return res;
    }
    
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();
	int d = stdin.nextInt();
	int m[][] = new int[n][n];
        
	for (int i=0; i<d; i++){
	    int c = stdin.nextInt();
	    int p = stdin.nextInt();    
	    m[n-c][p-1] = -1;
	}
	/*
	for(int i=0; i<n; i++){
	    for(int j=0; j<i+1; j++)
		System.out.print(m[i][j] + " ");
	    System.out.println();
	}
	
	System.out.println();
	System.out.println();
	*/
	System.out.println(resolve(n, m));
	
	/*for(int i=0; i<n; i++){
	    for(int j=0; j<i+1; j++)
		System.out.print(m[i][j] + " ");
	    System.out.println();
	}
	*/
	
 }
}
