import java.util.*;

class Inversions{
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] a = new int[n];

        for(int i=0;i<n;i++)
            a[i] = input.nextInt();

        int inv = mergesort(a,0,n);

        System.out.println(inv);
	}

	static int mergesort(int a[], int start, int end) {
		if(start==end-1) return 0;
               
		int middle = (start + end)/2;
		
		return mergesort(a, start, middle) + mergesort(a, middle, end) + merge(a, start, middle, end);
	}

	static int merge (int[] a, int start, int middle, int end) {
        int[] aux = new int[end+1];
		int mcount = 0;
		int i = start;
		int p1 = start;
		int p2 = middle;

		while(i<end){
            if(p2>=end || p1<middle && a[p1]<=a[p2])
                aux[i] = a[p1++];
            else{
                mcount = mcount + (middle - p1); 
                aux[i] = a[p2++];
            }

            i++;
        } 

      	return mcount;
    }
}