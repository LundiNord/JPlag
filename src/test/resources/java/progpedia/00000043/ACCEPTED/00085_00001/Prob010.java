import java.io.*;
import java.util.*;

public class Prob010{
	
    public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		double p[] = new double[n];
		
		for(int i=0; i<n; i++){
			int dur = stdin.nextInt();
			int mul = stdin.nextInt();
			p[i]= dur/(mul+0.0);
		}
	
	
		for(int i=0; i<n; i++){
			int aux=0;
			double min=9999999.9999999;
			
			for(int j=0; j<n; j++){
				if(p[j]< min && p[j] > 0){
					aux=j;
					min=p[j];
				}
			}
			if(i==n-1)
				System.out.print(aux+1);
			else 
				System.out.print(aux+1 + " ");
				
			p[aux]=0.0;
		}
		
		System.out.println();	
	}
	
}
