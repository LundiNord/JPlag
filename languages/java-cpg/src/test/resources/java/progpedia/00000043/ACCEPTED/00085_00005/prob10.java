import java.io.*;
import java.util.*;

class prob10{
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		double v[] = new double[n];
		
		int d,m;
		
		for(int i = 0; i < n; i++){
			d = stdin.nextInt();
			m = stdin.nextInt();
			v[i] = d/(m+0.0);
		}
		
		int count;
		double min;
		
		for(int i = 0; i < n; i++){
			min = 99999; 
			count = 0; 
			
			for(int j = 0; j<n; j++)
				if(min > v[j] && v[j] > 0.0){
					min = v[j];
					count = j;
				}
			
			if(i == n-1)
				System.out.print(count + 1);
			else
				System.out.print(count + 1 + " ");
			v[count] = 0.0;
		}
		System.out.println();
	}	
}