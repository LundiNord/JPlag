import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {

	int n, i;
	int v[];
	Scanner stdin = new Scanner(System.in);

	n = stdin.nextInt();

	v = new int[n];
	
	for (i = 0; i < n; i++)
	    v[i] = stdin.nextInt();
	
	Arrays.sort(v);
        
	for (i=0; i < n-1; i++)
	    System.out.print(v[i] + " ");
	
	System.out.println(v[i]);
    }
}
