 import java.util.*;
import java.io.*;

class MergeSort{

    
    public static void main(String[] args){

	Scanner in = new Scanner(System.in);
    
	//input the array size / define array / cicle to input the array
	int size = in.nextInt();
	int inputArray[] = new int[size];
	for(int i = 0; i < size ; i++)
	    inputArray[i] = in.nextInt();
    
	//send the array to the "divide" function
       
	int cont = 0;
	cont = mergeSort(inputArray, 0, size-1);
    
	// Imprimir
	System.out.println(cont);
    } 			 			 		 
    //"divide" function
    static int  mergeSort(int inputArray[], int start, int end){
	int middle;
	int cont = 0;
	
	if(start < end){
	    
	    middle = (start+end) / 2;
	    cont += mergeSort(inputArray, start, middle); //recursive left side
	    cont += mergeSort(inputArray, middle+1, end); //recursive right side
	    cont += merge(inputArray, start, middle, end);
	}
	return cont;
    }
    static int merge(int inputArray[], int start, int middle, int end){
	int i;
	int start2, middle2;
	int tempArray[] = new int[end+1];
	int cont = 0;
	
	start2 = start;
	middle2 = middle+1;
	i = start;
	while(start2 <= middle && middle2 <= end){

	    if(inputArray[start2] <= inputArray[middle2])
		tempArray[i++] = inputArray[start2++];
        
	    
	    else{
		tempArray[i++] = inputArray[middle2++];
		cont += middle - start + 1;
	    }
	    

	    
	}
	while(start2 <= middle) tempArray[i++] = inputArray[start2++];
	while(middle2 <= end)   tempArray[i++] = inputArray[middle2++];

	for(i = start; i<=end; i++) inputArray[i] = tempArray[i];
	return cont;
    }
}
	    
    
	    
