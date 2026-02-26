import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int size = stdin.nextInt();
	int v[] = new int[size];
	for (int i=0; i<size; i++)
	    v[i] = stdin.nextInt();
	Arrays.sort(v);
	for (int i=0; i<size-1; i++)
	    System.out.print(v[i] + " ");
	System.out.println(v[size-1]);
    }
}
