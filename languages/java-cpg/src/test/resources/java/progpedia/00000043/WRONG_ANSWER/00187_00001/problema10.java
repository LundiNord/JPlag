import java.util.*;
import java.io.*;

class problema10{
    public static void main(String [] args){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	double[] racio = new double[n]; 	
	for(int i=0;i<n;i++){
	    int d=in.nextInt();
	    int m=in.nextInt();
	    racio[i] = d/m;
	}
	for(int i=0;i<n;i++){
	    double min=1000;
	    int index=0;
	    for( int j=0;j<n;j++)
		if(min>racio[j] && racio[j]>0.00){
		    min=racio[j];
		    index=j;
		}
	    if(i!=n-1)
		System.out.print((index+1)+" ");
	    else
		System.out.println(index+1);
	    racio[index]=0.00;
	}
    }
}
