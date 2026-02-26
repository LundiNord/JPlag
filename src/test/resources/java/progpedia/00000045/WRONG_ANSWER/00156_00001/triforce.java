import java.util.*;
class triforce{
    public static void main(String args[]){
	Scanner input = new Scanner (System.in);
	int N = input.nextInt();
	int k = N+1;
	int piramide[][] = new int[k][k];
	boolean horus[][] = new boolean[k][k];
	for (int i=1; i<k; i++)
	    for (int j=1; j<k; j++)
		horus[i][j] = true;
	int D = input.nextInt();
	for (int i=0; i<D; i++)
	    horus[input.nextInt()][input.nextInt()] = false;
	for (int i=1; i<k; i++){
	    if (horus[1][i])
		piramide[1][i] = 1;
	    else
		piramide[1][i] = 0;
	}
	for (int i=2; i<N+1; i++){
	    k--;
	    for (int j=1; j<k; j++){
		if (horus[i][j]){
		    piramide[i][j] = piramide[i-1][j];
		    piramide[i][j] += piramide[i-1][j+1];
		}
		else
		    piramide[i][j] = 0;
	    }
	}
	System.out.println (piramide[N][1]);
    }
}
