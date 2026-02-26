import java.io.*;
import java.util.*;


public class compara {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int vetor[] = new int[n];

	for(int i = 0; i < n; i++) {
	    vetor[i] = in.nextInt();
	}

	Arrays.sort(vetor);
	System.out.print(vetor[0]);
	for(int i = 1; i <n; i++) {
	    System.out.print(" " + vetor[i]);
	}
	System.out.println();
    }
}
