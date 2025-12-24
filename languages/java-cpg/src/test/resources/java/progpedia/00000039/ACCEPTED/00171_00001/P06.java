import java.io.*;
import java.util.*;

class P06{
    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	
	int N = stdin.nextInt();
	int numero[] = new int[N]; 
	for(int i=0 ; i<N ; i++)	    
	    numero[i] = stdin.nextInt();

	int posicaoatual = 0;             //iniciar a posicao 0 do vetor somas a 0
	int size = N*(N-1)/2 ;            //calculo do tamanho do array somas, numero possivel de somas que se pode fazer com N numeros
	int somas[] = new int[size];      //onde guarda todas as somas
	for(int i=0 ; i<N ; i++)
	    for(int j=i+1 ; j<N ; j++){
		somas[posicaoatual] = numero[i] + numero[j];
		posicaoatual++;           //incremento da posicao soma para avancar para proxima posicao
	    }
	
	Arrays.sort(somas);
	int P = stdin.nextInt();          //numero de perguntas

	
	for(int i = 0 ; i < P ; i++){
	
	    int v = stdin.nextInt();      //pedir valor
	    //caso o valor seja igual ou menor a primeira posicao imprime a primeira posicao
	    if(v <= somas[0])
		System.out.println(somas[0]);
	    else
		//caso o valor seja igual ou maior que a ultima posicao imprime a ultima posicao
		if(v >= somas[size-1])
		    System.out.println(somas[size-1]);
	    //caso contrario temos de verificar onde se situa esse valor, e em caso de nao existir, encontrar o mais proximo
		else
		    bsearch(somas, 0, size-1, v);
	}
	
    }
    public static void  bsearch(int[] v,int low,int high,int key){

	while(low < high) {

	    int mid = low + (high-low)/2;     //calculo para descobrir a posicao do middle
	    if(v[mid] >= key) { high = mid; } //no caso do v dado for maior que o valor da posicao middle
	    else { low = mid + 1; }           //no caso de v dado for menor que o valor da posicao middle
		
	}

	if(Math.abs(v[low] - key) == Math.abs(v[low-1] - key)) //caso o mais proximos a igual distancia sejam dois numeros 
	    System.out.println(v[low-1] + " " + v[low]);

	if(Math.abs(v[low] - key) < Math.abs(v[low-1] - key)) //caso a diferenca do valor da pos low e menor que low-1
	    System.out.println(v[low]);

	if(Math.abs(v[low] - key) > Math.abs(v[low-1] - key)) //caso a diferenca do valor da pos low e maior que low+1
	    System.out.println(v[low-1]);

    }
}

	
	


