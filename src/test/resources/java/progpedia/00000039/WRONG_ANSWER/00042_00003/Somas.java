import java.util.*;
import java.io.*;
import java.lang.Math;

class Somas{

    public static long diff(long a, long b){

	return Math.abs(a-b);

    }


    public static void main(String[] args){

	Scanner input = new Scanner(System.in);
	int S = input.nextInt();
	long[] numbers = new long[S];
	for(int i=0; i<S; i++){
	    numbers[i] = input.nextLong();
	}
	
	int P = input.nextInt();
	long[] quests = new long[P];
	for(int i=0; i<P; i++){
	    quests[i] = input.nextLong();
	}

        ArrayList<Long> somas = new ArrayList<Long>();
	int k=0;
	for(int i=0; i<S; i++){
	    for(int j=i+1; j<S; j++){
		somas.add(numbers[i]+numbers[j]);		
		k++;
	    }
	}
	
	Collections.sort(somas);
	//para cada pergunta
        long current=0;
        int index=0;
	
	loop:for(int i=0; i<P; i++){
	    long temp = quests[i];
	   
	    //Binary Search

	    int inicio = 0;
	    int fim = somas.size()-1;
		
	    while(inicio <= fim){
	        int meio = (inicio+fim)/2;
			
	        if(somas.get(meio) == temp){		    
		    System.out.println(somas.get(meio));
		    continue loop;
	        }
			
	        if(temp > somas.get(meio)){
		    current = somas.get(meio);
		    index = meio;
		    inicio = meio+1;
	        } else {
		    current = somas.get(meio);
		    index = meio;
		    fim = meio-1;
	       	}
	    }
	    try{
	    if(diff(current, temp) == diff(somas.get(index-1), temp)){		
		System.out.println(somas.get(index-1) + " "+ current);
		continue loop;
	    }else if(diff(current, temp) == diff(somas.get(index+1), temp)){		    
		System.out.println(somas.get(index+1) + " "+ current);
		continue loop;
	    }
	    else if(diff(current, temp) >  diff(somas.get(index-1), temp)){
		System.out.println(somas.get(index-1));
		continue loop;
	    }
	    else if(diff(current, temp) >  diff(somas.get(index+1), temp)){
		System.out.println(somas.get(index+1));
		continue loop;
	    }
	    }
	    catch(Exception ArrayIndexOutOfBoundsException){

	    }
	    System.out.println(current);
	    
	    
	}
	

	
    }
    

}
