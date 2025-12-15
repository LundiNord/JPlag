/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package exer007;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Joao Fonseca
 */
public class Exer007 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   //fazer o scan das variaveis e guardar no vetor v1 e v2
        Scanner teclado = new Scanner(System.in);
    
    int n = teclado.nextInt();
    int v1[] = new int[n];
    
    for(int i = 0; i<n;i++){
        v1[i]=teclado.nextInt();
    }
    
    int p= teclado.nextInt();
    int v2[] = new int[p];
    
    for(int i = 0 ; i < p ; i++){
       v2[i] = teclado.nextInt();
    }
    //ordenar o vetor v1
    Arrays.sort(v1);
    
    //for(int i = 0; i<n;i++){
      //  System.out.println(v[i]);}
    
  // https://pt.wikipedia.org/wiki/N%C3%BAmero_triangular
  int tot = ((n - 1) * n) / 2;
  
  int total[] = new int [tot];
  int x = 0;
   
  for(int i =0; i < n; i++) {
      for(int j=i+1; j<n; j++){
          total[x]= v1[i] + v1[j];
          x++;
          
      }
  }
  //ordenar o vetor total
  Arrays.sort(total);
  
  for(int i=0; i<p; i++)
    bsearch_conditional(total, v2[i]);

  
    }
    // Funcoes realizadas com ajuda
    static void bsearch_conditional(int v[], int key) {
		int lo = 0;
		int hi = v.length -  1;
		
                
                
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;

			if (key <= v[mid])
				hi = mid;
			else
				lo = mid + 1;
		}
		
		if (lo > 0 && key != v[lo]) {
			int dist_para_seguinte = Math.abs(key - v[lo]);
			int dist_para_anterior = Math.abs(key - v[lo - 1]);
			
			if (dist_para_anterior < dist_para_seguinte)
				System.out.println(v[lo - 1]);
			else if (dist_para_anterior == dist_para_seguinte) 
				System.out.println(v[lo - 1] + " " + v[lo]);
			else 
				System.out.println(v[lo]);

		} 
		else 
		{
			if (key <= v[lo])
				System.out.println(v[lo]);
			else
				return;
		}
	}
	
	
	int bsearch(int v[], int key) {
		int lo = 0;
		int hi = v.length - 1;
		while (lo <= hi) {
			
			int mid = lo + (hi - lo) / 2;

			if (key < v[mid])
				hi = mid - 1;
			else if (key > v[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;

	}

}

  
  
  
  
  
  
