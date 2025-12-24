import java.util.*;

class Inversoes{

	static void funcao(int n,int v[]){
		int contador = 0;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<=n-1;j++){
				if(v[i]>v[j]){
					contador++;
					//System.out.println("("+v[i]+","+v[j]+")");
				}
			}
		}
		System.out.println(contador);
	}



	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int vetor[] = new int[1000000];

		for(int i=0;i<n;i++)
			vetor[i] = input.nextInt();

		funcao(n,vetor);

	}
}