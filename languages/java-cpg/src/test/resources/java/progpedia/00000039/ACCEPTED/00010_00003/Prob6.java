import java.util.*;
import java.lang.*;
import java.io.*;

class Prob6{
    public static void main(String[] args){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	
	int[] numeros = new int[n];
	for(int i = 0; i < n; i++)
	    numeros[i] = stdin.nextInt();
	
	int p = stdin.nextInt();
	int[] perguntas = new int[p];
	for(int j = 0; j < p; j++)
	    perguntas[j] = stdin.nextInt();
	
	int number_somas = ((n*(n-1))/2);
	int[] somas = new int[number_somas];
	int posicao = 0;
	for(int i = 0; i < n; i++)
	    for(int j = i + 1; j < n; j++){
		somas[posicao] = numeros[i] + numeros[j];
		posicao++;
	    }
	posicao = 0;
	Arrays.sort(somas);
	for(int i = 0; i < p; i++){
	    if(somas[0] >= perguntas[i])
		System.out.println(somas[0]);
	    else if(somas[number_somas-1] <= perguntas[i])
		System.out.println(somas[number_somas-1]);
	    else
		pesquisar_numero(0,number_somas,somas,i,perguntas);
	    
	}
    }


    public static void pesquisar_numero(int min, int max,int somas[],int a, int questions[]){
	int low = min;
	int high = max;
	while(low < high){
	    int middle = low + (high - low)/2;
	    if(somas[middle] >= questions[a]) high = middle;
	    else low = middle + 1;
	    //System.out.println("a pesquisar por " + questions[a]);
	}
	//System.out.println("Para " + questions[a] + " obtemos " + somas[low]);
	int dist_1 = Math.abs(somas[low] - questions[a]);
	int dist_2 = Math.abs(somas[low-1] - questions[a]);
	//System.out.println("Distancia de " + somas[low] + " a " +  questions[a] + " = " + dist_1);
	//System.out.println("Distancia de " + somas[low-1] + " a " +  questions[a] + " = " + dist_2);
	if(dist_1 == dist_2)
	    System.out.println(somas[low-1] + " " + somas[low]);
	else if (dist_1 < dist_2)
	    System.out.println(somas[low]);
	else
	    System.out.println(somas[low-1]);
    }
}


	
			
		
		
	    
	
