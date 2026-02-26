import java.util.*;

public class P07{
    public static void main(String args[]){
	int[] ordenado;
	Scanner in = new Scanner(System.in);
	int nnumeros = in.nextInt();
	int numeros[] = new int[nnumeros];
	for(int i=0;i<nnumeros;i++)
	    numeros[i]=in.nextInt();
	Mergesort t = new Mergesort();
	ordenado = new int[nnumeros];
	ordenado=t.sort(numeros);
	int nperguntas = in.nextInt();
	for(int k=0;k<nperguntas;k++){
	    int somap=0;
	    int soma=0;
	    int distprox=0;
	    int dist=0;
	    int cont=0;
	    int somapp=0;
	    int per = in.nextInt();
	    somap=ordenado[0]+ordenado[1];
	    for(int i=0;i<nnumeros;i++){
	        if(somap>=per)
		    break;
		distprox=per-somap;
		for(int j=i+1;j<nnumeros;j++){
		    soma=ordenado[i]+ordenado[j];
		    if(soma>per)
			dist=soma-per;
		    else dist=per-soma;
		    if(dist<distprox){
			distprox=dist;
			somap=soma;
			cont=0;
		    }
		    else
		    if(dist==distprox){
			cont=2;
			somapp=soma;
		    }
		    
		}
	    }
	    if(cont==2)
		System.out.print(somap+" "+somapp);
	    else
		System.out.print(somap);
	    System.out.println();
	}
	
    }
}

class Mergesort {
    int[] numbers;
    int[] helper;
    
    int number; 
    
     public int[] sort(int[] values) {
	 numbers = values;
	 number = values.length;
	 helper = new int[number];
	 mergesort(0, number - 1);
	 return numbers;
     }
     
    private void mergesort(int low, int high) {
	// check if low is smaller then high, if not then the array is sorted
	if (low < high) {
	  // Get the index of the element which is in the middle
	    int middle = low + (high - low) / 2;
	    // Sort the left side of the array
	    mergesort(low, middle);
	    // Sort the right side of the array
      mergesort(middle + 1, high);
      // Combine them both
      merge(low, middle, high);
	}
    }

    private void merge(int low, int middle, int high) {
	
	// Copy both parts into the helper array
	for (int i = low; i <= high; i++) {
	    helper[i] = numbers[i];
	}

	int i = low;
	int j = middle + 1;
	int k = low;
	// Copy the smallest values from either the left or the right side back
	// to the original array
	while (i <= middle && j <= high) {
	    if (helper[i] <= helper[j]) {
		numbers[k] = helper[i];
		i++;
	    } else {
		numbers[k] = helper[j];
		j++;
	    }
	    k++;
	}
	// Copy the rest of the left side of the array into the target array
	while (i <= middle) {
	    numbers[k] = helper[i];
	    k++;
	    i++;
	}
	
	
    }
} 
