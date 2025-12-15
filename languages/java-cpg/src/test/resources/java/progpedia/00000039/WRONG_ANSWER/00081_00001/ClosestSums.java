import java.util.*;

class ClosestSums{
	static int[] temp;
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int[] a = new int[n];
		for(int i=0;i<n;i++)
			a[i] = input.nextInt();

		int nQ = input.nextInt();
		int[] q = new int[nQ];
		for(int i=0;i<nQ;i++)
			q[i] = input.nextInt();

		int[] sums = new int[n*(n-1)/2];

		int k=0;

		for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++){
				sums[k] = a[i] + a[j];
				k++;
			}
		
		Arrays.sort(sums);
	
		temp = new int[2];

		for(int i=0;i<nQ;i++){
			int dif = sums[sums.length-1]-q[i];
			if(dif!=0)
				bsearch(sums,0,sums.length-1,q[i],dif);
			else
				System.out.println(sums[sums.length-1]);
			temp[0] = 0;
			temp[1] = 0;
		}
	}

	static void bsearch(int[] sums, int first, int last, int key, int dif){
		int middle;
		
		middle = first + (last - first)/2;

		if(first<=last){
			if(sums[middle]<key){
				if(Math.abs(sums[middle]-key)<=dif){
					dif = Math.abs(sums[middle]-key);
					temp[0] = sums[middle];				
				}
				bsearch(sums,middle+1,last,key,dif);
			}
			else if(sums[middle]>key){
				if(Math.abs(sums[middle]-key)<=dif){
					dif = Math.abs(sums[middle]-key);
					temp[1] = sums[middle];
				}
				bsearch(sums,first,middle-1,key,dif);
			}
			else
				System.out.println(sums[middle]);
			}
		else
			if(temp[0]==0)
				System.out.println(temp[1]);
			else if(temp[1]==0)
				System.out.println(temp[0]);
			else
				System.out.println(temp[0]+" "+temp[1]);
	}
}