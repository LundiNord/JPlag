import java.util.Scanner;
import java.util.Arrays;

public class seven {
	
	public static void main(String[] args){
	
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int v[] = new int[n];

		for (int i=0; i<n; i++) 
			v[i] = in.nextInt();
		
		int somazes[]=calcsomas(v);
		
		Arrays.sort(somazes);
		
		int p = in.nextInt();
		for (int i=0; i<p; i++){
			System.out.println(search(somazes, 0, somazes.length-1, in.nextInt()));
		}
		
	}

	private static int search(int v[], int low, int high, int key) {
		
		int middle= low+(high-low)/2;
		while(low<=high){
			middle= low+(high-low)/2;
			if(key==v[middle])
				return v[middle];
			if(key<v[middle])
				high=middle-1;
			else low=middle+1;
		}
		if(low>=v.length)
			return v[high];
		if(high<0)
			return v[low];
		if(Math.abs(key-v[low])==Math.abs(key-v[high])){
			System.out.print(v[high] + " ");
			return v[low];
		}
		if(Math.abs(key-v[low])<Math.abs(key-v[high]))
			return v[low];
		return v[high];
		
	}

	private static int[] calcsomas(int[] v) {
		
		int x=1;
		for(int i=v.length-1;i>1;i--)
			x+=i;
		int somas[]= new int[x];
		for(int i=0;i<v.length-1;i++)
			for(int j=i+1;j<v.length;j++){
				somas[x-1]=v[i]+v[j];
				x--;
			}
		return somas;
	}
	 
}
