
//package ex007;

import java.util.*;

public class Ex007{
    static int numero_somas;

    static int[] soma_pares(int s, int n[]){
       numero_somas = (s*(s-1))/2;
       int soma[] = new int[numero_somas];
       int pos = 0;
       
       for(int i=0; i<s; i++){
           for(int j = i+1; j<s; j++){
               soma[pos] = n[i] + n[j];
               pos++;
           }
       }
       
       Arrays.sort(soma); 
       return soma;
    }
    
    
    static void bsearch(int soma[], int a, int b, int c){
        int min = a;
        int max = b;
        
        while(min < max){
            int middle = min + (max - min)/2;
            if(soma[middle] >= c)
                max = middle;
            else
                min = middle +1;
        }
        
        int distancia_1 = Math.abs(soma[min] - c);
        int distancia_2 = Math.abs(soma[min - 1] - c);
        
        if(distancia_1 == distancia_2)
            System.out.println(soma[min - 1] + " " + soma[min]);
        else if(distancia_1 < distancia_2)
            System.out.println(soma[min]);
        else
            System.out.println(soma[min - 1]);
    } 
    
    
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        
        int s = stdin.nextInt();
        int n[] = new int[s];
        for(int i=0; i<s; i++)
            n[i]= stdin.nextInt();
        
        int p = stdin.nextInt();
        int valores[] = new int[p];
        for(int i=0; i<p; i++)
            valores[i] = stdin.nextInt();
        
        n= soma_pares(s,n);
        
        for(int i=0; i<p; i++){
            if( n[i] >= valores[i])
                System.out.println(n[i]);
            else if(n[numero_somas-1] <= valores[i])
                System.out.println(n[numero_somas - 1]);
            else{
                int key = valores[i];
                bsearch(n, i, numero_somas, key);
            }
        }
    }
}    
    
    



