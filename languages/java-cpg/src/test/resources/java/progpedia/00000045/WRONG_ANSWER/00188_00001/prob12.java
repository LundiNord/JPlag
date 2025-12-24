import java.util.*;



public class prob12{
	public static void main(String args[]){
		
		Scanner input=new Scanner(System.in);
		int n, a, b,t;
		n=input.nextInt();
		t=input.nextInt();
		int[][] m=new int[n+1][n+1];
		for(int i=0;i<t;i++){
			a=input.nextInt();
			b=input.nextInt();
			m[a][b]=(-1);
		}
		
		for(int i=1;i<=n;i++){
			if(m[1][i]==(-1)) m[1][i]=0;
			else m[1][i]=1;	
		}
		
		for(int i=2;i<=n;i++)
			for(int j=1;j<=(1+n-i);j++){
				if(m[i][j]==-1) m[i][j]=0;
				else m[i][j]=m[i-1][j]+m[i-1][j+1];
	
			}
		
		System.out.println(m[n][1]);
		
	}
}