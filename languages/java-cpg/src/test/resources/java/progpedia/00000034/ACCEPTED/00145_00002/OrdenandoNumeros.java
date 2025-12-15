// Exercicio OrdenandoNumeros DAA 1516
// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Criado por Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------
// Editado por Rui Pedro Oliveira - 02/11/2015
// ----------------------------------

import java.util.Arrays;
import java.util.Scanner;

public class OrdenandoNumeros {
    static int[] list;
    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();	
        list = new int[n];
        for (int i = 0; i<n; i++){
            list[i] = stdin.nextInt();
        }
        /*int n=5;
        list = new int[] {2,4,2,3,1};
	*/
        Arrays.sort(list);

	for (int i=0; i<n; i++){
	    System.out.print(list[i]);
            if (i != n-1){
                System.out.print(" ");
            }
            
        }
    }
}