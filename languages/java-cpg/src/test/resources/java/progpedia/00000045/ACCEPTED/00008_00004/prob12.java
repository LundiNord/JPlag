import java.util.*;
import java.io.*;

public class prob12 {
	
	/*static int N;
		
	public static int calc(int p[][], int c[][]){
		for (int i = N-2; i >= 0; i++) {
            for (int j = 0; j <= 0; j++) {
				if(p[i][j]==0)
					return 1 + calc(p[i][j+1]) + calc(p[i+1][j+1]);
			}
		}
		return -1;
	} */
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		int D = in.nextInt();
		
		int p[][] = new int[N][N];
		long count[][] = new long[N][N];
		
		for(int i=0; i<D; i++){
			int C = in.nextInt(); // Camada
			int P = in.nextInt(); // Pedra deteriorada
			p[N-C][P-1]= 1;
		}
		
		//int melhor = calc(p, count);
		
		for (int j=0; j<N; j++)
			if(p[N-1][j]==0)
				count[N-1][j] = 1;
			else
				count[N-1][j] = 0;
				
		for (int i=N-2; i>=0; i--)
			for (int j=0; j<=i; j++)
				if (p[i][j] == 0)
					count[i][j] = count[i+1][j] + count[i+1][j+1];
     
		 System.out.println(count[0][0]);
		
		/*
		//Imprimir a piramide com as pedras deterioradas
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j <= i) {
                    System.out.print(p[i][j]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println();

		//Imprimir o count
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j <= i) {
                    System.out.print(count[i][j]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
		*/
	}
}

