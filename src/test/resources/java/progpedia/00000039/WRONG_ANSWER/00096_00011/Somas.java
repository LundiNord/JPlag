import java.util.*;
import java.lang.*;

class Somas{

	static int aux;
	static int somas[];
	static boolean t = false;
	static int v[] = new int[2];

	//PREENCHER O VETOR SOMAS COM TODAS AS SOMAS POSSIVEIS NO VETOR,NAO REPETINDO AS SOMAS JA EXISTENTES
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
	    int low = 0,middle = 0,d = 0,d1 = 0,maximo = high;
	    if(key <= vetor[0])
			return vetor[0]; // SE A KEY FOR MENOR QUE O VALOR MINIMO DEVOLVO O MINIMO
	    if(key >= vetor[high])
			return vetor[high]; //SE FOR MAIOR , DEVOLVO O MAXIMO
	    while(low < high){
	        middle = low + (high - low)/2;
			if(vetor[middle] == key)
		 	   return vetor[middle]; //SE KEY FOR IGUAL AO VALOR DO MEIO DO VETOR/SOMAS DEVOLVO LOGO
			if(vetor[middle] > key){
		  	  	d = Math.abs(key - vetor[middle]);//DISTANCIA ENTRE A KEY QUE PROCURO E O MIDDLE CORRENTE
		  	    high = middle - 1; //SE O VALOR DO MEIO FOR MAIOR QUE O QUE EU PROCURO POSSO IR PROCUAR DO LADO ESQUERDO
			}
			else if(vetor[middle] < key){ //CASO CONTRARIO
			    d1 = Math.abs(key - vetor[middle]);
			    low = middle + 1;
			}
	    } 
	    if(d1 == d){  //SE FOR A MESMA , TENHO 2 NUMEROS PARA IMPRIMIR
	       t = true; //BOLEANO PARA DECIDIR NA HORA DE IMPRIMIR SE TENHO 2 OU 1 VALOR
	       if(vetor[middle] < key){	//PARA SABER SE O MEU MIDDLE ESTA A ESQUERDA OU DIREITA DA KEY PARA PODER POR NO VETOR OS RESULTADOS PELA ORDEM CERTA	
	       		v[0] = vetor[middle];
	       		v[1] = vetor[middle + 1];
	   	   }
	   	   else if(vetor[middle] > key){
	   	   		v[0] = vetor[middle - 1];
	       		v[1] = vetor[middle];
	   	   }
	       return 0;
        }
        else if(d1 != d){ //1 VALOR PARA IMPRIMIR
        	if(vetor[middle] > key){//SABER SE O MIDDLE ESTA A ESQUERDA OU A DIRETA DA KEY
        		// PROCURO UM VALOR QUE NAO TEM NO VETOR SOMAS, LOGO NO FIM TENHO DE VERIFICAR SE O VALOR QUE QUERO É ONDE PAREI OU O AO LADO
        		d = Math.abs(key - vetor[middle]); 
        		d1 = Math.abs(key - vetor[middle - 1]);
        		if(d1 < d)
        			return vetor[middle - 1];
        		else if(d1 > d)
        			return vetor[middle];
        	}
        	else if(vetor[middle] < key){
        		d = Math.abs(key - vetor[middle]);
        		d1 = Math.abs(key - vetor[middle + 1]);
        		if(d < d1)
        			return vetor[middle];
        		else if(d > d1)
        			return vetor[middle + 1];
        	}
        }
	    return 0;
	}


	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int vetor[] = new int[n];
		int resultado = 0;

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
	
		for(int i=0;i<p;i++){   // IMPRIMIR RESULATDO
		  resultado = bsearch((aux-1),vetor1[i],somas);
		  	if(t == false)
		  		System.out.println(resultado);
		  	else{
		  		System.out.println(v[0] + " " + v[1]);
		  		t = false;
		  	}
	    }   
	}
}
