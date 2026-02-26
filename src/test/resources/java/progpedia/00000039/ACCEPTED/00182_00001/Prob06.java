import java.util.*;
import java.io.*;

class Prob06
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		//numeros para somar
		int n_numeros= input.nextInt();
		int numeros[] = new int[n_numeros];
	
		for( int i = 0; i < n_numeros; i++){
			numeros[i] = input.nextInt();
		}
		//perguntas(outros numeors par ao qual a soma tem que dar)
		int n_pirguntas= input.nextInt();
		int pirguntas[] = new int[n_pirguntas];
		
		for ( int i = 0; i < n_pirguntas; i++ ){
			pirguntas[i] = input.nextInt();
		}
		//criacao de um vetor para juntar todas a somas do primeiro vetor
		int soma_numeros = ((n_numeros*(n_numeros - 1))/2);
		int soma[] = new int[soma_numeros];
		
		int posicao = 0;
		for(int i = 0; i < n_numeros; i++){
			for(int j = i + 1; j < n_numeros; j++){
				soma[posicao] = numeros[i] + numeros[j];
				posicao++;	
			}
		}
		posicao = 0;
		Arrays.sort(soma);
		
		for(int i = 0; i < n_pirguntas; i++ ){
			if(soma[0] >= pirguntas[i]){
				System.out.println(soma[0]);
			}else if(soma[soma_numeros-1] <= pirguntas[i]){
				System.out.println(soma[soma_numeros-1]);
			}else{
				pesquisa(0, soma_numeros, soma, i, pirguntas);
			}
		}
	}
	
	
	public static void pesquisa(int min, int max, int soma[], int q, int pirguntas[]){
		int low = min;
		int high = max;
		
		while(low < high){
			int middle = low + (high - low)/2;
			if(soma[middle] >= pirguntas[q]){
				high = middle;
			}else{
				low = middle + 1;
			}
		}
		
		int dis_1 = Math.abs(soma[low] - pirguntas[q]);
		int dis_2 = Math.abs(soma[low-1] - pirguntas[q]);
		
		if(dis_1 == dis_2){
			System.out.println(soma[low-1] + " " + soma[low]);
		}else if(dis_1 < dis_2){
			System.out.println(soma[low]);
		}else{
			System.out.println(soma[low-1]);
		}
	}
}