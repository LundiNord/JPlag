import java.util.*;
import java.io.*;
 
public class prob6 {
                public static void pesqbin(int[] array, int valor){
                int inicio = 0;
                int fim = array.length-1;
               
                    if(valor > array[fim]) {
                System.out.printf("%d\n", array[fim]);
                return;
                }
            if(valor < array[inicio]) {
                System.out.printf("%d\n", array[inicio]);
                return;
                     }
       
                while(inicio <= fim){
                        int meio = (inicio+fim)/2;            
                        if(valor < array[meio]){
                                fim = meio-1;
                        } else {
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
                else{System.out.println(array[inicio-1]);}
                }
               
 
 
     
       
       
     
 
        public static void main(String[] args) {
                Scanner in=new Scanner(System.in);
                int n = in.nextInt();
                int con[] = new int[n];
                for (int i = 0; i < n; i++){
                        con[i] = in.nextInt();}
                int somas[] = new int[n*(n-1)/2];
                int p = in.nextInt();
                int que[] = new int[p];
                for (int i = 0; i < p; i++){
                        que[i] = in.nextInt();}
                int s=0;
                for(int i=0; i<n;i++){
                        for(int j=i+1;j<=n-1;j++){
                                somas[s]=con[i]+con[j];
                                s++;
                        }
                }
                Arrays.sort(somas);
 
                /* for(int i=0;i<s;i++){
                        System.out.print(somas[i] + " ");
                } */
 
                for(int i=0;i<p;i++){
                pesqbin(somas,que[i]);
                }
        }
}