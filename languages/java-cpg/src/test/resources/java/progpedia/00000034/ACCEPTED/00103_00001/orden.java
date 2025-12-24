import java.util.*;
import java.io.*;
class orden {
    static public void main(String[] args){
	Scanner input = new Scanner(System.in);
	int N=input.nextInt();
	input.nextLine();
	int[] array = new int[N];
	for (int i=0;i<N;i++){
	    array[i]=input.nextInt();
	}
	Arrays.sort(array);
	if (N>0){
	    System.out.print(array[0]);
	for (int i=1;i<N;i++){
	    System.out.print(" "+array[i]);
	}
	System.out.println();
	}
	
    }
}
