/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package prob009;

/**
 *
 * @author Joao Fonseca
 */
import java.util.*;



class Segmento implements Comparable<Segmento>{
    int inicio, fim1;

     Segmento(int b, int c){
	this.fim1=c;
        this.inicio=b;
	/*int medio=0;
        medio=(b+c)/2;*/

    }
// metodo @Override 
    @Override
    public int compareTo(Segmento b){
	return this.inicio - b.inicio;
    }
   
}


public class Prob009{
    public static void main(String[] args){
	Scanner teclado= new Scanner(System.in);
//Scan do m, n e das coordenadas do segmentos guardando num vetor
	int m= teclado.nextInt();
	int n= teclado.nextInt();
	Segmento[] segmentos= new Segmento [n];
	for(int i=0;i<n;i++)
	    segmentos[i]= new Segmento(teclado.nextInt(), teclado.nextInt());

	//Usar a funcao para ordenadar o vetor segmentos
	Arrays.sort(segmentos);

	int posicao=0;
	int fim2=0;
	int soma=0;
        //int soma2=0;
        int x=fim2;
        while(fim2<m){
          while( posicao<n && segmentos[posicao].inicio <=fim2 ){
		if(segmentos[posicao].fim1 > x)
		    x=segmentos[posicao].fim1;
		posicao++;
               // System.out.println(posicao);
	    }
            fim2=x;
	    soma++;
        }
	

	System.out.println(soma);

    }
}




