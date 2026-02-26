import java.io.*;
import java.util.*;

public class Somapar{
    public static int soma[];

    public static void calcularSoma(int v[],int n) {

        int maximo = ((n*n)-n)/2;
        soma = new int[maximo+1];
        int count=0;
        
        for(int i=0; i<n; i++)
            for(int j=i+1; j<n; j++){
                soma[count] = v[i] + v[j];
                count++;
            }
            return;

}//calcularSoma

public static int bsearch(int soma[], int low, int high, int key ){

    while (low < high){
     int middle = low + (high - low)/2;
         
     if(key==soma[middle])
        return middle; 
    else if(soma[middle]>key){
       high=middle;}

       else if (soma[low]<key){
           low=middle+1;}
       }

       return low;
   }

   public static void main(String args[]) {

    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int numeros[] = new int[n];


    for (int i=0; i<n; i++)
        numeros[i] = stdin.nextInt();

    Arrays.sort(numeros);

    int p = stdin.nextInt();
    int perguntas[] = new int[p];

    for (int i=0; i<p; i++)
        perguntas[i] = stdin.nextInt();

    calcularSoma(numeros,n);

    int maximo2 = ((n*n)-n)/2;
    soma[maximo2] = Integer.MAX_VALUE;

    Arrays.sort(soma);
        
    int index = 0;
    
    for (int i=0; i<p; i++){
        int key = perguntas[i];

        index = bsearch(soma, 0, maximo2, key); //menor soma encontrada
    
        if (index==0){
            System.out.println(soma[index]);                 
        }

        else{

            if((soma[index] - key) > (key - soma[index-1])){
            index--;
            System.out.println(soma[index]);                 
            
            }
  
            else {

              if((soma[index] - key) == (key - soma[index-1]) && soma[index] != soma[index-1]){ // nao repetir 14 14
              System.out.println(soma[index-1] +  " " + soma[index]);
              }
  
              else{
              System.out.println(soma[index]);  
              }
            }
        }
}



}//main
}//SomaPar
