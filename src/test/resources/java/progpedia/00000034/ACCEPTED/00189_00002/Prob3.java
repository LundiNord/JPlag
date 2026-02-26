
import java.io.*;
import java.util.*;


public class Prob3 {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);

	int[] v= new int[stdin.nextInt()];
	for(int i=0;i<v.length;i++){
		v[i]=stdin.nextInt();
	}
	Arrays.sort(v);
	System.out.print(v[0]);
	for(int i=1;i<v.length;i++)
		System.out.print(" " + v[i]);
    System.out.println();
    }
    
}