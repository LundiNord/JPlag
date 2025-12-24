import java.util.Scanner;

public class Prob3 {

	public static void main(String[] args) {

		int n;
		int [] array;

		Scanner in = new Scanner(System.in);

		n = in.nextInt();
		array = new int [n];

		for(int i= 0; i < n; i++){
			array[i] = in.nextInt();
		}

		mergeSort(array,0,n-1);
		
		for(int i = 0; i < n-1; i++){
			System.out.print(array[i] + " ");
		}
		
		System.out.println(array[n-1]);
		
	}

	private static void mergeSort(int[] array, int start, int end) {


		int middle;

		if(start < end){

			middle = (start+end)/2;
			mergeSort(array,start,middle);
			mergeSort(array,middle+1,end);
			merge(array,start,middle,end);
		}

	}

	private static void merge(int[] array, int start, int middle, int end) {

		int i, p1, p2;
		int aux[] = new int[end+1];

		p1=start;     
		p2=middle+1;  
		i = start;    

		while (p1<=middle && p2<=end) {

			if (array[p1] <= array[p2]) 
				aux[i++] = array[p1++]; 

			else               
				aux[i++] = array[p2++];

		}

		while (p1<=middle) 
			aux[i++] = array[p1++];
		while (p2<=end)    
			aux[i++] = array[p2++];

		for (i=start; i<=end; i++) 
			array[i] = aux[i];

	}

}
