import java.io.*;
import java.util.*;

public class SomasMaisProximas {
	
	static int s[],p[],somas[];
	static int nP,tamanho;
	
	static int somaFib(int a){
		if(a-1 > 1) return (a-1) + somaFib(a-1);
		return 1;
	}
	
	static int dist(int pergunta, int soma){
		if(pergunta>soma) return pergunta - soma;
		else return soma - pergunta;
	}
	
	static void verificaSomas(int pergunta, int soma[]){
		int best[] = new int [tamanho];
		best[0] = Integer.MAX_VALUE;
		//auxiliar que incrementa quando existem 2 valores iguais ou com a mesma distancia
		int aux = 0;
		for(int i = 0; i < tamanho; i++){
			if(pergunta == soma[i] && pergunta == best[0]){
				aux++;
				best[aux] = soma[i];
			}
			if(pergunta == soma[i]) best[aux] = soma[i];
			if(dist(pergunta,soma[i]) < dist(pergunta,best[aux])) best[aux] = soma[i];
			if(dist(pergunta,soma[i]) == dist(pergunta,best[aux]) && best[aux] != soma[i]){
				aux++;
				best[aux] = soma[i];
			}
			if(dist(pergunta,soma[i]) > dist(pergunta,best[aux])) i = tamanho;
		}
		//imprimir o vector best
		for(int i = 0; i<=aux; i++)
			System.out.print(best[i] + " ");
		System.out.println();
	}
	
	public static void main(String args[]){
		
		Scanner stdin = new Scanner(System.in);
		int n;
		int pares[], somas[];
		 		
		
		//tamanho do conjunto s
		n = stdin.nextInt();
		s = new int [n];
		for(int i = 0; i<n; i++)
			s[i] = stdin.nextInt();
		
		//n perguntas
		nP = stdin.nextInt();
		p = new int [nP];
		for(int i = 0; i<nP; i++)
			p[i] = stdin.nextInt();
		
		//preencher o vetor somas
		int aux = 0;
		tamanho = somaFib(n);
		somas = new int [tamanho];
		for(int i = 0;i<n-1; i++){
			for(int j=i+1; j<n;j++){
				somas[aux] = s[i] + s[j];
				//debug
				//System.out.print(somas[aux]+ " ");
				aux++;
			}
		}
		
		//ordenar o vetor somas
		Arrays.sort(somas);
		
		/*
		for(int i = 0; i<tamanho; i++)
			System.out.print(somas[i] + " ");
		System.out.println();
		*/	
				
		for(int i = 0; i<nP; i++){
			verificaSomas(p[i],somas);
		}	
	}

}
