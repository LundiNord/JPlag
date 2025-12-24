import java.util.*;

public class prob12 {
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();   // nº pedras da primeira camada / altura da piramide
		int d = in.nextInt();   // nº de pedras deterioradas
		
		int p[][] = new int[n][n];
		long count[][] = new long[n][n];
		
		for(int i=0; i<d; i++){
			int cam = in.nextInt(); // Camada
			int pedra = in.nextInt(); // Pedra deteriorada
			p[n-cam][pedra-1]= 1;
		}
				
		for (int j=0; j<n; j++)
			if(p[n-1][j]==0)
				count[n-1][j] = 1;
			else
				count[n-1][j] = 0;
				
		for (int i=n-2; i>=0; i--)
			for (int j=0; j<=i; j++)
				if (p[i][j] == 0)
					count[i][j] = count[i+1][j] + count[i+1][j+1];
     
		 System.out.println(count[0][0]);
		
		/*
		//Imprimir a piramide com as pedras deterioradas
		for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
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
		for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
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

