
//package piramides;

import java.util.Scanner;


public class Piramides {

    
    public static void main(String[] args) {
        
    Scanner teclado = new Scanner(System.in);
        
    int N = teclado.nextInt(); 
    int S = teclado.nextInt(); 
        
        long[][] P = new long[N][N];
        
	for(int i = 0; i < N; i++) 
	    for(int k = 0; k < N; k++)  
		P[i][k] = 1;
        
	for(int i = 0; i < S; i++)
	    P[teclado.nextInt() - 1][teclado.nextInt() - 1] = 0;   
        

        for(int i = 1; i < N; i++) {
	
            
            for(int j = 0; j < N - i; j++) {
                
		if(P[i][j] == 0) P[i][j] = 0;
                
                else P[i][j] = P[i - 1][j] + P[i - 1][j + 1];
	    }
        }
        
        
    System.out.println(P[N - 1][0]); 
    
    }
  }
    
