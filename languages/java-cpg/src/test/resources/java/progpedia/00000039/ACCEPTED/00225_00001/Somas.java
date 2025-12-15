import java.io.*;
import java.util.*;

public class Somas {
	public static int somas[];

	static void resultados(int p){
		int d, e;
		int half = binarySearch(somas, p);
		if(half !=0 && somas[half] == somas[half-1])
			System.out.println(somas[half]);
		else{
			d = somas[half] - p;
			if (half != 0){

				e = p - somas[half - 1];
			}
			else
				e = d + 1;
			if(d==e)
				System.out.println(somas[half - 1] + " " + somas[half]);
			else if(e < d)
				System.out.println(somas[half - 1]);
			else
				System.out.println(somas[half]);
		}


	}

	public static int binarySearch(int [] values, int val) {
		return binarySearch(values, val, 0, values.length -1);
	}

		public static int binarySearch(int [] values, int val, int low, int high){
			int half = low + (high - low)/2;
			if (high < low)
				return half;

			else{

				if (val == values[half] )
					return half;
				else if (val < values[half])
					return binarySearch(values, val, low, half-1);
				else{
					if (half == (values.length -1))
						return half;
					else
						return binarySearch(values, val, half +1 , high);
				}
			}

	}



	public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	// Ler N numeros
	int n   = stdin.nextInt();
	int v[] = new int[n];
	int val = ((n-1)*n)/2;
	somas = new int[val];
	int contador = 0;

	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	for (int i=0; i<n; i++)
		for (int j=i+1; j<n; j++){
			somas[contador] = v[i]+v[j];
			contador++;
		}

	Arrays.sort(somas);


	int num = stdin.nextInt();

	for (int i = 0; i < num; i++)
		resultados(stdin.nextInt());
	}



}
