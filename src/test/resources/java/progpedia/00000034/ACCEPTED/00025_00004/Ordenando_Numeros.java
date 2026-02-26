import java.io.*;
import java.util.*;

public class Ordenando_Numeros {

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int n = inp.nextInt();
		int v[] = new int[n];
		for(int i=0; i<n; i++){
			v[i]=inp.nextInt();
		}
		
		Arrays.sort(v);
		
		for(int i=0; i<n-1; i++)
			System.out.print(v[i] + " ");
		
		System.out.println(v[n-1]);
	}
	
}
