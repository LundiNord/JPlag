import java.util.Scanner;


public class DAA012 {
    static int n; //Quantidade de números da minha sequência
    static int d;
    static int matrix[][];
    static int count[][]; // conta número de caminhos;
    static int min = 999999; // custo do caminho mínimo;~
    static int conta = 0;

     //----------------------------------------------------------------------

     static void output(int v[][]){
        int c = count[0][0]; // custo do topo da pirâmide
        conta = c;
        for(int i=0;i<n;i++){
	        for(int j=0;j<i+1;j++){
                for(int k=0;k<n;k++) //percorre cada coluna e vê o mínimo
                    conta = conta + count[k][j];
                    if(conta < min){
                        min = conta;
                        conta = 0;
                    }
            } 
        }
          System.out.println(min);
     }

    //----------------------------------------------------------------------

    static boolean obstaculo(int a, int b, String v){
        for(int i=0;i<n-1;i++){
	        for(int j=0;j<n-1;j++){
                if(i==a && b==j && v == "NORTE"){
                    if(matrix[i+1][j] == 1)
                        return false;
                    else 
                        return true;
                }
                else if(i==a && b==j && v == "ESTE"){
                    if(matrix[i][j+1] == 1)
                        return false;
                    else
                        return true;
                }
            }   
        }
        return true;
    }
   
    //----------------------------------------------------------------------

    static void minPath(){
        count[n-1][n-1] = 1;
        for(int i = n-1;i>=0;i--){
            for(int j = n-1;j>=0;j--){
                if(i < n-1 && (obstaculo(i, j, "NORTE")))
                    count[i][j] = count[i][j] + count[i + 1][j];
                else if(j < n-1 && (obstaculo(i, j, "ESTE")))
                    count[i][j] = count[i][j] + count[i][j + 1];
            }
        }
    }
    
    //----------------------------------------------------------------------
    
    static void readInput(){
	 
	    Scanner stdin = new Scanner(System.in);
	
	    n = stdin.nextInt(); //Quantidade de números da minha sequência

	    d = stdin.nextInt(); //pedras deterioradas

	    matrix = new int[n][n];

        count = new int[n][n];
	
	    for(int i=0;i<n;i++){
	        for(int j=0;j<i+1;j++){
		    matrix[i][j] = 0;
	        }	    
	    }

	    for(int i=0;i<d;i++)
	        matrix[n-stdin.nextInt()][stdin.nextInt()-1] = 1;

	    minPath();

	    output(count);
	
    }
    
    //------------------------------------------------------------------
    
    public static void main(String args[]) {
	
	readInput();

    }
}
