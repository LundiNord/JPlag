
import java.util.Scanner;

public class Piramides {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int nPedNumNiv = in.nextInt();
		int numPedFalta = in.nextInt();
		
		boolean obra[][] = new boolean[nPedNumNiv][nPedNumNiv];
		for (int i=0; i<numPedFalta; i++) {
			int camada = in.nextInt();
			int posPedra = in.nextInt();
			for (int j=0; j<nPedNumNiv; j++){
				for (int k=0; k<nPedNumNiv; k++) {
					if (j==camada-1 && k==posPedra-1){
						obra[j][k] = true ;
					}
				}
			}		
		}
		
		long conta[][] = new long[nPedNumNiv][nPedNumNiv];
        
		for (int i=0; i<nPedNumNiv; i++) 
			if (!obra[0][i])
				conta[0][i] = 1;
			        
		for (int i=1; i<nPedNumNiv; i++) {
			for (int j=0; j<nPedNumNiv-i; j++){
				if (!obra[i][j] ){
					conta[i][j] = conta[i-1][j] + conta[i-1][j+1];
			}
		}
	}		
		System.out.println(conta[nPedNumNiv-1][0]);
		in.close();
	}
}

