
//package daa_012;

import java.io.*;
import java.util.*;

public class DAA_012 {

   
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        
	int n = stdin.nextInt(); //pedras na primeira camada e  número de níveis da pirâmide 
        
	long[][] Piramide = new long[n][n];
        
	int d = stdin.nextInt(); 
        
	for(int i = 0; i < n; i++) 
	    for(int j = 0; j < n; j++)  
		Piramide[i][j] = 1;
        
	for(int i = 0; i < d; i++)
	    Piramide[stdin.nextInt() - 1][stdin.nextInt() - 1] = 0;   
        

        
    System.out.println(Piramide[n - 1][0]); //número de maneiras diferente de subir a pirâmide
    
    }
    
}

