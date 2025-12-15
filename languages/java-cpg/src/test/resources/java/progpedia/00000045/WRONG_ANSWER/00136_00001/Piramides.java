import java.io.*;
import java.util.*;


class Piramides{
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);

	int N = in.nextInt(); //numero de pedras
	int D = in.nextInt(); //pedras em falta
	int[][] matriz = new int[N][N];
	int N1 = N;
	int N2 = N;
	
	for(int i = 0; i < N1 ; i++){     //colocar '1' onde n há parede
	    for(int j = 0; j < N ; j++){

		matriz[i][j] = 1;
		
	       
	    }
	    N--;
	}
	
	for(int i = 0; i < D ; i++){    //colocar '0' onde há parede
	    int C = in.nextInt();
	    int P = in.nextInt();
	    matriz[C-1][P-1] = 0;
	}

	int N3 = N2;
	
	for(int i = 1; i < N2 ; i++){
	    for(int j = 0; j < N3 ; j++){
		
		if(matriz[i][j] == 1) matriz[i][j] = matriz[i-1][j] + matriz[i-1][j+1];

	    }
	    N3--;
	}
		


		
	System.out.println(matriz[N2-1][0]);
        
	
	 
    }
}
	

	
	    
	
	
