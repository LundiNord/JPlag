import java.io.*;
import java.util.*;

public class ordenar{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in); 
		int x;
		int n = input.nextInt();
		int v[]=new int[n];

		for(int i=0;i<n;i++){
			x=input.nextInt();
			v[i]=x;
		}

		Arrays.sort(v);

		for (int i=0;i<n;i++)
			System.out.print(v[i]+" ");
			System.out.println();



	}
}













