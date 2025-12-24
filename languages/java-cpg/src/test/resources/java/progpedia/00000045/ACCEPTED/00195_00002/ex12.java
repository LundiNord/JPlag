import java.io.*;
import java.util.*;


public class ex12{
static int n;
static int d;
static long p[][];

static void caminho(int n, int d, long piramide[][]){
for(int i=1;i<n;i++){
	    for(int j= 0;j<n-i;j++){
		if(p[i][j]==0)
		    p[i][j]=0;
		else
		    p[i][j]= p[i-1][j] + p[i-1][j+1];
	    }
	}


	System.out.println(p[n-1][0]);


}
    public static void main(String args []){
	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	p= new long[n][n];
	d = stdin.nextInt();
	for(int i=0;i<n;i++)
	    for(int j=0;j<n;j++)
		p[i][j]=1;
	for(int i=0;i<d;i++)
	    p[stdin.nextInt()-1][stdin.nextInt()-1]= 0;
        
	caminho(n,d,p);
	

    /*for(int i=0;i<n;i++){
System.out.println();
	    for(int j=0;j<n;j++){
		System.out.print(p[i][j]);
}
		
}*/
    }

}
