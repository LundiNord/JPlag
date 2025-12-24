import java.io.*;
import java.util.*;



public class Piramides {
    static int n,d,k;
    static long camadas[][];
    static long v[][];

static long count(int i,int j){

	if(v[i][j] != -1)
	    return v[i][j];
	
	if (camadas[i][j] == 0) {
	    v[i][j] = 0;
	    return 0;	
	}

	else if(i == n-1) {
	    v[i][j] = camadas[i][j];
	    return  camadas[i][j];
	}

	else{
	    v[i][j] = count(i + 1, j) + count(i + 1,j + 1);
	    return v[i][j];
	}
	 	
    }

   
    
 
    public static void main(String args[]) {

	
	
	Scanner stdin = new Scanner(System.in);
    	
	
	n = stdin.nextInt();
	d = stdin.nextInt();

	v= new long [n][n];
	camadas = new long [n][n];


	for(int i=0;i<n;i++){
	    for(int j=0;j<n;j++){
		v[i][j] = -1;
	    }	    
	}
	
	for(int i=0;i<n;i++){
	    for(int j=0;j<i+1;j++){
		camadas[i][j] = 1;
	    }	    
	}

	for(int i=0;i<d;i++){
	   camadas[n-stdin.nextInt()][stdin.nextInt()-1] = 0;
	}
	System.out.println(count(0,0));

	
	
    }





    

}
