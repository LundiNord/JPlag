import java.io.*;
import java.util.*;

public class piramides {
     public static void calcular (long [][] count, int ultcam){
	 for (int j = 0; j < ultcam; j++)
	     if (count [ultcam-1][j]!=-1)
		 count [ultcam-1][j]=1;
	 for (int i = ultcam - 2; i>=0; i--){
	     for (int j = i; j>=0; j--){
		 if (count [i][j]!=-1){
		     if (count [i+1][j]!=-1 && count [i+1][j+1]!=-1)
			 count [i][j] = count [i][j] + count[i+1][j] + count [i+1][j+1];
		     
		     if (count [i+1][j]==-1 && count [i+1][j+1]!=-1)
			 count [i][j] = count[i][j] + count [i+1][j+1];
		     
		     if (count [i+1][j]!=-1 && count [i+1][j+1]==-1)
			 count [i][j] = count[i][j] + count [i+1][j];
		 }
	     }
	 }
	
     }
    public static void main (String [] args){
	Scanner stdin = new Scanner (System.in);
	int ultcam = stdin.nextInt ();
	int falta = stdin.nextInt ();
	long [] [] count = new long [ultcam][ultcam];
	for (int i = 0; i < ultcam; i++)
	    for (int j = 0; j < ultcam; j++)
		count [i][j] = 0;
	for (int i = 0; i < falta; i++){
	    int k = stdin.nextInt ();
	    int h = stdin.nextInt ();
	    //System.out.println (k + " " + h);
	    count [ultcam-k][h-1] = -1;
	}
	calcular (count,ultcam);
	/*for (int i = 0; i < ultcam; i++){
	    for (int j = 0; j <= i; j++){
		System.out.print (count[i][j]);
		System.out.print (" ");
	    }
	    System.out.println();
	    }*/
	System.out.println (count[0][0]);
	
    }
}
