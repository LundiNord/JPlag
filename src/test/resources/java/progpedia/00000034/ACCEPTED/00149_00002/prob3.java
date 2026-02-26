import java.util.*;
import java.io.*;

public class prob3 {
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		int ord[] = new int[N];
		
		for(int i=0; i<N; i++)
			ord[i]=in.nextInt();
			
		Arrays.sort(ord);	
			
		for(int i=0; i<N; i++)
			System.out.print(ord[i] + " ");
			
		System.out.println();
	}
}

