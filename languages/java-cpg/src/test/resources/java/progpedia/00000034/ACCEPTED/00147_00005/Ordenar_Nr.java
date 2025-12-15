import java.util.Arrays;
import java.util.Scanner;

public class Ordenar_Nr {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int num[] = new int[n];
		for(int i = 0; i<n; i++){
			num[i] = in.nextInt();
		}
		//bubble sort
		/*boolean existem_trocas = true;
		int aux;
		while(existem_trocas){
			existem_trocas = false;
			for(int i = 0; i<n-1; i++){
				if(num[i] > num[i+1]){
					aux = num[i];
					num[i] = num[i+1];
					num[i+1] = aux;
					existem_trocas = true;
				}
			}
		}*/
		Arrays.sort(num);
		for(int i=0; i<n; i++){
			System.out.print(num[i]);
			if(i<n-1) System.out.print(" ");
		}
		in.close();
	}
}
