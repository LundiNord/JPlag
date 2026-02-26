import java.util.*;

public class Piramide{
	static void JustDoIt(long piramide[][],int n){
		for (int i=1;i<n; i++) {
			for (int j = 0; j<n;j++){			
				if (  (j+1<n) &&  (piramide[i][j] != -1) ) {
					long b = piramide[i-1][j];
					long a = piramide[i-1][j+1]; 
					if (a !=-1 && b !=-1){
						piramide[i][j] = a+b;
					}
					else if (a != -1){
						piramide[i][j] += a;
					}
					else if (b !=-1){
						piramide[i][j] += b;
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		long piramide[][] = new long[n][n];
		
		int d=input.nextInt();
		for (int i=0;i<d;i++)
			piramide[input.nextInt()-1][input.nextInt()-1]=-1;
			

		for (int i=0;i<n;i++) 
			piramide[0][i] +=1;
				
		
	
		JustDoIt(piramide,n);

	
		
		if  ( piramide[n-1][0] != -1 ){
			System.out.println(piramide[n-1][0]);
		}

		else{
			System.out.println(0);
		}
		
	}

}
