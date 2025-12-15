import java.util.Arrays;
import java.util.Scanner;

public class Somasprox {

	public static void PesquisaBinaria(int v[], int valor){
		int low = 0;
		int high = v.length-1;
		boolean check = false;
		while(low <= high){
			int meio = (low+high)/2;
			if(v[meio] == valor){
				System.out.println(v[meio]);
				check = true;
				break;
			}
			if(valor > v[meio])	low = meio+1;
			if(valor < v[meio]) high = meio-1;
		}if(!check){
		if(high >0 && low < v.length && valor-v[low] == v[high]-valor)
			System.out.println(v[high]+ " "+ v[low]);
		if(high >0 && low < v.length && v[low]-valor < valor - v[high])
			System.out.println(v[low]);
		if(high >0 && low < v.length && v[low]-valor > valor - v[high])
			System.out.println(v[high]);
		if(high < 0) System.out.println(v[high+1]);
		if(low == v.length) System.out.println(v[low-1]);
		}
	}
	public static int tamanho(int num){
		int soma = 0;
		while(num>0){
			num-=1;
			soma+=num;
		}
		return soma;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int v[] = new int[n];
		int tam = tamanho(n);
		int somas[] = new int[tam];
		for(int i=0; i<n; i++)
			v[i] = in.nextInt();
		int p = in.nextInt();
		int c = 0;
		for(int i = 0; i<n ; i++){
			for(int j=i+1; j<=n-1; j++){
				somas[c] = v[i] + v[j];
				//System.out.println(v[i] + " "+ v[j] + " " + somas[c]);
				c++;
			}
		}
		Arrays.sort(somas);
		//System.out.println(Arrays.toString(somas));
		for(int i=0; i<p; i++){
			int temp = in.nextInt();
		PesquisaBinaria(somas,temp);
		}
	}
}
