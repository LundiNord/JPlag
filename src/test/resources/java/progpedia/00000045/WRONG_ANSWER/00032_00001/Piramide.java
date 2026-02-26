import java.io.*;
import java.util.*;


public class Piramide {
	


    public static void main(String args[]) {
    	int tamanho;
		int det;
		int pir[][];
		
	    Scanner stdin = new Scanner(System.in);
		tamanho   = stdin.nextInt();
		det = stdin.nextInt();
		pir = new int[tamanho][tamanho];
		for (int i=0;i<tamanho;i++){
			for (int j=0;j<tamanho-i;j++){
				pir[i][j]=1;
			}
		}
		
		for (int i= 0; i<det;i++)
		{
			pir[stdin.nextInt()-1][stdin.nextInt()-1]=0;
		}

		
		for (int i=1;i<tamanho;i++){
			for (int j=0;j<tamanho-i;j++){
				
				if (pir[i][j]!=0){
					pir[i][j]=pir[i-1][j]+pir[i-1][j+1];
					
				}
			}	
		}
		
		System.out.println(pir[tamanho-1][0]);
    }
}