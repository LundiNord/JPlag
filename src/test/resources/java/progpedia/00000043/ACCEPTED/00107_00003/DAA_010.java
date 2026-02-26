
//package daa_010;

import java.util.*;

class Vetor implements Comparable<Vetor>{
	int vetor1;
	float vetor2;

	
	Vetor (int vet1, float vet2){
	    this.vetor1=vet1;
	    this.vetor2=vet2;
	    

	}
	 
	   @Override
    public int compareTo(Vetor p) {
	       if(this.vetor2 == p.vetor2 ){
		   if(vetor1 < p.vetor1)
		       return -1;
		   else
		       return 1;
	       }
        else{
            if(p.vetor2 > this.vetor2)
                return 1;
            else
                return -1;
        }
	    }
	
    }

public class DAA_010 {

    
    public static void main(String[] args) {
        
        
        Scanner stdin= new Scanner(System.in);
        
      
        int n=stdin.nextInt(); //número de encomendas a processar
        
        Vetor[] vetor= new Vetor[n];
        
        
        for(int i=0;i<n;i++){
            int dr= stdin.nextInt();
            vetor[i]= new Vetor(i+1, stdin.nextFloat()/dr);
        }
        
        
        Arrays.sort(vetor);
        
        for(int i=0; i<n-1;i++)
            System.out.print(vetor[i].vetor1 + " ");
       
        System.out.println(vetor[n-1].vetor1);
           
    
       } 
        
    }
    


