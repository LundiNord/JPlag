


import java.util.*;

class Vetor implements Comparable<Vetor>{
	int vetor1=0;
	int vetor2=0;

	
	Vetor (int vet1, int vet2){
	    this.vetor1=vet1;
	    this.vetor2= vet2;
	    

	}
	 
	   @Override
    public int compareTo(Vetor vet1) {
	       return this.vetor1 - vet1.vetor1;
	    }
	
    }

public class DAA_09 {

        static int f=0;
	static int pos=0;
	static int soma=0; // a quantidade mínima de segmentos necessária para cobrir o segmento [0,M]
        static int x=f;
   
    public static void main(String[] args) {

       Scanner stdin = new Scanner(System.in);

	int m   = stdin.nextInt();  // tamanho do segmento que queremos cobrir
	int p = stdin.nextInt(); // quantidade de segmentos 


	//ler os segmentos e criar um vetor que guarde duas variaveis cada
	Vetor[] vetor= new Vetor [p];
	for(int i=0;i<p;i++)
	    vetor[i]= new Vetor(stdin.nextInt(), stdin.nextInt());

	
	Arrays.sort(vetor);

	
        while(f<m){
            
	    while( pos<p && vetor[pos].vetor1 <=f ){
		if(vetor[pos].vetor2 > x)
		    x=vetor[pos].vetor2;
		pos++;
	    }
            f=x;
	    soma++;
        }
	

	System.out.println(soma); // quantidade mínima de segmentos 
        
        
        
    }
    
}


