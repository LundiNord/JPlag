import java.util.*;
public class prob12{
    static int[][] piramide;
    static int n;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        piramide = new int[n][n];
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<=i; j++) piramide[i][j] = 1;
        }
        int p = in.nextInt();
        for(int i = 0; i<p; i++){
            int camada = in.nextInt();
            int pedra = in.nextInt();
            piramide[n-camada][pedra-1] = 0;
        }
        calcula_caminhos();
        System.out.println(piramide[0][0]);
    
    }
    public static void calcula_caminhos(){
        for(int i = n-2; i>=0; i--){
            for(int j = 0; j<i+1;j++){
                if(piramide[i][j] != 0){
                    if(j+1 < n && piramide[i+1][j] != 0){
                        if(piramide[i+1][j+1] != 0) {
                            piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1];
                        }
                        else piramide[i][j] = piramide[i+1][j];
                    }
                    else if(j+1 < n && piramide[i+1][j+1] != 0) piramide[i][j] = piramide[i+1][j+1];
                    else piramide[i][j] = 0;
                }
            }
        }
    }
}