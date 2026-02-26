import java.util.*;

public class Inversoes{

	public static long count = 0;
	public static void merge(int[] a1, int[] a2, int[] result){
		int j = 0, i = 0, k = 0;
		while (i < a1.length && j < a2.length){
			if(a1[i] > a2[j]){
				result[k++] = a2[j++];
				int length = a1.length-i;
				count += length;
			}
			else {
				result[k++] = a1[i++];
			}
		}
		for(int temp = i; temp < a1.length; temp++){
			result[k++] = a1[temp];
		}
		for(int temp = j; temp < a2.length; temp++){
			result[k++] = a2[temp];
		}
	}

	public static int[] mergesort(int[] v){
		if(v.length <= 1){
			return v;
		}
		else {
			int middle = v.length/2;
			int left[] = new int[middle];
			int right[] = new int[v.length-middle];
			System.arraycopy(v, 0, left, 0, left.length);
			System.arraycopy(v, left.length,right, 0, right.length);
			mergesort(left);
			mergesort(right);
			merge(left, right, v);
			return v;
		}
	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		int numbers[] = new int[n];
		for(int i = 0; i < n; i++){
			numbers[i] = sc.nextInt();
		}
		mergesort(numbers);
		System.out.println(count);
	}
}