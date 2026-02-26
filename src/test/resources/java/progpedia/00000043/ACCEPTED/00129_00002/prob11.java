import java.io.*;
import java.util.*;

class prob11{
    public static double racio[] = new double[1000];

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	double enc[][] = new double[n][2];

	for(int i = 0; i < n; i++){
	    for(int j = 0; j < 2; j++){
		enc[i][j] = in.nextInt();
	    }
	    racio[i] = enc[i][1] / enc[i][0];
	}

	//por ordem de maior racio;
	for(int i = 0; i < n; i++){
	    if(i == n-1)
		System.out.print(max(n));
	    else
		System.out.print(max(n) + " ");
	}
	System.out.println();
    }

    public static int max(int n){
	int max = 0;
	for(int i = 1; i < n; i++)
	    if(racio[i] > racio[max])
		max = i;
	racio[max] = -1;
	return (max+1);
    }
}
