//Joana Silva 200908475

import java.io.*;
import java.util.*;

class prob02{
    public static void main(String args[]){

        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] seq = new int[n];
//ler input
        for(int i=0; i<n; i++){
            seq[i]=stdin.nextInt();
        }
        
        int bestMax = seq[0];
        int thisMax = seq[0];
//usando os dois vectores para calcular o max corrente, e o max a cada iteracao
        for (int i=1; i<n; i++){
            thisMax = Math.max(seq[i], thisMax+seq[i]);
            bestMax = Math.max(thisMax, bestMax);
        }

        System.out.println(bestMax);
        
    }
}
