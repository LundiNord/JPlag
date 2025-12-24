
//package prob0010;

import java.util.*;

class Vetor implements Comparable<Vetor>{ // funcao feita em conjunto na aula
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

public class Prob0010 {

    
    public static void main(String[] args) {
        
        
        Scanner stdin= new Scanner(System.in);
        
      
        int x =stdin.nextInt(); 
        
        Vetor[] vetor= new Vetor[x];
        
        
        // Guardar valores da duracao e multa por dia 
        for(int i=0;i<x;i++){
            int mi= stdin.nextInt();
            int di = i+1;
            vetor[i]= new Vetor(di, stdin.nextFloat()/mi);
        }
        
        
        Arrays.sort(vetor);
        
        for(int i=0; i<x-1;i++)
            System.out.print(vetor[i].vetor1 + " ");
       
        System.out.println(vetor[x-1].vetor1);
           
    
       } 
        
    }
    


