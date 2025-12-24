import java.util.*;


class ContandoInversoes {
	static int N;//quantidade de numeros
	static int seq[];//sequencia de numeros
	static int inversoes;
	
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		N = stdin.nextInt();
		//inversoes = 0;
		seq = new int[N];
		
		for(int i = 0;i < N; i++)
			seq[i] = stdin.nextInt();
		
		
		//inversoes = bruteforce();
		inversoes = mergesort(seq,0,N-1);
		
		System.out.println(inversoes-1);
	}

	//algoritmo força bruta
	static int bruteforce(){
		int count = 0;
		for(int i = 0; i<N ; i++)
			for(int j = i+1; j<N;j++)
				if(seq[i] > seq[j]) count++;
		return count;
	}
	
	 // Ordenar array v[] com mergesort entre posicoes start e end
    static int mergesort(int v[], int start, int end) {
    	int middle;
    	int count = 0;
    	if (start<end) {                 // Parar quando tamanho do array < 2
    		middle = (start+end)/2;        // Calcular ponto medio
    		count += mergesort(v, start, middle);   // Ordenar metade esquerda
    		count += mergesort(v, middle+1, end);   // Ordenar metade direita
    		count += merge(v, start, middle, end);  // Combinar duas metades ordenadas
    	}
    	return count;
    }
	
    // Juntar dois arrays ordenados
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
    	int i, p1, p2;
    	int aux[] = new int[end+1];
    	int count = 0;

    	p1=start;     // "Apontador" do array da metade esquerda
    	p2=middle+1;  // "Apontador" do array da metade direita
    	i = start;    // "Apontador" do array aux[] a conter juncao
    	while (p1<=middle && p2<=end) {            // Enquanto de para comparar
    		if (v[p1] <= v[p2]){ //menor esta do lado esquerdo
    			count += (end - p2) +1;
    			aux[i++] = v[p1++];
    			
    		}  
    		else{//menor esta do lado direito                
    			
    			count += (middle - p1) +1;
    			aux[i++] = v[p2++];
    			}
    	}
    	while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
    	while (p2<=end)    aux[i++] = v[p2++];
	
    	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]
    	
    	return count;
    }
}
