
import java.io.*;
import java.util.*;

public class Prob005{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int k=0;
	int n=in.nextInt();
	int v[] = new int[n];
	for(int i=0;i<n;i++)
	    v[i]=in.nextInt();
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		if(v[i]>v[j])
		    k++;
	    }
	}
	System.out.println(k);
	    



    }
}
    
    
