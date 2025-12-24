import java.io.*;
import java.util.*;

public class Prob03 {
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int N = stdin.nextInt();
	int v [] = new int [N];

	for (int i=0; i<N; i++)
	    v[i] = stdin.nextInt();

	Arrays.sort(v);
	
	for (int i=0; i<N; i++)
	    System.out.print(v[i] + " ");
	System.out.println();
    }
}
