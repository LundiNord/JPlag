import java.util.*;
import java.io.*;

class Problema3{

	public static void main(String args[]){

		Scanner ler = new Scanner(System.in);
		int n = ler.nextInt();
		int[] v = new int[n];
		for(int i=0;i<n;i++)
			v[i]=ler.nextInt();

		Arrays.sort(v);

		for(int i=0;i<n;i++){
			System.out.print(" ");
			System.out.print(v[i]);
		}
		System.out.println();
	}
}