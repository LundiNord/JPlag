import java.io.*;
import java.util.*;

class DAA_3{
    public static void main(String args[]){
	Scanner inp = new Scanner(System.in);

	int n = inp.nextInt();
	int array[] = new int[n];

	for(int i=0;i<n;i++)
	    array[i]=inp.nextInt();

	Arrays.sort(array);
	
	for (int i=0; i<n; i++){
     
	    if(i>0)
		System.out.print(" ");
	    
	    System.out.print(array[i]);
	}
	
	System.out.println();
    }
}
