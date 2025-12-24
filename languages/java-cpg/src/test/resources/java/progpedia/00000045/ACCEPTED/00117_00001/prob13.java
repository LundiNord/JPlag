import java.io.*;
import java.util.*;
class prob13{
    public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	int N=sc.nextInt();
	int D=sc.nextInt();
	int a,b;
	long [][]p=new long[N][N];
	for(int t=0;t<N;t++){
	    for(int u=0;u<=t;u++)
		p[t][u]=-1;
	}
	for(int t=0;t<N;t++)
	    p[N-1][t]=1;
	for(int i=0;i<D;i++){
	    a=N-sc.nextInt();
	    b=sc.nextInt()-1;
	    p[a][b]=0;
	}
	for(int j=N-2;j>=0;j--){
	    for(int k=0;k<=j;k++){
		if(p[j][k]!=0)
		    p[j][k]=p[j+1][k]+p[j+1][k+1];
	    }
	}
	       
	/*for(int q=0;q<N;q++){
	    for(int w=0;w<N;w++)
		System.out.print(p[q][w]+" ");
	    System.out.println();*/
	System.out.println(p[0][0]);
    }
}
