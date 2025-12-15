import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {

	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	
	// Um array exemplo 
	int v[] = new int[n];

	for(int i=0; i<n;i++)
	    v[i]=in.nextInt();
  
	/*System.out.print(" Antes de ordenar: "); 
	for (int i=0; i<10; i++)
	    System.out.print(v[i] + " ");
	System.out.println();
	*/
	// Chamada ao sort padrao da linguagem Java

	Arrays.sort(v);
        
        for (int i=0; i<n; i++)
	    System.out.print(v[i] + " ");
	System.out.println();

    }
}
