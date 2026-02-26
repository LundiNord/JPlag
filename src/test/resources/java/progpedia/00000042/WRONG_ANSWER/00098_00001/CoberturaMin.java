
import java.io.*;
import java.util.*;




public class CoberturaMin{
	 public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);

	int end=0;
	int quant=0;
    int M = stdin.nextInt();
    int N = stdin.nextInt();
    
    int[] inicio= new int[N];
    int[] fim=new int[N];
    
    for(int i=0; i<N; i++){
		
		 inicio[i] = stdin.nextInt();
		 fim[i] = stdin.nextInt();
				
	}
	
	Arrays.sort(inicio);
	//for(int i=0; i<N;i++)
	//System.out.println(inicio[i]);
	
	for (int j = 1; j < N; j++){
        
         if (inicio[j] <= fim[end] && fim[end] <= M) {
			// System.out.println(inicio[j]+" "+fim[end]);
              quant++;
              end = j;
			}
		}
    
      
	
	
	System.out.println(quant);
	
	
	}
}
