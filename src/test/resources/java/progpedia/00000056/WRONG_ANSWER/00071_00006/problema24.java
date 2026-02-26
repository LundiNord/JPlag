import java.util.Scanner;

public class problema24 {

	static Scanner in = new Scanner(System.in);
	static double [][]matrix;
	static boolean [][]visited;
	static int n;
	static double res = 0;
	
	public static void main(String[] args) {
		
		n = in.nextInt();
		matrix = new double[n*2][n*2];
		visited = new boolean[n*2][n*2];
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
		clearVisited();
		prim(matrix);
		System.out.printf("%.2f\n",res);	
	}
	
	static void prim(double [][]grafo){
		double x = Double.MAX_VALUE;
		int pred = 0,k=0,i=0;	
		while(k<n-1){
			for(int j=0; j<n; j++){
				if(i==j)
					visited[i][j]=true;	
				if(grafo[i][j]<x && !visited[i][j]){
					visited[i][j]=true;
					visited[j][i]=true;
					x=grafo[i][j]; 
					pred = j;
				}
				visited[i][j]=true;
			}
			i=pred;
			res+=x;
			x = Double.MAX_VALUE;
			k++;
		}
	}
	
	static void clearVisited(){
		for(int i=0; i<n;i++)
			for(int j=0; j<n; j++)
			  visited[i][j]=false;
	}
}