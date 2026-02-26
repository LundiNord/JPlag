import java.io.*;
import java.util.*;

class Somas007{

	public static int binarySearch(int somas[], int low, int high, int k){

		int half = low + (high - low)/2;
		if(high>low){
			if(somas[half]==k){
				System.out.println(somas[half]);
				return somas[half];
			}
			else if(k>somas[half])
				return binarySearch(somas,half+1,high,k);
			else
				return binarySearch(somas,low,half-1,k);
		}
		else{
			if(half>0){
				if((somas[half]-k)>(k-somas[half-1]))
					System.out.println(somas[half-1]);
				else if((somas[half]-k)<(k-somas[half-1]))
					System.out.println(somas[half]);
				else if((somas[half]-k)==(k-somas[half-1]))
					System.out.println(somas[half-1]+ " "+somas[half]);
			}
			else if(half==0)
				System.out.println(somas[half]);
			return -1;
		}
	}

	public static void main(String args[]){

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = 0;
		int s = (n*(n-1)/2);
		int[] v = new int[n];
		int[] somas = new int[s];
		for(int i=0;i<n;i++){
			v[i]=in.nextInt();
		}
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				somas[k] = v[i] + v[j];
				k++;
			}
		}

		Arrays.sort(somas);
		int P = in.nextInt();
		int [] p = new int[P];
		for(int i=0;i<P;i++){
			p[i]=in.nextInt();
		}
		for(int i=0;i<P;i++){
			int valor=binarySearch(somas,0,somas.length-1,p[i]);
		}
	}
}