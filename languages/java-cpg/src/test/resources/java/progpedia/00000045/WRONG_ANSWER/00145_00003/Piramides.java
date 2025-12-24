import java.util.Scanner;

public class Piramides {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int nump_d = in.nextInt();
		boolean pedras_d[][] = new boolean[n][n];
		for (int i=0; i<nump_d; i++) {
			int c = in.nextInt();
			int p = in.nextInt();		
			pedras_d[c-1][p-1] = true;	
		}
		/*for (int i=0; i<n; i++){
			for (int j=0; j<n; j++)
				System.out.print(pedras_d[i][j]);
			System.out.println();
		}*/
		int piram[][] = new int[n][n];
		for (int j=0; j<n; j++)
			if (!pedras_d[0][j])
				piram[0][j] = 1;
		
		for (int i=1; i<n; i++)
			for (int j=0; j<n-i; j++)
				if (!pedras_d[i][j])
					piram[i][j] = piram[i-1][j] + piram[i-1][j+1];		
		
		/*for (int i=0; i<n; i++){
			for (int j=0; j<n; j++)
				System.out.print(piram[i][j] + " ");
			System.out.println();
		}*/
		System.out.println(piram[n-1][0]);
		in.close();
	}
}
