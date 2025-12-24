import java.util.Scanner;

public class ProblemaSapateiro {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		double racio[] = new double[n];
		for (int i=0
				; i<n; i++){
			int d = in.nextInt();
			int m = in.nextInt();
			racio[i] = d / (m + 0.0) ;
			//System.out.println( racio[i]);
		}
		
		for (int i=0; i<n; i++){
			double minimo = 99999;
			int indice = 0;
			for (int j=0; j<n; j++) 
				if (minimo > racio[j] && racio[j] > 0.0) {
					minimo = racio[j];
					indice = j;
				}
			//System.out.println( minimo + " " + indice);
			if (i==n-1)
				System.out.print( indice+1);
			else
				System.out.print( indice+1 + " ");
			racio[indice] = 0.0;
			
		}
		System.out.println();
		in.close();
	}
	
}
