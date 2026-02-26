import java.util.*;
import java.io.*;

public class Prob06{

	//É chamada para cada valor chave
	public static void PesquisaBinaria(int sum[], int inicio, int fim, int chave){
		
		int meio=inicio;

        //Se o valor for menor que a primeira soma
        if(chave <= sum[inicio])
            System.out.println(sum[0]);
        
        //Se o valor for maior que a ultima soma
        else if(chave >= sum[fim])
            System.out.println(sum[fim]);
        
        else{
            while(inicio<fim){
                meio = inicio+(fim-inicio)/2;		//posição do meio
                
                if(chave <= sum[meio]) 				//Se o valor for menor que a pos meio, divide-se o array
                	fim=meio;						//fim passa a ser a posição do meio
                
                else 
                	inicio=meio+1;					//Senão inicio passa a ser a posição seguinte ao meio
            }
               
            //Se chave = soma mais proxima
            int centro = Math.abs(sum[inicio] - chave);
            //Se chave < soma mais proxima
            int esquerda = Math.abs(sum[inicio-1] - chave);
            //Se chave > soma mais proxima
            int direita;
            if(inicio + 1 <= fim) 
            	direita = Math.abs(sum[inicio+1] - chave);
            else
            	direita=-1;

            //Se ambas as somas se encontram a mesma distancia
            //ordena a soma por ordem crescente
            if(esquerda == centro && sum[inicio-1] != sum[inicio])
                System.out.println(sum[inicio-1] + " " + sum[inicio]); 
            else if(direita != -1 && centro == direita && sum[inicio] != sum[inicio + 1])
                System.out.println(sum[inicio] + " " + sum[inicio+1]);
            
            //Se a soma é menor que o valor de chave
            else if(esquerda < centro)
                System.out.println(sum[inicio-1]);
                   
            //Se a soma é maior que o valor de chave
            else if(direita!=-1 && direita < centro)
                System.out.println(sum[inicio+1]);
                       
            //Se a soma for o valor de chave
            else
                System.out.println(sum[inicio]);
        }
	}

	public static void main(String args[]){
		Scanner inp = new Scanner(System.in);

		int n  = inp.nextInt();
		int v[] = new int [n];
		
		int n2, chave=0;

		//le o array v
		for(int i=0; i<n; i++){
			v[i]=inp.nextInt();
		}

		//Array soma 
		int nSomas = (n*(n-1))/2;
		//nSomas são todas as somas de elementos diferentes sem repetições
		int sum[] = new int [nSomas];
		
		int k=0; 
		//Faz todas a somas possiveis com o array v
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				sum[k++] = v[i]+v[j];
			}
		}

		//ordena o array soma
		Arrays.sort(sum);

		//Com o array soma ordenado le cada valor chave e faz pesquisa binaria para encontrar a solução pedida
		n2 = inp.nextInt();
		for(int i=0;i<n2; i++){
			chave=inp.nextInt();
			PesquisaBinaria(sum, 0, nSomas-1, chave);
		}
	}
}