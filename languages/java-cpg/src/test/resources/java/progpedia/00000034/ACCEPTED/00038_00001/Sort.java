import java.io.*;
import java.util.*;

public class Sort {
    static int array[];
    public static void main(String args[]) {

	Scanner in = new Scanner(System.in);
	// O array input 
	int n = in.nextInt();
        array = new int[n];
	for (int i=0; i<n; i++)
		array[i] = in.nextInt();
	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(array);
	
	for (int i=0; i<n; i++)
	    System.out.print(array[i] + " ");
	System.out.println();

    }
}
