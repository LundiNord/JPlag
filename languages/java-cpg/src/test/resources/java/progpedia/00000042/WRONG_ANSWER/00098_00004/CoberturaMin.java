
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
	
	
	for(int i=0; i<N; i++){
		for(int j=i+1; j<N-1;j++){
			
			int aux;
			if(inicio[i] > inicio[j]){
				aux=inicio[i];
				inicio[i] = inicio[j];
				inicio[j] = aux;
				
				aux=fim[i];
				fim[i] = fim[j];
				fim[j] = aux;
				
			}
			
			else if(inicio[i] == inicio[j]){
				if(fim[i]> fim[j]){
					
				aux=inicio[i];
				inicio[i] = inicio[j];
				inicio[j] = aux;
				
				aux=fim[i];
				fim[i] = fim[j];
				fim[j] = aux;
				
				}
			}
			
			
		}
	}
	
	for (int j = 0; j < N; j++){
		int best = j;
        for(int i=j+1;i<N;i++){
			
			if(inicio[i] <=end){
				if(fim[i]>fim[best])
					best = i;
			}
			else{
				j = best;
				
				break;
			}
			
		}
         if (fim[j] > end) {
			// System.out.println(inicio[j]+" "+fim[end]);
			 end = fim[j];
              quant++;
			}
              //end = j;
		}
    
      
	
	
	System.out.println(quant);
	
	
	}
}
