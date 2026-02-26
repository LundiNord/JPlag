import java.util.*;
import java.io.*;

class Piramides{
    public static void main(String[] args){
	Scanner hello = new Scanner(System.in);
	int N = hello.nextInt();                //Numero de pedras da 1ª camada
	double[][] matriz = new double[N][N];        //Matriz que guarda os resultados

	for(int i=0;i<=N-1;i++){
	    for(int j=0; j<=N-1; j++){
		matriz[i][j]=0;
	    }
	}

	matriz[0][0] = 1;                       //Pedra do topo
	
	int D = hello.nextInt();               //Numero de pedras deterioradas
	for(int i=0; i<D; i++){                 //Ciclo que le as pedras deterioradas
	    int C = hello.nextInt();            //Numero da camada
	    int P = hello.nextInt();            //Posiçao nessa camada
	    matriz[N-C][P-1] = -1;
	}
		
       	for(int i=1; i<=N-1; i++){
	    for(int j=0; j<=i; j++){

		if(matriz[i][j]!=-1){
		
		
		    if(j==0){                        //Primeira coluna
			if(matriz[i-1][j]!=-1){
			    matriz[i][j]=matriz[i-1][j];
			}
		    }
		
		    else if(j>0){                    //Outras colunas
			if(matriz[i-1][j]!=-1){
			    matriz[i][j]=matriz[i-1][j];
			}
			if(matriz[i-1][j-1]!=-1){
			    matriz[i][j]= matriz[i][j] + matriz[i-1][j-1];
			}
		    }
		}
	    }
	}

	double contador = 0;
	
	for(int j=0;j<=N-1; j++){
	    if(matriz[N-1][j]!=-1)
		contador=contador +  matriz[N-1][j];
	}
	System.out.println(contador);
    }
}
