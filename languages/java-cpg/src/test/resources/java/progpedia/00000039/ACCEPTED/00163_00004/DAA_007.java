
//package daa_007;

import java.util.Arrays;
import java.util.Scanner;


public class DAA_007 {

    
    public static void main(String[] args) {
       
        Scanner stdin = new Scanner(System.in);

		
		int n = stdin.nextInt();
		int s[] = new int[n];
		for (int i = 0; i < n; i++)
			s[i] = stdin.nextInt();

		int n_perguntas = stdin.nextInt();
		int perguntas[] = new int[n_perguntas];
		for (int i = 0; i < n_perguntas; i++)
			perguntas[i] = stdin.nextInt();

		

		Arrays.sort(s);

		// tamanho do numero triangular: 1, 3, 6, 10, 15, 21
		int n_somas = ((n - 1) * n) / 2;

		
		int total[] = new int[n_somas];
		int p = 0; 

		// Calculo das somas entre todos os pares
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				total[p] = s[i] + s[j];
				p++;
			}
		}

		Arrays.sort(total);

		
		for (int i = 0; i < n_perguntas; i++)
			bsearch(total, perguntas[i]);

	}
    
    static void bsearch(int v[], int key) { // funçao feita com ajuda durante a aula
		
        int low = 0;
        int high = v.length - 1;
		
		while (low < high) {
			int midle = low + (high - low) / 2;

			if (key <= v[midle])
				high = midle;
			else
				low = midle + 1;
		}
		//caso nao seja o primeiro indice (low>0)
		if (low > 0 && key != v[low]) {
			int distancia_seguinte = Math.abs(key - v[low]);
			int distancia_anterior = Math.abs(key - v[low - 1]);
			//se o anterior for mais pequeno que o seguinte escreve o anterior
			if (distancia_anterior < distancia_seguinte)
				System.out.println(v[low - 1]);
			else if (distancia_anterior == distancia_seguinte) //se forem iguais
				System.out.println(v[low - 1] + " " + v[low]);
			else 
				System.out.println(v[low]);

		} 
		else //sendo o primeiro indice, saber qual é a soma mais proxima 
		{
			if (key <= v[low])
				System.out.println(v[low]);
			else
				return;
		}
	}


    }
    
