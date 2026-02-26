//----------------------------------------------------
//DAA_012 - Pirâmides
//Bruna Madeira, com Federica Murillo 
//----------------------------------------------------

import java.io.*;
import java.util.*;

public class DAA_012 {
    public static void main (String args[]) {

	Scanner stdin = new Scanner(System.in);

	int ncamadas = stdin.nextInt();
	int pedras_det = stdin.nextInt();

	long a[][] = new long[ncamadas][ncamadas];

	for(int i = 0; i < ncamadas; i++)
	    for(int j = 0; j < ncamadas; j++)  
		a[i][j] = 1;

	for(int i = 0; i < pedras_det; i++)
	    a[stdin.nextInt() - 1][stdin.nextInt() - 1] = 0;

	for(int i = 1; i < ncamadas; i++) {
	    for(int j = 0; j < ncamadas - i; j++) {
		if(a[i][j] != 0){
		    a[i][j] = a[i - 1][j] + a[i - 1][j + 1]; 
		}
	    }
	}	
	System.out.println(a[ncamadas - 1][0]);
    }
}
