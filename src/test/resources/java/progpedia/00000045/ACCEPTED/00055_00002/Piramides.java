import java.util.*;

class Piramides {
    public static void main(String args[]){
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt(); //numero de pedras nxn
        int pedras[][] = new int[n][n];


        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if( j <= ( (n-i)-1 ) ) pedras[n-i-1][j] = 1;
                else pedras[n-i-1][j] = 0;
            }
        }

        int pedras_falta = stdin.nextInt();
        for(int i=0; i<pedras_falta; i++)
            pedras[n-stdin.nextInt()][stdin.nextInt()-1] = 0;


        /*for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(pedras[i][j]+ " ");
            System.out.println();
        }*/

        long count[][] = new long[n][n];
        for(int i=0; i<n; i++)
            count[n-1][i] = pedras[n-1][i];

        for(int i=n-2; i>-1; i--){
            for(int j=0; j<n; j++){
                if(pedras[i][j] == 1) {count[i][j] = count[i+1][j] + count[i+1][j+1];}
                else count[i][j] = 0;
            }
        }


         /*for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(count[i][j]+ " ");
            System.out.println();
        }*/

        System.out.println(count[0][0]); 
    }
}