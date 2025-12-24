import java.util.Scanner;


public class DAA012 {
    static int n; //Quantidade de números da minha sequência
    static int d;
    static long matrix[][];
    static long aux[][];

    //----------------------------------------------------------------------

    static long count(int i,int j){

	if(aux[i][j] != -1)
	    return aux[i][j];
	
	if (matrix[i][j] == 0) {
	    aux[i][j] = 0;
	    return 0;	
	}

	else if(i == n-1) {
	    aux[i][j] = matrix[i][j];
	    return  matrix[i][j];
	}

	else{
	    aux[i][j] = count(i + 1, j) + count(i + 1,j + 1);
	    return aux[i][j];
	}
	 	
    }
   
    //----------------------------------------------------------------------
    
    static void readInput(){
	 
	Scanner stdin = new Scanner(System.in);
	
	n = stdin.nextInt(); //Quantidade de números da minha sequência

	d = stdin.nextInt(); //pedras deterioradas

	matrix = new long[n][n];

	aux = new long[n][n];

	for(int i=0;i<n;i++){
	    for(int j=0;j<n;j++){
		aux[i][j] = -1;
	    }	    
	}
	
	for(int i=0;i<n;i++){
	    for(int j=0;j<i+1;j++){
		matrix[i][j] = 1;
	    }	    
	}

	for(int i=0;i<d;i++)
	    matrix[n-stdin.nextInt()][stdin.nextInt()-1] = 0;

	System.out.println(count(0,0));

    }
    
    //------------------------------------------------------------------
    
    public static void main(String args[]) {
	
	readInput();

    }
}
