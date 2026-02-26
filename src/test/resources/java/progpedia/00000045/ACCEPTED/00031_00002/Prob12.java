import java.util.*;
import java.io.*;

public class Prob12{
    static int n;
    static int d;
    static long pir[][];
    static int count[][];
    
    static long solve(){
	
	for(int i=1;i<n;i++)
	    for(int j=0;j<n-i;j++)
		if(pir[i][j]!=0){
		    pir[i][j]=pir[i-1][j]+pir[i-1][j+1];
		    
		}
	
	return pir[n-1][0];
    }
		
    
    static void readInput(){
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();
	pir = new long[n][n];

	
	for(int i=0;i<n;i++)
	    for(int j=0;j<n-i;j++)
		pir[i][j]=1;
	
	d = stdin.nextInt();
	
	for(int i=0;i<d;i++)
	    pir[stdin.nextInt()-1][stdin.nextInt()-1]=0;
    }
    
    public static void main(String args[]){

	readInput();       
	System.out.println(solve());
	
    }
}
