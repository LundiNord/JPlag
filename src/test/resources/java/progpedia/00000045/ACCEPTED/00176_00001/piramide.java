import java.io.*;
import java.util.*;

class piramide {

    static long formas(boolean[][]pir, int n){

	long maneiras[][] = new long[n][n];

	for(int i = 0; i<n; i++)
	    for(int j = 0; j<n; j++)
		maneiras[i][j]=0;

	if(pir[n-1][0] == false) return 0;

	maneiras[n-1][0]=1;

	int i;
	int j;
	for(i=n; i>0; i--)
	    for(j=0; j<n-i; j++){
		if(pir[i][j]==true){
		    if(pir[i-1][j]==true)
			maneiras[i-1][j] = maneiras[i-1][j] + maneiras[i][j];
		    else
			maneiras[i-1][j] = 0;
		    if(pir[i-1][j+1]==true)
			maneiras[i-1][j+1] = maneiras[i-1][j+1] + maneiras[i][j];
		    else
			maneiras[i-1][j+1] = 0;
		}
	    }
	
	long total = 0;

	for(i=0; i<n; i++)
	    total+=maneiras[0][i];

	return total;
    }
    
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();

	boolean pir[][] = new boolean[n][n];

	for(int i = 0; i<n; i++)
	    for(int j = 0; j<n; j++)
		pir[i][j]=true;

	int estragos = in.nextInt();

	for(int i = 0; i<estragos; i++){
	    int c = in.nextInt();
	    int p = in.nextInt();
	    pir[c-1][p-1] = false;
	}

	long formas_diferentes = formas(pir,n);

	System.out.println(formas_diferentes);
	
    }
}
