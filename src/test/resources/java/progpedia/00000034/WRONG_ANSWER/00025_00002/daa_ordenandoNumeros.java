import java.io.*;
import java.util.*;

public class daa_ordenandoNumeros {

	public static void main(String[] args){
		
		Scanner inp = new Scanner(System.in);
		
		int n = inp.nextInt();
		int v[] = new int[n];
		for(int i=0; i<n; i++){
			v[i]=inp.nextInt();
		}
		
		Arrays.sort(v);
		
		System.out.println(n);
		for(int i=0; i<n; i++)
			System.out.print(v[i] + " ");
	}
	
}

