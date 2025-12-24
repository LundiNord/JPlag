import java.util.*;
import java.io.*;

class p012{

    public static long caminhos(int n , long m[][]){
	
	for(int i=0; i<n; i++){
	    for(int j=0; j < n ; j++){

		if(m[i][j] == -1){}
		
		else if( j==0 ){
		    if(i != n-1){
			if(m[i+1][j] != -1)
			    m[i+1][j] = m[i+1][j] + m[i][j];
		    } 
		}else{
		    if(i != n-1){
			if( m[i+1][j] != -1)
			    m[i+1][j] = m[i+1][j] + m[i][j];
			if( m[i+1][j-1] != -1)
			    m[i+1][j-1] = m[i+1][j-1] + m[i][j];
		    }
		}
	    }
	}
	return m[n-1][0];
    }
    
    public static void main (String args []){

	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int d = in.nextInt();
	long m[][] = new long[n][n];
	for(int i=0; i<d; i++){
	    int c = in.nextInt()-1;
	    int p = in.nextInt()-1;
	    m[c][p] = -1;
	}
	for(int i=0; i<n;i++){
	    if(m[0][i] != -1)
		m[0][i] = 1;
	}

	int k=n-1;
	for(int i=1; i<n;i++){
	    m[i][k] = -1;
	    k--;
        
	    
	}
	
       	System.out.println(caminhos(n,m));
	
    }
}
