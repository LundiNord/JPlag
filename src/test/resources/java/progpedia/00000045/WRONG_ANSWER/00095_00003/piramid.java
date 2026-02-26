import java.util.Scanner;



public class piramid {
	public static void main(String[] args) {

		int[][] pyramid = new int[1001][10001];	

		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();	

		int t=1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<=n-t; j++)
				pyramid[i][j]=1;
			t++;
		}


		int d = stdin.nextInt();

		for(int i=0; i<d; i++) {
			int x = stdin.nextInt();
			int y = stdin.nextInt();
			pyramid[x-1][y-1]= 0;
		}

		for(int i=0; i<n; i++) {
			if(pyramid[1][i]==0)
				pyramid[1][i]=0;
		}

		t=1;
		if(n>=2)
			for(int i=1; i<n; i++) {
				for(int j=0; j<n-t; j++) {
					if(pyramid[i][j]==0)
						pyramid[i][j]=0;
					else 
						pyramid[i][j]=pyramid[i-1][j] + pyramid[i-1][j+1];
				}
				t++;
			}

			System.out.println(pyramid[n-1][0]);


		}
	}
