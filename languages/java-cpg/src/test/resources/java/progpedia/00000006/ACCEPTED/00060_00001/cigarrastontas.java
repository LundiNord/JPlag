import java.io.*;
import java.util.*;

class cigarrastontas{
	
	private static Scanner in;
	
	public static void main (String[] args){
	
			in = new Scanner(System.in);
			
			int[] vector = new int[30];
			
			int j=0;
			int valor;
			
			valor = in.nextInt();
			
			while (valor != 0){
				j= search(valor,vector);
				vector[j]= valor;
				vector[j+1] = 0;
				valor = in.nextInt();
				
			}
			
			for(j=0; vector[j]!=0; j++){
				System.out.println(vector[j]);
			}
		
		
	}

	private static int search(int valor, int[] vector) {
		int j=0;
		
		while(vector[j] != valor && vector[j] != 0) j++;
		
		return j;
	}
		
}