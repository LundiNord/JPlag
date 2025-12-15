import java.io.*;
import java.util.*;
 
public class somaproxima{
 
    public static void main(String args[]){
         
    Scanner input = new Scanner(System.in);
         
    int n = input.nextInt();
    int v[] = new int[n];
        for (int i=0; i<n; i++){
            v[i]=input.nextInt();
        }
   
    int p = input.nextInt();
    int prg[] = new int[p];
        for (int i = 0; i < p; i++){
            prg[i] = input.nextInt();}
 
    int somas[]= new int[(n*(n-1)/2)];
    int x=0;
        for (int j=0; j<n-1; j++){
            for (int i=j+1; i<n; i++){
                somas[x]=v[j]+v[i];
                x++;
            }  
        }
 
    Arrays.sort(somas);
 
    for (int i=0; i<p;i++){
         pesqBinaria(somas,prg[i],x);
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

//nota: A ajuda do Fabio Queiros e Pedro Faria foi fundamental para a realizar a pesqBinaria