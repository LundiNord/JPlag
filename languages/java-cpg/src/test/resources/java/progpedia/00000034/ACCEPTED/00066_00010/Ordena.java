import java.util.Arrays;
import java.util.Scanner;

public class Ordena {
	
	

	public static void main (String args[]){

		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int lista_numeros [] = new int[N];

		for(int i = 0; i<N; i++){
			lista_numeros[i] = in.nextInt();
		}
		
		Arrays.sort(lista_numeros);

		for(int i = 0; i < lista_numeros.length; i++){
			if(i > 0){
				System.out.print(" ");
			}
			System.out.print(lista_numeros[i]);
		}
		System.out.println();
	}

}
