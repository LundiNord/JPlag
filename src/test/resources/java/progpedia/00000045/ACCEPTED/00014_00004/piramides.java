import java.io.*;
import java.util.*;

class piramides{
    static int n;
    static long m[][];
    public static void main(String args[]){
	
	Scanner in = new Scanner(System.in);
	
	n = in.nextInt();
	int d = in.nextInt();
	m = new long[n][n];
	
	for(int i=0;i<d;i++){
	    int c = in.nextInt()-1;
	    int p = in.nextInt()-1;
	    m[c][p]=-1;
	}
	for(int i=0;i<n;i++)
	    if(m[0][i]==0)
		m[0][i]=1;
	caminhos();
	/*	for(int i=0;i<n;i++){
	    for(int j=0;j<n-i;j++)
		System.out.print(m[i][j]+" ");
	    System.out.println();
	    }*/
	if(m[n-1][0]==-1)
	    System.out.println("0");
	else
	    System.out.println(m[n-1][0]);
    }
    
    static void caminhos(){
	for(int i=1;i<n;i++)
	    for(int j=0;j<n-i;j++)
		if(m[i][j]!=-1){
		    if(m[i-1][j]!=-1 && m[i-1][j+1]!=-1)
			m[i][j]+=m[i-1][j]+m[i-1][j+1];
		    else if(m[i-1][j]!=-1)
			m[i][j]+=m[i-1][j];
		    else if(m[i-1][j+1]!=-1)
			m[i][j]+=m[i-1][j+1];
		}
    }
}
