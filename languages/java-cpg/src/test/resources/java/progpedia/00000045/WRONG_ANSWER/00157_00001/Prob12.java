import java.util.*;
class Prob12{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] piramide = new int[n][n];
		
		for(int i=0;i<n;i++){                              //iniciar base da piramide a 1
			piramide[0][i]=1;
		}
		
		for(int i=0;i<k;i++){								//localizar buracos
			int x = in.nextInt();
			int y = in.nextInt();
			if(x==1) piramide[0][y-1]=0;
			else piramide[x-1][y-1]=-1;
		}
		for(int i=1;i<n;i++){
			for(int j=0;j<n-i;j++){
				if(piramide[i][j]==-1) piramide[i][j]=0;
				else piramide[i][j]=piramide[i-1][j]+piramide[i-1][j+1];
			}
		}
		System.out.println(piramide[n-1][0]);
		
	}

}