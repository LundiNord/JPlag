import java.util.*;

public class Prog03 {
 public static void main(String args[]) {

	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int v[] = new int[n];
	for(int i =0; i<n;i++) {
		v[i]=in.nextInt();
	}
	Arrays.sort(v);
	for (int i=0; i<n-1; i++)
	    System.out.print(v[i] + " ");
	System.out.print(v[n-1]);

 }
}