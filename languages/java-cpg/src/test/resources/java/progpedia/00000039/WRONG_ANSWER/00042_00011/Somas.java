import java.util.*;
import java.io.*;
import java.lang.Math;

class Somas{

    public static void main(String[] args){

	Scanner input = new Scanner(System.in);
	int S = input.nextInt();
	int[] numbers = new int[S];
	for(int i=0; i<S; i++){
	    numbers[i] = input.nextInt();
	}
	
	int P = input.nextInt();
        
	int[] somas = new int[S * (S - 1) / 2];
	int a=0;
   
	for(int i=0; i<S; i++){
	    for(int j=i+1; j<S; j++){
		somas[a] = numbers[i]+numbers[j];
		a++;
	    }
	}
	
	Arrays.sort(somas);
	
	for(int i=0; i<P; i++){
	    int temp = input.nextInt();

	    int inicio = 0;
	    int fim = somas.length-1;
	    int shortest = 2000001;
	    while(inicio <= fim){
		int meio = (inicio+fim)/2;
				
		if(somas[meio] == temp){
		    shortest = meio;
		    System.out.println(somas[meio]);
		    break;
		}

	        
		
		if(temp > somas[meio]){
		    inicio = meio+1;
		} else {
		    fim = meio-1;
		}

		if(inicio > fim){
		    try{
			if(Math.abs(somas[meio] - temp) > Math.abs(somas[meio+1] - temp) && somas[meio] != somas[meio+1]){
			    shortest = meio+1;
			    System.out.println(somas[shortest]);
			    break;
			}
		    }catch(Exception e){}
		    try{
			if(Math.abs(somas[meio] - temp) > Math.abs(somas[meio-1] - temp)&& somas[meio] != somas[meio-1]){
			    shortest = meio-1;
			    System.out.println(somas[shortest]);
			    break;
			}
		    }catch(Exception e){}
		    try{
			if(Math.abs(somas[meio] - temp) == Math.abs(somas[meio+1] - temp)&& somas[meio] != somas[meio+1]){
			    shortest = meio+1;
			    System.out.println(somas[shortest] + " " + somas[meio]);
			    break;
			}
		    }catch(Exception e){} 
		    try{
			if(Math.abs(somas[meio] - temp) == Math.abs(somas[meio-1] - temp)&& somas[meio] != somas[meio-1]){
			    shortest = meio-1;
			    System.out.println(somas[shortest] + " " + somas[meio]);
			    break;
			}						
		    }catch(Exception e){

		    }
		    
		    System.out.println(somas[meio]);

	        }
	    }
	    

	    
	}
	


	
    }
    

}
