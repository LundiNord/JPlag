import java.util.Scanner;


public class thirteen {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		int max=s.nextInt();
		int n=s.nextInt();
		
		boolean pir[][]=new boolean[max][max];
		long count[][]=new long[max][max];
		
		for(int i=0;i<max;i++)
			for(int j=0;j<max;j++){
				count[i][j]=0;
		}
		
		for(int i=0;i<n;i++)
			pir[max-s.nextInt()][s.nextInt()-1]=true;
		
		calcular(pir, count);		
		System.out.println(count[0][0]);
	}

	private static void calcular(boolean p[][],long c[][]) {
		
		for(int j=0;j<c.length;j++)
			if(!p[c.length-1][j])
				c[c.length-1][j]++;
		
		for(int i=c.length-2;i>=0;i--)
			for(int j=0;j<=i;j++)
				if(!p[i][j]){
					c[i][j]+=c[i+1][j];
					if(j+1<c.length)
						c[i][j]+=c[i+1][j+1];
				}
	}		
}
