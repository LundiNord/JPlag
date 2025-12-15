import java.util.*;

public class inversao{
	static int conta;
	
	
	public static void quickSort(int[] arr, int low, int high) {
		int count=0;
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				count=j-i;
				i++;
				j--;
				conta+=count;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
	
	
	
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int array[]=new int[n];
		
		for(int i=0;i<n;i++)
			array[i]=input.nextInt();
		
		quickSort(array,0,n-1);
	
		System.out.println(conta);
	}
}


