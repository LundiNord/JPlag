import java.util.*;
import java.io.*;

public class Prob16 {
	
	static void dfs(int no, int np, int Mat[][], int V[]){
		V[no] = 1;
		
		for(int i=0; i<np; i++){
			if(Mat[no][i]==1){
				if(V[i]==0) dfs(i, np, Mat, V);
			}
		}
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
	
		int np = in.nextInt();
		int nl = in.nextInt();
		int n1, n2;
		int M[][] = new int [np+1][np+1];
		
		for(int i=0; i<nl; i++){
			n1 = in.nextInt();
			n2 = in.nextInt();
			
			M[n1-1][n2-1] = 1;
			M[n2-1][n1-1] = 1;
		}
		
		int cont = 0;
		int Vis[] = new int [np+1];
		
		for(int i=0; i<np; i++){
			Vis[i] = 0;
		}
		
		for(int i=0; i<np; i++){
			if(Vis[i]==0){
				cont++;
				dfs(i, np, M, Vis);
			}
		}
		
		System.out.println(cont);
	}
}

