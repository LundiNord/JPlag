import java.util.*;

public class P13{

	public static void main(String[] args){

		Scanner teclado	= new Scanner(System.in);

		int nCamadas		= teclado.nextInt();
		int nDamaged		= teclado.nextInt();
		
		//Criar matriz para colocar as pedras da piramide
		int piramide[][] = new int[nCamadas][nCamadas];

		//Preencher a piramide com as pedras danificadas
		for (int i=0; i<nDamaged; i++) {
			
			int c	= teclado.nextInt();
			int p   = teclado.nextInt();
						
			piramide[c-1][p-1] = -1;  // -20 Significa danificada. Porquê? Porque sim.
		}

		for (int i=0;i<nCamadas ;i++ ) {
			piramide[0][i] +=1;
		}
		
		
		/*for (int i=0; i<nDamaged; i++) {
			
			int c	= teclado.nextInt();
			int p   = teclado.nextInt();
						
			piramide[c-1][p-1] = -1;  // -20 Significa danificada. Porquê? Porque sim.
		} */

		
		/*System.out.println("---Ola-----");

		
		for (int i=0; i<nCamadas ; i++) {
			for (int j=0;j<nCamadas ; j++ ) {
				System.out.print("[" + piramide[i][j] + "\t]");				
			}
			System.out.println();
			
		}*/
		
		
		for (int i = 1; i<nCamadas ; i++) {
			
							//System.out.println("i:" + i);
			
			for (int j = 0; j<nCamadas ; j++ ) {
				
							// System.out.println("j:" + j);
				
				if (  (j+1<nCamadas) &&  (piramide[i][j] != -1) ) {
					
					int b = piramide[i-1][j];
					int a = piramide[i-1][j+1]; 
					
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
		
		/*System.out.println("----Adeus-----");
		for (int i=0; i<nCamadas ; i++) {
			for (int j=0;j<nCamadas ; j++ ) {
				System.out.print("[" + piramide[i][j] + "\t]");				
			}
			System.out.println();
			
		}*/

		//Resposta Final
		
		if  ( piramide[nCamadas-1][0] != -1 ){
			System.out.println(piramide[nCamadas-1][0]);
		}

		else{
			System.out.println(0);
		}
	}
}



