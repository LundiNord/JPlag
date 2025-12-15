import java.util.*;

public class Ex007 {
    static int numero_somas;

    public static void main(String[] args) {
       Scanner stdin = new Scanner(System.in);
       
       int n= stdin.nextInt();
       int s[]= new int[n];
       for(int i=0; i<n; i++)
           s[i]=stdin.nextInt();
       
       int p= stdin.nextInt();
       int perguntas[]= new int[p]; 
       for(int i=0; i<p; i++)
           perguntas[i]=stdin.nextInt();

       s=soma_pares(s,n);
       /* for(int i=0; i<numero_somas; i++)
	  System.out.println(s[i] + " ");*/
       

       for(int i=0; i<p; i++){
	   if(s[0] >= perguntas[i])
	       System.out.println(s[0]);
	   else if(s[numero_somas-1] <= perguntas[i])
	       System.out.println(s[numero_somas -1]);
	   else
	       solucao(0, numero_somas, s,i, perguntas);
       }
    }
		   
    static int[] soma_pares(int s[], int n){
       numero_somas=(n*(n-1))/2;
       int[] somas=new int[numero_somas];
       int posição=0;
        for(int i=0; i<n; i++)
            for(int j=i+1; j<n; j++){
                somas[posição]=s[i]+s[j];
		posição++;
	    }
	
        posição=0;
        Arrays.sort(somas);
	return somas;
       

  
       }
    


    static void solucao(int a, int b, int somas[], int c, int v[]){
	int min=a;
	int max=b;

	while(min < max){
	    int middle=min + (max-min)/2;
	    if(somas[middle] >= v[c])
		max=middle;
	    else
		min=middle+1;
	}

	int d1= Math.abs(somas[min]- v[a]);
	int d2= Math.abs(somas[min-1]- v[a]);

	if(d1 == d2)
	    System.out.println(somas[min-1] + " " + somas[min]);
	else if(d1 < d2)
	    System.out.println(somas[min]);
	else
	    System.out.println(somas[min-1]);
    }
}
	
    
           

	
    


    
   
        
       
        
    
    
    
    
    
    

