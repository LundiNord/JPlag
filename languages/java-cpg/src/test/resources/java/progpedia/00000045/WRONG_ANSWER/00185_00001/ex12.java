import java.io.*;
import java.util.*;

public class ex12{
    public static int ncaminhos(int n, boolean deter[][]){
	int cont[][]=new int[n][n];
	for(int i=n-1;i>=0;i--){
	    for(int j=0;j<=i;j++){
		if(deter[i][j]==true)
		    cont[i][j]=0;
		else if(i==n-1 && deter[i][j]==false)
		    cont[i][j]=1;
		else{
		    cont[i][j]=cont[i+1][j]+cont[i+1][j+1];	
		}
	    }
	}
	return cont[0][0];
    }
    public static void main(String args[]){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	int d=in.nextInt();
	int[][] p=new int[n][n];
	boolean[][] deter=new boolean[n][n];
	for(int i=0;i<d;i++){
	    int cam=in.nextInt();
	    int pos=in.nextInt();
	    deter[n-cam][pos-1]=true;
	}
	System.out.println(ncaminhos(n,deter));
    }
}


