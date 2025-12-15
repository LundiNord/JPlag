
//package prob12;

import java.util.*;

public class Prob12 {
    public static long analizar(long [][] a , int k){
        for(int i=k-2; i>=0; i--)
            for(int j=0; j<=i; j++){
                if(a[i][j]!=-1)
                    if(a[i+1][j]==-1 && a[i+1][j+1]!=-1)
                        a[i][j]=a[i+1][j+1];
                    else if(a[i+1][j]!=-1 && a[i+1][j+1]==-1)
                        a[i][j]=a[i+1][j];
                    else if(a[i+1][j]!=-1 && a[i+1][j+1]!=-1)
                        a[i][j]=a[i+1][j]+a[i+1][j+1];
            }
        return a[0][0];
    }
    public static void main(String args[]){
	
	Scanner le= new Scanner(System.in);
        int d=le.nextInt();
        int p=le.nextInt();
        long piramide [][]= new long [d][d];
        int pedras [][]= new int [p][2];
        
        for(int i=d-1; i>=0; i--){
            for(int j=d-1; j>=0; j--)
                if(i==d-1)
                    piramide[i][j]=1;
                else 
                    piramide[i][j]=0;
        }
        
        for(int i=0; i<p; i++){
            pedras[i][0]=le.nextInt();
            pedras[i][1]=le.nextInt();
            piramide[d-pedras[i][0]][pedras[i][1]-1]=-1;
        
        }
        
        System.out.println(analizar(piramide, d));
        
	
    }
}
    