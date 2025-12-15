import java.util.Arrays;
import java.util.Scanner;

class SomasProx {
	public static void main( String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int S[] = new int[n];
	
		for (int i=0;i<n; i++)
			S[i] = in.nextInt();
		int num_p = in.nextInt();
		int p[] = new int[num_p];
		for (int i=0;i<num_p; i++)
			p[i] = in.nextInt();
		/*for (int i=0;i<n+2; i++)
			System.out.println(S[i]);*/
		int somas[] = new int[(n*(n-1)/2) + 2 ];
		int k=1;
		somas[0] = -2000000;
		somas[(n*(n-1)/2) + 1] = 2000000;
		for (int i=0;i<n; i++)
			for (int j=i+1;j<n; j++){
				somas[k] = S[i] +S[j];
				k++;
			}
		/*for (int i=0;i< (n*(n-1)/2) +2 ; i++)
		    System.out.print(somas[i]+ " ");
		    System.out.println();*/
		Arrays.sort(somas);
		/*for (int i=0;i< (n*(n-1)/2) +2 ; i++)
			System.out.print(somas[i]+ " ");
			System.out.println();*/
		for (int i=0;i<num_p; i++) {
		    int resul = bsearch (0,(n*(n-1)/2) +1 , somas, p[i]);
			if (somas[resul] == p[i])
				System.out.println(somas[resul]);
			else if(Math.abs(somas[resul]- p[i]) > Math.abs(somas[resul-1]- p[i]) )
				System.out.println(somas[resul-1]);
			else if(Math.abs(somas[resul]- p[i]) < Math.abs(somas[resul-1]- p[i]) )
				System.out.println(somas[resul]);
			else
				if (somas[resul] == somas[resul-1])
					System.out.println(somas[resul]);
				else
					System.out.println(somas[resul-1] + " " + somas[resul]);
		}
		in.close();
	}
	
	public static int bsearch(int low,int high, int v[], int x) {
		while (low< high) {
			int middle = low + (high - low)/2;
			//System.out.println(  v[low]  +" " +v[middle] + " "+v [high]);
			if (v[middle] == x)
				return middle;
			else if (v[middle] > x)
				high = middle;
			else
				low = middle+1;
		}
		return low;
		
		
	}

}




