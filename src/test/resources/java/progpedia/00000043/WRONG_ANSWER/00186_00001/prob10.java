import java.io.*;
import java.util.*;

class prob10{
	public static void main(String [] args){

		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		double ctrl;
		double [] ratio = new double[n];
		
		for(int i=0; i<n; i++){
			int duration = in.nextInt();
			int fee = in.nextInt();
			ratio[i]=duration/(fee);
		}
		
		for(int i=0; i<n; i++){
			ctrl = Double.MAX_VALUE;
			int index = 0;

			for(int j=0; j<n; j++){
				if(ratio[j]>0.0 && ratio[j]<ctrl){
					ctrl=ratio[j];
					index=j;
				}
			}

			if(i==n-1)
				System.out.print(index + 1);
			else
				System.out.print(index + 1 + " ");
			
			ratio[index] = 0.0;
		}
		
		System.out.println();	
	}
}