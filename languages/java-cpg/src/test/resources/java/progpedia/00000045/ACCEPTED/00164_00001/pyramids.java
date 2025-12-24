import java.util.Scanner;


public class pyramids {

	static long available(long matrix[][], int length) {

		for (int i=length-2; i>=0; i--) {
			for (int j=0; j<length; j++) {
				if(matrix[i][j]!=0) matrix[i][j] = matrix[i+1][j] + matrix[i+1][j+1];
			}

		}
		return matrix[0][0];
	}

	public static void main (String args[]){
		int n, d, c, p;
		Scanner input = new Scanner(System.in);  
		
		n = input.nextInt();
		long [][] pyramid = new long[n][n];

		for (int i=0; i<n; i++) {
			for (int j=0; j<=i; j++){
				pyramid[i][j] = 1;
			}
		}

		d = input.nextInt();
		while (d!=0) {

			c = input.nextInt();
			p = input.nextInt();
			pyramid[n-c][p-1] = 0;
			d--;

		}

		System.out.println(available(pyramid, n));
	}
}
