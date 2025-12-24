import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class sumpair{

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int na = input.nextInt();
		int a[] = new int [na];
		for(int i=0; i<na; i++)
		{
			a[i] = input.nextInt();
		}
		
		int nb = input.nextInt();
		int b[] = new int [nb];
		for(int i=0; i<nb; i++)
		{
			b[i] = input.nextInt();
		}
		
		
		Arrays.sort(a);
		int nsum = ((na-1)*na)/2;
		int sum[] = new int [nsum];
		int n=0;
		
		for(int i=0; i<na; i++)
		{
			for(int j=i+1; j<na; j++)
			{
				sum[n] = a[i]+a[j];
				n++;			
			}	
		}
		
		Arrays.sort(sum);
		
		for (int i = 0; i < nb; i++)
		{
			bsearch(sum, b[i]);
		}
		
		
				
	}
	static void bsearch(int v[], int key) {
		int lo = 0;
		int hi = v.length - 1;
		
		while (lo < hi) 
		{
			int mid = lo + (hi - lo) / 2;

			if (key <= v[mid])
				hi = mid;
			else
				lo = mid + 1;
		}
		
			if (lo > 0 && key != v[lo]) 
			{
				int dist_para_seguinte = Math.abs(key - v[lo]);
				int dist_para_anterior = Math.abs(key - v[lo - 1]);
				
				if (dist_para_anterior < dist_para_seguinte)
					System.out.println(v[lo - 1]);
				else if (dist_para_anterior == dist_para_seguinte)
					System.out.println(v[lo - 1] + " " + v[lo]);
				else 
					System.out.println(v[lo]);

			} 
			else 
			{
				if (key <= v[lo])
					System.out.println(v[lo]);
				else
					return;
			}
		}
}