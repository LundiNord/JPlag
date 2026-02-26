import java.util.*;


public class Piramides {
	static int n; //representa o n de pedras na 1 camada e o n de niveis
	static int d; //n de pedras em falta ou muito deterioradas
	static int c; //representa a camada
	static int p; //representa a posicao
	static long piramide [][];//matriz representante da piramide
	
	static long prog_dinamic(long piramide[][], int n){
		for(int i = n-2; i >= 0; i--){
			for(int j = 0; j <= i; j ++){
				if(piramide[i][j] != 0)
					piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1];
			}
		}
		return piramide[0][0];
		
	}
	
	public static void main (String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		d = stdin.nextInt();
		piramide = new long [n][n];
		
		//preencher a piramide a 1's
		for(int i = n-1; i >= 0; i--)
			for(int j = 0; j <= i; j++)
				piramide[i][j] = 1;
	
		//prencher os espacos com pedras em falta a 0's
		for(int i = 0; i < d; i ++){
			c = stdin.nextInt();
			p = stdin.nextInt();	
			piramide[n-c][p-1] = 0;
		}
		
		long calc_piramide = prog_dinamic (piramide, n);
		System.out.println(calc_piramide);
	}
}
