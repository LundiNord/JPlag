import java.util.*;

public class ClosestSums {
	public static int dif;
	public static int s1;
	public static int s2;

	static void bsearch(int[] sums, int first, int last, int key) {
		int mid;

		mid = (last + first)/2;

		if(first<=last){
			if(sums[mid]<key){
				if(Math.abs(sums[mid]-key)<=dif){
					dif = Math.abs(sums[mid]-key);
					s1 = sums[mid];				
				}
				bsearch(sums,mid+1,last,key);
			}
			else if(sums[mid]>key){
				if(Math.abs(sums[mid]-key)<=dif){
					dif = Math.abs(sums[mid]-key);
					s2 = sums[mid];
				}
				bsearch(sums,first,mid-1,key);
			}
			else
				System.out.println(sums[mid]);
			}
		else {
			if(s1==s2)
				System.out.println(s1);
			else if(Math.abs(s1-key)<Math.abs(s2-key))
				System.out.println(s1);
			else if(Math.abs(s1-key)>Math.abs(s2-key))
				System.out.println(s2);
			else
				System.out.println(s1+" "+s2);
		}
	}
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int[] a = new int[n];
		for(int i=0;i<n;i++)
			a[i] = input.nextInt();

		int l = input.nextInt();
		int[] q = new int[l];
		for(int i=0;i<l;i++)
			q[i] = input.nextInt();

		int[] sums = new int[n*(n-1)/2];

		int k = 0;

		for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++){
				sums[k] = a[i] + a[j];
				k++;
			}
		
		Arrays.sort(sums);

		for(int i=0;i<l;i++){
			dif = sums[sums.length-1];
			s1 = sums[sums.length-1];
			s2 = sums[sums.length-1];
			bsearch(sums,0,sums.length-1,q[i]);
		}
	}
}