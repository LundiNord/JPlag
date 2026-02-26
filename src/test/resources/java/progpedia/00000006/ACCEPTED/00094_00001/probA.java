/* Solução do problema do mooshak A de DAA - Cigarras Tontas
 * Implementação com arrays para menor tempo de execução
 * Solução por Pedro Ângelo a 26/10/2013
 */
import java.util.*;
import java.io.*;

class probA {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		// vetor locais irá conter os diferentes locais que
		// a cigarra vai visitar
		int[] locais = new int[30];
		int i, j = 0;
		// a cada iteração do ciclom ou seja enquanto nao
		// for lido o input 0
		while((i = in.nextInt())!=0) {
			// guardar o local no vector
			locais[j] = i;
			// e verificar se o local não e repetido,
			// ou seja se a sigarra anda nao o visitou
			for(int a = 0; a<j;a++) {
				// caso ja o tenha visitado, j = a que é a
				// posição no vector que contem o mesmo local
				// mas visitado anteriormente
				if(locais[a] == locais[j]) {
					j = a;
					// esta operação serve para parar o ciclo
					// e impedir que seja verificados locais
					// visitados que ja não interessam
					a = j;
				}
			}
			j++;
		}
		for(i=0;i<j;i++) System.out.println(locais[i]);
	}
}
