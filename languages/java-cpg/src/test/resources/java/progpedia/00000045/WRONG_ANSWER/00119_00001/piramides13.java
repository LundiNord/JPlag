import java.util.Scanner;


public class piramides13 {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int n,d;
		n =stdin.nextInt();
		int p[][]= new int[n][n];
		for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					p[i][j]=-1;
					if(i==n-1)
						p[i][j]=1;
				}
		}
		d=stdin.nextInt();
		for(int i=0;i<d;i++){
			int a=n-stdin.nextInt();
			int b=stdin.nextInt()-1;
			p[a][b]=0;
		}
		
		for(int i=n-2;i>=0;i--){
			for(int j=0;j<=i;j++){
				if(p[i][j]!=0)
					p[i][j]=p[i+1][j] + p[i+1][j+1];
			}
		}
		
					System.out.println(p[0][0]);
		
	}
}
