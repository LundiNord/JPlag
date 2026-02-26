
import java.util.Scanner;

public class Piramides {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int d = in.nextInt();
		
		boolean obra[][] = new boolean[n][n];
		for (int i=0; i<d; i++) {
			int c = in.nextInt();
			int p = in.nextInt();
			for (int j=0; j<n; j++)
				for (int k=0; k<n; k++) {
					if (j==c-1 && k==p-1)
						obra[j][k] = true ;
				}
		}
		/*	for (int i=n-1; i>=0; i--) {
			for (int j=0; j<n; j++)
				System.out.print(obra[i][j] + " ");
			System.out.println();
			}*/
		int count[][] = new int[n][n];
        
		for (int i=0; i<n; i++) 
			if (!obra[0][i])
				count[0][i] = 1;
				
			
		/*	for (int i=0; i<n; i++)
		    	System.out.println(obra[0][i]);*/
	        
	
		for (int i=1; i<n; i++) 
			for (int j=0; j<n-i; j++) 
				if (!obra[i][j] )
					count[i][j] = count[i-1][j] + count[i-1][j+1];
				
				
		/*	for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++)
				System.out.print(count[i][j]);
			System.out.println();
			}*/
		
		System.out.println(count[n-1][0]);
		in.close();
	}
}
