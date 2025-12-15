import java.util.Arrays;
import java.util.Scanner;



public class somasmaisproximas07 {
	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();
		int vec[] = new int[n];
		int somas[] = new int[(n-1)*n/2 + 2];

		for (int i = 0; i < n; i++) {
			vec[i] = stdin.nextInt();
		}
		int f = 0;
		// somas
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i!=j) {
					somas[f] = vec[i] + vec[j];
					f++;
				}
			}
		}
		
		//ordenação
		somas[f++] = -5000001;
		somas[f++] =  5000001;
		Arrays.sort(somas);
			
		int p = stdin.nextInt();
		int val;
		
		//bsearch
		for (int i = 0; i < p; i++) {
			val = stdin.nextInt();
			int r=bsearch(somas,0,f,val);
			if(r!=-1) System.out.println(r);
			
		}

	}
	
	static int bsearch(int v[], int low, int high, int key){
	       int mid;
	       while(low<high){
		   int middle=low+(high-low)/2;
		   if(v[middle]>=key)
		       high = middle;
		   else
		       low=middle+1;
	       }

	       if(Math.abs(v[low]-key)==Math.abs(v[low-1]-key))
		   System.out.println(v[low-1]+" "+v[low]);
	       else if(Math.abs(v[low]-key)<Math.abs(v[low-1]-key))
		   System.out.println(v[low]);
	       else
		   System.out.println(v[low-1]);
	       return -1;
	   }
	
	
}
