import java.util.*;
import java.io.*;

class ProbA{
	
	public static void main (String args[]){
		int x, y;
		x=y=0;
		int aux=0;
		int a=0;
		int vector[] = new int [30];
		Scanner kb = new Scanner(System.in);
		
		
		//Input
		
		for(int b=0; b<30; b++){
			vector[b]=kb.nextInt();
			if (vector[b]==0){
				aux = b;
				break;
			}
		}
		
		
		//Tratamento do vector
		
		for(int c=0; c<30; c++){
			for (int n=(c+1); n<30; n++){
				if (vector[c] == vector [n]){
					for(int d=c; d<n; d++){
						vector[d]=-1;
					}
				}			
			}
		}
				

		
		for (int i=0; i<aux; i++){
			if (vector[i]>0) System.out.println(vector[i]);
		}
		
		}
}