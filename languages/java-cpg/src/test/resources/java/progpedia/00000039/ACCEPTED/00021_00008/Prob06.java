import java.util.Arrays;
import java.util.Scanner;

public class Prob06 {

	static int[] bsearch(int s[], int low, int high, int key) {
		int middle, dif = Integer.MAX_VALUE, tdif;
		int a[] = new int[2];
		a[0] = a[1] = -1;

		while (low <= high) {
			middle = low + (high - low) / 2;
			tdif = Math.abs(key - s[middle]);
			
			if (tdif < dif){
				dif = tdif;
				a[0] = s[middle];
				a[1] = -1;
			}
			if(tdif == dif && a[0] != s[middle])
				a[1] = s[middle];

			if (key == s[middle]){
				 a[0] = s[middle];
				 a[1] = -1;
				 return a;
			}

			else if (key < s[middle])
				high = middle - 1;
			else
				low = middle + 1;
		}
		
		return a;
	}

	// calcular qual o tamanho do array soma
	public static int size(int s) {
		int v = 0;
		for (int i = 1; i < s; i++) {
			v += i;
		}
		return v;
	}

	// array com todas as diferentes somas possiveis
	static int[] soma(int[] v) {
		int s[] = new int[size(v.length)];
		int k = 0;
		for (int i = 0; i <= v.length - 2; i++)
			for (int j = i + 1; j <= v.length - 1; j++) {
				s[k++] = v[i] + v[j];
			}
		return s;
	}

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		int n = stdin.nextInt();
		int v[] = new int[n];

		for (int i = 0; i < n; i++)
			v[i] = stdin.nextInt();

		int[] s = soma(v);
		int p = stdin.nextInt();
		Arrays.sort(s);

		for (int i = 0; i < p; i++) {
			int pi = stdin.nextInt();
			int[] bs = bsearch(s, 0, s.length - 1, pi);
			
			if(bs[1]==-1)
				System.out.print(bs[0]);
			else{
				if(bs[0] < bs[1])
					System.out.print(bs[0]+" "+bs[1]);
				else
					System.out.print(bs[1]+" "+bs[0]);
			}
	
			System.out.println();
		}
	}

}



/*
 * 		for (int j = 0; j < s.length; j++) {
				if (Math.abs(pi - s[j]) == dif && (s[j] != first)) {
					if (sec) {
						System.out.print(" " + s[j]);
						break;
					} else {
						System.out.print(s[j]);
						first = s[j];
						sec = true;
					}
				}
			}
			sec = false;
			*/
