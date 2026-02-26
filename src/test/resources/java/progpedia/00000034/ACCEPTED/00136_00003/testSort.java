import java.util.*;
import java.io.*;

public class testSort{
	public static void main( String args[] ){
		Scanner input = new Scanner(System.in);
		int n;
		
		n = input.nextInt();
		int seq[] = new int[n];
		
		for(int i=0; i<n; i++)
			seq[i] = input.nextInt();
		
		Arrays.sort(seq);
		
		for(int i=0; i<n; i++){
			if(i == n-1)
				System.out.println(seq[i]);
			else
				System.out.print(seq[i] + " ");
		}
	}
}
