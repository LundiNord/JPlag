/* Mergesort em Java. Funcionamento identico ao do C, contudo, devido a limitacoes do Java que tornam a leitura e escrita de input e output muito mais lenta que em C, foi necessario fazer alguns "atalhos" para que o programa passasse no mooshak. Para alem de utilizar um BufferedReader em vez do tradicional Scanner, e utilizado um metodo da API do Java para converter o array de ints para string e depois e utilizada uma expressao regular para retirar as virgulas e parentesis rectos (o metodo toString retornaria por exemplo [1, 2, 3, 4, 5] em vez de 1 2 3 4 5 que e o que queremos) */

import java.io.*;
import java.util.*;

public class prat03 {
    static long merge (int v[], int start, int med, int end) {
	int i,j,k;
	long inv = 0;
	i = start;
	j = med + 1;
	k = 0;
	int[] tmp = new int [end-start+1];
	while (i <= med && j <= end) {
	    if (v[i] <= v[j]) {
		tmp[k] = v[i];
		k++;
		i++;
	    }
	    else {
		inv += (med - i + 1);
		tmp[k] = v[j];
		k++;
		j++;
	    }
	}
  
	while (i <= med) {
	    tmp[k] = v[i];
	    k++;
	    i++;
	}
  
	while (j <= end) {
	    tmp[k] = v[j];
	    k++;
	    j++;
	}
  
	for (i = 0; i < k; i++) {
	    v[i + start] = tmp[i];
	}
	return inv;
    }

    static long mergesort (int v[], int start, int end) {
	int med = (start + end) / 2;
	if (start == end) return 0;
	long inv = 0;
	inv += mergesort (v, start, med);
	inv += mergesort (v, med+1, end);
	inv += merge (v, start, med, end);
	return inv;
    }

    public static void main (String[] args) {
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(reader.readLine());
	    int[] array = new int[n];
	    String[] tokens = reader.readLine().split(" ");
	    for (int i = 0; i < n; i++) {
		array[i] = Integer.parseInt(tokens[i]);
	    }
	    long inv = mergesort(array,0,n-1);
	    System.out.println(inv);
	}
	catch (Exception e) {}
    }
}
