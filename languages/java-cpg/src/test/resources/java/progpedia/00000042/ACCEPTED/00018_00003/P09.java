import java.io.*;
import java.util.*;

class Posicoes implements Comparable<Posicoes> {
    int pinicial;
    int pfinal;
    Posicoes (int pi, int pf) {
	pinicial = pi;
	pfinal = pf; }

    @Override
    public int compareTo(Posicoes pi) {
	int calculo = pinicial - pi.pinicial;
	return calculo; } }

class P09 {
    public static void main (String args[]) {
	Scanner in = new Scanner (System.in);
	int M = in.nextInt();
	int N = in.nextInt();
	Posicoes posicao []= new Posicoes [N]; 
	
	for (int i=0; i<N; i++) {
	    posicao[i] = new Posicoes(in.nextInt(), in.nextInt()); }
	
	Arrays.sort(posicao);
	int fim = 0;
	int contador = 0;
	int posicaoatual = 0;
	
	while (fim < M) { 
	    int fimanterior = fim;
	    posicaoatual = 0;
	    while (posicaoatual < N && posicao[posicaoatual].pinicial <= fim) {
		if(posicao[posicaoatual].pfinal > fimanterior) 
		    fimanterior = posicao[posicaoatual].pfinal; 
		posicaoatual ++; }
	    
	    fim = fimanterior; 
	    contador ++; }
	
	System.out.println(contador);
    }
}

