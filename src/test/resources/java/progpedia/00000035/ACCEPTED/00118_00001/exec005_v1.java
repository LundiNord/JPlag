import java.util.*;

public class exec005_v1 {
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int[] v = new int[n];
		for (int i=0; i<n; i++) {
			v[i] = stdin.nextInt();
		}

		int contador=0; 
		for(int i=0; i<n-1; i++) 
			for(int j=i+1; j<n; j++)
				if (v[i] > v[j])
					contador+=1;
				System.out.println(contador);
			}
		}