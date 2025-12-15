import java.util.*;

public class SomasMaisProximas {

	static int n;//tamanho do conjunto S
	static int s[];//os numeros do conjunto
	static int p;//numero de perguntas
	static int pi;//pi de cada pergunta
	
	
	static int bsearch(int [] v, int low, int high, int key){
		int middle = 0;
		int tam = high;
		while(low < high){
			middle = low + (high - low)/2;
			if(key == v[middle])
				return middle;
			else if(key < v[middle])
				high = middle;
			else
				low = middle + 1;
		}
		middle = low;

		if(middle > 0 && middle < tam){ 
			if(key - v[middle - 1] < v[middle] - key)
				return middle - 1;
			if(key - v[middle - 1] == v[middle] - key){
				System.out.print(v[middle - 1] + " ");
				return middle;
			}
			if(v[middle] - key < key - v[middle - 1]){
				return middle;
			}
		}
		if(middle >= tam)
			middle = tam-1;
		
		return middle;
	}
	
	public static void main(String args []){
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		s = new int[n];
		int tam = ((n*(n-1))/2);
		int soma[] = new int[tam];
		int pos = 0;
		int binario;
		
		for(int i = 0; i< n; i++){
			s[i] = stdin.nextInt();
			
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				soma[pos] = s[i] + s[j];
				pos++;
			}
		}
		
		Arrays.sort(soma); 
		
		
		
		p = stdin.nextInt();
		for(int i = 0; i < p; i++){
			pi = stdin.nextInt();
			
			
			
			
			binario = bsearch(soma, 0, tam , pi);
			if(binario != -1)
				System.out.println(soma[binario]);
			else
				System.out.println(binario);
		}
	}
	
}