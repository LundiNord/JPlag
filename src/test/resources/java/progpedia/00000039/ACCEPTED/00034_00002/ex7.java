import java.util.Arrays;
import java.util.Scanner;


public class ex7 {
	
	private static int[] somas;
	
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
	
		//numeros a ordenar e a calcular distancias
		int quantos = in.nextInt();
		int [] v = new int[quantos];
		
		int  num_somas = (quantos*(quantos-1))/2;
		somas = new int[num_somas];
		
		for(int i=0;i<quantos;i++){
			int aux = in.nextInt();
			v[i]=aux;
		}
		
		int k=0;
		for(int i=0;i<quantos;i++){
			for(int j=(i+1);j<quantos;j++){
				somas[k] = v[i]+v[j];
				k++;
			}
		}
				
		Arrays.sort(somas);
		
		int quantas_perguntas = in.nextInt();
		for(int i=0;i<quantas_perguntas;i++){
			int aux = in.nextInt();
			int resposta = binary(0,k-1,aux);
			if(resposta!=0)
				System.out.println(resposta);
			
		}
		
	}

	private static int binary(int i, int j, int aux) {
	
		if ((j - i) == 1) {
			int diff1 = Math.abs(somas[i] - aux);
			int diff2 = Math.abs(somas[j] - aux);
			if (diff1 < diff2)
				return somas[i];

			else if (diff1 == diff2) {
				System.out.println(somas[i] + " " + somas[j]);
				return 0;
			}

			else
				return somas[j];
		}

		int mid = (i + j) / 2;
		if (aux <= somas[mid])
			return binary(i, mid, aux);
		else
			return binary(mid, j, aux);
	
		
	}
}
