import java.util.*;
import java.io.*;

class Segmento implements Comparable<Segmento> {
    int inicio;
    int fim;

    Segmento(int x, int y){
	inicio =x;
	fim=y;
    }
    @Override
    public int compareTo(Segmento y){
	if(this.inicio < y.inicio) return -1;
	else if(this.inicio == y.inicio){
	    if(this.fim > y.fim) return -1;
	    else if(this.fim == y.fim) return 0;
	    else return 1;
	}
	else return 1;
    }
}

class Cobertura{
    public static void main(String[] args){
	Scanner hello = new Scanner(System.in);
	int M = hello.nextInt(); //TAMANHO DO SEGMENTO A COBRIR
	int N = hello.nextInt(); //NUMERO DE LINHAS
	Segmento[] array = new Segmento[N]; // CRIAR O ARRAY
	for(int i=0;i<N;i++){ //PREENCHER O ARRAY
	    Segmento novo = new Segmento(hello.nextInt(), hello.nextInt());
	    array[i]=novo;
	}

	int inicio = 0;
	int fim = 0;
	int i=0;
	int contador = 0;
	int maiorfim = 0;
	Segmento maiorateagora = new Segmento(0,0);
	Segmento considerado = new Segmento(0,0);
	Arrays.sort(array); //ORDENAR



	
	while(fim < M){
	    
	    if(i==N-1){
		contador++;
		break;
	    }

	    if(array[i].inicio > considerado.fim){
		
		considerado = maiorateagora;
		inicio = considerado.inicio;
		fim = considerado.fim;
		contador++;
	    }
	    	    
	    if(array[i].inicio<=considerado.fim){
		if(array[i].fim>maiorfim){
		    maiorateagora = array[i];
		    maiorfim = array[i].fim;
		}
	    }
	    

	    
	    i++;    
	}

	System.out.println(contador);
    }
}
	    
	    
	
	    
	    
