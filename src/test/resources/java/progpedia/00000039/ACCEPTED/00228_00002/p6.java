import java.util.*;
import java.io.*;

class p6{
  public static void main(String args[]){
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int[] v = new int[n];
    for(int i = 0; i<n; i++) v[i] = stdin.nextInt();
    int f = 0;

    for(int i  = 0; i<n; i++){
      for(int c = i+1; c<n; c++){
        f++;
      }
    }
    int[] g = new int[f];
    int n1 = 0;
    for(int i  = 0; i<n; i++){
      for(int c = i+1; c<n; c++){
        g[n1] = v[i] + v[c];
        n1++;
      }
    }
    Arrays.sort(g);

    //segunda parte
    int queries1 = stdin.nextInt();
    int[] queries = new int[queries1];

    for(int i = 0; i<queries1; i++) queries[i] = stdin.nextInt();
    for(int i = 0; i<queries1; i++){
      pesqBinaria(g, queries[i], f);
    }




  }

  public static void pesqBinaria(int[] array, int valor, int fim){
    int inicio = 0;
    fim = fim -1;

    if (valor > array[fim]){
        System.out.printf("%d\n", array[fim]);
        return;
    }

    if (valor < array[inicio]){
         System.out.printf("%d\n", array[inicio]);
         return;
    }

    while(inicio <= fim){
           int meio = (inicio+fim)/2;
           if(valor < array[meio]){
              fim = meio-1;
           }
           else{
             inicio = meio+1;
           }
    }

                if(inicio==0){
                   System.out.println(array[0]);
                }
                else if(array[inicio-1]==valor){
                        System.out.println(valor);
                }
                else if(Math.abs(array[inicio]-valor)==Math.abs(array[inicio-1]-valor)){
                       System.out.printf("%d %d\n", array[inicio-1], array[inicio]);
                }
                else if(Math.abs(array[inicio]-valor)<Math.abs(array[inicio-1]-valor)){
                       System.out.println(array[inicio]);
                }
                else{
                        System.out.println(array[inicio-1]);
                }


        }
}
