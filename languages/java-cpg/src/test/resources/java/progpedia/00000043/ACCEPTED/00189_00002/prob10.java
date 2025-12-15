//Joana Silva 200908475

import java.io.*;
import java.util.*;

class prob10{
	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double [] ratio = new double[n];
		
		for(int i = 0; i < n; i++){
			int dim = in.nextInt(); 
			int multa = in.nextInt(); 
			ratio[i] = dim/(multa+0.0);
		}
		
		for(int i = 0; i < n; i++){
			double min = 1000;
			int index = 0; 
			for(int j = 0; j<n; j++)
				if(min > ratio[j] && ratio[j] > 0.0){
					min = ratio[j];
					index = j;
				}
			if(i == n-1)
				System.out.print(index + 1);
			else
				System.out.print(index + 1 + " ");
			ratio[index] = 0.0;
		}
		System.out.println();
		
		
	}
}
