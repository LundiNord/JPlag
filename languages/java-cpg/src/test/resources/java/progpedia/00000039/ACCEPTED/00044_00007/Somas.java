import java.util.*;
import java.io.*;

class Somas{




    //FUNCAO BSEARCH INICIAL
    static int[] bsearch(int pergunta, int[] somas){
	int[] resultados = new int[somas.length];
	for(int i=0;i<resultados.length;i++){
	    resultados[i]=-1;
	}
	resultados[0] = somas[0];
	int distancia = Math.abs(pergunta - resultados[0]);
	return bsearch(pergunta, somas, resultados,distancia, 0, 0, somas.length-1);
    }

    //FUNCAO BSEARCH FINAL
    private static int[] bsearch(int pergunta, int[] somas, int[] resultados, int distancia, int indice, int low, int high){
	int half = (low+high)/2;
	int ajudante;

	//VER SE O ARRAY RESULTADOS CONTINUA IGUAL
	if(low<high){

	    if(Math.abs(somas[half] - pergunta)<distancia){
		for(int w=0;w<2;w++){
		    resultados[w]=-1;
		}
		indice=1;
		distancia = Math.abs(somas[half] - pergunta);
		resultados[0]=somas[half];
	    }
	    else if(Math.abs(somas[half] - pergunta)==distancia){
		if(resultados[0]!=somas[half]){
		    if(somas[half]<resultados[0]){
			ajudante = resultados[0];
			resultados[0]=somas[half];
			resultados[1]=  ajudante;
		    }
		    else if(somas[half]>resultados[0]){
			resultados[1]=somas[half];
		    }
		    indice++;
		}
	    }
	
    
	    //VER PARA A FRENTE OU PARA TRAS

	    if(pergunta<somas[half] && low+1!=high){
		return bsearch(pergunta, somas, resultados, distancia, indice, low, half-1);
	    }

	    else if(pergunta>somas[half]){
		return bsearch(pergunta, somas, resultados, distancia, indice, half+1, high);
	    }

	    else if(pergunta==somas[half]){

		for(int w=0;w<resultados.length;w++){
		    resultados[w]=-1;
		}

		resultados[0]=pergunta;
		
		return resultados;
	    }
	}

	//SE LOW = HIGH 

	else if(low>=high){

	    if(Math.abs(somas[low] - pergunta)<distancia){
		for(int w=0;w<2;w++){
		    resultados[w]=-1;
		}
		resultados[0]=somas[low];
	    }
	    else if(Math.abs(somas[low] - pergunta)==distancia){
		if(resultados[0]!=somas[low]){
		    if(somas[half]<resultados[0]){
			ajudante = resultados[0];
			resultados[0]=somas[low];
			resultados[1]=  ajudante;
		    }
		    else if(somas[low]>resultados[0]){
			resultados[1]=somas[low];
		    }
		    indice++;
		}
	    }
	}

	return resultados;
    }



    public static void main(String[] args){

	Scanner hello = new Scanner(System.in);
	int z=0;
	int nnumeros = hello.nextInt();
	int[] arraynumeros = new int[nnumeros];

	//LER OS NUMEROS
	for(int i=0;i<nnumeros;i++){
	    arraynumeros[i] = hello.nextInt();
	}
	int[] somas = new int[((nnumeros)*(nnumeros-1))/2];

	//PREENCHER O ARRAY SOMAS
	for(int i=0;i<=nnumeros-2;i++){
	    for(int j=i+1;j<=nnumeros-1;j++){
		somas[z]=arraynumeros[i]+arraynumeros[j];
		z++;
		
	    }
	}

	Arrays.sort(somas);



	//LER AS PERGUNTAS E IMPRIMIR OS RESULTADOS
	int nperguntas = hello.nextInt();
	for(int i=0;i<nperguntas;i++){
	    int pergunta = hello.nextInt();
	    int[] resultados = bsearch(pergunta, somas);
	    for(int j=0;j<resultados.length;j++){
		if(j==0) System.out.print(resultados[j]);
		else{
		    if(resultados[j]!=-1){
			System.out.print(" " + resultados[j]);
		    }
		    else if(resultados[j]==-1) break;
		}
	    }
	    System.out.println();
	}
    }
}
