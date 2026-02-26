import java.io.*;
import java.util.*;

public class moo12{
    static int caminhos(int[][] aux, int n) {
	int[][] count = new int[n+1][n+1];

	for(int l=1; l<=n; l++){
	    count[1][l]=1;
	}
	
	for(int i=2; i<=n; i++){
	    for(int j=1; j<=n-i+1; j++){
		
		if(aux[i-1][j]!=1 && aux[i-1][j+1]!=1)
		    count[i][j] = count[i-1][j] + count[i-1][j+1];

		else if(aux[i-1][j]!=1)
		    count[i][j]=count[i-1][j];

		else if(aux[i-1][j+1]!=1)
		    count[i][j]=count[i-1][j+1];

	
	    }
	    
	}
	if(aux[n][1]==1) return 0;
	return count[n][1];
    }
	
    public static void main(String Args[]){
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int d = in.nextInt();            //menor que n!
	int aux[][] = new int[n+1][n+1];

	int c, p;
	
	for(int i=0; i<d; i++){
	    c = in.nextInt();
	    p = in.nextInt();
	    aux[c][p] = 1;  
	}
	
	              /* for(int i=1; i<=n; i++){
			 for(int j=1; j<=n-i+1; j++){
			 System.out.print(aux[i][j] + " ");
			 }
			 System.out.println();
			 }*/
	int res;
	res = caminhos(aux, n);
	System.out.println(res);
    }
}
