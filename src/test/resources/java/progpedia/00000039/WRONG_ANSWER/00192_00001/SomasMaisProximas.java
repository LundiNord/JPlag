import java.util.*;

public class SomasMaisProximas {

	static int n;//tamanho do conjunto S
	static int s[];//os numeros do conjunto
	static int p;//numero de perguntas
	static int pi[];//pi de cada pergunta
	
	
	static int bsearch(int [] v, int low, int high, int key){
		int middle = 0;
		while(low <= high){
			middle = low + (high - low)/2;
			if(key == v[middle])
				return middle;
			else if(key < v[middle])
				high = middle - 1;
			else
				low = middle + 1;
		}
		return -1;
	}
	
	public static void main(String args []){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		s = new int[n];
		
		for(int i = 0; i< n; i++){
			s[i] = stdin.nextInt();
		}
		
		p = stdin.nextInt();
		pi = new int[p];
		
		for(int j = 0; j < p; j++){
			pi[j] = stdin.nextInt();
		}
		int tam = n*n;
		int soma[] = new int[tam];
		int pos = 0;
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				soma[pos] = s[i]+s[j];
				pos++;
			}
		}
		Arrays.sort(soma);
		
		int binario;
		
		for(int i = 0; i < p; i++){
			binario = bsearch(soma,0, tam-1,pos);
			if(binario != -1)
				System.out.println(soma[binario]);
			else
				System.out.println(binario);
		}
	}
	
}
