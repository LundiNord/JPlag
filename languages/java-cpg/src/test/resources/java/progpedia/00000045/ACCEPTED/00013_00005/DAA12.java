/**
 * Created by DevM on 15/11/2016.
 */
import java.util.Scanner;

/*public class DAA12 {
    public static long matrixAux[][];
    public static void main(String args[]){
        int n;
        int d;
        int matrix[][];
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        matrix = new int[n][n];
        matrixAux= new long [n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                matrix[i][j]=1;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                matrixAux[i][j]=-1;
            }
        }
        for(int i=0;i<d;i++){
            matrix[n-in.nextInt()][in.nextInt()-1]=0;
        }
        System.out.println(minPaths(0,0,n,matrix));

    }
    private static long minPaths(int pY, int pX, int n,int matrix[][]){
        long counter=0;
        if(matrix[pY][pX]==0){
            return 0;
        }
        if(pY==n-1){
            return 1;
        }
        if(matrixAux[pY][pX] != -1){
            return matrixAux[pY][pX];
        }
        else{
            counter+=minPaths(pY+1,pX,n,matrix);
            counter+=minPaths(pY+1,pX+1,n,matrix);
        }
        matrixAux[pY][pX]=counter;
        return counter;
    }
}*/

public class DAA12 {
    public static long matrixAux[][];
    public static void main(String args[]) {
        int n;
        int d;
        long matrix[][];
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        matrix = new long[n][n];
        matrixAux= new long [n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                matrix[i][j]=1;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<i+1;j++){
                matrixAux[i][j]=-1;
            }
        }
        for(int i=0;i<d;i++){
            matrix[n-in.nextInt()][in.nextInt()-1]=0;
        }
        System.out.println(minPaths(0,0,n,matrix));

    }
    private static long minPaths(int pY, int pX, int n,long matrix[][]){
        long counter=0;
        for(int i=n-1 ;i>=0;i--){
            for(int j=0;j<i+1;j++){
                if(matrix[i][j]==1){
                    if(i==n-1){
                        matrixAux[i][j]=1;
                    }
                    /*if(matrixAux[i][j]!=-1){
                        counter=matrixAux[i][j];
                    }*/
                    else{
                        matrixAux[i][j]=matrixAux[i+1][j]+matrixAux[i+1][j+1];
                    }
                }
                else{
                    if(matrix[i][j]==0){
                        matrixAux[i][j]=0;
                    }
                }
            }
        }
        /*if(matrix[pY][pX]==0){
            return 0;
        }
        if(pY==n-1){
            return 1;
        }
        if(matrixAux[pY][pX] != -1){
            return matrixAux[pY][pX];
        }
        else{
            counter+=minPaths(pY+1,pX,n,matrix);
            counter+=minPaths(pY+1,pX+1,n,matrix);
        }
        matrixAux[pY][pX]=counter;*/
        return matrixAux[0][0];
    }

}
