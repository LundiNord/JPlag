import java.io.*;
import java.util.*;

public class pir{
    
    public static void main(String args[]){
	
	Scanner stdin=new Scanner(System.in);
	
	int n=stdin.nextInt();
	Integer mx[][]= new Integer[n][n];
	for(int i=0; i<n;i++)
	    for(int j=0; j<n; j++)
		mx[i][j]=1;
	
	int d= stdin.nextInt();
	
	for(int i=0; i<d;i++){
	    int c=stdin.nextInt();
	    int p=stdin.nextInt();
	    mx[n-c][p-1]=0;
	}

	if(n>=3){
	    for(int k=n-2; k>=0; k--)
		for(int l=0; l<=k; l++)
		    if(mx[k][l]>0)
			mx[k][l]=mx[k+1][l]+mx[k+1][l+1];
	}
	else if(n==1 && d==0)
	    mx[0][0]=1;
	else
	    mx[0][0]=mx[1][0]+mx[1][1];
			
			
	
	
	
	
	 
	
	System.out.println(mx[0][0]);

	
    }
}

