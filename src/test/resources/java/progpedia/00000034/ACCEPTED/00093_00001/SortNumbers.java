import java.io.*;
import java.util.*;

class SortNumbers{

    public static void main(String args[]){

	Scanner hello = new Scanner(System.in);
	int n;
	int valor;
	n = hello.nextInt();
	int[] array = new int[n];
	for(int i=0; i<n; i++){
	    valor = hello.nextInt();
	    array[i]=valor;
	}
	Arrays.sort(array);
	
	for(int j=0;j<array.length-1;j++){
	    System.out.print(array[j]);
	    System.out.print(" ");
	}
	System.out.print(array[array.length-1]);
	
    }
}
