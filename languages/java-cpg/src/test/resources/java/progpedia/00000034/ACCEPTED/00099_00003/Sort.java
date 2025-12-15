
import java.util.*;

public class Sort {
    public static void main(String args[]) {
    	Scanner stdin = new Scanner(System.in);
    	
    	
    	int n = stdin.nextInt();
    	int v[] = new int[n];
    	for (int i=0; i<n; i++)
    		v[i] = stdin.nextInt();
    	
    	Arrays.sort(v);
    	for (int i=0; i<n-1; i++)
    		System.out.print(v[i] + " ");
    	System.out.print(v[n-1]);
    	

    	stdin.close();
    	}
}
