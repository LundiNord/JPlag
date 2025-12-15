import java.util.*;
import java.io.*;

public class p12{
	public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	long m[][]= new long[n][n];
	
	for(int i=0; i<n;i++){
	    for(int j=0; j<n; j++){
		    m[i][j]=1;
	    }
	}

	int d = in.nextInt(); //pedras em falta
	
	for(int i=0; i<d;i++){
	    int c = in.nextInt();
	    int p = in.nextInt();
	    m[n-c][p-1] = 0;
	}

	if(n>=3){
	   for(int k=n-2; k>=0; k--){
		   for(int l=0; l<=k; l++){
		       if(m[k][l]>0)
			       m[k][l]=m[k+1][l]+m[k+1][l+1];
	        }
        }
	}
	

	else if(n==1 && d==0){
	    m[0][0]=1;
	}
	else{
	    m[0][0]=m[1][0]+m[1][1];
	}		
		System.out.println(m[0][0]);
   }
}