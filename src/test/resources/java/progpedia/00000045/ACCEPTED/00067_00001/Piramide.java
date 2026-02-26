import java.io.*;
import java.util.*;

class Piramide{
    public static void main (String[] args){
	Scanner in = new Scanner(System.in);
	int camada = in.nextInt();
	long [][] piramide = new long[camada][camada];
	int deteriorada = in.nextInt();
	for(int i=0; i<camada; i++) {
	    for(int j=0; j<camada; j++) {
		piramide[i][j]=1;
	    }
	}
	for(int i=0; i<deteriorada; i++) {
	    piramide[in.nextInt()-1][in.nextInt()-1]=0;
	}
	for(int i=1; i<camada; i++) {
	    for(int j=0; j<camada-i; j++) {
		if(piramide[i][j]==0)
		    piramide[i][j]=0;
		else
		    piramide[i][j]=piramide[i-1][j]+piramide[i-1][j+1];
	    }
	}
	System.out.println(piramide[camada-1][0]);
    }
}

