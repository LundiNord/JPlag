import java.util.Arrays;
import java.util.Scanner;

public class aula3{
	
	private static Scanner in;

	public static void main (String[] args){
		
		in = new Scanner(System.in);
		
		int n = in.nextInt();
		int[] array = new int[n];
		
		for (int i=0; i<n; i++) 
			array[i] = in.nextInt();
		
		Arrays.sort(array);
		
		System.out.println( Arrays.toString(array) ); 
		
	}
}
