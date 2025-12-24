//package Exercicios;
import java.util.*;
public class prob03 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int v[] = new int[n];
		for ( int i=0;i<n;i++)
		{
			v[i]=in.nextInt();
			
		}
		Arrays.sort(v);
		for (int j=0;j<n;j++)
		{
			System.out.print(v[j] + " ");
						
		}

		System.out.println();



	}

}
