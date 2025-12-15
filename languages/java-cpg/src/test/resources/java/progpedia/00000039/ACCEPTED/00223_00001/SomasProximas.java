import java.util.Arrays;
import java.util.Scanner;

class SomasProximas {
	
	public static int bsearch(int inicio,int fim, int v[], int x) {
		while (inicio+1 < fim) {
			int meio = inicio + (fim - inicio)/2;
			if (v[meio] == x)
				return x;
			else if (v[meio] > x)
				fim = meio;
			else
				inicio = meio;
		}
		if (inicio < fim)
			return inicio;
		else
			return fim;
	}
	
	public static void main( String args[]) {
		
		Scanner in = new Scanner(System.in);
		int tamConjS = in.nextInt();
		int conjuntoS[] = new int[tamConjS];
		
		for (int i=0;i<tamConjS; i++){
			conjuntoS[i] = in.nextInt();
		}
		int numPerg = in.nextInt();
		int perguntas[] = new int[numPerg];
		for (int i=0;i<numPerg; i++)
			perguntas[i] = in.nextInt();
		
		int somas[] = new int[tamConjS*(tamConjS-1)/2];
		int k=0;
		for (int i=0;i<tamConjS; i++)
			for (int j=i+1;j<tamConjS; j++){
				somas[k] = conjuntoS[i] +conjuntoS[j];
				k++;
			}
		Arrays.sort(somas);
		
		for (int i=0;i<numPerg; i++) {
			int resultado = bsearch (0, tamConjS*(tamConjS-1)/2 - 1, somas, perguntas[i]);
			if (resultado == perguntas[i])
				System.out.println(resultado);
			else if(Math.abs(somas[resultado]- perguntas[i]) > Math.abs(somas[resultado+1]- perguntas[i]) )
				System.out.println(somas[resultado+1]);
			else if(Math.abs(somas[resultado]- perguntas[i]) < Math.abs(somas[resultado+1]- perguntas[i]) )
				System.out.println(somas[resultado]);
			else
				if (somas[resultado] == somas[resultado+1])
					System.out.println(somas[resultado]);
				else
					System.out.println(somas[resultado] + " " + somas[resultado+1]);
		}
	}
	
}




