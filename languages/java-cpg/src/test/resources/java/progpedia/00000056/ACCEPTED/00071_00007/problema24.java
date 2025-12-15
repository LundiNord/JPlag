import java.util.Scanner;

public class problema24 {

	static Scanner in = new Scanner(System.in);
	static double [][]matrix;
	static boolean [][]visited;
	static int n;
	static double res = 0;
	
	public static void main(String[] args) {
		
		n = in.nextInt();
		matrix = new double[n][n];
		visited = new boolean[n][n];
		double []x = new double[n];
		double []y = new double[n];
		
		for(int i=0; i<n; i++){
			x[i] = in.nextDouble();
			y[i] = in.nextDouble();
		}
		
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				double peso = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
				matrix[i][j] = peso;
				matrix[j][i] = peso;
			}
		}
		
		prim(matrix);
		System.out.printf("%.2f\n",res);
		
	}
	
	static void prim(double [][]grafo){
		double []custo = new double[n];
		boolean []fixo = new boolean[n];
		for(int i=0;i<n;i++){
			fixo[i]=false;
			custo[i]=Double.MAX_VALUE;
		}
		custo[0]=0;
		
		for(int faltam =n; faltam>0; faltam--){
			int no=-1;
			for(int i=0; i<n; i++){
				if(!fixo[i] && (no==-1 || custo[i]<custo[no]))
					no=i;
			}
			fixo[no]=true;
			
			if(custo[no] == Double.MAX_VALUE){
				res = Double.MAX_VALUE;
				break;
			}
			res+=custo[no];
			
			for(int i=0; i<n; i++){
				if(custo[i] > grafo[no][i])
					custo[i] = grafo[no][i];
			}
		}
	}
}