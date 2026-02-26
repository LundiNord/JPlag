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
    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();	
        int[] list = new int[n];
        for (int i = 0; i<n; i++){
            list[i] = stdin.nextInt();
        }
        
        /*
        int n = 10;
        int[] list = new int[] {3,3,1,1,4,2,4,4,2,5};
        */
	Arrays.sort(list);

	for (int i=0; i<n-1; i++)
	    System.out.print(list[i] + " ");
        System.out.println(list[n-1]);
    }
}