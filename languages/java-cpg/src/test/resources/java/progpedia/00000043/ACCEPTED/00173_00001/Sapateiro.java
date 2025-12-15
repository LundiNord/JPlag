import java.util.Scanner;

//tive ajuda neste problema 

public class Sapateiro {

	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int numEncomendas = in.nextInt();
		
		
		double racio[] = new double[numEncomendas];
		for (int i=0; i<numEncomendas; i++){
			int duracao = in.nextInt();
			int multa = in.nextInt();
			racio[i] = duracao / (multa + 0.0) ;
		}
		
		for (int i=0; i<numEncomendas; i++){
			double minimo = 99999;
			int indice = 0;
			for (int j=0; j<numEncomendas; j++) 
				if (minimo > racio[j] && racio[j] > 0.0) {
					minimo = racio[j];
					indice = j;
				}
			//System.out.println( minimo + " " + indice);
			if (i==numEncomendas-1)
				System.out.print( indice+1);
			else
				System.out.print( indice+1 + " ");
			racio[indice] = 0.0;
			
		}
		System.out.println();
		in.close();
	}
	
}
