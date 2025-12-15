import java.util.*;

class Pyramid{

	public static void main(String[] args){
		int c,p;

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int d = input.nextInt();
		int[][] pyr = new int[n][n];

		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				pyr[i][j] = 1;

		for(int i=0;i<d;i++){
			c = input.nextInt();
			p = input.nextInt();
			pyr[n-c][p-1] = 0;
		}

		for(int i=n-2;i>=0;i--){
			n--;
			for(int j=n-1;j>=0;j--){
				if(pyr[i][j]!=0){
					pyr[i][j] = 0;
					pyr[i][j] = pyr[i][j]+pyr[i+1][j];
					pyr[i][j] = pyr[i][j]+pyr[i+1][j+1];
				}
			}
		}
		
		System.out.println(pyr[0][0]);
	}
}