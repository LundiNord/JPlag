import java.io.*;
import java.util.*;




class piramides{
    public static int n=0;
    public static boolean[][] detioradas = new boolean [1000][100];
    public static int[][] contador = new int [1000][1000];
    public static int count=0;

    public static int trepar (int i , int j){
  	 

	if( i>1 && detioradas[i-1][j]==false && j<=n-(i-1)){
	     if(i-1==1){count++;}
	  
	     // System.out.println(i + " " + j + " Baixo " + count);
	    trepar(i-1,j);
	    // System.out.println(i + " " + j + " Baixo " + count);
	   
	}
	if(i>1 && detioradas[i-1][j+1]==false && j+1<=(n+1)-(i-1)){
	    if(i-1==1){count++;}
	    // System.out.println(i + " " + j + " D " + count );
	    trepar(i-1,j+1);
	    // System.out.println(i + " " + j + " D " + count );

	}
	return count;
    }


 


    public static void main(String args[]){



	Scanner stdin = new Scanner(System.in);
	n = stdin.nextInt();
	
	detioradas = new boolean[n+1][n+1];
	contador= new int[n+1][n+1];



	int d = stdin.nextInt();
	for(int i=0; i<d; i++){
	    int c= stdin.nextInt();
	    int p= stdin.nextInt();
	    detioradas[c][p]=true;
	}
	for(int i=0; i<=n; i++){
	    for(int j=0; j<=n; j++){
		if(i==1 && detioradas[i][j]==false){
		contador[i][j]=1;
		}

		else{contador[i][j]=0;}
	    }
	}

	/*		for(int i=1; i<=n; i++){
	    for(int j=1; j<=n-(i-1); j++){
		System.out.print(detioradas[i][j]+ " ");
	    }
	    System.out.println();
	    }*/


		for(int i=1; i<=n; i++){
		    for(int j=1; j<=n-(i-1); j++){
			if(i+1<=n && detioradas[i+1][j]==false){
			    contador[i+1][j]=contador[i][j] + contador[i+1][j];
			}
			/*	if(j+1<=n-(i-1) && detioradas[i+1][j+1]==false){
			    contador[i+1][j+1]= contador[i+1][j+1] + contador[i][j];
			    }*/
			if(j-1>=1 && detioradas[i+1][j-1]==false){
			    contador[i+1][j-1]= contador[i+1][j-1] + contador[i][j];
			}
		    }
		}
		/*	for(int i=1; i<=n; i++){
	    for(int j=1; j<=n-(i-1); j++){
		System.out.print(contador[i][j]+ " ");
	    }
	    System.out.println();
	    }*/

	System.out.println(contador[n][1]);
    }
}

		//	int resultado= trepar(n,1);
	//	if(n==1){System.out.println(n);}
	//	else{
	//	System.out.println(resultado);
	//	}
	
	



