import java.util.*;

public class probl3 {

	public static void main (String args[]){
		Scanner stdin = new Scanner(System.in);
	
		int n = stdin.nextInt();
		int v[]= new int[n];
		
		for(int i=0; i<n; i++)
			v[i]=stdin.nextInt();
		
		Arrays.sort(v);
		
		for(int i=0; i<n; i++)
			System.out.print(v[i] + " ");
		System.out.println();
		
	}

}

  