import java.util.*;

class Somas{

	static int aux;
	static int somas[];

	static void somatorio(int vetor[],int n){
		int contador = 0;
		for(int i=0;i<n-1 && contador != aux;i++){
			for(int j=i+1;j<=n-1 && contador != aux;j++){
				somas[contador] = vetor[i] + vetor[j];
				contador++;
			}
		}
	}

        static int bsearch(int high,int key,int vetor[]){
	    int low = 0,middle = 0;
	    while(low <= high){
	        middle = low + (high - low)/2;
		if(vetor[middle] == key)
		    return vetor[middle];
		else if(vetor[middle] > key)
		    high = middle - 1;
		else
		    low = middle + 1;
	    }
	    return vetor[middle];
	 }

	public static void main(String args[]){

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int vetor[] = new int[n];

		for(int i=0;i<n;i++)
			vetor[i] = input.nextInt();

		int p = input.nextInt();
		int vetor1[] = new int[p];

		for(int i=0;i<p;i++)
			vetor1[i] = input.nextInt();		

		for(int i=n-1;i>0;i--)
			aux = aux + i;
		somas = new int[aux];

		somatorio(vetor,n);

		Arrays.sort(somas);    //	ORDENAR	ARRAY COM A BIBLIOTECA PADRAO DO JAVA

		//for(int i=0;i<aux;i++)
		// System.out.println(somas[i]);

	       	for(int i=0;i<p;i++)
		   System.out.println(bsearch((aux-1),vetor1[i],somas));
		
	}
}
