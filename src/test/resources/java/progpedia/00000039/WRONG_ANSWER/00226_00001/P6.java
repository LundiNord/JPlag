import java.util.*;

public class P6{

	public static void main(String[] args){

		//Inicializar Scanner
		Scanner in = new Scanner(System.in);
		//Numero de casas
		int n = in.nextInt();
		//Criar um vector para as distancias das casas
		int v[] = new int[n];
		//Preencher o vector
		for(int i = 0; i < n; i++){
			v[i] = in.nextInt();
		}
		//Ordenar as diastancias
		Arrays.sort(v);
		//Calcular o resultado
		long resultado = min_dis(v,n);
		//Imprimir o resultado
		System.out.println(resultado);

	}

	public static long min_dis(int v[],int n){
		
		int meio1 = n/2; 
		int meio2 = (n+1)/2;

		long dist1 = 0;

		for(int j = 0; j < n; j++){
			dist1 = dist1 + (Math.abs(v[meio1]-v[j]));
		}

		long dist2 = 0;

		for(int j = 0; j < n; j++){
			dist2 = dist2 + (Math.abs(v[meio2]-v[j]));
		}

		return Math.min(dist1,dist2);

	} 

}