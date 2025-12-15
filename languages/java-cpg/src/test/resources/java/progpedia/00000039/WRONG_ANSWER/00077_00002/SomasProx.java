import java.util.Arrays;
import java.util.Scanner;

public class SomasProx {
	public static int low;
	public static int hight;
	public static int middle;
	
	public static void main(String args []) {
		Scanner stdin = new Scanner(System.in);
	
		int n = stdin.nextInt();
		
		int s[] = new int[n];
		for (int i=0; i<n; i++)
			s[i] = stdin.nextInt();
		
		int num_p = stdin.nextInt();
		int p[] = new int[num_p];
		for (int i=0; i<num_p; i++)
			p[i] = stdin.nextInt();
		
		int somas[] = new int[n*(n-1)/2];
		int k = 0;
		for (int i=0; i<n; i++)
			for (int j=i+1; j<n; j++) {
				somas[k] = s[i] + s[j];
				k++;
			}
		
		Arrays.sort(somas);
		
		for (int i=0; i<num_p; i++) {
			bsearch(0,(n*(n-1)/2)-1, p[i], somas, n);
			//System.out.println( "!"+ p[i]+ "!");
			if (p[i] == somas[middle])
					System.out.println( somas[middle]);
			else if	((Math.abs(somas[hight]-p[i]) == Math.abs(somas[low]-p[i])) && somas[hight] != somas[low]){
				if (somas[hight] > somas[low])
					System.out.println( somas[low] + " " + somas[hight]);
				else
					System.out.println( somas[hight] + " " + somas[low]);
			}
			else if	((Math.abs(somas[hight]-p[i]) == Math.abs(somas[middle]-p[i])) && somas[hight] != somas[middle]){       
				if (somas[hight] > somas[middle])
					System.out.println( somas[middle] + " " + somas[hight]);
				else
					System.out.println( somas[hight] + " " + somas[middle]);
			}
				
			else if	((Math.abs(somas[low]-p[i]) == Math.abs(somas[middle]-p[i])) && somas[low] != somas[middle]){
				if (somas[middle] > somas[low])
					System.out.println( somas[low] + " " + somas[middle]);
				else
					System.out.println( somas[middle] + " " + somas[low]);
			}
				
			else if (Math.abs(somas[hight]- p[i]) < Math.abs(somas[low]-p[i]))
					System.out.println( somas[hight]);
			else if (Math.abs(somas[hight]-p[i]) > Math.abs(somas[low]-p[i]))
					System.out.println( somas[low]);
		
			else
				System.out.println( somas[low]);
			
			//System.out.println( somas[low] + " " + somas[middle] + " " + somas[hight]);	
		}
		stdin.close();
	}

	public static void bsearch(int l, int h, int p, int somas[], int n) {
		int m = 0;
		while (l < h) {
		
			m = l+ (h - l)/2;
			//System.out.println(somas[l] + " -" + somas[m] + "- " +somas[h] );
			if (p == somas[m] || somas[h] == somas[l] || somas[h] == somas[m] || somas[l] == somas[m] ) {
				low = l ; 
				hight = h ;
				middle = m;
				return ;
			}
		
			else if ( p < somas[m] )
				h = m - 1;
			else
				 l = m ;
			//System.out.println(somas[l] + " -" + somas[m] + "- " +somas[h] );
		}
		
		low = l ; 
		hight = h ;
		middle = m;
	}
}
		


	

