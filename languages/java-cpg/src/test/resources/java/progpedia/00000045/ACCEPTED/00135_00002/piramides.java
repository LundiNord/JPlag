import java.util.*;
import java.io.*;

public class piramides {

	public static void main(String[] args) {
		int n, d;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		long [][] p = new long[n+1][n+1];
		
		for(int i = 1; i <= n; i++)
			Arrays.fill(p[i],1);
		
		d = in.nextInt();
		while( d-- != 0){
			int a,b;
			a = in.nextInt();
			b = in.nextInt();
			p[n - a + 1][b] = 0;
			
		}
		/*
		for( int i = n; i >= 0; i--)
		    { 
			System.out.println();
			for(int j = 1; j <= i; j++)
			    System.out.print(p[i][j]);
		
		    }
		*/
		for( int i = n - 1; i>=0; i--)
		    for(int j = 1; j <= i;j++)
			if(p[i][j] != 0) p[i][j] = p[i+1][j] + p[i+1][j+1];

		System.out.println(p[1][1]);


		//Fiz um exercicio em 1he30(c/ ajuda) mas esta a melhorar!!!!
		//Viva ao Vitoria de Guimaraes!!!!!
	}
}
