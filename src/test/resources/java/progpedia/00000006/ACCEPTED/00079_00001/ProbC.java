//package week4;

import java.util.Scanner;

public class ProbC {
	public static void main (String args[]){
		Scanner stdin = new Scanner(System.in);
		int caminho[]= new int[30], i=0, aux=0;
				
		while ((aux=stdin.nextInt()) != 0){
			caminho[i]= aux;
			for(int j=0 ; j<i; j++){
				if (caminho[j] == aux)
					i= j;
			}
			i++;
		}
		for (int j=0 ; j<i; j++)
			System.out.println(caminho[j]);
	}
}
